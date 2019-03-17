package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
/**
 * the record that read from the .csv file, each line is read into one instance of this class
 * @author Charles Chen
 *
 */
public class Record {
	public static final String IMAGE_TYPE = "Image21", TEXT_TYPE = "Text21";
	
	private String type;		//either image type or text type password record
	
	private Date time;			//TODO: not sure can Data do arithmetic calculation, it would help a lot if it can
	private String userid;
	private String site;
	private String scheme;
	private String mode;
	private String event;
	private String data;
	
	
	/**
	 * @param type
	 * @param time
	 * @param userid
	 * @param site
	 * @param scheme
	 * @param mode
	 * @param event
	 * @param data
	 */
	
	public Record() {
		
	}
	public Record(String type, String time, String userid, String site, String scheme, String mode, String event, String data) {
		this.type = type;
		try {
			this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
		} catch (ParseException e) {
			Logger.getGlobal().warning("can't prase time " + time);
		}
		this.userid = userid;
		this.site = site;
		this.scheme = scheme;
		this.mode = mode;
		this.event = event;
		this.data = data;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Record [type=").append(type)
		.append(", time=").append(time)
		.append(", userid=").append(userid)
		.append(", site=").append(site)
		.append(", scheme=").append(scheme)
		.append(", mode=").append(mode)
		.append(", event=").append(event)
		.append(", data=").append(data)
		.append("]");
		return builder.toString();
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
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}


	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
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
	 * @return the site
	 */
	public String getSite() {
		return site;
	}


	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}


	/**
	 * @return the scheme
	 */
	public String getScheme() {
		return scheme;
	}


	/**
	 * @param scheme the scheme to set
	 */
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}


	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}


	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}


	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}


	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}


	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	
	
}
