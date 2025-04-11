
package com.icedemo.www.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public enum HomeTypeEnum {

	TOWN_HOME(HomeTypeEnum.th, "Town Home"),
	CONDO(HomeTypeEnum.co, "Condo"),
	IND_HOME(HomeTypeEnum.ih, "Individual Home"),
	UNKNOWN(HomeTypeEnum.unknown, "Unknown");

	public static final int th = 1;
	public static final int co = 2;
	public static final int ih = 3;
	public static final int unknown = 99;
	

	private HomeTypeEnum(int code, String name) {
		_code = code;
		_name = name;
	}

	private int _code;
	private String _name;

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

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static HomeTypeEnum get(int id) {
		HomeTypeEnum types[] = HomeTypeEnum.values();
		for (HomeTypeEnum type : types) {
			int i = type.getCode();
			if (i == id) {
				return type;
			}
		}
		return HomeTypeEnum.UNKNOWN;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static HomeTypeEnum get(String name) {
		HomeTypeEnum types[] = HomeTypeEnum.values();
		for (HomeTypeEnum type : types) {
			String n = type.getName();
			if (n.equalsIgnoreCase(name)) {
				return type;
			}
		}
		return HomeTypeEnum.UNKNOWN;
	}

	public static List<String> getAllValidEnums() {
		List<String> getAll = new ArrayList<String>();
		HomeTypeEnum types[] = HomeTypeEnum.values();
		for (HomeTypeEnum type : types) {
			if (type.getCode() == unknown) {
				continue;
			}
			getAll.add(type.getName());
		}
		return getAll;
	}

	public static List<String> getAllEnums() {
		List<String> getAll = new ArrayList<String>();
		HomeTypeEnum types[] = HomeTypeEnum.values();
		for (HomeTypeEnum type : types) {
			getAll.add(type.getName());
		}
		return getAll;
	}

	public String toString() {
		StringBuilder b = new StringBuilder("[HomeTypeEnum={");
		b.append("code=").append(_code).append(";");
		b.append("name=").append(_name).append(";");
		b.append("}]");
		return b.toString();
	}

}
