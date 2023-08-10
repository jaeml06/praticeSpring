package com.dcm.spring.druginfo.controller;

import com.dcm.spring.druginfo.model.DrugResponseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class DrugOpenApiController {
    private final static String BASE_URL = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
    private final String API_KEY = "LBfaHapjnwmhChga%2FgsL2yJJUiy7YQ9hH%2Bq%2FEP9pJ9OBS6v%2B8VUYyJUoWnRV8b9nz3NI%2F7Rq35MPx5NvqGj8CQ%3D%3D";

    private Mono<Object> convertToMedication(DrugResponseDto drugResponseDTO) {
        return Mono.just(drugResponseDTO.getBody().getItems().get(0));
    }

    @GetMapping(value = "/PublicData/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Object> getDrugInfo(@PathVariable String itemName) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        String encodedItemName = URLEncoder.encode(itemName, StandardCharsets.UTF_8);
        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .build();

        Mono<DrugResponseDto> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", API_KEY)
                        .queryParam("pageNo", "1")
                        .queryParam("numOfRows", "1")
                        .queryParam("itemName", encodedItemName)
                        .queryParam("type", "json")
                        .build())
                .retrieve()
                .bodyToMono(DrugResponseDto.class);

        return response.flatMap(this::convertToMedication);
    }
}