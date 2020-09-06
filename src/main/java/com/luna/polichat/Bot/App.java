package com.luna.polichat.Bot;

import javax.security.auth.login.LoginException;

import com.luna.polichat.BotEvents.BanWordEvent;
import com.luna.polichat.BotEvents.MuteEvent;
import com.luna.polichat.BotEvents.RandomEvent;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JDABuilder jdaBuilder = JDABuilder.createDefault("NzUxMzcyNzMzNDA4ODcwNDIw.X1IIYw.rSwI4_btpqeNUBbDjGM0g_uCd7Y").setMemberCachePolicy(MemberCachePolicy.ALL).enableIntents(GatewayIntent.GUILD_MEMBERS);
        JDA jda = null;
        try {
        	jda = jdaBuilder.build();
        } catch (LoginException e) {
        	e.printStackTrace();
        }
        
        jda.addEventListener(new BanWordEvent());
        jda.addEventListener(new MuteEvent());
        jda.addEventListener(new RandomEvent());
    }
}
