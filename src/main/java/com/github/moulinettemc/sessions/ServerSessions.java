package com.github.moulinettemc.sessions;

import com.github.moulinettemc.sessions.response.SessionInfo;
import com.google.gson.Gson;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.minecraft.InsecurePublicKeyException;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;
import com.mojang.logging.LogUtils;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ServerSessions implements MinecraftSessionService {
    private static final Logger LOG = LogUtils.getLogger();
    public static ServerSessions sessions = new ServerSessions();

    HttpClient client = HttpClient.newBuilder().build();

    public CompletableFuture<SessionInfo> getSessionInfo(String token) throws URISyntaxException {
        return client.sendAsync(
                HttpRequest.newBuilder().GET()
                        .uri(new URI("http://valentinkh.ddns.net:3005/session?token=" + token)).build(),
                HttpResponse.BodyHandlers.ofString()
        ).thenApply(r->{
            Gson gson = new Gson();
            LOG.info(r.body());
            return gson.fromJson(r.body(), SessionInfo.class);
        });
    }

    public CompletableFuture<Boolean> verifySession(String token, String username) throws URISyntaxException {
        return getSessionInfo(token).thenApply(info->{
            LOG.info("Authentication of {}", username);
            LOG.info("Token: {}", token);
            LOG.info("username obtained: {}", info.playername);
            return info.playername.equals(username);
        });
    }

    @Override
    public void joinServer(GameProfile profile, String authenticationToken, String serverId) throws AuthenticationException {

    }

    @Nullable
    @Override
    public GameProfile hasJoinedServer(GameProfile profile, String token, @javax.annotation.Nullable InetAddress address) throws AuthenticationUnavailableException {
        //GameProfile profile = new GameProfile(UUID.randomUUID(), profileName);


        return profile;
    }

    @Override
    public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> getTextures(GameProfile profile, boolean requireSecure) throws InsecurePublicKeyException {
        return Map.of();
    }

    @Override
    public GameProfile fillProfileProperties(GameProfile profile, boolean requireSecure) {
        return null;
    }

    @Override
    public String getSecurePropertyValue(Property property) throws InsecurePublicKeyException {
        return "";
    }
}
