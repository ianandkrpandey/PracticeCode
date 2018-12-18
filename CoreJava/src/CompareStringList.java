import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.*;
import org.apache.commons.collections4.CollectionUtils;

public class CompareStringList {
	public static void main(String[] args) {
		List<String> s1 = new ArrayList<>();
		List<String> s2 = new ArrayList<>();
		s1.add("a");
		s1.add("b");
		s1.add("j");
		s2.add("j");
		s2.add("m");
		System.out.println(	CollectionUtils.isEqualCollection(s1, s2));
		System.out.println(new ArrayList<>(CollectionUtils.subtract(s1, s2)));
		List<String> aList = Arrays.asList("l","e","t","'","s");
		List<String> bList = Arrays.asList("g","o","e","s","t");

		List<String> result = aList.stream().filter(aObject -> {
		     return bList.contains(aObject);
		 }).collect(Collectors.toList());

		System.out.println(result);
		
	}
}
