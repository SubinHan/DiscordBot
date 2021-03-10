package com.luna.subin.BotEvents;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.luna.subin.Model.ApexDataCollector;
import com.luna.subin.Model.ApexMatchData;
import com.luna.subin.Model.ApexPlayerData;
import com.luna.subin.Web.SteamPriceCrawler;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ApexEvent implements IEventHandler {
	private final String[] commands = { "-apexstats" };
	String[] messageSent;

	@Override
	public String[] getCommands() {
		// TODO Auto-generated method stub
		return commands;
	}

	@Override
	public String getDescription(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleEvent(GuildMessageReceivedEvent event) {
		messageSent = event.getMessage().getContentRaw().split(" ");

		if (messageSent[0].equalsIgnoreCase(commands[0])) {
			TextChannel textChannel = event.getJDA().getTextChannelById(817739832599642122L);
			
			
			MessageHistory messageHistory = textChannel.getHistory();
			messageHistory.getHistoryFromBeginning(textChannel).queue(message -> {
				ApexDataCollector collector = new ApexDataCollector();
				
				Iterator iter = message.getRetrievedHistory().iterator();
				while (iter.hasNext()) {
					String rawData = ((Message) iter.next()).getContentDisplay();
					if(rawData.startsWith("!")) {
						String substringData = rawData.substring(1);
						System.out.println(substringData);
						String[] refinedData;
						refinedData = substringData.split(" ");
						ApexMatchData matchData = new ApexMatchData();
						for (int i = 0; i < refinedData.length; i++) {
							String[] temp = refinedData[i].split(":");

							String name = temp[0];
							ApexPlayerData apexPlayerData;
							if(temp[1].contains(",")) {
								String[] playerData = temp[1].split(",");
								apexPlayerData = new ApexPlayerData(name, Integer.parseInt(playerData[0]), Integer.parseInt(playerData[1]));
							}
							else {
								apexPlayerData = new ApexPlayerData(name, Integer.parseInt(temp[1]));
							}
							matchData.add(apexPlayerData);
						}
						collector.add(matchData);
					}
				}
				
				collector.collect();
				
				EmbedBuilder eb = new EmbedBuilder();
				eb.setTitle("Apex Legends 통계");
				
				String embedMessage = "";
				
				Map<String, Double> stats;

				stats = collector.getAverageDamage();
				for (String name : stats.keySet()) {
					embedMessage = embedMessage.concat(name + ": " + String.format("%.1f", stats.get(name)) + "\n");
				}
				eb.addField("평균 데미지", embedMessage, false);
				
				embedMessage = "";
				stats = collector.getAverageKills();
				for (String name : stats.keySet()) {
					embedMessage = embedMessage.concat(name + ": " + String.format("%.2f", stats.get(name)) + "\n");
				}
				eb.addField("평균 처치", embedMessage, false);
				
				embedMessage = "";
				stats = collector.getDamageRatio();
				for (String name : stats.keySet()) {
					embedMessage = embedMessage.concat(name + ": " + String.format("%.1f %%", stats.get(name) * 100) + "\n");
				}
				eb.addField("팀 데미지 점유율", embedMessage, false);
				
				embedMessage = "";
				stats = collector.getKillsRatio();
				System.out.println(stats);
				for (String name : stats.keySet()) {
					embedMessage = embedMessage.concat(name + ": " + String.format("%.1f %%", stats.get(name) * 100) + "\n");
				}
				eb.addField("팀 처치 점유율", embedMessage, false);
				
				embedMessage = "";
				stats = collector.getDamageVariation();
				System.out.println(stats);
				for (String name : stats.keySet()) {
					embedMessage = embedMessage.concat(name + ": " + String.format("%.1f", Math.sqrt(stats.get(name))) + "\n");
				}
				eb.addField("데미지 기복 (높을수록 기복이 심함)", embedMessage, false);
			
				event.getChannel().sendMessage(eb.build()).queue();
			});
		}

	}
}
