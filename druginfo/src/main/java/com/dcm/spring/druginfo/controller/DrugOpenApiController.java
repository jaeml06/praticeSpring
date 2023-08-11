package com.dcm.spring.druginfo.controller;

import com.dcm.spring.druginfo.Service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DrugOpenApiController {
    @RestController
    public class DrugOpenAPIController {
        private final DrugService drugService;

        @Autowired
        public DrugOpenAPIController(DrugService drugService) {
            this.drugService = drugService;
        }

        @GetMapping(value = "/PublicData/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
        public Mono<Object> getDrugInfo(@PathVariable String itemName) {
            return drugService.getInfo(itemName);
        }

    }
}