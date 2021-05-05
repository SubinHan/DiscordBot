package com.luna.subin.Web;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class SteamPriceCrawler {
	
	public static final String TITLE = "title";
	public static final String ORIGINAL_PRICE = "originalPrice";
	public static final String DISCOUNT_PERCENT = "discountPercent";
	public static final String DISCOUNT_PRICE = "discountPrice";
	public static final String IS_DISCOUNTED = "isDiscounted";
	public static final String URL = "url";
	
	private ArrayList gameList;
	
	public SteamPriceCrawler(final Set<URL> links) {
		crawl(links);
	}
	
	private void crawl(final Set<URL> urls) {
		
		try {
			String gameTitle, originalPrice, discountPercent, discountPrice;
			gameList = new ArrayList<HashMap>();
			
			
			for(final URL url : urls) {
				final Document document = Jsoup.connect(url.toString()).get();
				final Elements linksOnPage = document.select(".game_area_purchase_game_wrapper");
				for(final Element element : linksOnPage) {
					gameTitle = element.select("h1").text().substring(4);
					if(gameTitle.contains("BUNDLE"))
						continue;
					originalPrice = element.select(".discount_original_price").text();
					if(originalPrice.length() == 0)
						originalPrice = element.select(".game_purchase_price").text();
					if(originalPrice.length() == 0)
						continue;
					discountPercent = element.select(".discount_pct").text();
					discountPrice = element.select(".discount_final_price").text();
					HashMap gameInfos = new HashMap<>();
					gameInfos.put(TITLE, gameTitle);
					gameInfos.put(ORIGINAL_PRICE, originalPrice);
					gameInfos.put(DISCOUNT_PERCENT, discountPercent);
					gameInfos.put(DISCOUNT_PRICE, discountPrice);
					gameInfos.put(IS_DISCOUNTED, discountPercent.length() != 0);
					gameInfos.put(URL, url.toString());
					gameList.add(gameInfos);
					System.out.println(gameTitle);
					System.out.println(originalPrice);
					System.out.println(discountPercent);
					System.out.println(discountPrice);
				}
			}
		} catch(Exception e) {
			;
		}
		
	}
	
	public ArrayList getGameList() {
		return gameList;
	}

	
	
}
