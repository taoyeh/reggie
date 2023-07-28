package com.taoyehao.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyehao.reggie.dto.DishDto;
import com.taoyehao.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    //新增菜品功能，同时插入菜品对应的口味数据，需要同时操作两张表：dish，dish_flavor
    public void saveWithFlavor(DishDto dishDto);
    //寻找菜品id寻找菜品和口味数据
    public DishDto getByIdWithFlavor(Long id);
    //修改菜品，更新dish和dishFlavor
    public void updateWithdishFlavor(DishDto dishDto);

    //删除菜品对应的口味数据
    public void deleteWithFlavor(Long ids);
}
