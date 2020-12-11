package com.luna.subin.BotEvents;

import java.awt.Color;
import java.util.Iterator;

import com.luna.subin.Model.BanWordManager;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BanWordEvent implements IEventHandler {

	private final String[] commands = { "-addbanword", "-removebanword", "-showbanwords", "-clearbanwords" };

	BanWordManager banWordManager;
	String koreanFilter;
	String filter;

	@Override
	public String[] getCommands() {
		return commands;
	}

	@Override
	public String getDescription(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleEvent(GuildMessageReceivedEvent event) {
		String[] messageSent = event.getMessage().getContentRaw().split(" ");
		filter = event.getMessage().getContentRaw().replaceAll("[^\\uAC00-\\uD7A3xfea-zA-Z]", "");
		koreanFilter = event.getMessage().getContentRaw().replaceAll("[^\uAC00-\uD7A3]", "");

		if (messageSent.length != 2)
			return;
		if (messageSent[0].equalsIgnoreCase(commands[0])) {
			banWordManager = BanWordManager.getInstance();
			if (banWordManager.addBanWord(messageSent[1])) {
				event.getChannel().sendMessage(messageSent[1] + " added.").queue();
			} else {
				event.getChannel().sendMessage("Failed.").queue();
			}
		}

		if (messageSent[0].equalsIgnoreCase(commands[1])) {
			banWordManager = BanWordManager.getInstance();
			if (banWordManager.removeBanWord(messageSent[1])) {
				event.getChannel().sendMessage(messageSent[1] + " removed.").queue();
			} else {
				event.getChannel().sendMessage("Failed.").queue();
			}
		}

		if (messageSent[0].equalsIgnoreCase(commands[2])) {
			banWordManager = BanWordManager.getInstance();
			Iterator iter = banWordManager.getList().iterator();
			StringBuffer sb = new StringBuffer();

			while (iter.hasNext()) {
				sb.append(iter.next());
				if (iter.hasNext())
					sb.append(", ");
				else
					sb.append(".");
			}

			EmbedBuilder eb = new EmbedBuilder();
			eb.setTitle("List");
			eb.setColor(Color.YELLOW);
			eb.addField("Banned Words", sb.toString(), true);
			event.getChannel().sendMessage(eb.build()).queue();
		}

		if (messageSent[0].equalsIgnoreCase(commands[3])) {
			banWordManager = BanWordManager.getInstance();
			banWordManager.clearBanWords();
			event.getChannel().sendMessage("Banned Words List Cleared.").queue();
		}

	}
}
