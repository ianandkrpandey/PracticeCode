package com.pearson.global.configuration.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;

public class DynamoDBImpl implements DynamoDBOps {

	protected DynamoDBMapper dynamoDBMapper;
	private AmazonDynamoDB amazonDynamoDB;
	private DynamoDBMapperConfig dynamoDBMapperConfig;

	public DynamoDBImpl(AmazonDynamoDB amazonDynamoDB) {
		this.amazonDynamoDB = amazonDynamoDB;
		setDynamoDBMapperConfig(null);
	}

	public void setDynamoDBMapperConfig(DynamoDBMapperConfig dynamoDBMapperConfig) {
		this.dynamoDBMapperConfig = dynamoDBMapperConfig;
		dynamoDBMapper = dynamoDBMapperConfig == null ? new DynamoDBMapper(amazonDynamoDB)
				: new DynamoDBMapper(amazonDynamoDB, dynamoDBMapperConfig);
		if (dynamoDBMapperConfig == null) {
			this.dynamoDBMapperConfig = DynamoDBMapperConfig.DEFAULT;
		}
	}

	public <T> T load(Class<T> domainClass, Object hashKey, Object rangeKey) {
		T entity = dynamoDBMapper.load(domainClass, hashKey, rangeKey);

		return entity;
	}

	public <T> T load(Class<T> domainClass, Object hashKey) {
		T entity = dynamoDBMapper.load(domainClass, hashKey);
		return entity;
	}


	public <T> T save(Object entity) {
		dynamoDBMapper.save(entity);
		return (T) entity;
		}
	
	public void delete(Object entity) {
		dynamoDBMapper.delete(entity);
	}

	public <T> PaginatedQueryList<T> query(Class<T> domainClass, DynamoDBQueryExpression<T> queryExpression) {
		PaginatedQueryList<T> results = dynamoDBMapper.query(domainClass, queryExpression);
		return results;
	}

	public <T> PaginatedScanList<T> scan(Class<T> domainClass, DynamoDBScanExpression scanExpression) {
		PaginatedScanList<T> results = dynamoDBMapper.scan(domainClass, scanExpression);
		return results;
	}

}
