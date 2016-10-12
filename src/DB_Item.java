import java.util.Calendar;

public class DB_Item {

	String modeS;
	String callSign;
	Position_Dec position;
	Calc_Velocity velocity;
	Calendar cal;

	DB_Item(String modeS,String callSign,Position_Dec position,Calc_Velocity velocity){
		this.modeS = modeS;
		this.callSign = callSign;
		this.position = position;
		this.cal = Calendar.getInstance();
	}

}



