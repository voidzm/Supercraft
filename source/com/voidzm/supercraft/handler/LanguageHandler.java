//////////////////////////////////////
//*      LanguageHandler.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// This class isn't much more than a skin on HashMap.
// This class is to future-proof for possible localization.

package com.voidzm.supercraft.handler;

import java.util.HashMap;

public class LanguageHandler {

	private HashMap<Object, Object> itemMap = new HashMap<Object, Object>();
	
	public void add(Object key, Object languageValue) {
		itemMap.put(key, languageValue);
	}
	
	public void remove(Object key) {
		itemMap.remove(key);
	}
	
	public Object getString(Object key) {
		return itemMap.get(key);
	}
	
	public int count() {
		return itemMap.size();
	}
	
}
