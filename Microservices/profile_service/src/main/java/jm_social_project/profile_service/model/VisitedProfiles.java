package jm_social_project.profile_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Data
@Document(collection = "visitedProfiles")
public class VisitedProfiles {

    @Id
    private String id;

    private String profileId;

    private Date visitDate;

    public VisitedProfiles(String id, String profileId, Date visitDate) {
        this.id = id;
        this.profileId = profileId;
        this.visitDate = visitDate;
    }

    public VisitedProfiles(String profileId, Date visitDate) {
        this.profileId = profileId;
        this.visitDate = visitDate;
    }
}
