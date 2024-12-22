package com.github.moulinettemc.mixin.client;

import com.github.moulinettemc.sessions.ClientSessions;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(
            method = "getMinecraftSessionService",
            cancellable = true,
            at = @At("HEAD")
    )
    public void injectSessionService(CallbackInfoReturnable<MinecraftSessionService> cir){
        cir.setReturnValue(ClientSessions.sessions);
    }
}
