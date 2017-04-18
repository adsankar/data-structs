import java.util.ArrayList;

/**
 * Queue Class
 * @author Aleksander Sankar
 * Analysis of Algorithms Pd. 9
 */
public class Queue {

	//the Queue object is stored in the form of an ArrayList<Object>
	private ArrayList<Object> q = new ArrayList<Object>();

	/**
	 * Enqueues (adds the object to the end) the object in the parameter. 
	 * @param o the object to be added
	 */
	public void enqueue(Object o){
		q.add(o);
	}//end enqueue

	/**
	 * Dequeues (removes from the end of the list) and returns that object.
	 * @return the object at the end of the list
	 */
	public Object dequeue(){
		Object o = q.get(0);
		q.remove(0);
		return o;
	}//end dequeue

	/**
	 * Checks to see if the queue is empty.
	 * @return true if empty, false if not empty
	 */
	public boolean isEmpty(){
		if (q.size()==0){
			return true;
		}//end if
		else return false;
	}//end isEmpty

	/**
	 * Returns the size of the queue
	 * @return the size of the queue
	 */
	public int getSize(){
		return q.size();
	}//end getSize

	public String toString(){
		String s ="";
		for (int i=0; i<q.size(); i++){
			s+= "["+q.get(i)+"]";
		}//end for
		return s;
	}//end toString
}//end Queue Class