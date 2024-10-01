package com.electricity.converter;

import com.electricity.model.entity.User;
import com.electricity.model.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author jiaowei
 * @since 2024/9/30 15:50
 */
@Mapper(componentModel = "spring")
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserVo convert(User user);

    List<UserVo> convert(List<User> users);
}
