package cse416.server.dao;

import cse416.server.model.StateInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

public interface StateInformationRepository extends MongoRepository<StateInformation, String> {
    List<StateInformation> findAll();


}
