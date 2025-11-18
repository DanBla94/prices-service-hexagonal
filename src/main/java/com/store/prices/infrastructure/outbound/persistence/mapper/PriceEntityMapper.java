package com.store.prices.infrastructure.outbound.persistence.mapper;

import com.store.prices.domain.model.Price;
import com.store.prices.infrastructure.outbound.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    Price toDomain(PriceEntity entity);

    List<Price> toDomainList(List<PriceEntity> entities);

    @Mapping(target = "id", ignore = true)
    PriceEntity toEntity(Price price);
}