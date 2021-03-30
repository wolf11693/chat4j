package org.ra.repository;

import java.util.List;

import org.ra.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "dataUserMongoRepository")
public interface UserRepository extends MongoRepository<UserModel, String> {

	public UserModel findByUsername(String username);

}
