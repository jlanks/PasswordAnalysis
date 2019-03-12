package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Pile_Of_Records {
	private ArrayList<Record> rawRecords, outputRecords;
	
	private HashMap<String, ArrayList<Record>> recordsGroupedByUserid;
	private HashMap<String, ArrayList<AnalyzedRecord>> analyzedRecordByUserid;
	
	public Pile_Of_Records(){
		this.rawRecords = new ArrayList<>();
		outputRecords = new ArrayList<>();
		this.recordsGroupedByUserid = new HashMap<>();
		analyzedRecordByUserid = new HashMap<>();
	}
	
	private void forEachUserRecords(BiConsumer<String, ArrayList<Record>> consumer) {
		for (Entry<String, ArrayList<Record>> pairEntry : recordsGroupedByUserid.entrySet()) {
			consumer.accept(pairEntry.getKey(), pairEntry.getValue());
		}
	}
	
	public void groupAndSort() {
		recordsGroupedByUserid = getRawRecords().stream().collect(
				Collectors.groupingBy(Record::getUserid, HashMap<String, ArrayList<Record>>::new, 
						Collectors.toCollection(ArrayList<Record>::new)));
	}
	
	public HashMap<String, ArrayList<AnalyzedRecord>> analyze() {
		if(rawRecords.isEmpty() || recordsGroupedByUserid.isEmpty()) return null;
		
		return analyzedRecordByUserid;
	}
	
	public ArrayList<Record> getRawRecords() {
		return rawRecords;
	}
}
