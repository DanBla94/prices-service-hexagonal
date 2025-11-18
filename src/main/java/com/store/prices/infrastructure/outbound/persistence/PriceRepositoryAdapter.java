package com.store.prices.infrastructure.outbound.persistence;

import com.store.prices.domain.model.Price;
import com.store.prices.domain.ports.out.PriceRepositoryPort;
import com.store.prices.infrastructure.outbound.persistence.entity.PriceEntity;
import com.store.prices.infrastructure.outbound.persistence.mapper.PriceEntityMapper;
import com.store.prices.infrastructure.outbound.persistence.repository.SpringDataPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final SpringDataPriceRepository springDataPriceRepository;
    private final PriceEntityMapper priceEntityMapper;

    @Override
    public List<Price> findPricesByBrandProductAndDate(Long brandId, Long productId, LocalDateTime applicationDate) {
        List<PriceEntity> entities = springDataPriceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            brandId, productId, applicationDate, applicationDate);

        return priceEntityMapper.toDomainList(entities);
    }
}