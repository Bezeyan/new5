package jm_social_project.media_storage.dto;

import jm_social_project.media_storage.model.PhotoContent;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

public class  PhotoDTO {


    private String photoId;

    @NotNull
    @Positive(message = "Количество лайков не может быть отрицательным!")
    private Integer likes;

    @NotNull
    private Set<String> likedUserIds;

    public PhotoDTO(PhotoContent photo, Integer likes) {
        this.photoId = photo.getPhotoId();
        this.likes = likes;
        this.likedUserIds = photo.getLikedUserIds();
    }

    public String getPhotoId() {
        return photoId;
    }

    public Integer getLikes() {
        return likes;
    }

    public Set<String> getLikedUserIds() {
        return likedUserIds;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setLikedUserIds(Set<String> likedUserIds) {
        this.likedUserIds = likedUserIds;
    }
}
