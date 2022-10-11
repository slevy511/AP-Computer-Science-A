import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TwoInputsPerLine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// keep input file in the parent folder for the project
		// not in the src folder
		File file = new File("twotypes"); // point to file

		try {

			Scanner sc = new Scanner(file); // setup scanner
			while (sc.hasNextLine()) { // check a line exists in the input file
				int i = sc.nextInt(); // NOTE format of input file is expected
				System.out.println(i); // call nextInt()

				double j = sc.nextDouble();
				System.out.println(j);

			}
			sc.close(); // done with scanner

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
