package com.taoyehao.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyehao.reggie.dto.SetmealDto;
import com.taoyehao.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    public void saveWithSetmealDish(SetmealDto setmealDto);
    public void deleteWithSetmealDish(List<Long> ids);
}
