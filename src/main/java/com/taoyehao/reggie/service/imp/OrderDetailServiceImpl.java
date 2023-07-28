package com.taoyehao.reggie.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyehao.reggie.entity.OrderDetail;
import com.taoyehao.reggie.mapper.OrderDetailMapper;
import com.taoyehao.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}