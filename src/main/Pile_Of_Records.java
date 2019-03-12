package main;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Pile_Of_Records {
	private static String SUCCESS = "success", FAILURE = "failure";
	
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
			boolean foundLoginAction = false;
			boolean isGoodLogin = true;
			Date endTime = null;
			String type = "";
			for (int i = records.size() - 1; i >= 0; i--) {//for each record of this user, in Inverse order
				Record thisRecord = records.get(i);
				System.out.println(thisRecord);
				if ((!foundLoginAction) || (foundLoginAction && 
						(thisRecord.getData().equals(SUCCESS) || thisRecord.getData().equals(FAILURE)))) { //start finding a login success of login fail
					endTime = thisRecord.getTime();
					if (thisRecord.getData().equals(SUCCESS)) {
						type = thisRecord.getType();
						isGoodLogin = true;
						if (type.equals(Record.TEXT_TYPE)) {
							textAnalyzedRecord.increaseSuccessCount();
						} else { //image type
							imageAnalyzedRecord.increaseSuccessCount();
						}
					} else if (thisRecord.getData().equals(FAILURE)) {
						type = thisRecord.getType();
						isGoodLogin = false;
						if (type.equals(Record.TEXT_TYPE)) {
							textAnalyzedRecord.increaseFailCount();
						} else { //image type
							imageAnalyzedRecord.increaseFailCount();
						}
					} else {
						//we don't want other information, skip
						continue;
					}//endif .equal function for getting record we interested
					foundLoginAction = !foundLoginAction;
				} else { //endif !foundLoginAction //start finding a login 
					if (thisRecord.getData().equals("start") && type.equals(thisRecord.getType())) {
						Date startTime = thisRecord.getTime();
						int loginTime = (int) ((endTime.getTime() - startTime.getTime()) / 1000L);
						System.out.println("login time is " + loginTime);
						if(isGoodLogin && type.equals(Record.TEXT_TYPE)) {
							textAnalyzedRecord.addAverageSuccessTime(loginTime);
						} else if(isGoodLogin && type.equals(Record.IMAGE_TYPE)) {
							imageAnalyzedRecord.addAverageSuccessTime(loginTime);
						} else if(!isGoodLogin && type.equals(Record.TEXT_TYPE)) {
							textAnalyzedRecord.addAverageFailTime(loginTime);
						} else if(!isGoodLogin && type.equals(Record.IMAGE_TYPE)) {
							imageAnalyzedRecord.addAverageFailTime(loginTime);
						}
					} else {
						continue;
					}
					foundLoginAction = !foundLoginAction;
				} //end elseif !foundLoginAction
				
			} //end for loop
			System.out.println("here is the two result: ");
			System.out.println(imageAnalyzedRecord);
			System.out.println(textAnalyzedRecord);
			analyzedRecordByUserid.add(textAnalyzedRecord);
			analyzedRecordByUserid.add(imageAnalyzedRecord);
		});
		
		return analyzedRecordByUserid;
	}
	
	public ArrayList<Record> getRawRecords() {
		return rawRecords;
	}
}
