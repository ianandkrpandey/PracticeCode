package com.pearson.cd.lambda.implementations;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pearson.cd.configuration.AWSSpecificConfiguration;
import com.pearson.cd.content.factory.configuration.ContentDefStorageConfig;
import com.pearson.cd.lambda.implementations.constants.LambdaImplementationConstants;
import com.pearson.cd.lambda.implementations.errors.ServiceOrchestatorProcessException;
import com.pearson.cd.requestresponse.models.JsonToPojoBuilder;
import com.pearson.cd.requestresponse.models.MSBaseDataModel;
import com.pearson.cd.transaction.objects.ContentDeliveryTransaction;
import com.pearson.cd.wip.WipParserHelper;

public class WipParserFunctionImplementation extends AbstractLambdaServiceImplementation {

	private static WipParserFunctionImplementation _impl;
	private static LambdaLogger _logger;

	private WipParserFunctionImplementation() {
		super();
	}

	public static void process(String event, String publishMessage, LambdaLogger logger)
			throws ServiceOrchestatorProcessException {

		MSBaseDataModel datamodel = null;
		if (_impl == null)
			_impl = new WipParserFunctionImplementation();

		WipParserFunctionImplementation._logger = logger;

		logger.log("WipParserFunctionImplementation Event Info : Event" + event + " ;Message : " + publishMessage);

		try {

			// build the request object from the message
			datamodel = new JsonToPojoBuilder().build(publishMessage, MSBaseDataModel.class);

			// get the transaction object now - which houses all the necessary
			// details we need
			ContentDeliveryTransaction cdt = _impl.getTransaction(datamodel.get_transaction().getTransactionid());

			// do work here and construct the response message
			WipParserFunctionImplementation.doWork(cdt);
			// everything successful
			datamodel.get_requestResponse().setStatus(LambdaImplementationConstants.SO_SUCCESS_RESPONSE_EVENT_NAME);
			datamodel.get_requestResponse().setMessage("Message back from WIPParser");
		} catch (Exception e) {

			logger.log("ERROR in WipParserFunctionImplementation. Error is : " + e.getMessage());
			datamodel.get_requestResponse().setStatus(LambdaImplementationConstants.SO_ERROR_RESPONSE_EVENT_NAME);
			datamodel.get_requestResponse().setMessage(e.getMessage());
		} finally {
			try {
				_impl.processNextWorkflowTask(datamodel.get_responseChannel(),
						WipParserFunctionImplementation.buildResponseMessage(datamodel),
						datamodel.get_transaction().getTransactionid(), logger);
			} catch (JsonProcessingException e) {

				throw new ServiceOrchestatorProcessException(e);
			} catch (Exception e) {

				throw new ServiceOrchestatorProcessException(e);
			}
			// destroy the model
			datamodel = null;
		}
	}

	private static void doWork(ContentDeliveryTransaction cdt) throws Exception {
	
		// construct the content storage location object
		ContentDefStorageConfig storageConfig = new ContentDefStorageConfig();
		storageConfig.setS3Bucket(AWSSpecificConfiguration.getC4c6integrationbucket());
		storageConfig.setS3Key(cdt.getCdcl().getContentlocation());

		WipParserHelper wipParserHelper = new WipParserHelper();
		wipParserHelper.processWipResponse(cdt,storageConfig);

	}

	private static String buildResponseMessage(MSBaseDataModel datamodel) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(datamodel);
	}

}
