package casestudy;

public class Velocity {

	int S_Vr;//垂直方向の動き
	double Vel;//速さ
	double deg;//方位
	double Vr;//垂直方向の速さ

	public Velocity(int S_Vr, double Vel, double deg, double Vr){
		this.S_Vr = S_Vr;
		this.Vel = Vel;
		this.deg = deg;
		this.Vr = Vr;
	}

	public int getS_Vr() {
		return S_Vr;
	}

	public void setS_Vr(int s_Vr) {
		S_Vr = s_Vr;
	}

	public double getVel() {
		return Vel;
	}

	public void setVel(double vel) {
		Vel = vel;
	}

	public double getDeg() {
		return deg;
	}

	public void setDeg(double deg) {
		this.deg = deg;
	}

	public double getVr() {
		return Vr;
	}

	public void setVr(double vr) {
		Vr = vr;
	}

}
