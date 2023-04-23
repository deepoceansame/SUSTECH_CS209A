package com.example.week10;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;
import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.google.gson.Gson;
import com.github.oscar0812.pokeapi.utils.Client;

public class RestCommunication {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://pokeapi.co/api/v2/pokemon/pikachu/"))
                .header("Auth","abc")
                .GET()
                .build();

        HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

//        System.out.println(response.body());
        Gson gson = new Gson();
        Pokemon pikachu = gson.fromJson(response.body(),Pokemon.class);


//        Client.CACHE = false;
//        Pokemon pikachu = Client.getPokemonByName("pikachu");
        System.out.println("Name: "+pikachu.getId());
        System.out.println("Height "+pikachu.getHeight());
        System.out.println("Weight "+pikachu.getWeight());
        System.out.println("Abilities "+pikachu.getAbilities());
    }
}
