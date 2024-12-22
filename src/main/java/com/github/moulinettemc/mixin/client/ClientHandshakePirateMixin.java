package com.github.moulinettemc.mixin.client;

import com.github.moulinettemc.login.LoginManager;
import com.github.moulinettemc.net.TokenPacket;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.login.ClientboundHelloPacket;
import net.minecraft.network.protocol.login.ServerboundKeyPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.crypto.Cipher;

@Mixin(ClientHandshakePacketListenerImpl.class)
public class ClientHandshakePirateMixin {

    @Shadow @Final private Connection connection;

    @Inject(method = "handleHello", at=@At("HEAD"))
    public void injectkey(ClientboundHelloPacket p_104549_, CallbackInfo ci){
        connection.send(new TokenPacket(LoginManager.getToken()));
    }


}
