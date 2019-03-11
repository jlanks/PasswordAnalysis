import java.util.Date;
/**
 * the record that will be produced and used for thr group project
 * @author Charles Chen
 *
 */
public class AnalizedRecord {
	private String userid;
	private String type;
	private int successLoginCount;
	private int failLoginCount;
	private Date successLoginTime;
	private Date failLoginTime;
	
	/**
	 * @param userid
	 * @param type
	 * @param successLoginCount
	 * @param failLoginCount
	 * @param successLoginTime
	 * @param failLoginTime
	 */
	public AnalizedRecord(String userid, String type, int successLoginCount, int failLoginCount, Date successLoginTime, Date failLoginTime) {
		this.userid = userid;
		this.type = type;
		this.successLoginCount = successLoginCount;
		this.failLoginCount = failLoginCount;
		this.successLoginTime = successLoginTime;
		this.failLoginTime = failLoginTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnalizedRecord [userid=").append(userid).append(", type=").append(type).append(", successLoginCount=").append(successLoginCount).append(", failLoginCount=").append(failLoginCount).append(", successLoginTime=").append(successLoginTime).append(", failLoginTime=").append(failLoginTime).append("]");
		return builder.toString();
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
	public Date getSuccessLoginTime() {
		return successLoginTime;
	}

	/**
	 * @param successLoginTime the successLoginTime to set
	 */
	public void setSuccessLoginTime(Date successLoginTime) {
		this.successLoginTime = successLoginTime;
	}

	/**
	 * @return the failLoginTime
	 */
	public Date getFailLoginTime() {
		return failLoginTime;
	}

	/**
	 * @param failLoginTime the failLoginTime to set
	 */
	public void setFailLoginTime(Date failLoginTime) {
		this.failLoginTime = failLoginTime;
	}
}
