package com.siemens.allkindsoftest.mapper;


import com.siemens.allkindsoftest.model.FirstModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Simons on 2017/5/22.
 */
@Repository
public interface FirstMapper{

    @Select("SELECT * FROM historical WHERE id = #{Id}")
    FirstModel selectModelById(@Param("Id") String id);

}
