package com.pearson.cd.lambda.implementations.constants;

public class LambdaImplementationConstants {
	
	//hard coded for now this needs to be read out of a configuration dynamodo table at some point
	public static final String SO_PUBLISH_EVENT_NAME = "PUBLISH";
	public static final String SO_CONVERTER_EVENT_NAME = "CONVERTER";
	public static final String SO_URNRESOLVER_EVENT_NAME = "URNRESOLVER";
	public static final String SO_COURIER_EVENT_NAME = "COURIER";
	public static final String SO_NOTIFIER_EVENT_NAME = "NOTIFIER";
	public static final String SO_ANALYZER_EVENT_NAME = "ANALYZER";
	public static final String SO_WIPPARSER_EVENT_NAME = "WIPPARSER";
	
	public static final String SO_SUCCESS_RESPONSE_EVENT_NAME = "SUCCESS";
	public static final String SO_ERROR_RESPONSE_EVENT_NAME = "ERROR";
	public static final String SO_CONTENT_EVENT_NAME = "CONTENTDEF_V2";
	
	public static final String COURIER_DELIVERY_EVENT_NAME = "DELIVER";
	public static final String COURIER_DELIVERY_MONITOR_EVENT_NAME = "DELIVER MONITOR";
	
	public static final String DOC_SERVICE_READ_OP = "READ";
	public static final String DOC_SERVICE_UPDATE_OP = "UPDATE";
	public static final String DOC_SERVICE_INSERT_OP = "INSERT";
	public static final String DOC_SERVICE_QUERY_OP = "QUERY";
	
	public static final String DOC_SERVICE_REQUEST_TRANSACTION = "TRANSACTION";
	public static final String DOC_SERVICE_REQUEST_CONTENT = "CONTENT";
	public static final String DOC_SERVICE_REQUEST_TRANSACTION_CONTENT = "BOTH";
	
	public static final String DOC_SERVICE_QUERY_OP_PROJECT = "PROJECTURN";
	
	public static final String VERSION_1 = "V1";
	public static final String VERSION_2 = "V2";
	public static final String VERSION_3 = "V3";
	
	//public static final String MDS_CONFIG_ID = "MDS1";
	public static final String MDS_ENDPOINT_TYPE = "MDS";
	public static final String IDM_ENDPOINT_TYPE = "IDM";
	public static final String SCAPI_ENDPOINT_TYPE = "SCAPI";
	public static final String MMI_ENDPOINT_TYPE = "MMI";
	public static final String SCAPI_CONFIG_ID = "SCAPI1";
	public static final String SO_AUTOBAHN_EVENT_NAME = "AUTOBAHN";
	public static final String PROJECT = "project";
	
	public static final String CHAPTHER_PATH = "$.contents.bodymatter[?(@.type == 'chapter')].id";
	public static final String MODULE_PATH = "$.contents.bodymatter[?(@.type == 'module')].id";
	public static final String SECTION_PATH = "$.contents.bodymatter[?(@.type == 'section')].id";
	public static final String FIGURE_PATH = "$.contents.bodymatter[?(@.type == 'figure')].id";
	public static final String INTERACTIVE_PATH = "$.[?(@.type == 'figure' && @.figuretype == 'interactive')]";
	
	public LambdaImplementationConstants() {
		// TODO Auto-generated constructor stub
	}

}
