package com.luna.subin.BotEvents;

import java.awt.Color;
import java.util.Iterator;

import com.luna.subin.Model.BanWordManager;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BanWordEvent implements IEventHandler {

	BanWordManager banWordManager;
	String koreanFilter;
	String filter;

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
		String[] messageSent = event.getMessage().getContentRaw().split(" ");
		filter = event.getMessage().getContentRaw().replaceAll("[^\\uAC00-\\uD7A3xfea-zA-Z]", "");
		koreanFilter = event.getMessage().getContentRaw().replaceAll("[^\uAC00-\uD7A3]", "");

		if (!event.getMember().getUser().isBot()) {
//			for (int i = 0; i < messageSent.length; i++) {
//				if (messageSent[i].equalsIgnoreCase("hello")) {
//					event.getChannel().sendMessage("Hi! I am Subin Bot!").queue();
//					event.getChannel().sendMessage("Channel Name: " + event.getChannel().getName()).queue();
//					event.getChannel().sendMessage("Messenger: " + event.getMessage().getMember().getNickname())
//							.queue();
//					event.getChannel().sendMessage("Message: " + event.getMessage().getContentRaw()).queue();
//				}

//				if (messageSent[i].equalsIgnoreCase("delete")) {
//					event.getChannel().sendMessage("Deleted").queue();
//					event.getMessage().delete().queue();
//				}
//			}
			if(messageSent.length != 2)
				return;
			if (messageSent[0].equalsIgnoreCase("-addbanword")) {
				banWordManager = BanWordManager.getInstance();
				if (banWordManager.addBanWord(messageSent[1])) {
					event.getChannel().sendMessage(messageSent[1] + " added.").queue();
				} else {
					event.getChannel().sendMessage("Failed.").queue();
				}
			}

			if (messageSent[0].equalsIgnoreCase("-removebanword")) {
				banWordManager = BanWordManager.getInstance();
				if (banWordManager.removeBanWord(messageSent[1])) {
					event.getChannel().sendMessage(messageSent[1] + " removed.").queue();
				} else {
					event.getChannel().sendMessage("Failed.").queue();
				}
			}

			if (messageSent[0].equalsIgnoreCase("-showbanwords")) {
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

			if (messageSent[0].equalsIgnoreCase("-clearbanwords")) {
				banWordManager = BanWordManager.getInstance();
				banWordManager.clearBanWords();
				event.getChannel().sendMessage("Banned Words List Cleared.").queue();
			}
		}	
	}
}
