package jm_social_project.media_storage.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Document(collection = "photoContent")

public class PhotoContent {
    @Id
    private String photoId;
    @Indexed
    private String userId;
    private String url;
    private String photoName;
    private String photoPath;
    private Set<String> likedUserIds = new HashSet<>();


    public PhotoContent(String userId, String url, String photoName) {
        this.userId = userId;
        this.url = url;
        this.photoName = photoName;
    }

}
