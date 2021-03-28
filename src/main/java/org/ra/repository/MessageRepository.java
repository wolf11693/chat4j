package org.ra.repository;

import java.util.List;

import org.ra.model.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageRepository {

	private static final Logger LOG = LoggerFactory.getLogger(MessageRepository.class);

	public List<MessageModel> findAll() {
		LOG.debug("findAll - START");

		// TODO Auto-generated method stub

		LOG.debug("findAll - END - return {} element", 2);
		return null;
	}

	public MessageModel findById(String id) {
		LOG.debug("findById - START - id={}", id);

		// TODO Auto-generated method stub

		LOG.debug("findById - END");
		return null;
	}

	public MessageModel insert(String idChat, MessageModel obj) {
		LOG.debug("insert - START - objToSave={}", obj);

		// TODO Auto-generated method stub

		LOG.debug("insert - END");
		return null;
	}

	public void delete(String idObjToDel) {
		LOG.debug("delete - START - idObjToDel={}", idObjToDel);

		// TODO Auto-generated method stub

		LOG.debug("delete - END");
	}
}
