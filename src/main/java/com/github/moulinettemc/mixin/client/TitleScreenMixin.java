package com.github.moulinettemc.mixin.client;

import com.github.moulinettemc.Moulinette;
import com.github.moulinettemc.login.LoginManager;
import com.mojang.realmsclient.RealmsMainScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.SafetyScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Component p_96550_) {
        super(p_96550_);
    }



    @Inject(
            method = "createNormalMenuOptions",
            at = @At("HEAD"),
            cancellable = true
    )
    public void replaceButtons(int p_96764_, int p_96765_, CallbackInfo ci){
        ci.cancel();

        this.addRenderableWidget(
                Button.builder(Component.translatable("menu.singleplayer"), p_280832_ -> this.minecraft.setScreen(new SelectWorldScreen(this)))
                        .bounds(this.width / 2 - 100, p_96764_, 200, 20)
                        .build()
        );

        this.addRenderableWidget(Button.builder(Component.translatable("menu.multiplayer"), p_280833_ -> {
            LoginManager.handleClick();
        }).bounds(this.width / 2 - 100, p_96764_ + p_96765_ * 1, 200, 20).build());

    }
}
