package com.store.prices.infrastructure.inbound.mapper;

import com.store.prices.domain.model.Price;
import com.store.prices.infrastructure.inbound.dto.PriceResponse;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface PriceDtoMapper {

    PriceResponse toResponse(Price price);

    default OffsetDateTime map(LocalDateTime value) {
        if (value == null) {
            return null;
        }

        return value.atOffset(ZoneOffset.UTC);
    }
}