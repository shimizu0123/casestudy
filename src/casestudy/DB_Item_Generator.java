package casestudy;

public class DB_Item_Generator {

	public static void dB_Item_CallSign_Generate(String modeS, String callSign){
		DB_Item_CallSign db_Item_CallSign = new DB_Item_CallSign(modeS, callSign);

		/**
		 * テスト用
		 */
		System.out.println("** Generated dB_Item_CallSign **");
		System.out.println(db_Item_CallSign.getModeSAddress());
		System.out.println(db_Item_CallSign.getCallSign());

	}

	public static void dB_Item_Velocity_Generate(String modeS, Velocity velocity){
		DB_Item_Velocity db_Item_Velocity = new DB_Item_Velocity(modeS, velocity);

		/**
		 * テスト用
		 */
		System.out.println("** Generated dB_Item_Velocity **");
		System.out.println(db_Item_Velocity.getModeSAddress());
		System.out.println(db_Item_Velocity.getVelocity().getS_Vr());
		System.out.println(db_Item_Velocity.getVelocity().getVr());
		System.out.println(db_Item_Velocity.getVelocity().getDeg());
		System.out.println(db_Item_Velocity.getVelocity().getVel());

	}

	public static void dB_Item_PlanePosition_Generate(String modeS, PlanePosition planePosition){
		DB_Item_PlanePosition db_Item_PlanePosition = new DB_Item_PlanePosition(modeS, planePosition);

		/**
		 * テスト用
		 */
		System.out.println("** Generated dB_Item_PlanePosition **");
		System.out.println(db_Item_PlanePosition.getModeSAddress());
		System.out.println(db_Item_PlanePosition.getPlanePosition().getAlt());
		System.out.println(db_Item_PlanePosition.getPlanePosition().getLat());
		System.out.println(db_Item_PlanePosition.getPlanePosition().getLon());

	}

}
