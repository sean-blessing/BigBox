package bigbox.db;

import bigbox.business.Division;
import bigbox.business.Store;

public interface BigBoxWriter {
	boolean addStore(Store s);
	boolean addDivision(String d);
}
