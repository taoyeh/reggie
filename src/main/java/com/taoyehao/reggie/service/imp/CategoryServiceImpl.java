package com.taoyehao.reggie.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyehao.reggie.common.CustomException;
import com.taoyehao.reggie.entity.Category;
import com.taoyehao.reggie.entity.Dish;
import com.taoyehao.reggie.entity.Setmeal;
import com.taoyehao.reggie.mapper.CategoryMapper;
import com.taoyehao.reggie.service.CategoryService;
import com.taoyehao.reggie.service.DishService;
import com.taoyehao.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private  DishService dishService;
    @Autowired
    private SetmealService setmealService;
    /**
     * 根据id来删除分类
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper =new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        if(count1>0){
            throw new CustomException("当前分类下关联了菜品，不能删除！");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper =new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if(count2>0){
            throw new CustomException("当前分类下关联了套餐，不能删除！");
        }
        super.removeById(id);
    }
}
