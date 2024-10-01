package com.electricity.mapper;

import com.electricity.model.entity.NowElectricity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author JiaoWei
* @description 针对表【now_electricity】的数据库操作Mapper
* @createDate 2024-09-30 21:50:29
* @Entity com.electricity.model.entity.NowElectricity
*/
@Mapper
public interface NowElectricityMapper extends BaseMapper<NowElectricity> {
    @Delete("delete from now_electricity where update_time < #{beforeNow}")
    void deleteNow(String beforeNow);

    //    @Update("update now_electricity set descript = 0 where update_time <= #{nowTime}")
    int updateNow(NowElectricity nowElectricity);

    @Insert("insert into now_electricity(price,descript,create_time,update_time) values (#{price}, #{descript}, now(), #{updateTime})")
    void insertNow(NowElectricity nowElectricity);

    @Select("select  * from now_electricity" +
            " where update_time between #{beforeNow} and #{afterNow}")
    List<NowElectricity> getNow(String beforeNow, String afterNow);

    @Update("update now_electricity set descript = 0 where update_time <= #{format}")
    void updateDescript(String format);
}




