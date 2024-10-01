package com.electricity.mapper;

import com.electricity.model.entity.Electricity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author JiaoWei
* @description 针对表【electricity】的数据库操作Mapper
* @createDate 2024-09-30 21:50:29
* @Entity com.electricity.model.entity.Electricity
*/
@Mapper
public interface ElectricityMapper extends BaseMapper<Electricity> {

    List<Electricity> getElec(String dateTime);

    void deleteBefore(String dateTime);

    void insertElec(Electricity list);

    void deleteAfter(String now);

    int updateElec(Electricity electricity);
}




