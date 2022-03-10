package jm_social_project.media_storage.repository;

import jm_social_project.media_storage.model.PhotoContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableMongoRepositories
@Repository
public interface PhotoContentRepository extends MongoRepository<PhotoContent, String> {

    List<PhotoContent> findPhotoContentByUserId(String userId);
}

