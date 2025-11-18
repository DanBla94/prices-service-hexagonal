package com.store.prices.infrastructure.outbound.persistence.repository;

import com.store.prices.infrastructure.outbound.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
        Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate);
}