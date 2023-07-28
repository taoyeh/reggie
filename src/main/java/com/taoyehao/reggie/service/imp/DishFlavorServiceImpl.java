package com.taoyehao.reggie.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyehao.reggie.entity.DishFlavor;
import com.taoyehao.reggie.mapper.DishFlavorMapper;
import com.taoyehao.reggie.mapper.DishMapper;
import com.taoyehao.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
