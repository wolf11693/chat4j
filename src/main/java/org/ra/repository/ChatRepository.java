package org.ra.repository;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.ra.model.ChatModel;
import org.ra.model.MongoCollectionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

@Repository
public class ChatRepository {

	private static final Logger LOG = LoggerFactory.getLogger(ChatRepository.class);
	
	private MongoCollection<Document> collection;
	
	@Autowired
	public ChatRepository(Map<String, MongoCollection<Document>> collections) {
		collection = collections.get(MongoCollectionEnum.CHAT_USER_COLLECTION.getValue());
	}
	
	public List<ChatModel> findAll() {
		LOG.debug("findAll - START");

		// TODO Auto-generated method stub

		LOG.debug("findAll - END");
		return null;
	}

	public ChatModel findById(String id) {
		LOG.debug("findById - START - id={}", id);

		// TODO Auto-generated method stub

		LOG.debug("findById - END");
		return null;
	}

	public ChatModel insert(String idChatUser, ChatModel chat) throws RuntimeException {
		LOG.debug("insert - START - obj={}", chat);
		
		Bson filter = eq("_id",new ObjectId(idChatUser));
        Bson updateOperation = push("userChat", chat);
        UpdateResult updateResult = this.collection.updateOne(filter, updateOperation);

        if( updateResult == null || 
            updateResult.getMatchedCount()  != 1 || 
            updateResult.getModifiedCount() != 1 ) {
        	throw new RuntimeException("insert document failure");
        }
        
		LOG.debug("insert - END - id={}", "");
		return null;
	}

	public void delete(String idObjToDel) {
		LOG.debug("delete - START - idObjToDel={}", idObjToDel);

		// TODO Auto-generated method stub

		LOG.debug("delete - END");
	}
}
