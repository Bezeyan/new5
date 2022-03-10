package jm_social_project.profile_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@Data
@Document(collection = "profilePhoto")
public class ProfilePhoto {

    @Id
    private String id;

    private String pathToPhoto;

    private Profile profileId;

    public ProfilePhoto(String pathToPhoto, Profile profileId) {
        this.pathToPhoto = pathToPhoto;
        this.profileId = profileId;
    }
}
