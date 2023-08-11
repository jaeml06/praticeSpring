package com.dcm.spring.druginfo.Service;

import reactor.core.publisher.Mono;

public interface PublicApiService {
    Mono<Object> getInfo(String query);
}
