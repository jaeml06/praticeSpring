package com.dcm.spring.druginfo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

@RestController
public class DrugOpenApiController {
    private final static String BASE_URL = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
    private final String API_KEY = "LBfaHapjnwmhChga%2FgsL2yJJUiy7YQ9hH%2Bq%2FEP9pJ9OBS6v%2B8VUYyJUoWnRV8b9nz3NI%2F7Rq35MPx5NvqGj8CQ%3D%3D";

    @GetMapping(value = "/publicData", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> getDrugInfo() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .build();

        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", API_KEY)
                        .queryParam("pageNo", "1")
                        .queryParam("numOfRows", "1")
                        .queryParam("entpName", "")
                        .queryParam("itemName", "")
                        .queryParam("itemSeq", "")
                        .queryParam("efcyQesitm", "")
                        .queryParam("useMethodQesitm", "")
                        .queryParam("atpnWarnQesitm", "")
                        .queryParam("atpnQesitm", "")
                        .queryParam("intrcQesitm", "")
                        .queryParam("seQesitm", "")
                        .queryParam("depositMethodQesitm", "")
                        .queryParam("openDe", "")
                        .queryParam("updateDe", "")
                        .queryParam("type", "json")
                        .build())
                .retrieve()
                .bodyToMono(String.class);

        return response;
    }
}