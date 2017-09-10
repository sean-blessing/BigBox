package bigbox.db;

public class BigBoxFactory {
	public static BigBoxDAO getBigBoxDAO() {
		BigBoxDAO dao = new BigBoxArray();
		return dao;
	}
}
