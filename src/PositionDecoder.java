public class PositionDecoder {


	public static String position(String data, int TC){

		int NZ = 15;

		double Lat_CPR_E;
		double Lat_CPR_O;




		//EVENフレームの場合
		if((9 <= TC && TC <= 18) && data.substring(108,108+1) == "0"){

			data_E_list(data);
			for(Data_Even data:list){
				
			}
				
			/*
			 * arraylist：リスト_データ_EVENにdata、（タイムスタンプ？）、ModeSアドレスを格納
			 */


			return null;
		}

		//ODDフレームの場合
		if((9 <= TC && TC <= 18) && data.substring(108,108+1) == "1"){

			/*
			 * リスト_データ_EVENを参照し、同じModeSアドレスのデータを探す
			 * （データが有った場合）→処理を続行
			 * （データがなかった場合）→「破棄」
			 */

			Lat_CPR_E = (double)Integer.parseInt(data.substring(109, 109 + 17), 2) / 131072.0;
			Lat_CPR_O = (double)Integer.parseInt(data.substring(109, 109 + 17), 2) / 131072.0;


			int j = floor(59.0 * Lat_CPR_E - 60.0 * Lat_CPR_O + 0.5);

			double DLat_E = (double)360 / (4 * NZ);
			double DLat_O = (double)360 / (4 * (NZ - 1));

			double Lat_E = DLat_E * (mod(j, 60) + Lat_CPR_E);
			double Lat_O = DLat_O * (mod(j, 59) + Lat_CPR_O);

			if(Lat_E >= 270) Lat_E -= 360;
			if(Lat_O >= 270) Lat_O -= 360;

			System.out.println("Lat_E = " + Lat_E);
			System.out.println("Lat_O = " + Lat_O);

		}

		return "position";

	}

	//mod(x,y)の値を返す
	static double mod(double x ,double y){
		return (x - y * floor(x/y));
	}

	//floor(x)の値を返す
	static int floor(double x){
		return (int)Math.floor(x);
	}
	

}
