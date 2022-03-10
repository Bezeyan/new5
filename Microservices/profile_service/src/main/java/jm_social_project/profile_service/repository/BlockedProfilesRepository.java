package jm_social_project.profile_service.repository;

import jm_social_project.profile_service.model.BlockedProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@EnableMongoRepositories
@Repository
public interface BlockedProfilesRepository extends MongoRepository<BlockedProfile, String> {

    BlockedProfile getBlockedProfileByProfileId(String profileId);
    BlockedProfile getBlockedProfileByBlockingProfilesIdAndBlockedProfileId(String blockedProfileId, String blockingProfileId);
}
