import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Main {
	File image21, text21;
	File outputFile;
	
	public Main() {
		image21 = new File(new File("./files"), "imagept21.csv");
		text21 = new File(new File("./files"), "text21.csv");
		outputFile = new File(new File("./files"), "merged.csv");
		try {
			outputFile.createNewFile();
		} catch (IOException e) {
			System.err.println("can't create output file");
		}
	}
	
	public void doJob() {
		//step 1: read two files and get two big giant strings
		String image21Strings = "", text21String = "";
		try {
			image21Strings = Files.readString(Path.of(image21.getAbsolutePath()));
			text21String = Files.readString(Path.of(text21.getAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//step 2: break them down into 
		System.out.println(image21Strings);
		System.out.println(text21String);
		
		try {
			Files.writeString(Path.of(outputFile.getAbsolutePath()), image21Strings);
			Files.writeString(Path.of(outputFile.getAbsolutePath()), text21String, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		var main = new Main();
		main.doJob();
	}

}
