package bigbox.presentation;

import java.util.ArrayList;

import bigbox.business.Store;
import bigbox.db.BigBoxDAO;
import bigbox.db.BigBoxFactory;
import bigbox.util.Console;
import bigbox.util.Formatter;

public class BigBoxApp {
	private static BigBoxDAO dao;

	public static void main(String[] args) {
		Console.displayLine("Welcome to the BigBoxApp! - Array DAO Version");
		dao = BigBoxFactory.getBigBoxDAO();
		String command = "";
		while (!command.equalsIgnoreCase("exit")) {
			Console.displayLine();
			Console.displayLine("COMMAND MENU");
			Console.displayLine("list      - List All Stores");
			Console.displayLine("array     - Array of stores");
			Console.displayLine("div       - List All Stores for a Division");
			Console.displayLine("sales     - Sales Summary All Stores");
			Console.displayLine("divsales  - Sales Summary for a Division");
			Console.displayLine("exit      - Exit the Application");
			Console.displayLine();
			String displayString = "";
			command = Console.getString("Enter command:  ");
		
			if (command!=null) {
				if (command.equalsIgnoreCase("list")) {
					displayString = listAllStores();
					Console.displayLine(displayString);
//				}
//				else if (command.equalsIgnoreCase("array")) {
//						displayString = listAllStoresArray();
//						Console.displayLine(displayString);
				} else if (command.equalsIgnoreCase("div")) {
					String divNbr = Console.getStringNbr("Enter division #:  ");
					displayString = listAllStores(divNbr);
					Console.displayLine(displayString);
				} else if (command.equalsIgnoreCase("add")) {
					Console.displayLine("Not yet implemented");
				} else if (command.equalsIgnoreCase("sales")) {
						String formattedSales = Formatter.getFormattedDouble(dao.getSalesSummary());
						Console.displayLine("Sales summary:  "+formattedSales);
				} else if (command.equalsIgnoreCase("divsales")) {
					String divNbr = Console.getStringNbr("Div #:  ");
					String formattedSales = Formatter.getFormattedDouble(dao.getSalesSummary(divNbr));
					Console.displayLine("Sales summary for Division "+divNbr+":  "+formattedSales);
				} else if (command.equalsIgnoreCase("exit")) {
					Console.displayLine("Bye");
				} else {
					Console.displayLine("Invalid command.  Please try again");
				}
			}
		}		
	}
	
	private static String listAllStores() {
		String str = "";
		ArrayList<Store> stores = dao.listAllStores();
		for (Store s: stores) {
			str+=s.toString()+"\n";
		}
		return str;
	}

//	private static String listAllStoresArray() {
//		String str = "";
//		ArrayList<Store> stores = dao.listAllStores();
//		String[][] storesArray = new String[stores.length][9];
//		for (int i=0; i<stores.length;i++) {
//			storesArray[i] = stores[i].toStringArray();
//		}
//		System.out.println(storesArray);
//		return str;
//	}

	private static String listAllStores(String d) {
		String str = "";
		ArrayList<Store> stores = dao.listAllStores(d);
		for (Store s: stores) {
			str+=s.toString()+"\n";
		}
		return str;
	}

}
