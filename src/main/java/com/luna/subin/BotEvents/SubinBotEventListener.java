package com.luna.subin.BotEvents;

import java.util.ArrayList;
import java.util.Iterator;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SubinBotEventListener extends ListenerAdapter {
	
	ArrayList<IEventHandler> eventHandlers;
	
	public SubinBotEventListener() {
		eventHandlers = new ArrayList<IEventHandler>();
		eventHandlers.add(new BanWordEvent());
		eventHandlers.add(new RandomEvent());
		eventHandlers.add(new MuteEvent());
		eventHandlers.add(new SteamEvent());
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
//		System.out.println("event Handled");
		String[] messageSent = event.getMessage().getContentRaw().split(" ");

		if (!event.getMember().getUser().isBot()) {
			if (messageSent[0].equalsIgnoreCase("-help")) {
				;
			}
			if (messageSent[0].equalsIgnoreCase("-hello")) {
				EmbedBuilder eb = new EmbedBuilder();
				eb.setTitle("Hi! I am Subin Bot!");
				eb.addField("Channel Name", event.getChannel().getName(), false);
				eb.addField("Channel ID(long)", String.valueOf(event.getChannel().getIdLong()), false);
				eb.addField("Messenger", event.getMessage().getMember().getNickname(), false);
				eb.addField("Message",  event.getMessage().getContentRaw(), false);
				event.getChannel().sendMessage(eb.build()).queue();
//				event.getChannel().sendMessage("Hi! I am Subin Bot!").queue();
//				event.getChannel().sendMessage("Channel Name: " + event.getChannel().getName()).queue();
//				event.getChannel().sendMessage("Channel ID(long): " + event.getChannel().getIdLong()).queue();
//				event.getChannel().sendMessage("Messenger: " + event.getMessage().getMember().getNickname())
//						.queue();
//				event.getChannel().sendMessage("Message: " + event.getMessage().getContentRaw()).queue();
			}
			
			Iterator<IEventHandler> iter = eventHandlers.iterator();
			while(iter.hasNext()){
				IEventHandler eventHandler = iter.next();
				eventHandler.handleEvent(event);
			}
		}
	}
}
