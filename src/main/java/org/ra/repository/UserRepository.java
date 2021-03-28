package org.ra.repository;

import org.ra.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

	public UserModel findByUsername(String username);
}
