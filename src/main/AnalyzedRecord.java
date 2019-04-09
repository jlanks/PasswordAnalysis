package main;
/**
 * the record that will be produced and used for thr group project
 * @author Charles Chen
 *
 */
public class AnalyzedRecord {
	private String userid;
	private String type;
	private int successLoginCount;
	private int failLoginCount;
	private int successLoginTime;
	private int failLoginTime;
	
	/**
	 * @param userid
	 * @param type
	 * @param successLoginCount
	 * @param failLoginCount
	 * @param successLoginTime
	 * @param failLoginTime
	 */
	public AnalyzedRecord(String userid, String type, int successLoginCount, int failLoginCount, int successLoginTime, int failLoginTime) {
		this.userid = userid;
		this.type = type;
		this.successLoginCount = successLoginCount;
		this.failLoginCount = failLoginCount;
		this.successLoginTime = successLoginTime;
		this.failLoginTime = failLoginTime;
	}
	
	

	/**
	 * empty constructor
	 */
	public AnalyzedRecord(String userid, String type) {
		this(userid, type, 0, 0, 0, 0);
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(userid).append(",")
		.append(type).append(",")
		.append(successLoginCount).append(",")
		.append(failLoginCount).append(",")
		.append(successLoginTime).append(",")
		.append(failLoginTime).append(",")
		.append(successLoginCount + failLoginCount).append(",")
		.append(successLoginCount / (double)(successLoginCount + failLoginCount)).append(",")
		.append(failLoginCount / (double)(successLoginCount + failLoginCount));
		return builder.toString();
	}
	
	public boolean isEmpty() {
		return successLoginCount == 0 && failLoginCount == 0 && successLoginTime == 0 && failLoginTime == 0;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the successLoginCount
	 */
	public int getSuccessLoginCount() {
		return successLoginCount;
	}

	/**
	 * @param successLoginCount the successLoginCount to set
	 */
	public void setSuccessLoginCount(int successLoginCount) {
		this.successLoginCount = successLoginCount;
	}

	/**
	 * @return the failLoginCount
	 */
	public int getFailLoginCount() {
		return failLoginCount;
	}

	/**
	 * @param failLoginCount the failLoginCount to set
	 */
	public void setFailLoginCount(int failLoginCount) {
		this.failLoginCount = failLoginCount;
	}

	/**
	 * @return the successLoginTime
	 */
	public int getSuccessLoginTime() {
		return successLoginTime;
	}

	/**
	 * @param successLoginTime the successLoginTime to set
	 */
	public void setSuccessLoginTime(int successLoginTime) {
		this.successLoginTime = successLoginTime;
	}

	/**
	 * @return the failLoginTime
	 */
	public int getFailLoginTime() {
		return failLoginTime;
	}

	/**
	 * @param failLoginTime the failLoginTime to set
	 */
	public void setFailLoginTime(int failLoginTime) {
		this.failLoginTime = failLoginTime;
	}


	public void increaseSuccessCount() {
		successLoginCount++;
	}
	
	public void increaseFailCount() {
		failLoginCount++;
	}

	public void addAverageSuccessTime(int loginTime) {
		successLoginTime = (int) Math.round(((successLoginTime * (successLoginCount - 1)) + loginTime) / (double) successLoginCount);
		
	}
	
	public void addAverageFailTime(int loginTime) {
		failLoginTime = (int) Math.round(((failLoginTime * (failLoginCount - 1)) + loginTime) / (double) failLoginCount);
		
	}
}
