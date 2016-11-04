package casestudy;

public class MainTest {

	public static void main(String[] args) {

		SensorAccessObject testSOA = null;
		testSOA = new SensorAccessObject("192.168.3.171",10001);
		testSOA.connect();

		//100行出力する
		for(int i=0;i < 100; i++){
			String hex = testSOA.readSensor();
			ADS_B_Analyst test = new ADS_B_Analyst();


			//System.out.println(hex);
		}

		testSOA.close();



		// TODO 自動生成されたメソッド・スタブ
		ADS_B_Analyst test = new ADS_B_Analyst();
		test.read_Data();
	}

}
