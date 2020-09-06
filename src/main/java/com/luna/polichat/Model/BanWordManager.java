package com.luna.polichat.Model;

import java.util.ArrayList;
import java.util.List;

public class BanWordManager {
	List<String> words;
	static BanWordManager instance;
	
	private BanWordManager() {
		words = new ArrayList();
	}
	
	public static BanWordManager getInstance() {
		if(instance == null)
			instance = new BanWordManager();
		return instance;
	}
	
	public boolean addBanWord(String word) {
		if(words.contains(word))
			return false;
		words.add(word);
		return true;
	}
	
	public boolean removeBanWord(String word) {
		if(words.contains(word)) {
			words.remove(word);
			return true;
		}
		return false;
	}
	
	public void clearBanWords() {
		words.clear();
	}
	
	public List getList() {
		return words;
	}
}
