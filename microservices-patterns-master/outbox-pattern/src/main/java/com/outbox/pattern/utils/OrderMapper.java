package com.outbox.pattern.utils;

import com.outbox.pattern.domain.OrderDomain;
import com.outbox.pattern.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDomain orderDomainToOrderEntity(OrderEntity orderDomain);
    OrderEntity orderEntityToOrderDomain(OrderDomain orderDomain);
}