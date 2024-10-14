package com.electricity.converter;

import com.electricity.model.entity.Tractics;
import com.electricity.model.entity.UserOrder;
import com.electricity.model.vo.TracticsVo;
import com.electricity.model.vo.UserOrderVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author jiaowei
 * @since 2024/9/30 15:50
 */
@Mapper(componentModel = "spring")
public interface TracticsConverter {
    TracticsConverter INSTANCE = Mappers.getMapper(TracticsConverter.class);

    TracticsVo convert(Tractics tractics);

    List<TracticsVo> convert(List<Tractics> tractics);
}
