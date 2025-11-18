package com.store.prices.domain.ports.out;

import com.store.prices.domain.model.Price;
import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {
    List<Price> findPricesByBrandProductAndDate(Long brandId, Long productId, LocalDateTime applicationDate);
}