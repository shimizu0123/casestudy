import casestudy.Velocity;

public class Calc_Velocity {






	public static Velocity velocity(String data) {

		int S_EW;//東―西軸方向のどちらに向かって進むのか
		int S_NS;//北―南軸方向のどちらに向かって進むのか
		int S_Vr;//垂直方向の動き
		double V_EW=0;//東―西軸方向の速さ
		double V_NS=0;//北―南軸方向の速さ
		double Vel;//速さ
		double deg;//方位
		double Vr;//垂直方向の速さ

		S_EW = Integer.parseInt(data.substring(101,101+1), 2);
		if(S_EW==1){
			V_EW = (-1) * (Integer.parseInt(data.substring(102,102+10), 2) - 1);
		}
		if(S_EW==0){
			V_EW = Integer.parseInt(data.substring(102,102+10), 2) - 1;
		}

		S_NS = Integer.parseInt(data.substring(112,112+1), 2);
		if(S_NS==1){
			V_NS = (-1) * (Integer.parseInt(data.substring(102,102+10), 2) - 1);
		}
		if(S_NS==0){
			V_NS = Integer.parseInt(data.substring(102,102+10), 2) - 1;
		}

		Vel = Math.sqrt(Math.pow(V_EW,2.0) + Math.pow(V_NS, 2.0));

		deg = Math.atan(V_EW/V_NS) * 360.0 / (2 * Math.PI );

		System.out.println("Velocity = "+ Vel + "kn");
		System.out.println("deg = "+ deg + "deg");

		Vr = Integer.parseInt(data.substring(125,125+8), 2);

		S_Vr = Integer.parseInt(data.substring(124,124+1), 2);
		if(S_Vr==1){
			System.out.println("Down");
		}
		if(S_Vr==0){
			System.out.println("UP");
		}

		System.out.println(Vr + "kn");

		return new Velocity(S_Vr, Vel, deg, Vr);
	}
}
