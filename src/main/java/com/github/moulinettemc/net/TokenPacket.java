package com.github.moulinettemc.net;

import com.github.moulinettemc.etendator.TrucAToken;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.login.ServerLoginPacketListener;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;

public class TokenPacket implements Packet<ServerLoginPacketListener> {
    public String token = "";


    public TokenPacket() {
    }

    public TokenPacket(String token) {
        this.token = token;
    }

    public TokenPacket(FriendlyByteBuf b) {
        token = b.readUtf();
    }


    public void write(FriendlyByteBuf b) {
        b.writeUtf(token);
    }


    @Override
    public void handle(ServerLoginPacketListener l) {
        // tkt jsais
        ServerLoginPacketListenerImpl imp = (ServerLoginPacketListenerImpl) l;

        ((TrucAToken)imp).uwu$setToken(token);
    }

}
