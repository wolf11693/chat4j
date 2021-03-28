package org.ra.config;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

@Configuration
@EnableMongoRepositories(basePackages = "org.ra.repository")
public class MongoConfig {
	
	@Value("${mongo.connection.url}")
	private String mongoConnectionUrl;

	@Value("${mongo.collections}")
	private String[] collectionsName;
	
	@Bean(name = "mongo")
	public MongoClient getMongoClient() {
		ConnectionString mongoConnectionString = new ConnectionString(this.mongoConnectionUrl.trim());
		
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
											        		.codecRegistry(codecRegistry)
        													.applyConnectionString(mongoConnectionString)
        													.build();
        
        MongoClient mongoClient = MongoClients.create(mongoClientSettings);
        
        return mongoClient;
	}
	
	@Bean(name = "mongoTemplate")
	public MongoTemplate getMongoTemplate() {
		String databaseName = mongoConnectionUrl.split("/")[mongoConnectionUrl.split("/").length-1];
		
		MongoClient mongoClient = this.getMongoClient();
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, databaseName);
		
		return mongoTemplate;
	}
	
	@Bean(name = "mongoCollections")
	public Map<String, MongoCollection<Document>> getMongoCollectionsMap() {
		Map<String, MongoCollection<Document>> mongoCollectionMap = new HashMap<>();
		
		if(this.collectionsName != null) {
			
			MongoCollection<Document> collection = null;
			for(String collectionName : this.collectionsName) {
				
				collection = this.getMongoTemplate().getCollection(collectionName.trim());
				if(collection != null) {
					mongoCollectionMap.put(collectionName, collection);
				}
			}
			
		}
		
		return mongoCollectionMap;
	}
}