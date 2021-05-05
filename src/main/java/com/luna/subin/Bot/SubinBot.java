package com.luna.subin.Bot;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import com.luna.subin.BotEvents.SubinBotEventListener;
import com.luna.subin.Firebase.Firebase;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

/**
 * Hello world!
 *
 */
public class SubinBot 
{
    public static void main( String[] args )
    {
    	String jdaToken = System.getenv("TOKEN");
        JDABuilder jdaBuilder = JDABuilder.createDefault(jdaToken).setMemberCachePolicy(MemberCachePolicy.ALL).enableIntents(GatewayIntent.GUILD_MEMBERS);
        JDA jda = null;
        try {
        	jda = jdaBuilder.build();
        } catch (LoginException e) {
        	e.printStackTrace();
        }
        
        jda.addEventListener(new SubinBotEventListener());
        
        try {
			Firebase.initFirebase();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
