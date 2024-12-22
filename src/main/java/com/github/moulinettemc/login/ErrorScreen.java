package com.github.moulinettemc.login;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;

public class ErrorScreen extends Screen {
    private final String erreur;

    public ErrorScreen(String erreur) {
        super(Component.literal("Erreur de connexion"));
        this.erreur = erreur;
    }

    @Override
    protected void init() {
        super.init();

        addRenderableWidget(new Button.Builder(Component.literal("Return"), btn->{
            Minecraft.getInstance().setScreen(new TitleScreen());
        }).bounds(width / 2 - 50, height / 2 + 20,
                100, 20).build());
    }

    @Override
    public void render(GuiGraphics g, int p_281550_, int p_282878_, float p_282465_) {
        renderBackground(g);
        super.render(g, p_281550_, p_282878_, p_282465_);

        int cx = width / 2, cy = height / 2;

        g.drawCenteredString(font, "Erreur de connexion", cx, cy - 50, 0xffffff);
        g.drawCenteredString(font, erreur, cx, cy - 30, 0xffffff);
    }
}
