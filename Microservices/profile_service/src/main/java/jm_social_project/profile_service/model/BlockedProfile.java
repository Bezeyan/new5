package jm_social_project.profile_service.model;

import jm_social_project.profile_service.repository.ProfileRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Data
@Document(collection = "blockedProfile")
public class BlockedProfile {

    @Id
    private String id;

    private String blockedProfileId;

    @DBRef
    private Set<Profile> blockingProfiles;

    private Date date;

    @Autowired
    ProfileRepository profileRepository;

    /**
     *
     * @param blockedProfileId - id блокируемого профиля
     * @param blockingProfileId - id блокирующего профиля
     */

    public BlockedProfile(String blockedProfileId, String blockingProfileId) {
        this.blockedProfileId = blockedProfileId;
        blockingProfiles.add(profileRepository.getProfileByAccountId(blockingProfileId));
        this.date = new Date();
    }

}
