package casestudy;

public class UsedTestDataTest {


	public static void main(String[] args) {
		/*
		 * 867行分のデータを受信、解析する
		 */
		for(int i = 1;i < 867; i++){
			String hex = TestDataRead.fileReadLine(i);
			if(hex.length() >= 75){

				EvenAndOddMatcher.analyzeData(hex);
			}
		}
		System.out.println("終了しました");
	}
}
