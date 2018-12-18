package com.demo.jsondiff;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;

public class DiffPojo {
	public static void main(String[] args) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		// File jsonInputFile = new
		// File("C:/Users/vkum168/Desktop/wiphierarchy_old.json");

		File jsonInputFile = new File("C:/Users/vkum168/Desktop/old.json");

		File jsonoutputFile = new File("C:/Users/vkum168/Desktop/new.json");

		// File jsonoutputFile = new
		// File("C:/Users/vkum168/Desktop/wiphierarchy_new.json");

		JsonNode source = mapper.readTree(jsonInputFile);

		JsonNode target = mapper.readTree(jsonoutputFile);

		JsonNode patch = JsonDiff.asJson(source, target);
		
		System.out.println(patch);
		System.out.println(patch.size());
		System.out.println("----------------------------");
		// NewObject no = new NewObject();
		DiffObject diffo = new DiffObject();
		List<NewObject> aNo = new ArrayList<NewObject>();

		List<RemovedObject> aRo = new ArrayList<RemovedObject>();
		
		List<CopiedObject> cpyObj = new ArrayList<CopiedObject>();
		if (patch.size() == 0) {
			System.out.println("There is No change");
		} else {

			for (int i = 0; i < patch.size(); i++) {
				String OPtype = patch.get(i).get("op").textValue();
				if (OPtype.equals("add")) {
					NewObject no = new NewObject();
					no.setId1(patch.get(i).get("value").get("id1").textValue());
					no.setId2(patch.get(i).get("value").get("id2").textValue());
					no.setType(patch.get(i).get("value").get("type").textValue());
					no.setLevel(patch.get(i).get("value").get("level").intValue());
					no.setLocation(patch.get(i).get("path").textValue());
					aNo.add(no);
					diffo.setNewObject(aNo);
					// System.out.println(no);

				} else if (OPtype.equals("remove")) {
					RemovedObject ro = new RemovedObject();
					ro.setLocation(patch.get(i).get("path").textValue());
					aRo.add(ro);
					diffo.setRemovedObject(aRo);
					// System.out.println(no);

				}
				
				 else if (OPtype.equals("copy")) {
						CopiedObject ro = new CopiedObject();
						ro.setPath(patch.get(i).get("path").textValue());
						ro.setFrom(patch.get(i).get("from").textValue());
						cpyObj.add(ro);
						diffo.setCopyiedObject(cpyObj);
						// System.out.println(no);

					}
				/*
				 * else if (OPtype.equals("replace")) { ReplacedObject ro = new
				 * ReplacedObject(); ro.setLocation(patch.get(i).get("path").textValue());
				 * aRo.add(ro); diffo.setRemovedObject(aRo); // System.out.println(no);
				 * 
				 * }
				 */

			}
		}
		System.out.println(diffo);
	}

}
