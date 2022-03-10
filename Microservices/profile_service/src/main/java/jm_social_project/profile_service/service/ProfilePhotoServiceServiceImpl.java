package jm_social_project.profile_service.service;

import jm_social_project.profile_service.model.ProfilePhoto;
import jm_social_project.profile_service.repository.ProfilePhotoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfilePhotoServiceServiceImpl implements ProfilePhotoServiceService {

    @NonNull
    private ProfilePhotoRepository photoRepository;

    @Override
    public void saveProfilePhoto(ProfilePhoto profilePhoto) {
        photoRepository.insert(profilePhoto);
    }

    @Override
    public void updateProfilePhoto(ProfilePhoto profilePhoto) {
        photoRepository.save(profilePhoto);
    }

    @Override
    public void deleteProfilePhoto(ProfilePhoto profilePhoto) {
        photoRepository.delete(profilePhoto);
    }

    @Override
    public List<ProfilePhoto> getAllProfilePhoto() {
        return photoRepository.findAll();
    }

    @Override
    public ProfilePhoto getProfilePhotoById(String id) {
        return photoRepository.findById(id).orElse(null);
    }
}
