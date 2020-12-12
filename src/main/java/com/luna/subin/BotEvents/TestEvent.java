package com.luna.subin.BotEvents;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.luna.subin.Firebase.Firebase;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class TestEvent implements IEventHandler {
	
	private final String[] commands = {"-testpushdb"};
	String messageSent[];
	

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

		if (messageSent[0].equalsIgnoreCase(commands[0])) {
			
			Map map = new HashMap();
			map.put("Hello", "World");
			Firebase.rootReference.child("test").setValueAsync(map);
			
			event.getChannel().sendMessage("DB Pushed!").queue();
		}

	}

}
