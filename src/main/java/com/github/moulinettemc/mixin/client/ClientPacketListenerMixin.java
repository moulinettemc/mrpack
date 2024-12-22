package com.github.moulinettemc.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.Connection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    /*
    @Shadow private boolean seenInsecureChatWarning;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void supprmsg(Minecraft p_253924_, Connection p_253614_, CommonListenerCookie p_298329_, CallbackInfo ci){
        seenInsecureChatWarning = true;
    }

     */
}
