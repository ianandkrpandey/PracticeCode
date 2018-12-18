package com.javer.objectdiff;

import java.util.ArrayList;
import java.util.List;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.ListCompareAlgorithm;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ObjectRemoved;
import org.javers.core.diff.changetype.ValueChange;

import com.javer.objectdiff.ChangedValue;
import com.javer.objectdiff.Container;
import com.javer.objectdiff.ObjectDifference;
import com.javer.objectdiff.AddedObject;
import com.javer.objectdiff.RemovedObject;

com.javer.objectdiff.ChangedValue

/**
 * This class contains the actual implementation of the Objects comparison using
 * the Javers framework. It prepared the final comparison and wrap all
 * information in the DiffObject class format.
 * 
 * @author VGup899
 *
 */
public class ObjectDifferenceProcessor {

	private static final String rootLocation = "/";

	public static void main(String[] args) {

		Container container1 = CotainerCompare.initilizeContainer3();
		Container container2 = CotainerCompare.initilizeContainer4();

		ObjectDifference difference = compareObject(container1, container2);
		if (null == difference) {
			System.out.println("No difference in both the objects");
		} else {
			System.out.println("Object Removed " + difference.getRemovedObjects());
			System.out.println("Object Added " + difference.getNewObjects());
			System.out.println("Object Changed " + difference.getValueChanges());
		}
	}

	private static ObjectDifference compareObject(Object object1, Object object2) {

		Javers javers = JaversBuilder.javers().withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE)
				.build();
		Diff diff = javers.compare(object1, object2);

		List<Change> changes = diff.getChanges();
		if (changes.size() == 0)
			return null;

		List<RemovedObject> objectRemovedList = new ArrayList<>();
		for (ObjectRemoved objectRemovedFromDiff : diff.getChangesByType(ObjectRemoved.class)) {
			RemovedObject objectRemoved = new RemovedObject();
			String path = objectRemovedFromDiff.getAffectedGlobalId().toString();
			path = path.replace("#", "");
			String[] absolutePathList = path.split("/", 2);

			if (absolutePathList[1].isEmpty()) {
				System.out.println("object removed at root location");
				objectRemoved.setLocation(rootLocation);
			} else {
				objectRemoved.setLocation(absolutePathList[1]);
			}
			objectRemoved.setObject(objectRemovedFromDiff.getAffectedObject().get());
			objectRemovedList.add(objectRemoved);
		}

		List<AddedObject> newObjectList = new ArrayList<>();
		for (NewObject objectAddedFromDiff : diff.getChangesByType(NewObject.class)) {
			AddedObject objectAdded = new AddedObject();
			String path = objectAddedFromDiff.getAffectedGlobalId().toString();
			path = path.replace("#", "");
			String[] absolutePathList = path.split("/", 2);

			if (absolutePathList[1].isEmpty()) {
				System.out.println("object added at root location");
				objectAdded.setLocation(rootLocation);
			} else {
				objectAdded.setLocation(absolutePathList[1]);
			}

			objectAdded.setObject(objectAddedFromDiff.getAffectedObject().get());
			newObjectList.add(objectAdded);
		}

		// Get the list of values changed between source and destination Objects
		List<ChangedValue> changeValueList = new ArrayList<>();
		for (ValueChange valueChnge : diff.getChangesByType(ValueChange.class)) {
			ChangedValue _valueChange = new ChangedValue();
			String path = valueChnge.getAffectedGlobalId().toString();
			path = path.replace("#", "");
			String[] absolutePathList = path.split("/", 2);

			if (absolutePathList[1].isEmpty()) {
				System.out.println("object changed at root location");
				_valueChange.setLocation(rootLocation);
			} else {
				_valueChange.setLocation(absolutePathList[1]);
			}

			_valueChange.setPropertyName(valueChnge.getPropertyName());
			_valueChange.setOldValue(valueChnge.getLeft());
			_valueChange.setNewValue(valueChnge.getRight());
			changeValueList.add(_valueChange);

		}

		ObjectDifference diffObject = new ObjectDifference();
		diffObject.setRemovedObjects(objectRemovedList);
		diffObject.setNewObjects(newObjectList);
		diffObject.setValueChanges(changeValueList);

		return diffObject;
	}
}
