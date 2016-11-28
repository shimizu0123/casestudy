package casestudy;

public class RealData extends Thread{
	final static String ipAddress = "192.168.3.171";
	final static int portNum = 10001;

	public void run(){
		SensorAccessObject testSOA = new SensorAccessObject(ipAddress, portNum);

		/*
		 * SBS-3と接続
		 */
		testSOA.connect();

		/*
		 * 500行分のデータを受信、解析する
		 */
		try{

			AircraftSerch kurachan = new AircraftSerch();

			kurachan.start();
			for(int i = 0; i < 4000; i++){
				String hex = testSOA.readSensor();
				EvenAndOddMatcher.analyzeData(hex);
			}
			//kurachan.run();


		}finally{

			/*
			 * SBS-3との接続を切断
			 */
			testSOA.close();
			System.out.println("*** 終了 ***");
		}
	}
	@Override
	public void start(){
		super.start();
	}


}
