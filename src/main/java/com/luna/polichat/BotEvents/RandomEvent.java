package com.luna.polichat.BotEvents;

import java.util.Iterator;
import java.util.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RandomEvent extends ListenerAdapter {
	String[] messageSent;

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		messageSent = event.getMessage().getContentRaw().split(" ");

		if (!event.getMember().getUser().isBot()) {
			if (messageSent[0].equalsIgnoreCase("-random")) {
				Random random = new Random();

				StringBuffer sb = new StringBuffer();

				for(int i = 1;i < messageSent.length;i++){
					sb.append(messageSent[i]);
					if (i + 1 < messageSent.length)
						sb.append(", ");
					else
						sb.append(".");
				}
				
				EmbedBuilder eb = new EmbedBuilder();
				eb.setTitle("Random Picker");
				eb.addField("Pick From..", sb.toString(), false);
				eb.addField("Result: ", messageSent[random.nextInt(messageSent.length - 1) + 1], false);
				event.getChannel().sendMessage(eb.build()).queue();
			}
		}
	}
}
