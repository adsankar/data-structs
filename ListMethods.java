package listMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * List Methods Program
 * It's recursion
 * 
 */
public class ListMethods {

	
	/**
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static <T extends Comparable<T>> boolean equal(List<T> list1,
			List<T> list2) {
		if (list1 == null || list2 == null)
			throw new NullPointerException();
		if (list1.size() == 0 && list2.size() == 0)
			return true;
		if (list1.size() != list2.size())
			return false;
		else return nodeEquals(list1, list2, list1.size()-1);
	}

	/**
	 * 
	 * @param list1
	 * @param list2
	 * @param index
	 * @return
	 */
	public static <T extends Comparable<T>> boolean nodeEquals(List<T> list1,
			List<T> list2, int index){
		if (index == 0)
			return list1.get(index).compareTo(list2.get(index)) == 0;
		else
			return list1.get(index).compareTo(list2.get(index)) == 0 &&
			nodeEquals(list1, list2, index-1);
	}


	/**
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static <T extends Comparable<T>> int compare(List<T> list1,
			List<T> list2) {
		if (equal(list1, list2))
			return 0;
		if (list1 == null || list2 == null)
			throw new NullPointerException();

		else return compareNode(list1, list2, 0);
	}

	/**
	 * 
	 * @param list1
	 * @param list2
	 * @param index
	 * @return
	 */
	public static <T extends Comparable<T>> int compareNode(List<T> list1,
			List<T> list2, int index) {
		if (index != list1.size()-1)
			if (list1.get(index).compareTo(list2.get(index)) == 0)
				return compareNode(list1, list2, index+1);
			else if (list1.get(index).compareTo(list2.get(index))>0)
				return 1;
			else return -1;
		else if (list1.size() > list2.size())
			return 1;
		else return -1;

	}

	public static <T> List<T> copyAndDuplicate(List<T> list) {
		ArrayList<T> newList = new ArrayList<T>();
		if (list == null)
			throw new NullPointerException();
		else doubleCopy(list, newList, 0);
		return newList;
	}

	public static <T> void doubleCopy(List<T> list, List<T> newList, int index) {
		if (index == list.size()) return;
		else {
			doubleCopy(list, newList, index+1);
			newList.add(0, list.get(index));
			newList.add(0, list.get(index));
		}

	}

	public static <T> List<T> backOfList(List<T> list, int num) {
		ArrayList<T> newList = new ArrayList<T>();
		if (list == null)
			throw new NullPointerException();
		else if (num > list.size())
			return newList;
		else nextElement(list, newList, num);
		return newList;
	}

	public static <T> void nextElement(List<T> list, List<T> newList, int index) {
		if (index >0 ){

			newList.add(list.get(list.size()-index));
			nextElement(list, newList, index-1);

		}


	}

}
