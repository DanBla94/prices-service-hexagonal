package com.store.prices.application.service;

import com.store.prices.domain.model.Price;
import com.store.prices.domain.ports.in.GetPriceUseCase;
import com.store.prices.domain.ports.out.PriceRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceService implements GetPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Optional<Price> getApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepositoryPort.findPricesByBrandProductAndDate(brandId, productId, applicationDate)
            .stream()
            .max(Comparator.comparingInt(Price::getPriority));
    }
}