package jm_social_project.media_storage.service;

import jm_social_project.media_storage.dto.PhotoDTO;
import jm_social_project.media_storage.model.PhotoContent;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface StorageService {

    void store(MultipartFile file, String userId);
    List<PhotoContent> getAllPhotoContent();
    List<PhotoContent> getPhotoContentByUserId(String userId);
    PhotoContent likePhoto(String photoContentId, String userId);
    PhotoDTO photoContentToPhotoDTO(String photoId);

}
