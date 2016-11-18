package casestudy;

public class RealDataTest {

	final static String ipAddress = "192.168.3.171";
	final static int portNum = 10001;

	public static void main(String[] args) {
		/*
		 * SBS-3と接続
		 */
		SensorAccessObject testSOA = null;
		testSOA = new SensorAccessObject(ipAddress, portNum);
		testSOA.connect();

		/*
		 * 10000行分のデータを受信、解析する
		 */
		for(int i = 0;i < 10000; i++){
			String hex = testSOA.readSensor();
			EvenAndOddMatcher.analyzeData(hex);
		}

		System.out.println("!終了！");

		testSOA.close();
	}
}
