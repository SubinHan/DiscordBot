package com.luna.subin.Bot;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import com.luna.subin.Web.SteamPriceCrawler;

import junit.framework.TestCase;

public class WebCrawlingTest extends TestCase {
	
	public void testWebCrawling() throws MalformedURLException {
		SteamPriceCrawler crawler = new SteamPriceCrawler(Collections.singleton(new URL("https://store.steampowered.com/app/435150/Divinity_Original_Sin_2__Definitive_Edition/")));
		
	}
}
