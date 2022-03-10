package jm_social_project.profile_service.repository;

import jm_social_project.profile_service.dto.ProfileDTO;
import jm_social_project.profile_service.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableMongoRepositories
@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

    Profile getProfileByAccountId(String accountId);

    @Query(value = "select new model.dto.ProfileDto (p.id, p.accountId, p.firstName" +
            ", p.lastName, p.status, p.avatarUrl, p.birthDate" +
            ", p.description, p.latitude, p.longitude) from Profile p")
    List<ProfileDTO> getProfileDtoPub();

    @Query(value = "select new model.dto.ProfileDto (p.id, p.accountId" +
            ", p.firstName, p.lastName) from Profile p")
    List<ProfileDTO> getProfileDtoPriv();
}
