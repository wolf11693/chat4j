package org.ra.repository;

import org.ra.model.ChatRoomUserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "dataChatRoomUserMongoRepository")
public interface ChatRoomUserRepository extends MongoRepository<ChatRoomUserModel, String> { 

}
