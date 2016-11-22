package casestudy;

public class RealDataTest {

	final static String ipAddress = "192.168.3.171";
	final static int portNum = 10001;

	public static void main(String[] args) {

		SensorAccessObject testSOA = new SensorAccessObject(ipAddress, portNum);

		/*
		 * SBS-3と接続
		 */
		testSOA.connect();

		/*
		 * 指定した行数分のデータを受信、解析する
		 */
		try{
			for(int i = 0; i < 500; i++){
				String hex = testSOA.readSensor();
				EvenAndOddMatcher.analyzeData(hex);
			}
		}finally{

			/*
			 * SBS-3との接続を切断
			 */
			testSOA.close();
			System.out.println("*** 終了 ***");
		}

	}

}
