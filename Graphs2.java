


public class Graphs2 {
//	private static Component frame;

	public static void main(String [] args){
		//TODO add dijkstra check on max value
		
		int[] w = { 1, 2,4,7,9996,7,3,44};
		System.out.println(findMax(w));
	}
//		String [] searchTypes = {"Breadth First","Depth First","Dijkstra"};
//		String starting =(String) JOptionPane.showInputDialog(frame,
//				"Enter the vertex you want to start on",
//				"Start Vertex Input",
//				JOptionPane.QUESTION_MESSAGE,
//				null,
//				null,
//				"");
//		String ending =(String) JOptionPane.showInputDialog(frame,
//				"Enter endpoint of the search",
//				"End Vertex Input",
//				JOptionPane.QUESTION_MESSAGE,
//				null,
//				null,
//				"");
//
//		int type = 0;
//		JOptionPane.showOptionDialog(frame,
//				"What type of search do you want to do?",
//				"Search Type",
//				JOptionPane.YES_NO_OPTION,
//				JOptionPane.QUESTION_MESSAGE,
//				null,   
//				searchTypes,  //the titles of buttons
//				searchTypes[0]); 
//	}
	public static int findMax(int[] x){
		int max = Integer.MIN_VALUE;
		for (int i=0; i<x.length; i++){
			if (x[i]>max){
				max = x[i];
			}//end if
		}//end for
		return max;
	}
	
}
