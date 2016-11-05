package casestudyVerText;


public class TestSensorAccessObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		SensorAccessObject testSOA = null;
		testSOA = new SensorAccessObject("192.168.3.171",10001);

		testSOA.connect();
		for(int i=0;i < 100; i++){
			String hex = testSOA.readSensor();
			System.out.println(hex);
		}

		testSOA.close();


	}

}
