package com.luna.subin.BotEvents;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MuteEvent implements IEventHandler {
	private final String[] commands = { "-mute" };

	Role adminRole;
	Member member;
	String memberId;
	String memberName;
	String[] messageSent;

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
		messageSent = event.getMessage().getContentRaw().split(" ");
		adminRole = event.getGuild().getRoleById("371604058185793538");

		if (messageSent[0].equalsIgnoreCase(commands[0])) {

			if (!event.getMessage().getMember().getRoles().contains(adminRole)) {
				event.getChannel().sendMessage("Only administrators can use.").queue();
				return;
			}

			memberId = messageSent[1].replace("<@!", "").replace(">", "");
			Member member = event.getGuild().getMemberById(memberId);
			if (member == null) {
				System.out.println("member null");
				System.out.println(messageSent[1].replace("<@!", "").replace(">", ""));
			}
			Role muteRole = event.getGuild().getRoleById("751433157584093266");
			if (muteRole == null)
				System.out.println("null");

			if (!member.getRoles().contains(muteRole)) {
				// Mute user
				event.getChannel().sendMessage("Muted " + messageSent[1] + ".").queue();
				event.getGuild().addRoleToMember(memberId, muteRole).queue();
				;
			} else {
				// Unmute user
				event.getChannel().sendMessage("Unmuted " + messageSent[1] + ".").queue();
				event.getGuild().removeRoleFromMember(memberId, muteRole).queue();
			}
		}

	}
}
