package jm_social_project.media_storage.service;

import jm_social_project.media_storage.dto.PhotoDTO;
import jm_social_project.media_storage.exception.PhotoContentNotFoundException;
import jm_social_project.media_storage.exception.StorageException;
import jm_social_project.media_storage.model.PhotoContent;
import jm_social_project.media_storage.repository.PhotoContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private PhotoContentRepository photoContentRepository;

    @Autowired
    private S3Client s3;

    public void store(MultipartFile file, String userId) {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file.");
        }

        storeFileToFilesystem(file, userId, s3, createBucket());
    }

    private void storeFileToFilesystem(MultipartFile file, String userId, S3Client s3, String bucketName) {
        String key = UUID.randomUUID().toString();
        try {
            s3.putObject(PutObjectRequest
                    .builder()
                    .bucket(bucketName)
                    .key(key)
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build(), RequestBody.fromBytes(file.getBytes()));
            String url = s3.utilities().getUrl(builder -> builder.bucket(bucketName).key(key)).toExternalForm();
            photoContentRepository.insert(new PhotoContent(userId, url, key));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createBucket() {
        final String BUCKET_NAME = "storage-bucket-for-jm-social-project";
        try {
            s3.createBucket(builder -> builder.bucket(BUCKET_NAME));
        } catch (S3Exception ignored) {
        }
        return BUCKET_NAME;
    }

    public List<PhotoContent> getAllPhotoContent() {
        return (List<PhotoContent>) photoContentRepository.findAll();
    }

    public List<PhotoContent> getPhotoContentByUserId(String userId) {
        return photoContentRepository.findPhotoContentByUserId(userId);
    }

    public PhotoContent likePhoto(String photoId, String userId) {
        PhotoContent photoContent = photoContentRepository.findById(photoId)
                .orElseThrow(() -> new PhotoContentNotFoundException("Photo content cannot be found"));

        Set<String> likedUsers = photoContent.getLikedUserIds();

        if (likedUsers.contains(userId)) {
            likedUsers.remove(userId);
        } else {
            likedUsers.add(userId);
        }

        photoContent.setLikedUserIds(likedUsers);

        return photoContentRepository.save(photoContent);
    }

    public PhotoDTO photoContentToPhotoDTO(String photoId) {
        PhotoContent photo = photoContentRepository.findById(photoId)
                .orElseThrow(() -> new PhotoContentNotFoundException("Photo content cannot be found"));

        return new PhotoDTO(photo, photo.getLikedUserIds().size());
    }

}
