package com.github.moulinettemc.mixin.common;

import com.github.moulinettemc.etendator.TrucAToken;
import com.github.moulinettemc.sessions.ServerSessions;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.login.ServerboundKeyPacket;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.net.URISyntaxException;

@Mixin(ServerLoginPacketListenerImpl.class)
public abstract class ServerLoginMixinUWU implements TrucAToken {

    @Shadow public abstract void disconnect(Component p_10054_);

    @Shadow @Nullable public GameProfile gameProfile;
    @Unique
    String token = "";

    @Inject(method = "handleKey", at=@At("HEAD"), cancellable = true)
    public void hsr(ServerboundKeyPacket p, CallbackInfo ci){
        try {
            if(!ServerSessions.sessions.verifySession(token, gameProfile.getName()).join()){
                ci.cancel();
                disconnect(Component.literal("Probleme de token"));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            ci.cancel();
            disconnect(Component.literal("Probleme de token"));
        }
    }

    @Override
    public void uwu$setToken(String t) {
        this.token = t;
    }

    @Override
    public String owo$getToken() {
        return token;
    }

}
