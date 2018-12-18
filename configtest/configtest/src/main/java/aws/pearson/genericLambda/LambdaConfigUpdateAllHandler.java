/**
 * 
 */
package aws.pearson.genericLambda;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.UpdateFunctionConfigurationRequest;
import com.amazonaws.services.lambda.model.UpdateFunctionConfigurationResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import aws.pearson.dao.DynamoDBConfig;
import aws.pearson.dao.DynamoDBImpl;
import aws.pearson.model.LambdaConfig;

/**
 * @author VGUDLSA
 *
 */
public class LambdaConfigUpdateAllHandler implements RequestStreamHandler, RequestHandler<Object, String> {

	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		if (context != null) {
			LambdaLogger logger = context.getLogger();
			List<LambdaConfig> lambdaConfigs = getLamdaconfigurations();
			AWSLambda client = AWSLambdaClientBuilder.defaultClient();
			logger.log("lambdaConfigs size : " + lambdaConfigs.size());
			for (LambdaConfig lambdaConfig : lambdaConfigs) {
				UpdateFunctionConfigurationRequest request = new UpdateFunctionConfigurationRequest()
						.withFunctionName(lambdaConfig.getLambdaId()).withRole(lambdaConfig.getRole()).withHandler(lambdaConfig.getHandler())
						.withDescription(lambdaConfig.getDesc()).withTimeout(lambdaConfig.getTimeOut())
						.withMemorySize(lambdaConfig.getMemorySize()).withRuntime(lambdaConfig.getRunTime());
				UpdateFunctionConfigurationResult response = client.updateFunctionConfiguration(request);
				logger.log("Updated desc : " + response.getDescription());
			}
			
		}

	}


	private List<LambdaConfig> getLamdaconfigurations() {
		AmazonDynamoDB init = null;
		try {
			init = DynamoDBConfig.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		scanExpression.addFilterCondition("Lambda_Id", new Condition().withComparisonOperator(ComparisonOperator.NOT_NULL));
		DynamoDBImpl dynamoDBImpl = new DynamoDBImpl(init);
		PaginatedScanList<LambdaConfig> lambdaConfigs = dynamoDBImpl.scan(LambdaConfig.class, scanExpression);
		return lambdaConfigs;
	}


	public String handleRequest(Object arg0, Context arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
