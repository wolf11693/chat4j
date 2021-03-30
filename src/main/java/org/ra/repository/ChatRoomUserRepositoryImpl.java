package org.ra.repository;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.ra.model.ChatRoomUserModel;
import org.ra.model.MongoCollectionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

@Repository(value = "chatRoomUserRepository")
public class ChatRoomUserRepositoryImpl {

	private static final Logger LOG = LoggerFactory.getLogger(ChatRoomUserRepositoryImpl.class);

	private MongoCollection<Document> chatRoomCollection;

	@Autowired
	private MongoTemplate MongoTemplate;

	@Autowired
	public ChatRoomUserRepositoryImpl(Map<String, MongoCollection<Document>> collections) {
		chatRoomCollection = collections.get(MongoCollectionEnum.CHAT_ROOM_COLLECTION.getValue());
	}
	
	public List<ChatRoomUserModel> findAll() {
		LOG.debug("findAll - START");

		FindIterable<Document> iterable = this.chatRoomCollection.find();
		
		MongoCursor<Document> cursor = iterable.iterator();
		System.out.println("Student list with cursor: ");
		while (cursor.hasNext()) {
		    System.out.println(cursor.next().toJson());
		}

		LOG.debug("findAll - END");
		return null;
	}

	public ChatRoomUserModel findById(String id) {
		LOG.debug("findById - START - id={}", id);

		// TODO Auto-generated method stub

		LOG.debug("findById - END");
		return null;
	}

	public ChatRoomUserModel insert(ChatRoomUserModel obj) {
		LOG.debug("insert - START - obj={}", obj);

		ChatRoomUserModel chatUserInserted = this.MongoTemplate.insert(obj);

		LOG.debug("insert - END - id={}", chatUserInserted.getId());
		return null;
	}

	public void delete(String idObjToDel) {
		LOG.debug("delete - START - idObjToDel={}", idObjToDel);

		// TODO Auto-generated method stub

		LOG.debug("delete - END");
	}
}
