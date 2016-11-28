package casestudy;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * CALLSIGNテーブルのDAO
 */
public class CallSignDAO {

	private  Connection con;

	public CallSignDAO(Connection con) {
		this.con = con;
	}

	/**
	 * DBにデータを格納する
	 * @param callsign DB_Item_CallSignオブジェクト
	 */
	public void insertCallSign(DB_Item_CallSign callsign) throws SQLException{

		String sql = 	"INSERT INTO callsign(modes, callsign, timestamp)"
						+ "VALUES(?,?,systimestamp)";

		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement(sql);

			stmt.setString(1,callsign.getModeSAddress());
			stmt.setString(2,callsign.getCallSign());

			//追加するinsert
			stmt.executeUpdate();

		} finally {
			if (stmt != null){
				stmt.close();
			}
		}

	}

}
