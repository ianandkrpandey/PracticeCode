import java.util.Arrays;

public class Replace {
public static void main(String[] args) {
	String s = "|| Router ID: f14d1345-8148-5351-9b46-47625abb6dbf || Batches [6e7e7773-d2b2-44f9-9e8e-e3b462defdfe, d8d27f27-1f1c-44b5-9b33-71164d772de9, 2e097a04-4fbd-40dc-8c6a-b2250e957e80, a67abe73-07b0-476f-bb68-f15ec414ea65, b26bedc3-0223-4666-8146-e2c6fd645dcd] for transaction a45137cd-9b86-4a55-a216-58c7b6deb522 has timed out";

int i =s.lastIndexOf("||");
s= s.substring(i).replace("||", "");;
System.out.println(s);

}
}	
