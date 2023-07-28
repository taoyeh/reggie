package com.taoyehao.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taoyehao.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}