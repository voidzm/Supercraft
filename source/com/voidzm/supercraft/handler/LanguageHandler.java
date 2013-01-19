//////////////////////////////////////
//*      LanguageHandler.java      *//
//*           Supercraft           *//
//*        (c) voidzm 2013         *//
//////////////////////////////////////

// This class isn't much more than a skin on HashMap.
// This class is to future-proof for possible localization.

package com.voidzm.supercraft.handler;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class LanguageHandler {

	private HashMap<Object, String> itemMap = new HashMap<Object, String>();
	
	public void add(Object key, String languageValue) {
		itemMap.put(key, languageValue);
	}
	
	public void remove(Object key) {
		itemMap.remove(key);
	}
	
	public String getString(Object key) {
		return itemMap.get(key);
	}
	
	public int count() {
		return itemMap.size();
	}
	
}
