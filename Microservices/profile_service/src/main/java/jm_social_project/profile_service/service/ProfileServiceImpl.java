package jm_social_project.profile_service.service;

import jm_social_project.profile_service.annotation.CheckExists;
import jm_social_project.profile_service.dto.ProfileDTO;
import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.model.BlockedProfile;
import jm_social_project.profile_service.model.VisitedProfiles;
import jm_social_project.profile_service.repository.BlockedProfilesRepository;
import jm_social_project.profile_service.repository.ProfileRepository;
import jm_social_project.profile_service.repository.VisitedProfileRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    @Value("${nearbyProfileSearchRadiusDefault}")
    private int nearbyProfileSearchRadiusDefault; // радиус поиска ближайших профилей, м
    @Value("${nearbyRandomProfileSearchRadius}")
    private int nearbyRandomProfileSearchRadius; // радиус поиска cлучайного профиля, если диапазон юзером НЕ выбран, то радиус по дефолту 3000, м
    @Value("${radiusOfEarth}")
    private final double EARTH_RADIUS; // радиус Земли, м
    @NonNull
    private ProfileRepository profileRepository;

    @NonNull
    private VisitedProfileRepository visitedProfileRepository;

    @NonNull
    private BlockedProfilesRepository blockedProfileRepository;

    private Profile profile;

    @Override
    public Profile saveProfile(Profile profile, String accountId) {
        profile.setAccountId(accountId);
        return profileRepository.insert(profile);
    }

    @Override
    public ProfileDTO getProfileByAccountId(String accountId, @CheckExists String user_id) {
        Profile profile = profileRepository.getProfileByAccountId(accountId);
        Set<VisitedProfiles> oldVisitors = profile.getVisitedProfilesSet();
        for (VisitedProfiles visitor : oldVisitors) {
            if (visitor.getProfileId().equals(user_id)) {
                visitor.setVisitDate(new Date());
                profileRepository.save(profile);
                return profile;
            }
        }
        VisitedProfiles newVisitor = new VisitedProfiles(user_id, new Date());
        visitedProfileRepository.insert(newVisitor);
        profile.getVisitedProfilesSet().add(newVisitor);
        limitVisitedProfiles(profile);
        profileRepository.save(profile);
        return profile;
    }

    @Override
    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public boolean deleteProfile(String id) {
        profileRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getProfileById(String id) {
        return profileRepository.findById(id).orElse(null);
    }

    @Override
    public Map getNearbyProfiles(Profile profile, int radius) {

        List<Profile> allProfiles = new ArrayList(getAllProfiles());

        //получил Map с профилями входящими в radius
        Map<String, Profile> profilesInCircle = new HashMap<>();
        for (int i = 0; i < allProfiles.size(); i++) {

            int distanceBetween = distanceInKilometers(allProfiles.get(i).getLatitude(), allProfiles.get(i).getLongitude(), profile.getLatitude(), profile.getLongitude());

            if (distanceBetween <= radius) {
                profilesInCircle.put("key" + i, allProfiles.get(i));
            }
        }
        return profilesInCircle;
    }

    // получаем случайную анкету в радиусе radius
    @Override
    public Profile getRandomNearbyProfile(Profile profile, int radius) {
        radius = radius <= 0? nearbyProfileSearchRadiusDefault : radius;

        Map<String, Profile> profilesInCircle = getNearbyProfiles(profile, radius);

        if (profilesInCircle.isEmpty()) {
            return null;
        }

        int randomNumber = (int) (Math.random() * profilesInCircle.size());
        String randomKey = (String) profilesInCircle.keySet().toArray()[randomNumber];
        return profilesInCircle.get(randomKey);

    }

    // получил расстояние между юзером и юзерами в radius
    @Override
    public List distanceBetweenProfiles(Profile profile, int distance) {
        List<Profile> allProfiles = new ArrayList(getAllProfiles());
        List profilesDistance = new ArrayList();

        for (int i = 0; i < allProfiles.size(); i++) {

            int distanceBetween = distanceInKilometers(allProfiles.get(i).getLatitude(), allProfiles.get(i).getLongitude(), profile.getLatitude(), profile.getLongitude());

            if (distanceBetween <= distance) {
                profilesDistance.add("Растояние между Вами и " + allProfiles.get(i).getFirstName() + " " + allProfiles.get(i).getLastName() + " составляет " + distanceBetween + " метра(ов)");
            }
        }
        return profilesDistance;
    }

    //расчет расстояния между двумя точками по широте и долготе
    public int distanceInKilometers(double friendLat, double friendLon,
                                    double userLat, double userLon) {

        double latDistance = Math.toRadians(friendLat - userLat);
        double lonDistance = Math.toRadians(friendLon - userLon);

        double cosOfSphericalTrianle = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(friendLat)) * Math.cos(Math.toRadians(userLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double centralAngle = 2 * Math.atan2(Math.sqrt(cosOfSphericalTrianle), Math.sqrt(1 - cosOfSphericalTrianle));

        return (int) (Math.round(EARTH_RADIUS * centralAngle));
    }
    @Override
    public Profile addToLikeOrDodgeList(String user_id, String id, Boolean isLiked) {
        Profile profile = getProfileById(user_id);
        Date date = new Date();
        if (isLiked) {
            profile.getLikeList().put(id, date);
        } else {
            if (profile.getDodgeList().size() >= 70) {
                String first = profile.getDodgeList().keySet().iterator().next();
                profile.getDodgeList().remove(first);
            }
            profile.getDodgeList().put(id, date);
        }
        profileRepository.save(profile);
        return profile;
    }

    @Override
    public Profile updateProfileDescription(String accountId, String description) {
        Profile foundAccount = profileRepository.getProfileByAccountId(accountId);
        foundAccount.setDescription(description);
        return profileRepository.save(foundAccount);
    }

    private void limitVisitedProfiles(Profile profile) {
        if (profile.getVisitedProfilesSet().size() > 10) {
            profile.getVisitedProfilesSet().stream().limit(1).forEach(x -> visitedProfileRepository.deleteById(x.getId()));
            profile.setVisitedProfilesSet(profile.getVisitedProfilesSet().stream().skip(1).collect(Collectors.toSet()));
        }
    }

    private ProfileDTO convertProfile(Profile profile) {
        if (profile.isPrivateProfile()) {
            return ProfileDTO.builder()
                    .id(profile.getId())
                    .accountId(profile.getAccountId())
                    .firstName(profile.getFirstName())
                    .lastName(profile.getLastName())
                    .build();
        }
        return new ProfileDTO(profile.getId(), profile.getAccountId(), profile.getFirstName(), profile.getLastName(),
                profile.getStatus(), profile.getAvatarUrl(), profile.getBirthDate(), profile.getDescription(),
                profile.getLatitude(), profile.getLongitude());
    }


    // Добавляет профиль в черный лист пользователя, возвращает профиль заблокированного пользователя
    @Override
    public Profile addProfileToBlockedProfiles(String blockedProfileId, String blockingProfileId) {

        if (!isProfileBlockedByUser(blockedProfileId, blockingProfileId)){
            Profile blockingProfile = profileRepository.getProfileByAccountId(blockingProfileId);
            BlockedProfile blockedProfile;

            if (!isProfileBlocked(blockedProfileId)) {
                blockedProfile = new BlockedProfile(blockedProfileId, blockingProfileId);

            } else {
                blockedProfile = blockedProfileRepository.getBlockedProfileByProfileId(blockedProfileId);
            }

            blockingProfile.getBlockedProfiles().add(blockedProfile);
            blockedProfile.getBlockingProfiles().add(blockingProfile);

            try {
                blockedProfileRepository.save(blockedProfile);
                return profileRepository.getProfileByAccountId(blockedProfileId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //Удаляет заблокированный профиль из черного листа пользователя, возвращает профиль разблокированного пользователя
    @Override
    public Profile deleteProfileFromBlockedProfiles(String blockedProfileId, String blockingProfileId) {

        if (isProfileBlockedByUser(blockedProfileId, blockingProfileId)){
            Profile blockingProfile = profileRepository.getProfileByAccountId(blockingProfileId);
            BlockedProfile blockedProfile = blockedProfileRepository.getBlockedProfileByProfileId(blockedProfileId);

            blockingProfile.getBlockedProfiles().remove(blockedProfile);
            blockedProfile.getBlockingProfiles().remove(blockingProfile);

            if (blockedProfile.getBlockingProfiles().isEmpty()) {
                try {
                    blockedProfileRepository.delete(blockedProfile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return profileRepository.getProfileByAccountId(blockedProfileId);
        }
        return null;
    }

    //Проверяет, заблокирован ли профиль конкретным юзером
    @Override
    public boolean isProfileBlockedByUser(String blockedProfileId, String blockingProfileId) {
        Profile blockingProfile =  profileRepository.getProfileByAccountId(blockedProfileId);
        BlockedProfile blockedProfile = blockedProfileRepository.getBlockedProfileByProfileId(blockedProfileId);

        return blockedProfile.getBlockingProfiles().contains(blockingProfile);
    }

    //Проверяет, заблокирован ли профиль в принципе
    @Override
    public boolean isProfileBlocked(String blockedProfileId) {
        return blockedProfileRepository.getBlockedProfileByProfileId(blockedProfileId) != null;
    }


}
