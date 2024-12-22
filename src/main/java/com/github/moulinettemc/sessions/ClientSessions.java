package com.github.moulinettemc.sessions;

import com.github.moulinettemc.sessions.response.TokenRequest;
import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ClientSessions extends ServerSessions {
    public static ClientSessions sessions = new ClientSessions();


    // tkt

    public CompletableFuture<TokenRequest> createToken(String username){
        try {
            return client.sendAsync(
                    HttpRequest.newBuilder().GET()
                            .uri(new URI("http://valentinkh.ddns.net:3005/login?username=" + username)).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).thenApply(r->{
                Gson gson = new Gson();
                return gson.fromJson(r.body(), TokenRequest.class);
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


}
