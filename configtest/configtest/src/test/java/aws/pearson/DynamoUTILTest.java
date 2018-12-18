package aws.pearson;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.HashSet;

public class DynamoUTILTest extends TestCase {
    public void testCreateItems() throws Exception {
        DynamoUTIL util = new DynamoUTIL("ProductCatalog","us-west-1");
        Item item = new Item().withPrimaryKey("Id", 120).withString("Title", "Book 120 Title")
                .withString("ISBN", "120-11111111112")
                .withStringSet("Authors", new HashSet<String>(Arrays.asList("Author122", "Author222")))
                .withNumber("Price", 20).withString("Dimensions", "8.5x11.0x.75").withNumber("PageCount", 500)
                .withBoolean("InPublication", false).withString("ProductCategory", "Book");
        PutItemResult result = util.createItems(item);
        //System.out.println(result.getAttributes().values().toString());
    }

    public void testRetrieveItem() throws Exception {
        DynamoUTIL util = new DynamoUTIL("ProductCatalog","us-west-1");
        // Item item = table.getItem("Id", 120, "Id, ISBN, Title, Authors", null);
        PrimaryKey pkey = new PrimaryKey();
        pkey.addComponent("Id",121);
        Item item = util.retrieveItem(pkey);
        System.out.println(item.toJSONPretty());
    }

}