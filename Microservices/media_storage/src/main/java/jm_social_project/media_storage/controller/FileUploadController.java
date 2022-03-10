package jm_social_project.media_storage.controller;

import jm_social_project.media_storage.dto.PhotoDTO;
import jm_social_project.media_storage.model.PhotoContent;
import jm_social_project.media_storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/upload_video/")
    public String handleVideoUpload(@RequestParam("file") MultipartFile file,
                                    //@RequestHeader HttpHeaders httpHeaders,
                                    @RequestHeader("user_id") String userId) {
//        String accountId = httpHeaders.getFirst("user_id");
        storageService.store(file, userId);
        return "You successfully uploaded video " + file.getOriginalFilename() + "!";
    }

    @PostMapping("/upload_photo/")
    public String handlePhotoUpload(@RequestParam("file") MultipartFile file,
                                    //@RequestHeader HttpHeaders httpHeaders,
                                    @RequestHeader("user_id") String userId) {
//        String accountId = httpHeaders.getFirst("user_id");
        storageService.store(file, userId);
        return "You successfully uploaded photo " + file.getOriginalFilename() + "!";
    }

    @GetMapping()
    public ResponseEntity<List<PhotoContent>> getAllPhotoContent() {
        List<PhotoContent> photoContents = storageService.getAllPhotoContent();
        return new ResponseEntity<>(photoContents, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PhotoContent>> getPhotoContentByUserId(@PathVariable String userId) {
        List<PhotoContent> photoContents = storageService.getPhotoContentByUserId(userId);
        return new ResponseEntity<>(photoContents, HttpStatus.OK);
    }

    @PostMapping("/{userId}/like/{photoId}")
    public ResponseEntity<PhotoContent> like(@PathVariable("photoId") String photoId,
                                             @PathVariable("userId") String userId) {
        PhotoContent photoContent = storageService.likePhoto(photoId, userId);
        return new ResponseEntity<>(photoContent, HttpStatus.OK);
    }

    @GetMapping("/photo/{photoId}")
    public ResponseEntity<PhotoDTO> getPhotoDTOByPhotoId(@PathVariable("photoId") String photoId) {
        PhotoDTO photoDTO = storageService.photoContentToPhotoDTO(photoId);
        return new ResponseEntity<>(photoDTO, HttpStatus.OK);
    }
}
