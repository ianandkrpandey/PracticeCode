package com.pearson.cd.wip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonSyntaxException;
import com.pearson.cd.content.factory.AbstractContentTypeBuilder;
import com.pearson.cd.content.factory.Container;
import com.pearson.cd.content.factory.configuration.ContentDefStorageConfig;
import com.pearson.cd.content.factory.configuration.ContentExtensions;
import com.pearson.cd.contentenrichment.ContentEnrichmentThread;
import com.pearson.cd.contentenrichment.constants.ContentEnrichmentConstants;
import com.pearson.cd.lambda.implementations.constants.LambdaImplementationConstants;
import com.pearson.cd.scapi.GetScapiContent;
import com.pearson.cd.scapi.SCAPIRestClient;
import com.pearson.cd.transaction.objects.ContentDeliveryTransaction;
import com.pearson.cd.utils.ParseUrn;

public class WipParserHelper {
	
	private ContentExtensions extension;
	private static JsonNode jsonNode;

	public String processWipResponse(ContentDeliveryTransaction cdt, ContentDefStorageConfig config) throws Exception {

		String c6MmiRes = null;

		try {
			extension = this.getContentExtension();
			
			SCAPIRestClient scapiRestClient = new SCAPIRestClient();
			String chauserRes = scapiRestClient.getContentWorkItems(cdt.getProjectid(), LambdaImplementationConstants.SCAPI_CONFIG_ID);

			Container rootcontainer = new Container(cdt.getProjectid(),cdt.getProjectid(),getTypeFromJsonPath(LambdaImplementationConstants.PROJECT),1);

			List<String> chauserPayloadList = new ArrayList<>();
			chauserPayloadList.add(chauserRes);
			
			List<Container> parentContainerList = new ArrayList<>();
			rootcontainer.setChildren(parentContainerList);
			
			List<Object> searchForPatterns = AbstractContentTypeBuilder.searchForPatterns(chauserRes, LambdaImplementationConstants.CHAPTHER_PATH);
			for (Object object : searchForPatterns) {
				Container container = new Container(object.toString(),object.toString(),getTypeFromJsonPath(LambdaImplementationConstants.CHAPTHER_PATH),2);
				parentContainerList.add(container);
			}
			
			List<Container> moduleIdsList = fetchSCAPIResponse(parentContainerList, LambdaImplementationConstants.MODULE_PATH,3);
			
			List<Container> sectionIdsList = fetchSCAPIResponse(moduleIdsList, LambdaImplementationConstants.SECTION_PATH,4);
			
			List<Container> figureIdsList = fetchSCAPIResponse(sectionIdsList, LambdaImplementationConstants.FIGURE_PATH,5);
			
		//	fetchSCAPIResponse(figureIdsList, LambdaImplementationConstants.INTERACTIVE_PATH,6); Leaf node processing
		
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(rootcontainer);
			System.out.println(jsonInString); // printing WIP hierarchy
			
			
			/*	c6MmiRes = new WIPResponseModelBuilder().build(cdt.getTransactionid(), interactiveListIds,
			config.getS3Bucket(), config.getS3Key());*/
			
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}

		return c6MmiRes;

	}
	
	private List<Container> fetchSCAPIResponse(List<Container> parentContainerList, String path,int level) throws IOException, Exception {
		
		List<Container> childContainerList = new ArrayList<>();
		List<String> scapiResp = new ArrayList<>();
	//	List<String> interactiveIds = new ArrayList<String>(); leaf node processing
	//	List<String> interactiveListIds = new ArrayList<String>(); leaf node processing
		List<ContentEnrichmentThread> workList = new ArrayList<ContentEnrichmentThread>();

		for (Container container : parentContainerList) {
			ParseUrn urnObj = new ParseUrn();
			urnObj.parseURN(container.getId1());
			ContentEnrichmentThread cet = new ContentEnrichmentThread(urnObj, null, null, extension);
			workList.add(cet);
		}

		List<ContentEnrichmentThread> manifestation = GetScapiContent.getManifestation(workList, LambdaImplementationConstants.SCAPI_CONFIG_ID);
		for (ContentEnrichmentThread contentEnrichmentThread : manifestation) {
			System.out.println("........" + contentEnrichmentThread.getRespData());
			scapiResp.add(contentEnrichmentThread.getRespData());
			for (Container cont : parentContainerList) {
				/*if(path.contains("interactive")) { // leaf node processing 
					boolean pathExists = AbstractContentTypeBuilder.IsJsonPathexists(contentEnrichmentThread.getRespData(), path);
					if (pathExists) {
						interactiveIds.add(contentEnrichmentThread.getRespData());
					}
				}else */if(cont.getId1().contains(contentEnrichmentThread.getId())) {
					List<Object> idsList = AbstractContentTypeBuilder.searchForPatterns(contentEnrichmentThread.getRespData(), path);
					for (Object object : idsList) {
						Container child = new Container(object.toString(),object.toString(),getTypeFromJsonPath(path),level);
						cont.addChild(child);
					}
					if(!cont.getChildren().isEmpty()) {
						childContainerList.addAll(cont.getChildren());
					}
					break;
				}
			}
		}
		
		// leaf node processing 
		/*for (String interactiveId : interactiveIds) {

			Object intObj = AbstractContentTypeBuilder.searchForPatternObject(interactiveId, "$.figuredata");

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(intObj);
			jsonNode = mapper.readTree(json);

			String interactiveformat = jsonNode.get("interactiveformat").asText();
			String id = "";

			if (interactiveformat.equals("mmi")) {
				id = jsonNode.get("interactiveid").asText().trim();
				interactiveListIds.add(id);
			}
		}*/

		return childContainerList;
	}

	
	private ContentExtensions getContentExtension() {
		ContentExtensions extension = new ContentExtensions();
		extension.setType(ContentEnrichmentConstants.CONTENT_ENRICH_SCAPI);
		extension.setUseConfigId(LambdaImplementationConstants.SCAPI_CONFIG_ID);
		extension.setForPattern("manifestion");
		
		return extension;
	}
	
	private String getTypeFromJsonPath(String jsonPath) {
		
		if(jsonPath.contains("project")) {
			return "project";
		} else if(jsonPath.contains("chapter")) {
			return "chapter";
		} else if(jsonPath.contains("module")) {
			return "module";
		} else if(jsonPath.contains("section")) {
			return "section";
		} else if(jsonPath.contains("figure")) {
			return "figure";
		} else if(jsonPath.contains("figure") && jsonPath.contains("interactive")) {
			return "interactive";
		}
		return null;
	}

}