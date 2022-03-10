package jm_social_project.profile_service.service;

import jm_social_project.profile_service.model.ProfilePhoto;

import java.util.List;

public interface ProfilePhotoServiceService {
    void saveProfilePhoto(ProfilePhoto profilePhoto);

    void updateProfilePhoto(ProfilePhoto profilePhoto);

    void deleteProfilePhoto(ProfilePhoto profilePhoto);

    List<ProfilePhoto> getAllProfilePhoto();

    ProfilePhoto getProfilePhotoById(String id);
}
