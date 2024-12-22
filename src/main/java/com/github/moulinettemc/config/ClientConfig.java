package com.github.moulinettemc.config;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;

public class ClientConfig {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 25565;

    public static final ServerAddress SERVER_ADDR = new ServerAddress(SERVER_IP, SERVER_PORT);
    public static final ServerData SERVER_DATA = new ServerData(SERVER_IP, SERVER_IP, false);
}
