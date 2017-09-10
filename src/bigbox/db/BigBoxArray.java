package bigbox.db;

import bigbox.business.Division;
import bigbox.business.Store;

public class BigBoxArray implements BigBoxDAO {
	private Division[] divisions = null;
	private Store[] stores = null;
	
	public BigBoxArray() {
		setupArrays();
	}
	
	private void setupArrays() {
		divisions = new Division[2];
		divisions[0] = new Division(1,"001","Cincinnati Division", 
				"1111 Division St.", "Cincinnati", "OH", "45555");
		divisions[1] = new Division(2,"111","Louisville Division", 
				"1222 Washtington St.", "Louisville", "KY", "40205");
		stores = new Store[6];
		stores[0] = new Store(1,divisions[0],"00011",25000,"Mason BigBox","5711 Fields Ertel Rd.", "Mason", "OH","45249");
		stores[1] = new Store(2,divisions[0],"00255",27500,"Downtown BigBox","9330 Main St.", "Cincinnati", "OH","45202");
		stores[2] = new Store(3,divisions[0],"00172",32555.55,"Goshen BigBox","6777 Goshen Rd.", "Goshen", "OH","45122");
		stores[3] = new Store(4,divisions[0],"00075",21425.37,"Bridgetown BigBox","3888 Race Rd.", "Cincinnati", "OH","45211");
		stores[4] = new Store(5,divisions[1],"00001",14432.77,"Louisville BigBox","1111 Washington St.", "Louisville", "KY","40206");
		stores[5] = new Store(6,divisions[1],"00044",17555.11,"Lawrenceburg BigBox","8000 Liberty St.", "Louisville", "KY","40204");
	}
	
	@Override
	public boolean addStore(Store s) {
		if (s.getId()==-1) { // the id needs to be determined
			s.setId(getNextId());
		}
		boolean success = true;
		System.out.println("addStore(Store s) method not yet defined");
		return success;
	}

	private int getNextId() {
		int maxID = 1;
		for (Store s: stores) {
			maxID = Math.max(maxID, s.getId());
		}
		// once max id is determined, add 1 to it for new facID assignment
		return maxID+1;
	}

	@Override
	public Store[] listAllStores() {
		return stores;
	}

	@Override
	public Store[] listAllStores(String d) {
		int count = 0;
		for (Store s: stores) {
			if (s.getDivisionNumber().equalsIgnoreCase(d))
				count++;
		}
		Store[] storesInDivision = new Store[count];
		int idx = 0;
		for (int i=0;i<stores.length;i++) {
			if (stores[i].getDivisionNumber().equalsIgnoreCase(d)) {
				storesInDivision[idx] = stores[i];
				idx++;
			}
		}
		
		return storesInDivision;
	}

	@Override
	public double getSalesSummary() {
		double sum = 0.0;
		for (Store s: stores) {
			sum+=s.getSales();
		}
		return sum;
	}

	@Override
	public double getSalesSummary(String d) {
		double sum = 0.0;
		for (Store s: stores) {
			if (s.getDivisionNumber().equalsIgnoreCase(d))
				sum+=s.getSales();
		}
		return sum;
	}

	@Override
	public boolean addDivision(String d) {
		System.out.println("addDivision(Division d) method not yet implemented");
		return true;
	}
}
