package bigbox.db;

import bigbox.business.Division;
import bigbox.business.Store;

public interface BigBoxReader {
	Store[] listAllStores();
	Store[] listAllStores(String d);
	double getSalesSummary();
	double getSalesSummary(String d);
}
