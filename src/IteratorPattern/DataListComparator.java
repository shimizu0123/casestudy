package IteratorPattern;

import java.util.Comparator;

public class DataListComparator implements Comparator<Data> {

    //比較メソッド（データクラスを比較して-1, 0, 1を返すように記述する）
    public int compare(Data a, Data b) {
        long no1 = a.getTimeStamp();
        long no2 = b.getTimeStamp();

        //昇順でソートされる
        if (no1 < no2) {
            return 1;

        } else if (no1 == no2) {
            return 0;

        } else {
            return -1;

        }
    }

}