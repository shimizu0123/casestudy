package casestudy;

import static casestudy.LatLonAltAnalyzer.*;

import java.util.ArrayList;
import java.util.Collections;

public class PlanePositionFactory {
	static final long DELETE_TIME = 1000 * 10;//削除のしきい値(ms)
	static ArrayList<Data> evenDataList = new ArrayList<Data>();
	static ArrayList<Data> oddDataList = new ArrayList<Data>();

	static public PlanePosition rawDataToPlanePosition(String rawData){
		ArrayList<Data>addDataList;
		ArrayList<Data>pairDataList;

		if(judgeEven(rawData)){
			addDataList		= evenDataList;
			pairDataList	= oddDataList;
		}else{
			addDataList		= oddDataList;
			pairDataList	= evenDataList;
		}

		listAdd(rawData,addDataList);
		oldListDelete(addDataList);

		return planePositonCreator(rawData,pairDataList);
	}
	public static ArrayList<Data> getEvenDataList() {
		return evenDataList;
	}
	public static ArrayList<Data> getOddDataList() {
		return oddDataList;
	}
	/**
	 * rawDataを対象Listに追加し、整理する
	 * @param rawData
	 */
	public static int listAdd(String rawData,ArrayList<Data> addDataList){

		addDataList.add(new Data(rawData));
		Collections.sort(addDataList, new DataListComparator());

		return addDataList.size();
	}

	private static int  oldListDelete(ArrayList<Data> dataList) {
		long now = System.currentTimeMillis();//現在時刻を取得
		for(int i = dataList.size() - 1; i >= 0; i--){
			if((now - dataList.get(i).getTimeStamp()) >=  DELETE_TIME) dataList.remove(i);
		}
		return dataList.size();
	}

	/**
	 * 位置情報のrawDataと対になるデータを
	 * evenDataList又はOddDataListから探し、
	 * PlanePostionを返す。なければ、null値を返す
	 * @param rawData
	 * @returnb PlanePositon 航空機の緯度経度高度情報
	 */
	public static PlanePosition planePositonCreator(String rawData,ArrayList<Data> pairDataList) {

		String listData;
		PlanePosition planePosition = null;


		//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
		for(Data pairData : pairDataList){
			if(pairData.timeAndModeSEquals(rawData)){
				listData = pairData.getData();
				if(judgeEven(rawData)){
					planePosition = calc_Position(rawData, listData, true);
				}else{
					planePosition = calc_Position(listData, rawData, false);
				}

				break;
			}
		}
		return planePosition;

	}
	private static boolean judgeOdd(String rawData) {
		return Integer.parseInt(rawData.substring(109, 109+1), 2) == 1;
	}

	private static boolean judgeEven(String rawData) {
		return !(judgeOdd(rawData));
	}
}
