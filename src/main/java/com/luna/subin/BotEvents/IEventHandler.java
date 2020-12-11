package com.luna.subin.BotEvents;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface IEventHandler {
	public String[] getCommands();
	public String getDescription(String command);
	public void handleEvent(GuildMessageReceivedEvent event);
}
