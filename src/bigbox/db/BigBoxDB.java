package bigbox.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteOpenMode;

import bigbox.business.Division;
import bigbox.business.Store;

public class BigBoxDB implements BigBoxDAO {
	private HashMap<Integer,Division> divisionIdMap = new HashMap<>();
	
    private Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:sqlite:C:\\DB\\SQLite\\big_box.sqlite";
        Connection connection = DriverManager.getConnection(dbUrl);
        return connection;
    }
	public BigBoxDB() {
        testSQL();
        populateDivisionMap();
	}
	@Override
	public ArrayList<Store> listAllStores() {
		ArrayList<Store> stores = new ArrayList<>();
		String stmt = "SELECT * from Stores";
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(stmt);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt(1);
				int divId = rs.getInt(2);
				Division d = divisionIdMap.get(divId);
				String storeNbr = rs.getString(3);
				double sales = rs.getDouble(4);
				String name = rs.getString(5);
				String address = rs.getString(6);
				String city = rs.getString(7);
				String state = rs.getString(8);
				String zip = rs.getString(9);
				Store s = new Store(id,d,storeNbr,sales,name,address,city,state,zip);
				stores.add(s);
			}
		}
		catch (SQLException se) {
			System.err.println("BigBoxDB:  Error getting all stores!");
			se.printStackTrace();
			return null;
		}
		
		return stores;
	}

	private void populateDivisionMap() {
		String stmt = "SELECT * from Divisions";
		try (Connection connection = getConnection();
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery(stmt)) {
			while (rs.next()) {
				int id = rs.getInt(1);
				String divNbr = rs.getString(2);
				String name = rs.getString(3);
				String address = rs.getString(4);
				String city = rs.getString(5);
				String state = rs.getString(6);
				String zip = rs.getString(7);
				Division d = new Division(id,divNbr,name,address,city,state,zip);
				divisionIdMap.put(id, d);
			}
		}
		catch (SQLException se) {
			System.err.println("BigBoxDB:  Error populating division map!");
			se.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Store> listAllStores(String divNbr) {
		ArrayList<Store> stores = new ArrayList<>();
		String stmt = "select * from stores where divisionID in "+
						"(select id from divisions where divisionNumber = ?)";
		try (Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(stmt)) {
			ps.setString(1, divNbr);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				int divId = rs.getInt(2);
				Division d = divisionIdMap.get(divId);
				String storeNbr = rs.getString(3);
				double sales = rs.getDouble(4);
				String name = rs.getString(5);
				String address = rs.getString(6);
				String city = rs.getString(7);
				String state = rs.getString(8);
				String zip = rs.getString(9);
				Store s = new Store(id,d,storeNbr,sales,name,address,city,state,zip);
				stores.add(s);
			}
		}
		catch (SQLException se) {
			System.err.println("BigBoxDB:  Error getting all stores!");
			se.printStackTrace();
			return null;
		}
		
		return stores;
	}

	@Override
	public double getSalesSummary() {
		double sum = 0.0;
		String stmt = "select sum(sales) from stores";
		try (Connection connection = getConnection();
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery(stmt)) {
			if (rs.next()) {
				sum = rs.getDouble(1);
			}
		}
		catch (SQLException se) {
			System.err.println("BigBoxDB:  Error in getSalesSummary()!");
			se.printStackTrace();
		}
		return sum;
	}

	@Override
	public double getSalesSummary(String d) {
		double sum = 0.0;
		// SQLite limitation will now allow a sum function on a join, so 
		// calling all records for a division then sum.
		ArrayList<Store> divStores = listAllStores(d);
		for (Store s:divStores) {
			sum+=s.getSales();
		}
		return sum;
	}

	@Override
	public boolean addStore(Store s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addDivision(String d) {
		// TODO Auto-generated method stub
		return false;
	}

	public void testSQL() {
		String stmt = "SELECT date('now')";
		try (Connection connection = getConnection();
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery(stmt)) {
			while (rs.next()) {
				String testStr = rs.getString(1);
				System.out.println("result from test Select date('now')="+testStr);
			}
		}
		catch (SQLException se) {
			System.err.println("BigBoxDB:  Error populating division map!");
			se.printStackTrace();
		}
	}
}
