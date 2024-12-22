package com.github.moulinettemc.login;

import com.github.moulinettemc.config.ClientConfig;
import com.github.moulinettemc.sessions.ClientSessions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraftforge.network.NetworkRegistry;

import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;

public class LoginManager {

    public static String token = null;

    // blc fonctions bzr
    public static void handleClick() {
        Minecraft mc = Minecraft.getInstance();

        boolean reconnect = token == null;
        if(!reconnect){
            try {
                Minecraft.getInstance().setScreen(new SessionVerificationScreen());
                ClientSessions.sessions.verifySession(token, mc.getUser().getName()).thenAccept(res->{
                    if(!res.booleanValue()) throw new RuntimeException();

                    Minecraft.getInstance().execute(LoginManager::connect);
                }).exceptionally(r->{
                    Minecraft.getInstance().setScreen(new LoginScreen());
                    return null;
                });
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }else{
            Minecraft.getInstance().setScreen(new LoginScreen());
        }
    }


    public static void connect(){
        Minecraft mc = Minecraft.getInstance();
        mc.getUser().accessToken = getToken();
        ConnectScreen.startConnecting(new TitleScreen(), mc, ClientConfig.SERVER_ADDR,
                ClientConfig.SERVER_DATA, false);
    }



    public static String getToken() {
        return token;
    }
}
