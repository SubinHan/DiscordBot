package com.luna.subin.BotEvents;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;
import com.luna.subin.Firebase.Firebase;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class TestEvent implements IEventHandler {
	
	private final String[] commands = {"-testpushdb", "-testgetdb"};
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
		
		if (messageSent[0].equalsIgnoreCase(commands[1])) {
			
			DataSnapshot ds;
			ds = Firebase.dataSnapshot.child("test");
			if(messageSent.length >= 2 ) {
				for(int i = 1; i < messageSent.length; i++) {
					ds.child(messageSent[i]);
				}
				event.getChannel().sendMessage("Value: " + ds.getValue()).queue();
			}
			else
				event.getChannel().sendMessage("Key 값을 입력해주세요!").queue();
		}

	}

}
