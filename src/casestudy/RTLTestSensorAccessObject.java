package casestudy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RTLTestSensorAccessObject {

	private static final String ipAddress = "127.0.0.1";
	private static final int portNumber = 31001;

	/**
	 * @param args 不使用
	 */
	public static void main(String[] args) {

		//RTL1090からTCPで受信した生データ
		String rawReceiveData = null;
		//受信データ
		String receiveData = null;
		//処理された1件ごとのデータ
		String hexData = null;

		//生データについているヘッダー
		String header = "1a .. ff ff ff ff ff ff .. ";

		RTLSensorAccessObject testSOA = null;
		testSOA = new RTLSensorAccessObject(ipAddress, portNumber);

		//RTL1090と接続
		testSOA.connect();





		for(int i = 0; i < 100; ){

			//RTL1090からデータを受信
			rawReceiveData = testSOA.readSensor();

//			System.out.println("生データ");
//			System.out.println(rawReceiveData);

			receiveData = rawReceiveData;

			Pattern p = Pattern.compile(header);
			Matcher m = p.matcher(receiveData);

			while(m.find()){

				//最初の1件分の文字列部分からヘッダーを除く
				receiveData = replaceString(receiveData, header, "");

				//最初の1件分のヘッダーを除いた文字列をmatcherへ格納
				m = p.matcher(receiveData);
				m.reset();

				if(!m.find()) hexData = receiveData;
				else{
					hexData = receiveData.substring(0, m.start());
					receiveData = receiveData.substring(m.start());
				}
				m = p.matcher(receiveData);
				m.reset();

				//結果をコンソールに出力
//				System.out.println(hexData);

				if(hexData.length() == 42){
	//				System.out.println("10 02 01 00 00 00 00 " + hexData + "10 03 00 00 ");
					EvenAndOddMatcher.analyzeData("10 02 01 00 00 00 00 " + hexData + "10 03 00 00 ");
				}
			}

		}

		//RTL1090を切断
		testSOA.close();

	}


	/**
	 * 文字列内の指定文字列を別の文字列に置き換える。
	 */
	public static String replaceString(String strSrc, String strPattern, String strReplace) {
	    Pattern pattern = Pattern.compile(strPattern);
	    Matcher matcher = pattern.matcher(strSrc);
	    String strTmp = matcher.replaceFirst(strReplace);
	    return strTmp;
	}

}
