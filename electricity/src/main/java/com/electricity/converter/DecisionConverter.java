package com.electricity.converter;

import com.electricity.model.entity.Decision;
import com.electricity.model.entity.UserOrder;
import com.electricity.model.vo.DecisionVo;
import com.electricity.model.vo.UserOrderVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author jiaowei
 * @since 2024/9/30 15:50
 */
@Mapper(componentModel = "spring")
public interface DecisionConverter {
    DecisionConverter INSTANCE = Mappers.getMapper(DecisionConverter.class);

    DecisionVo convert(Decision decision);

    List<DecisionVo> convert(List<Decision> decisions);
}
