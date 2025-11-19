package com.store.prices.domain.ports.in;

import com.store.prices.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPriceUseCase {
    Optional<Price> getApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate);
}