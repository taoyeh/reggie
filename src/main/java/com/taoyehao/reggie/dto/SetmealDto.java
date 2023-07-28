package com.taoyehao.reggie.dto;

import com.taoyehao.reggie.entity.Setmeal;
import com.taoyehao.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
