package casestudy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PositionDAO {

	private  Connection con;

	public PositionDAO(Connection con) {
		this.con = con;
	}

	public DB_Item_PlanePosition insertposition(DB_Item_PlanePosition position) throws SQLException{
		String sql = "INSERT INTO position(modeS,  latitude, longitude, altitude, timestamp)"+
					"VALUES(?,?,?,?,systimestamp)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1,position.getModeSAddress());
			stmt.setDouble(2,position.getPlanePosition().getLon());
			stmt.setDouble(3,position.getPlanePosition().getLat());
			stmt.setDouble(4,position.getPlanePosition().getAlt());

			stmt.executeUpdate();//追加するinsert

		} finally {
			if (stmt != null){
				stmt.close();
			}
		}

		return position;
	}
}
