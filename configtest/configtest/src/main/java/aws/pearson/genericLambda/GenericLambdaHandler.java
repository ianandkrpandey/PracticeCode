/**
 * 
 */
package aws.pearson.genericLambda;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.UpdateFunctionConfigurationRequest;
import com.amazonaws.services.lambda.model.UpdateFunctionConfigurationResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aws.pearson.dao.DynamoDBConfig;
import aws.pearson.dao.DynamoDBImpl;
import aws.pearson.model.LambdaConfig;

/**
 * @author VGUDLSA
 *
 */
public class GenericLambdaHandler implements RequestStreamHandler, RequestHandler<Object, String> {

	AmazonDynamoDB amazonDynamoDB = null;

	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		if (context != null) {
			LambdaLogger logger = context.getLogger();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode readTree = objectMapper.readTree(input);

			LambdaConfig lambdaConfig = objectMapper.treeToValue(readTree, LambdaConfig.class);

			DefaultAWSCredentialsProviderChain credentialsProvider = new DefaultAWSCredentialsProviderChain();

			amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider)
					.withRegion(lambdaConfig.getRegion()).build();

			LambdaConfig result = saveAndGetLamdaConfiguration(lambdaConfig);

			AWSLambda client = AWSLambdaClientBuilder.standard().withCredentials(credentialsProvider)
					.withRegion(lambdaConfig.getRegion()).build();

			UpdateFunctionConfigurationRequest request = new UpdateFunctionConfigurationRequest()
					.withFunctionName(result.getLambdaId()).withRole(result.getRole()).withHandler(result.getHandler())
					.withDescription(result.getDesc()).withTimeout(result.getTimeOut())
					.withMemorySize(result.getMemorySize()).withRuntime(result.getRunTime());
			UpdateFunctionConfigurationResult response = client.updateFunctionConfiguration(request);

			logger.log("Updated desc : " + response.getDescription());

		}

	}

	private LambdaConfig saveAndGetLamdaConfiguration(LambdaConfig objects) {
		AmazonDynamoDB init = null;
		try {
			init = DynamoDBConfig.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DynamoDBImpl dynamoDBImpl = new DynamoDBImpl(init);
		return dynamoDBImpl.save(objects);
	}

	public String handleRequest(Object arg0, Context arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
