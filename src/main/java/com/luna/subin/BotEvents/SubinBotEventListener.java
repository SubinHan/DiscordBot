package com.luna.subin.BotEvents;

import java.util.ArrayList;
import java.util.Iterator;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SubinBotEventListener extends ListenerAdapter implements IEventHandler {
	
	ArrayList<IEventHandler> eventHandlers;
	String[] messageSent;
	
	public SubinBotEventListener() {
		eventHandlers = new ArrayList<IEventHandler>();
		eventHandlers.add(this);
		eventHandlers.add(new BanWordEvent());
		eventHandlers.add(new RandomEvent());
		eventHandlers.add(new MuteEvent());
		eventHandlers.add(new SteamEvent());
		eventHandlers.add(new ApexEvent());
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] messageSent = event.getMessage().getContentRaw().split(" ");
		System.out.println(messageSent[0].toString());

		if (!event.getMember().getUser().isBot()) {
			Iterator<IEventHandler> iter = eventHandlers.iterator();
			while(iter.hasNext()){
				IEventHandler eventHandler = iter.next();
				eventHandler.handleEvent(event);
			}
		}
	}

	@Override
	public String[] getCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleEvent(GuildMessageReceivedEvent event) {
		messageSent = event.getMessage().getContentRaw().split(" ");
		
		if (messageSent[0].equalsIgnoreCase("-help")) {
			Iterator iter = eventHandlers.iterator();
			EmbedBuilder eb = new EmbedBuilder();
			eb.setTitle("Help");
			
			while(iter.hasNext()) {
				IEventHandler eventHandler = (IEventHandler)iter.next();
				String[] commands = eventHandler.getCommands();
				for(int i = 0; i < commands.length; i++) {
					eb.addField(commands[i], eventHandler.getDescription(commands[i]), false);
				}
			}
			event.getChannel().sendMessage(eb.build()).queue();
		}
		if (messageSent[0].equalsIgnoreCase("-hello")) {
			EmbedBuilder eb = new EmbedBuilder();
			eb.setTitle("Hi! I am Subin Bot!");
			eb.addField("Channel Name", event.getChannel().getName(), false);
			eb.addField("Channel ID(long)", String.valueOf(event.getChannel().getIdLong()), false);
			eb.addField("Messenger", event.getMessage().getMember().getNickname(), false);
			eb.addField("Message",  event.getMessage().getContentRaw(), false);
			event.getChannel().sendMessage(eb.build()).queue();
		}
		
	}
}
