/**
 * 
 */
package aws.pearson.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * @author VGUDLSA
 *
 */

@DynamoDBTable(tableName = "Lambda_Config")
public class LambdaConfig {
	
	@DynamoDBHashKey(attributeName = "Lambda_Id")
	private String lambdaId;
	
	@DynamoDBAttribute(attributeName = "Role")
	private String role;
	
	@DynamoDBAttribute(attributeName = "Handler")
	private String handler;
	
	@DynamoDBAttribute(attributeName = "Desc")
	private String desc;
	
	@DynamoDBAttribute(attributeName = "Timeout")
	private Integer timeOut;
	
	@DynamoDBAttribute(attributeName = "MemorySize")
	private Integer memorySize;
	
	@DynamoDBAttribute(attributeName = "Runtime")
	private String runTime;
	
	@DynamoDBAttribute(attributeName = "Region")
	private String region;
	
	
	public String getLambdaId() {
		return lambdaId;
	}
	public void setLambdaId(String lambdaId) {
		this.lambdaId = lambdaId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}
	public Integer getMemorySize() {
		return memorySize;
	}
	public void setMemorySize(Integer memorySize) {
		this.memorySize = memorySize;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

}
