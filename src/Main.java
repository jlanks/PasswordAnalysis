import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	File image21, text21;
	File outputFile;
	
	ArrayList<Record> rawRecords, outputRecords;
	
	public Main() {
		image21 = new File(new File("./files"), "imagept21.csv");
		text21 = new File(new File("./files"), "text21.csv");
		outputFile = new File(new File("./files"), "merged.csv");
		rawRecords = new ArrayList<>();
		outputRecords = new ArrayList<>();
		try {
			outputFile.createNewFile();
		} catch (IOException e) {
			System.err.println("can't create output file");
		}
	}
	
	public void doJob() {
		//step 1: read two files and generate arraylist of record
		System.out.println(image21.getAbsolutePath());
		try (BufferedReader imageReader = new BufferedReader(new FileReader(image21));
				BufferedReader textReader = new BufferedReader(new FileReader(text21))){
			
			for(String line = imageReader.readLine(); line != null && !line.equals("");line = imageReader.readLine()) {
				String[] imageField = line.split(",");
				rawRecords.add(new Record(Record.IMAGE_TYPE, 
						imageField[0], 
						imageField[1], 
						imageField[2], 
						imageField[3], 
						imageField[4],
						imageField[5], 
						imageField[6]));
			}
			
			for(String line = textReader.readLine(); line != null && !line.equals("");line = textReader.readLine()) {
				String[] textField = line.split(",");
				rawRecords.add(new Record(Record.TEXT_TYPE, 
						textField[0], 
						textField[1], 
						textField[2], 
						textField[3], 
						textField[4],
						textField[5], 
						textField[6]));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(rawRecords);
		
		//TODO: from here to write code that manipulate data read from files
		
		//step 2: generate a map where key = userid, value = a list of record that correspond to this userid, and sorted by site primary and by time secondly
		HashMap<String, ArrayList<Record>> recordsGroupedByUserid = rawRecords.stream().collect(
				Collectors.groupingBy(Record::getUserid, HashMap<String, ArrayList<Record>>::new, 
						Collectors.toCollection(ArrayList<Record>::new)));
		
		for (List<Record> records: recordsGroupedByUserid.values()) {
			records.sort((r1, r2) -> {
				int d = r1.getSite().compareTo(r2.getSite());
				if (d != 0) return d;
				else {
					return r1.getTime().compareTo(r2.getTime());
				}
			});
			System.out.println(records);
		}
		//step 3 TODO: from each list in the map, manipulate the data in the list to produce a list of Analized data
		
		//step 4 TODO: rewrite the toString method of analyized data class, to produce .csv format string, then print the result list using PrintWriter 
		try(PrintWriter writer = new PrintWriter(new FileWriter(outputFile), true)){
			//writer.println(rawRecords);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
	}

	public static void main(String[] args) {
		new Main().doJob();
	}

}
