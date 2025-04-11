
package com.icedemo.www.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */
public enum DataTables {

	Location(1, "location", new String[] {"city","county","zipcode","state","country"}),
	UNKNOWN(DataTables.unknown, "Unknown", null);

	public static final int unknown = 99999;

	private DataTables(int code, String name, String[] columns) {
		_code = code;
		_name = name;
		_columns = columns;
	}

	private int _code;
	private String _name;
	private String[] _columns;

	public int getCode() {
		return _code;
	}

	public void setCode(int code) {
		_code = code;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}
	
	public String[] getColumns() {
		return _columns;
	}

	public void setColumns(String[] columns) {
		_columns = columns;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static DataTables get(int id) {
		DataTables types[] = DataTables.values();
		for (DataTables type : types) {
			int i = type.getCode();
			if (i == id) {
				return type;
			}
		}
		return DataTables.UNKNOWN;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static DataTables get(String name) {
		DataTables types[] = DataTables.values();
		for (DataTables type : types) {
			String n = type.getName();
			if (n.equals(name)) {
				return type;
			}
		}
		return DataTables.UNKNOWN;
	}
	
	public static ArrayList<HashMap<String,String>> getColumns(DataTables d) {
		String[] cols = d.getColumns();
		ArrayList<HashMap<String,String>> l = new ArrayList<HashMap<String,String>>();
		for(String col : cols) {
			HashMap<String,String> h = new HashMap<String,String>();
			h.put("data", col);
			l.add(h);
		}
		return l;
	}

	public static List<String> getAllValidEnums() {
		List<String> getAll = new ArrayList<String>();
		DataTables types[] = DataTables.values();
		for (DataTables type : types) {
			if (type.getCode() == unknown) {
				continue;
			}
			getAll.add(type.getName());
		}
		return getAll;
	}

	public static List<String> getAllEnums() {
		List<String> getAll = new ArrayList<String>();
		DataTables types[] = DataTables.values();
		for (DataTables type : types) {
			getAll.add(type.getName());
		}
		return getAll;
	}

	public String toString() {
		StringBuilder b = new StringBuilder("[DataTables={");
		b.append("code=").append(_code).append(";");
		b.append("name=").append(_name).append(";");
		b.append("}]");
		return b.toString();
	}



}
