package casestudy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestDataRead {

	public static String fileReadLine(String filePath,int lineNo) {
		StringBuffer sb = new StringBuffer();
		FileReader fr = null;
		BufferedReader br = null;
		int nowLine = 1;
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if(nowLine == lineNo)sb.append(line);
				nowLine++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String fileReadLine(int lineNo) {
		return fileReadLine("test1000.txt",lineNo);
	}

}
