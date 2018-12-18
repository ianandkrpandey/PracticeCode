// 6be5ea2f-0e49-4717-8bf0-2729b97a5e10 QA workflow Table
public class ArrayDataFetch {
public static void main(String[] args) {
	/*String lastStatus = "\"endtime\": \"2018-04-19T21:16:23Z\",\r\n" + 
			"      \"message\": \" || Router ID: 50c3649b-9725-509f-b8b4-37c89e40ebcd || There was an error in the C6-C4 Get Title S3 call\",\r\n" + 
			"      \"starttime\": \"2018-04-19T21:16:21Z\",\r\n" + 
			"      \"workflowtask\": \"EMAIL\"";*/
	String lastStatus = "{\"endtime\": \"2018-04-19T21:16:20Z\",\r\n" + 
			"      \"message\": \" || Router ID: 7849830a-58a8-5e50-8ac5-d72dbfae1104 || Batches [32f1554e-234f-4020-bc23-822ac34bef3d, 0acc3fd0-f940-43c0-868b-431ab27c227c, b688c2f2-8f8b-4c35-bb0c-b9799dd0a2bf, cdb92d8d-313e-4854-ade1-53317773188a, 9bf6efbe-cc24-4254-a132-c0383485608d] for transaction 887ad931-e19d-447a-bce2-821fbab75aa6 has timed out" + 
			"      \"starttime\": \"2018-04-19T21:14:54Z\",\r\n" + 
			"      \"workflowstatus\": \"ERROR\",\r\n" + 
			"      \"workflowtask\": \"CONVERTER\"}";
	String[] values = lastStatus.replace('{', ' ').replace('}', ' ').split(",");
	System.out.println(values.length);
	//System.out.println(values[1].split("=")[1]);
	for(int i=0;i<values.length;i++) {
		System.out.println(values[i]);
	}
	Map<String,String> s = new Ha
}
}
