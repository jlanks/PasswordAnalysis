package main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author 11134
 *
 */
public class Test {


	public static void main(String[] args) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-07-05 14:37:11");
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
