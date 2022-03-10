package jm_social_project.profile_service.service;

import jm_social_project.profile_service.dto.ProfileDTO;
import jm_social_project.profile_service.model.Profile;

import java.util.List;
import java.util.Map;

public interface ProfileService {

    Profile saveProfile(Profile profile, String accountId);

    Profile updateProfile(Profile profile);

    boolean deleteProfile(String id);

    List<Profile> getAllProfiles();

    Profile getProfileById(String id);

    Profile getProfileByAccountId(String accountId, String user_id);

    Map getNearbyProfiles(Profile profile, int radius);

    Profile getRandomNearbyProfile(Profile profile, int radius);

    List distanceBetweenProfiles(Profile profile, int distance);

    Profile addToLikeOrDodgeList(String user_id, String id, Boolean isLiked);

    Profile updateProfileDescription(String accountId, String description);

    Profile addProfileToBlockedProfiles(String blockedProfileId, String blockingProfileId);

    Profile deleteProfileFromBlockedProfiles(String blockedProfileId, String blockingProfileId);

    boolean isProfileBlockedByUser(String blockedProfileId, String blockingProfileId);

    boolean isProfileBlocked(String blockedProfileId);
}
