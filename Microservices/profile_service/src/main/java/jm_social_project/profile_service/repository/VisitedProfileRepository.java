package jm_social_project.profile_service.repository;

import jm_social_project.profile_service.model.VisitedProfiles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@EnableMongoRepositories
@Repository
public interface VisitedProfileRepository extends MongoRepository<VisitedProfiles, String> {
}
