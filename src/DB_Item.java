import java.util.Calendar;

import casestudy.PlanePosition;
import casestudy.Velocity;

public class DB_Item {

	enum State{
		CALL_SIGN{
			private String callSign;
			public void recordDB(/*接続先情報*/){

			}
		},
		PLANE_POSITION{
			private PlanePosition position;
		},
		VELOCITY{
			private Velocity velocity;
		};
		public abstract void recordDB(/*接続先情報*/);
	}

	State STATE;

	String modeS;
	Calendar cal;

	public DB_Item(String modeS){
		this.modeS = modeS;
		this.cal = Calendar.getInstance();
	}
	public DB_Item(String modeS,String callSign){
		this.modeS = modeS;
		this.cal = Calendar.getInstance();
		this.STATE.CALL_SIGN = State.CALL_SIGN;


	}

	public DB_Item(String modeS,PlanePosition position){
		this.modeS = modeS;
		this.cal = Calendar.getInstance();
		this.STATE = State.PLANE_POSITION;


	}

	public DB_Item(String modeS,Velocity velocity){
		this.modeS = modeS;
		this.cal = Calendar.getInstance();
		this.STATE.VELOCITY = velocity;


	}

}




