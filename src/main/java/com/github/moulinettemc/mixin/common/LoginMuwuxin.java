package com.github.moulinettemc.mixin.common;

import com.github.moulinettemc.Moulinette;
import com.github.moulinettemc.net.TokenPacket;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.login.ServerLoginPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ConnectionProtocol.class)
public class LoginMuwuxin {

    @Inject(method = "getProtocolForPacket", at = @At("RETURN"), cancellable = true)
    private static void injectPacket(Packet<?> packet, CallbackInfoReturnable<ConnectionProtocol> cir){
        if(packet instanceof TokenPacket){
            cir.setReturnValue(ConnectionProtocol.LOGIN);
        }
    }

}
