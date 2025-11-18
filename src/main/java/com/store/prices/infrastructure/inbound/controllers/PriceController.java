package com.store.prices.infrastructure.inbound.controllers;

import com.store.prices.domain.ports.in.GetPriceUseCase;
import com.store.prices.infrastructure.inbound.dto.PriceResponse;
import com.store.prices.infrastructure.inbound.mapper.PriceDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
public class PriceController implements PricesApi {

    private final GetPriceUseCase getPriceUseCase;
    private final PriceDtoMapper priceDtoMapper;

    @Override
    public ResponseEntity<PriceResponse> getPrice(OffsetDateTime applicationDate, Long productId, Long brandId) {

        return getPriceUseCase.getApplicablePrice(brandId, productId, applicationDate.toLocalDateTime())
            .map(price -> ResponseEntity.ok(priceDtoMapper.toResponse(price)))
            .orElse(ResponseEntity.notFound().build());
    }
}