package IteratorPattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConcreteAggregate implements Aggregate {

    private List<Data> dataList = new ArrayList<Data>();

    public void addData(Data data){
        dataList.add(data);
    }

    public void sortDataList(){
    	Collections.sort(dataList, new DataListComparator());
    }

    public int getSize(){
        return dataList.size();
    }

    public Data getData(int index){
        return (Data)dataList.get(index);
    }

    @Override
	public Iterator iterator() {
		return new ConcreteIterator(this);
	}

}
