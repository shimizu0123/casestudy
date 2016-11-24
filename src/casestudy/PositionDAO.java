package casestudy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionDAO {

	private  Connection con;

	public PositionDAO(Connection con) {
		this.con = con;
	}

	public Position[] findposi() throws SQLException {
		String sql = "select modes,latitude,longitude,altitude from position where timestamp in (select max(timestamp) from position group by modes)  and  extract(second from systimestamp)-extract(second from timestamp)  < 10"
				+"and  extract(year from systimestamp)-extract(year from timestamp)=0"
				+ "and  extract(month from systimestamp)-extract(month from timestamp)=0"
				+"and  extract(day from systimestamp)-extract(day from timestamp)=0"
				+"and  extract(hour from systimestamp)-extract(hour from timestamp)=0"
				+"and  extract(minute from systimestamp)-extract(minute from timestamp)=0";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Position posi[] = new Position[100];


		try{
			stmt = con.prepareStatement(sql);
			//stmt.setString(1,modes);
			res = stmt.executeQuery();//sqlをとってくる
			int i=0;
			while(res.next()){



				posi[i] = new Position(//列の名前を書く.その列のデータをとってきてくれる
						res.getString("modes"),
						res.getFloat("latitude"),
						res.getFloat("longitude"),
						res.getFloat("altitude")
						);
				i++;

			}


		}finally{//絶対行う
			if(res != null){
				res.close();
			}
			if(stmt != null){
				stmt.close();

			}

		}
		return posi;
	}


	public DB_Item_PlanePosition insertposition(DB_Item_PlanePosition position) throws SQLException{
		String sql = "INSERT INTO position(modeS,  latitude, longitude, altitude, timestamp)"+
					"VALUES(?,?,?,?,systimestamp)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1,position.getModeSAddress());
			stmt.setDouble(2,position.getPlanePosition().getLat());
			stmt.setDouble(3,position.getPlanePosition().getLon());
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
