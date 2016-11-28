package casestudy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * VELOCITYテーブルのDAO
 */
public class VelocityDAO {

	private  Connection con;

	public VelocityDAO(Connection con) {
		this.con = con;
	}

	/**
	 * DBにデータを格納する
	 * @param velocity DB_Item_Velocityオブジェクト
	 */
	public void insertvelocity(DB_Item_Velocity velocity) throws SQLException{

		String sql = 	"INSERT INTO velocity(modes, H_velocity, V_velocity, H_direction, V_direction, timestamp)"
						+ "VALUES(?,?,?,?,?,systimestamp)";

		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement(sql);

			stmt.setString(1,velocity.getModeSAddress());
			stmt.setDouble(2,velocity.getVelocity().getVel());
			stmt.setDouble(3,velocity.getVelocity().getVr());
			stmt.setInt(4,velocity.getVelocity().getDeg());
			stmt.setInt(5,velocity.getVelocity().getS_Vr());
//			stmt.setLong(6,velocity.getTimeStamp());

			//追加するinsert
			stmt.executeUpdate();

		} finally {
			if (stmt != null){
				stmt.close();
			}
		}

	}

}
