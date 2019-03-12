package main;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Pile_Of_Records {
	
	private ArrayList<Record> rawRecords, outputRecords;
	
	private HashMap<String, ArrayList<Record>> recordsGroupedByUserid;
	/**
	 * arraylist of analyzed record, which are the output records
	 * don't be confuse that after analyzed, each user only have two analyzed record
	 */
	private ArrayList<AnalyzedRecord> analyzedRecordByUserid;		
	
	public Pile_Of_Records(){
		this.rawRecords = new ArrayList<>();
		outputRecords = new ArrayList<>();
		this.recordsGroupedByUserid = new HashMap<>();
		analyzedRecordByUserid = new ArrayList<>();
	}
	
	private void forEachUser(BiConsumer<String, ArrayList<Record>> consumer) {
		for (Entry<String, ArrayList<Record>> pairEntry : recordsGroupedByUserid.entrySet()) {
			consumer.accept(pairEntry.getKey(), pairEntry.getValue());
		}
	}
	
	public void groupAndSort() {
		recordsGroupedByUserid = getRawRecords().stream().collect(
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
			//System.out.println(records);
//			if (records.get(0).getUserid().equals("ipt119") && 
//					records.get(0).getType().equals(Record.IMAGE_TYPE))
//				for (int i = 0; i < records.size(); i++) {
//					if (records.get(i).getData().equals("start") || checkFail(records.get(i).getData()) || checkSuccess(records.get(i).getData()))
//						System.out.println(records.get(i));
//				}
		}
		
	}
	
	public ArrayList<AnalyzedRecord> analyze() {
		if(rawRecords.isEmpty() || recordsGroupedByUserid.isEmpty()) return null;
		forEachUser((userid, list) -> {
			ArrayList<Record> records = recordsGroupedByUserid.get(userid);
			//the two analyzed record for continuously building up
			AnalyzedRecord textAnalyzedRecord = new AnalyzedRecord(userid, Record.TEXT_TYPE);
			AnalyzedRecord imageAnalyzedRecord = new AnalyzedRecord(userid, Record.IMAGE_TYPE);
			//temp variables for building above two record
			boolean findingNewStartLogin = true;	//check does the program is finding start record or success/fail record
			Date startTime = null;					//save the start time
			String type = "";						//the type of record, either image21 or text21
			for (int i = 1; i < records.size(); i++) {//for each record of this user, in order sorted before
				Record thisRecord = records.get(i);
				//System.out.println(thisRecord);
				String data = thisRecord.getData();
				if (data.equals("start")) {
					type = thisRecord.getType();
					startTime = thisRecord.getTime();
//					if (thisRecord.getUserid().equals("ipt119")) {
//						System.out.println(thisRecord);
//					}
					findingNewStartLogin = false;
				} else if ((!findingNewStartLogin) && (checkFail(data) || checkSuccess(data)) && type.equals(thisRecord.getType())){
					Date endTime = thisRecord.getTime();
					int loginTime = (int) ((endTime.getTime() - startTime.getTime()) / 1000L);
//					if (thisRecord.getUserid().equals("ipt119")) {
//						System.out.println(thisRecord);
//						System.out.println("login time " + loginTime);
//					}
					if (type.equals(Record.IMAGE_TYPE) && checkSuccess(data)) {
						imageAnalyzedRecord.increaseSuccessCount();
						imageAnalyzedRecord.addAverageSuccessTime(loginTime);
					} else if (type.equals(Record.TEXT_TYPE) && checkSuccess(data)) {
						textAnalyzedRecord.increaseSuccessCount();
						textAnalyzedRecord.addAverageSuccessTime(loginTime);
					} else if (type.equals(Record.IMAGE_TYPE) && checkFail(data)) {
						imageAnalyzedRecord.increaseFailCount();
						imageAnalyzedRecord.addAverageFailTime(loginTime);
					} else if (type.equals(Record.TEXT_TYPE) && checkFail(data)) {
						textAnalyzedRecord.increaseFailCount();
						textAnalyzedRecord.addAverageFailTime(loginTime);
					} 
					findingNewStartLogin = true;
				}
				
			} //end for loop
//			System.out.println("here is the two result: ");
//			System.out.println(imageAnalyzedRecord);
//			System.out.println(textAnalyzedRecord);
			analyzedRecordByUserid.add(textAnalyzedRecord);
			analyzedRecordByUserid.add(imageAnalyzedRecord);
		});
		
		return analyzedRecordByUserid;
	}
	
	private boolean checkSuccess(String data) {
		return data.equals("success") || data.equals("goodLogin");
	}
	
	private boolean checkFail(String data) {
		return data.equals("failure") || data.equals("badLogin");
	}
	
	public ArrayList<Record> getRawRecords() {
		return rawRecords;
	}
}
