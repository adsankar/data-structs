import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JOptionPane;


public class Graphs {

	private static Component frame;

	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
	
		String [] searchTypes = {"Breadth First","Depth First","Dijkstra"};
		ArrayList<ArrayList<String>> x = readGraphs();
		for (ArrayList<String> a : x){
			int[][] g =makeGraph(a);//TODO fix here to read in the graph

			printArray2(g);
			String starting =(String) JOptionPane.showInputDialog(frame,
					"Enter the vertex you want to start on",
					"Start Vertex Input",
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					"");
			String ending =(String) JOptionPane.showInputDialog(frame,
					"Enter endpoint of the search",
					"End Vertex Input",
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					"");

			int type = 0;
			JOptionPane.showOptionDialog(frame,
					"What type of search do you want to do?",
					"Search Type",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,   
					searchTypes,  //the titles of buttons
					searchTypes[0]); 

			int v = getIntFromChar(starting.charAt(0));
			int end = getIntFromChar(ending.charAt(0));
			switch (type){
			case 0: breadthFirst(g,v,end); break;
			case 1: depthFirst(g,v,end); break;
			case 2: dijkstra(g,v,end); break;
			default: errMessage();
			}//end switch
		}//end while


	}//end main

	
	public static int findMax(int[] x){
		int max = Integer.MIN_VALUE;
		for (int i=0; i<x.length; i++){
			if (x[i]>max){
				max = x[i];
			}//end if
		}//end for
		return max;
	}
	
	public static ArrayList<String> readNext(int count){
		Scanner sc = null;
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		try{sc = new Scanner(new FileReader("graph.txt"));
		}//end try statement
		catch(FileNotFoundException e){
			System.out.println("File not found!");
			System.exit(0);
		}//end catch statement
		int i=0;
		//TODO fix here 
		while (sc.hasNext()){
			while (i<count){
				b.add(sc.nextLine());
			}
			while (sc.nextLine() !=null){
				a.add(sc.nextLine());
				
			}
			
		}
		return a;
	}

	public static ArrayList<ArrayList<String>> readGraphs(){
		Scanner sc  = null;
		ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
		ArrayList<String> t = new ArrayList<String>();
		try{sc = new Scanner(new FileReader("graph.txt"));
		}//end try statement
		catch(FileNotFoundException e){
			System.out.println("File not found!");
			System.exit(0);
		}//end catch statement
		while (sc.hasNext()){
			t.add(sc.nextLine());
		}//end while
		int i = 0;
		for (String s: t){
			if (s.equals("")){
				i++;
			}
			else a.get(i).add(s);
		}

		return a;
	}

	/**
	 * Generates a 2-D array that represents the graph presented in the ArrayList
	 * @param s the ArrayList storing the graph
	 * @return 2-D adjacency matrix
	 */
	public static int[][] makeGraph(ArrayList<String> s){
		boolean dir = false;
		if (s.get(0).equals("D")) dir = true;
		int [][] g = new int[26][26];
		s.remove(0);
		for (String t: s){
			t=t.toLowerCase();
			g[getIntFromChar(t.charAt(0))][getIntFromChar(t.charAt(1))] =Integer.parseInt(t.substring(2));
			if (!dir){
				g[getIntFromChar(t.charAt(1))][getIntFromChar(t.charAt(0))] =Integer.parseInt(t.substring(2));
			}//end if
		}//end for
		return g;
	}//end makeGraph

	/**
	 * 
	 */
	public static void errMessage(){
		JOptionPane.showMessageDialog(frame,
				"That is an invalid selection!",
				"Bad Input",
				JOptionPane.ERROR_MESSAGE);
	}//end errMessage

	/**
	 * 
	 * @param x
	 */
	public static void printArray2(int[][] x){
		for (int i=0; i<x.length; i++){
			for (int j=0; j<x[i].length; j++){
				System.out.print(x[i][j]);
			}//end for
			System.out.println();
		}//end for
	}//end printArray2

	/**
	 * 
	 * @param x
	 */
	public static void printArray(String[][] x){
		for (int i=0; i<x.length; i++){
			for (int j=0; j<x[i].length; j++){
				System.out.print(x[i][j]+"\t");
			}//end for
			System.out.println();
		}//end for
	}//end printArray

	/**
	 * 
	 * @param g
	 * @param v
	 */
	public static void breadthFirst(int [][] g, int v, int end){

		String[][] lbltbl = new String[26][4];
		Queue q = new Queue();
		q.enqueue(v);


		printArray(lbltbl);

	}//end breadthFirst

	/**
	 * 
	 * @param g
	 * @param v
	 */
	public static void depthFirst(int[][] g, int v, int end){

	//	int totLength = 0;
		String[][] lbltbl = new String[27][4];
		lbltbl[0][0] = "Vertex";
		lbltbl[0][1] = "Prev. Vertex";
		lbltbl[0][2] = "Cum. Sum";
		lbltbl[0][3] = "Marked?";
		for (int i=1; i<27; i++){
			lbltbl[i][0] = getCharFromInt(i)+"";
			lbltbl[i][3] = "no";
		}
		Stack<String> a= new Stack<String>();
		
		lbltbl[v][1] = "--";
		lbltbl[v][2] = "0";
		lbltbl[v][3] = "yes";
		
		ArrayList<Integer> possible = new ArrayList<Integer>();
		while (!a.isEmpty()){
		for (int i=0; i<26; i++){
			if (g[v][i] !=0){
				possible.add(g[v][i]);
				a.add(getCharFromInt(i)+"");
			}
		
		}
		int next = getIntFromChar(a.pop().charAt(0));
		lbltbl[next][1] = getCharFromInt(v)+"";
	///	totLength += 1;
		lbltbl[next][2] = ((Integer)g[0][0]).toString();
		}//end while
		printArray(lbltbl);

	}//end depthFirst


	/**
	 * 
	 * @param g
	 * @param v
	 */
	public static void dijkstra(int[][] g, int v, int end){

		String[][] lbltbl = new String[26][4];
		for (int i=0; i<26; i++){
			lbltbl[i][2] =((Integer)Integer.MAX_VALUE).toString();
		}
		printArray(lbltbl);

	}//end dijkstra


	/**
	 * 
	 * @param i
	 * @return
	 */
	public static char getCharFromInt(int i){
		switch (i){
		case 0: return 'a';
		case 1: return 'b';
		case 2: return 'c';
		case 3: return 'd';
		case 4: return 'e';
		case 5: return 'f';
		case 6: return 'g';
		case 7: return 'h';
		case 8: return 'i';
		case 9: return 'j';
		case 10: return 'k';
		case 11: return 'l';
		case 12: return 'm';
		case 13: return 'n';
		case 14: return 'o';
		case 15: return 'p';
		case 16: return 'q';
		case 17: return 'r';
		case 18: return 's';
		case 19: return 't';
		case 20: return 'u';
		case 21: return 'v';
		case 22: return 'w';
		case 23: return 'x';
		case 24: return 'y';
		case 25: return 'z';

		default: return 'a';
		}//end switch
	}//end getCharFromInt

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static int getIntFromChar(char c){
		switch (c){
		case 'a': return 0;
		case 'b': return 1;
		case 'c': return 2;
		case 'd': return 3;
		case 'e': return 4;
		case 'f': return 5;
		case 'g': return 6;
		case 'h': return 7;
		case 'i': return 8;
		case 'j': return 9;
		case 'k': return 10;
		case 'l': return 11;
		case 'm': return 12;
		case 'n': return 13;
		case 'o': return 14;
		case 'p': return 15;
		case 'q': return 16;
		case 'r': return 17;
		case 's': return 18;
		case 't': return 19;
		case 'u': return 20;
		case 'v': return 21;
		case 'w': return 22;
		case 'x': return 23;
		case 'y': return 24;
		case 'z': return 25;

		default: return 0;
		}//end switch
	}//end getIntFromChar

}//end class