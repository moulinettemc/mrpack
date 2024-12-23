package com.github.moulinettemc.login;

import com.github.moulinettemc.sessions.ClientSessions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.net.URISyntaxException;

public class LoginScreen extends Screen {

    public LoginScreen() {
        super(Component.translatable("moulinette.login.title"));

        minecraft = Minecraft.getInstance();
        ClientSessions.sessions.createToken(minecraft.getUser().getName())
                .thenAccept(req->{
                    if(req.token == null) throw new RuntimeException(req.error != null ? req.error : "");
                    LoginManager.token = req.token;
                    startChecking();
                }).exceptionally(thr->{
                    thr.printStackTrace();
                    minecraft.execute(()->{
                        minecraft.setScreen(new ErrorScreen(thr.getMessage()));
                    });
                    return null;
                });
    }

    public void startChecking(){
        nextCheck = 20;
    }

    int nextCheck = -1;

    @Override
    public void tick() {
        super.tick();
        if(nextCheck == -1) return;

        nextCheck -= 1;
        if(nextCheck == 0){
            try {
                ClientSessions.sessions.getSessionInfo(LoginManager.token)
                        .thenAccept(info->{
                            if(info.auth == -1) throw new RuntimeException("Connection denied");
                            if(info.auth == 1) minecraft.execute(()->{
                                LoginManager.connect();
                            });
                            else{
                                startChecking();
                            }
                        }).exceptionally(thr->{
                            thr.printStackTrace();
                            minecraft.execute(()->{
                                minecraft.setScreen(new ErrorScreen(thr.getMessage()));
                            });
                            return null;
                        });
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void init() {

    }

    @Override
    public void render(GuiGraphics g, int x, int y, float deltaTime) {
        renderBackground(g);
        super.render(g, x, y, deltaTime);

        g.drawCenteredString(Minecraft.getInstance().font, "Intent sent by Discord", width / 2, height / 2, 0xffffff);
    }
}
