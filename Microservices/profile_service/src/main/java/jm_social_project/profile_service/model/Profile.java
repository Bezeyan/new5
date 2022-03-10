package jm_social_project.profile_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


@NoArgsConstructor
@Data
@Document(collection = "profile")
public class Profile {

    public Profile(String id, String accountId, boolean privateProfile, String firstName, String lastName, String status, String avatarUrl, Date birthDate, String description, Double latitude, Double longitude, Set<VisitedProfiles> visitedProfilesSet) {
        this.id = id;
        this.accountId = accountId;
        this.privateProfile = privateProfile;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.avatarUrl = avatarUrl;
        this.birthDate = birthDate;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dodgeList = new LinkedHashMap<>();
        this.likeList = new LinkedHashMap<>();
        this.visitedProfilesSet = visitedProfilesSet;
    }

    @Id
    private String id;

    @Indexed(unique = true)
    private String accountId;

    private boolean privateProfile;

    private String firstName;

    private String lastName;

    private String status;

    private String avatarUrl;

    private Date birthDate;

    private String description;

    private Double latitude;

    private Double longitude;

    private Map<String, Date> dodgeList;

    private Map<String, Date> likeList;

    @DBRef
    private Set<VisitedProfiles> visitedProfilesSet;

    //Список заблокированных пользователей
    @DBRef(lazy = true)
    private Set<BlockedProfile> blockedProfiles;
}

