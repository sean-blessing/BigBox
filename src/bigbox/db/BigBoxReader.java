package bigbox.db;

import java.util.ArrayList;

import bigbox.business.Division;
import bigbox.business.Store;

public interface BigBoxReader {
	ArrayList<Store> listAllStores();
	ArrayList<Store> listAllStores(String d);
	double getSalesSummary();
	double getSalesSummary(String d);
}
