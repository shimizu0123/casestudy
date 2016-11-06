package casestudy;

public class MainTest {

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
		 * 100行分のデータを受信、解析する
		 */
		for(int i = 0;i < 1000; i++){
			String hex = testSOA.readSensor();
			StringBuilder sb = new StringBuilder();
			System.out.println("*** hexの長さ ***" + hex.length());
			if(hex.length() >= 75){
				for(int j = 0; j < hex.length(); j++){
					int ch = hex.charAt(j);
					if((48 <= ch && ch <= 57) || (97 <= ch && ch <= 102)){
						String str = String.valueOf((char)ch);
						String binary = Integer.toBinaryString(Integer.parseInt(str, 16));
						binary = String.format("%04d", Integer.parseInt(binary));
						sb.append(binary);
					}
				}
				String data = sb.toString();
				sb.delete(0, sb.length());

				System.out.println("受信データ(hex)");
				System.out.println(hex);
				System.out.println("受信データ(bin)");
				System.out.println(data);

				/*
				 * ADS_B_Analystで解析
				 */
				ADS_B_Analyst.analyzeData(data);
			}
		}

		testSOA.close();
	}
}
