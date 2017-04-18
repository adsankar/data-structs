

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * File Reading Program
 * @author Aleksander Sankar
 */
public class FileReader {
	/**
	 * @param args not used
	 */
	public static void main(String[] args) {

		File myFile = new File("numbers.txt");
		FileInputStream myStream =null;
		try{ myStream = new FileInputStream(myFile);
		}catch (FileNotFoundException ex){
			System.out.println("No File Here!");
			System.exit(0);//end process
		}
		Scanner myScanner = new Scanner(myStream);
		while (myScanner.hasNext()){
			System.out.println(myScanner.nextLine());
		}
		try { myStream.close();
		}catch (IOException ex){}
	}
}