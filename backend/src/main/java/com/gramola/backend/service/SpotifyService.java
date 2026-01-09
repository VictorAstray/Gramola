package com.gramola.backend.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyService {

    @Autowired
    private RestTemplate restTemplate;

    private final String TOKEN_URL = "https://accounts.spotify.com/api/token";
    private final String SEARCH_URL = "https://api.spotify.com/v1/search?q={query}&type=track&limit=10";

    // 1. Conseguir el Token (La llave)
    public String getAccessToken(String clientId, String clientSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(TOKEN_URL, request, Map.class);
            Map<String, Object> body = response.getBody();
            if (body != null && body.containsKey("access_token")) {
                return (String) body.get("access_token");
            }
        } catch (Exception e) {
            System.err.println("Error obteniendo token: " + e.getMessage());
        }
        return null;
    }

    // 2. Buscar Canciones (La acción)
    public List<Map<String, Object>> searchTracks(String query, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Hacemos la petición GET a la API de búsqueda
            ResponseEntity<Map> response = restTemplate.exchange(SEARCH_URL, HttpMethod.GET, entity, Map.class, query);
            
            // Navegamos por el JSON de respuesta de Spotify para sacar solo lo que nos interesa
            Map<String, Object> body = response.getBody();
            if (body != null && body.containsKey("tracks")) {
                Map<String, Object> tracks = (Map<String, Object>) body.get("tracks");
                return (List<Map<String, Object>>) tracks.get("items");
            }
        } catch (Exception e) {
            System.err.println("Error buscando canciones: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}