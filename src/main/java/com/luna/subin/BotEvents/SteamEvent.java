package com.luna.subin.BotEvents;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.luna.subin.Web.SteamPriceCrawler;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class SteamEvent implements IEventHandler {

	String[] messageSent;

	@Override
	public String getCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleEvent(GuildMessageReceivedEvent event) {
		messageSent = event.getMessage().getContentRaw().split(" ");

		

		if (!event.getMember().getUser().isBot()) {
			if (messageSent[0].equalsIgnoreCase("-discountlist")) {
				EmbedBuilder eb = new EmbedBuilder();
				eb.setTitle("현재 할인 중!");
				TextChannel textChannel = event.getJDA().getTextChannelById(733678943286198383L);
				MessageHistory messageHistory = textChannel.getHistory();
				messageHistory.getHistoryFromBeginning(textChannel).queue(message -> {
					Iterator iter = message.getRetrievedHistory().iterator();
					Set<URL> urls = new HashSet<URL>();
					while (iter.hasNext()) {
						try {
							urls.add(new URL(((Message) iter.next()).getContentDisplay()));
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
					}
					SteamPriceCrawler steamCrawler = new SteamPriceCrawler(urls);
					ArrayList gameList = steamCrawler.getGameList();
					iter = gameList.iterator();
					boolean isDiscounted = false;

					while (iter.hasNext()) {
						HashMap gameInfo = (HashMap) iter.next();
						if ((boolean) gameInfo.get("isDiscounted")) {
							isDiscounted = true;
							System.out.println(gameInfo.get(SteamPriceCrawler.ORIGINAL_PRICE) + " -> "
									+ gameInfo.get(SteamPriceCrawler.DISCOUNT_PRICE) + " ("
									+ gameInfo.get(SteamPriceCrawler.DISCOUNT_PERCENT) + ")");
							eb.addField(gameInfo.get(SteamPriceCrawler.TITLE).toString(),
									gameInfo.get(SteamPriceCrawler.ORIGINAL_PRICE) + " -> "
											+ gameInfo.get(SteamPriceCrawler.DISCOUNT_PRICE) + " ("
											+ gameInfo.get(SteamPriceCrawler.DISCOUNT_PERCENT) + ")",
									true);
						}
					}
					if(!isDiscounted)
						eb.addField("으악!", "할인 중인 게임이 없습니다 ㅜㅜ", true);
					event.getChannel().sendMessage(eb.build()).queue();
				});
			}
			if (messageSent[0].equalsIgnoreCase("-gamelist")) {
				EmbedBuilder eb = new EmbedBuilder();
				eb.setTitle("등록된 게임 목록");
				TextChannel textChannel = event.getJDA().getTextChannelById(733678943286198383L);
				MessageHistory messageHistory = textChannel.getHistory();
				messageHistory.getHistoryFromBeginning(textChannel).queue(message -> {
					Iterator iter = message.getRetrievedHistory().iterator();
					Set<URL> urls = new HashSet<URL>();
					while (iter.hasNext()) {
						try {
							urls.add(new URL(((Message) iter.next()).getContentDisplay()));
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
					}
					SteamPriceCrawler steamCrawler = new SteamPriceCrawler(urls);
					ArrayList gameList = steamCrawler.getGameList();
					iter = gameList.iterator();

					while (iter.hasNext()) {
						HashMap gameInfo = (HashMap) iter.next();

						eb.addField(gameInfo.get(SteamPriceCrawler.TITLE).toString(),
								gameInfo.get(SteamPriceCrawler.ORIGINAL_PRICE).toString(),	false);

					}
					event.getChannel().sendMessage(eb.build()).queue();
				});
			}
		}

	}
}
