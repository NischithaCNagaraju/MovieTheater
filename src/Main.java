import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
	/*
	 * Assuming a fixed theater layout, having 10 rows and 20 seats per row.
	 */
	private static int seatsInEachRow = 20;
	private static int  totalRows= 10;

	public static void main(String[] args) throws IOException, CannotAllocateSeatException {

		/*
		Read the input file name
		Create a class movie seating
        */
		System.out.println("Enter input file path");
		Scanner userInput = new Scanner(System.in);
		String path = userInput.next();
		File file = new File(path);
		Scanner sc = new Scanner(file);
		MovieTheaterSeating Seating = new MovieTheaterSeating(createTheaterLayout());
		while (sc.hasNextLine()) {
			Seating.parseInputFile(sc.nextLine());
		}
		sc.close();
		userInput.close();
		System.out.println(Seating.createFile());
	}

	private static HashMap<Character, ArrayList<Integer>> createTheaterLayout() {
		HashMap<Character, ArrayList<Integer>> theaterLayout = new HashMap<Character, ArrayList<Integer>>();
		char temp = 'A';
		for (int i = 0; i < totalRows; i++) {
			theaterLayout.put(temp, createEachRow());
			temp++;
		}
		return theaterLayout;
	}

	private static ArrayList<Integer> createEachRow() {
		ArrayList<Integer> seats = new ArrayList<Integer>();
		for (int i = 0; i < seatsInEachRow; i++)
			seats.add(i + 1);
		return seats;
	}
}
