package casestudy;

public class Velocity {

	private int s_Vr_;//垂直方向の動き
	private double vr_;//垂直方向の速さ

	private int deg_;//方位0～360
	private double vel_;//速さ

	//コンストラクタ
	Velocity(int S_Vr, double Vel, double deg, double Vr){
		this.s_Vr_ = S_Vr;
		this.vel_ = Vel;
		this.deg_ = (int)deg;
		this.vr_ = Vr;
	}

	public int getS_Vr() {
		return s_Vr_;
	}

	public double getVel() {
		return vel_;
	}

	public double getDeg() {
		return deg_;
	}

	public double getVr() {
		return vr_;
	}

}