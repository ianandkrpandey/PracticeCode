package com.pearson.global.configuration.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;

public interface DynamoDBOps {
	public <T> T load(Class<T> domainClass, Object hashKey, Object rangeKey);

	public <T> T load(Class<T> domainClass, Object hashKey);

	public <T> T save(Object entity);

	public void delete(Object entity);

	public <T> PaginatedQueryList<T> query(Class<T> domainClass, DynamoDBQueryExpression<T> queryExpression);

	public <T> PaginatedScanList<T> scan(Class<T> domainClass, DynamoDBScanExpression scanExpression);
}
