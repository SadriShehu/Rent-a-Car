package frames;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	// objekti per lidhje
	static Connection conn = null;
	// objekti per vendosje te rezultatit
	static ResultSet res = null;
	// objekti per query
	static PreparedStatement pst = null;
	
	// objeti per thirrjen e store procedurave
	static CallableStatement cStmt = null;
		
	public static String perdoruesiDB, fjalekalimiDB, eshteAktiv, emriDB, mbiemriDB, salt;
	public static int idDB;
	
	public static void searchDB(String perdoruesi) {
		try {
			String sql = "select * from llogarite where perdoruesi='" + perdoruesi + "'";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			
			while(res.next()){
				idDB = res.getInt("id");
				perdoruesiDB = res.getString("perdoruesi");
				fjalekalimiDB = res.getString("fjalekalimi");
				salt = res.getString("salti");
				eshteAktiv = res.getString("eshteAktiv");
				emriDB = res.getString("emri");
				mbiemriDB = res.getString("mbiemri");
	        }
			pst.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
	}
	public static void executeQueryDB(String sql) throws SQLException {
		pst = conn.prepareStatement(sql);
		pst.execute();
	}
	public static void executeQueryWithResult(String sql) throws SQLException {
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();		
	}
	
}