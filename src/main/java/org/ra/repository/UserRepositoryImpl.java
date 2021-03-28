package org.ra.repository;

import java.util.Map;

import org.bson.Document;
import org.ra.model.MongoCollectionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;

@Repository
public class UserRepositoryImpl {

	private MongoCollection<Document> collection;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	public UserRepositoryImpl(Map<String, MongoCollection<Document>> collections) {
		collection = collections.get(MongoCollectionEnum.USER_COLLECTION.getValue());
	}
}
