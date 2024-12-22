package com.github.moulinettemc;

import com.github.moulinettemc.net.TokenPacket;
import com.mojang.logging.LogUtils;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.login.ServerLoginPacketListener;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Moulinette.MODID)
public class Moulinette {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "moulinette";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    @SuppressWarnings("all") // tkt
    public Moulinette() {
        ConnectionProtocol.PacketSet<ServerLoginPacketListener> a =
                (ConnectionProtocol.PacketSet<ServerLoginPacketListener>)
                        ConnectionProtocol.LOGIN.flows.get(PacketFlow.SERVERBOUND);
        // tkt
        a.addPacket(TokenPacket.class, TokenPacket::new);

        //ConnectionProtocol.LOGIN.flows.get(PacketFlow.SERVERBOUND).addPacket(, TokenPacket::new);

    }
}
