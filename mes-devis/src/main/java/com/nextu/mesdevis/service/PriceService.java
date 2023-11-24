package com.nextu.mesdevis.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Service permettant de récuperer de manière externe le / les meilleurs prix du marché
 */
@Service
public class PriceService {
    /**
     * URL de l'API externe
     */
    @Value("${apiProductPrice}")
    private String apiUrl;

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    /**
     * Function qui effectue une recherche via api externe et retourne le prix d'un produit passé en paramètres.
     * (Ici l'appel est fake pour le moment est le prix est initialisé de manière aléatoire.
     *
     * @param idProduct Identifiant du produit.
     * @return le prix du produit (sous la forme décimal).
     */
    public float findProductPrice(long idProduct) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        float randomPrice = 200 + new Random().nextFloat() * (1000 - 200);
        String formattedPrice = decimalFormat.format(randomPrice);

        String jsonBody = "{\"productPrice\":\"" + formattedPrice + "\"}";

        HttpEntity<String> httpEntity = new HttpEntity<>(jsonBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, httpEntity, String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            String productPrice = jsonNode.path("json").path("productPrice").asText();
            return Float.parseFloat(productPrice.replace(",", "."));
        } catch (Exception e) {
            logger.error("An error occurred while parsing JSON", e);
        }
        return -1;
    }
}