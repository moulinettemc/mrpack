package com.github.moulinettemc.login;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SessionVerificationScreen extends Screen {
    public SessionVerificationScreen() {
        super(Component.literal(""));
    }

    @Override
    public void render(GuiGraphics g, int p_281550_, int p_282878_, float p_282465_) {
        renderBackground(g);
        super.render(g, p_281550_, p_282878_, p_282465_);

        g.drawCenteredString(font, "Verification de la session", width / 2, height / 2, 0xffffff);
    }
}
