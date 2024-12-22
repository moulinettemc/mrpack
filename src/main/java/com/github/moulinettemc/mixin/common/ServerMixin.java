package com.github.moulinettemc.mixin.common;

import com.github.moulinettemc.sessions.ServerSessions;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
public class ServerMixin {

    @Inject(at = @At("HEAD"), method = "getSessionService", cancellable = true)
    public void getSessionService(CallbackInfoReturnable<MinecraftSessionService> cir){
        cir.setReturnValue(ServerSessions.sessions);
    }
}
