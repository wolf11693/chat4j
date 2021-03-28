package org.ra.repository;

import java.util.List;

import org.ra.model.ChatRoomUserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomUserRepository {

	private static final Logger LOG = LoggerFactory.getLogger(ChatRoomUserRepository.class);

	@Autowired
	private MongoTemplate MongoTemplate;
	
	public List<ChatRoomUserModel> findAll() {
		LOG.debug("findAll - START");

		// TODO Auto-generated method stub

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
