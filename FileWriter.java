import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileWriter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	Scanner s= new Scanner(System.in);
	PrintWriter outputStream =null;
	try{
		outputStream = new PrintWriter(new FileOutputStream("newNumbers.txt"));
	}
	catch(FileNotFoundException e) {
		System.out.println("No file here!");
		System.exit(0);
	}
	System.out.println("Enter 3 lines of text");
	String line =null;
	for (int count =1; count<=3; count++){
		line=s.nextLine();
		outputStream.println(line);
	}
	outputStream.close();
	System.out.println("These lines were written to the file");

	}

}
