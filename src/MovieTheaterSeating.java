import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



/*
		Create a class movie seating

		Data Members :
			We need layout of theater[HashMap<Character, ArrayList<Integer>>]
			We need to save the input [HashMap<String, Integer>]
			Output [HashMap<String, ArrayList<String>>]
			Number of seats per row : 20
			Number of rows : J-A = 10
			total number of seats = 200

		Member Functions:
			Constructor : populate the theatre layout
			parse the string
			validateInput
			AllocateSeats
			GetBestRow


 */
public class MovieTheaterSeating  {

	private HashMap<Character, ArrayList<Integer>> theaterLayout = new HashMap<Character, ArrayList<Integer>>();
	private HashMap<String, Integer> inputReservation = new HashMap<String, Integer>();
	private LinkedHashMap<String, ArrayList<String>> reservationDetails = new LinkedHashMap<String, ArrayList<String>>();
	private HashSet<String> noReservation = new HashSet<String>();
	private int seatsInEachRow = 20;
	private int totalRows = 10;
	private int countAvailableSeats = seatsInEachRow * totalRows;

	public MovieTheaterSeating(HashMap<Character, ArrayList<Integer>> theaterMap) {
		theaterLayout = theaterMap;
	}


	public String parseInputFile(String s) throws CannotAllocateSeatException {
		String split[] = s.split(" ");
		return validateInput(split);
	}

	private String validateInput(String split[]) throws CannotAllocateSeatException {
		String name = "";
		int numSeats = 0;
		if (split.length == 2) {
			name = split[0];
			numSeats = Integer.parseInt(split[1]);
			inputReservation.put(name, numSeats);
			return allocateSeats(numSeats, name);
		}
		if (split.length > 2 || split.length < 2) {
			throw new CannotAllocateSeatException("Parameters incorrect");
		}
		if (numSeats <= 0) {
			throw new CannotAllocateSeatException("Number of seat requested is 0 or less");
		}

		if (name == null || name.isEmpty()) {
			throw new CannotAllocateSeatException("Name is empty");
		}
		return "";
	}

	private String allocateSeats(int numSeats, String name) {
		String result = "";
		if (numSeats > countAvailableSeats || numSeats > seatsInEachRow) {
			result = name.concat(" Reservation cannot be made, too many seats requested");
			noReservation.add(result);
			return result;
		}
		Character row = getTheBestRow(numSeats);
		ArrayList<Integer> availableSeats = theaterLayout.get(row);
		reservationDetails.put(name, printSeats(availableSeats, numSeats, row));
		updateSeats(row, numSeats);
		countAvailableSeats -= numSeats;
		return new String(name.concat(reservationDetails.get(name).toString()));
	}

	private void updateSeats(Character row, int numSeats) {
		ArrayList<Integer> availableSeats = theaterLayout.get(row);
		for (int i = 0; i < numSeats +3; i++)
			if(availableSeats.size() > 0)
				availableSeats.remove(0);
	}

	private ArrayList<String> printSeats(ArrayList<Integer> availableSeats, int numSeats, Character rowId) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < numSeats; i++) {
			result.add(Character.toString(rowId) + availableSeats.get(i));
		}
		return result;
	}

	private Character getTheBestRow(int numSeats) {
		int minSize = Integer.MAX_VALUE;
		Character row = Character.MIN_VALUE;

		ArrayList<Character> keys = new ArrayList<Character>(theaterLayout.keySet());
		for (int i=keys.size()-1; i>=0;i--) {
			if((theaterLayout.get(keys.get(i)).size() -numSeats) < minSize && (theaterLayout.get(keys.get(i)).size() -numSeats) >=0){
				minSize = theaterLayout.get(keys.get(i)).size() -numSeats;
				row = keys.get(i);
			}
		}

		return row;
	}

	public String createFile() throws IOException {
		String content = "";
		String newContent = "";
		for (Map.Entry<String, ArrayList<String>> entry : reservationDetails.entrySet()) {
			content += entry.getKey() + " " + entry.getValue() + System.lineSeparator();
			newContent = content.replace("[", "").replace("]", "");
		}
		String path = createOutputFile("output.txt", newContent);
		return path;
	}

	private String createOutputFile(String fileName, String content) throws IOException {
		Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
		File f = new File(fileName);
		return f.getCanonicalPath();
	}

}