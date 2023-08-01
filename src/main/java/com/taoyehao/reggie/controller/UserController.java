package com.taoyehao.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taoyehao.reggie.common.R;
import com.taoyehao.reggie.entity.User;
import com.taoyehao.reggie.service.UserService;
import com.taoyehao.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送手机短信验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)){
            //生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("phone={},code={}",phone,code);
            //调用阿里云api发送信息
            //SMSUtils.sendMessage("瑞吉外卖","",phone,code);
            session.setAttribute(phone,code);
            //生成的验证码缓存到redis当中，并设置有效期为5分钟
            redisTemplate.opsForValue().set(phone,code,5,TimeUnit.MINUTES);

            return R.success("发送验证码成功！");
        }
        return R.success("手机号码为空！");
    }

    /**
     * 移动端用户登录
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){

        String phone = map.get("phone").toString();
        String code = map.get("code").toString();

        //获取真实的验证码值
        //Object codeInSession = session.getAttribute(phone);
        Object codeInSession = redisTemplate.opsForValue().get(phone);

        if(codeInSession!=null && code.equals(codeInSession)){
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if(user == null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());

            //如果用户登录成功,则删除redis中验证码
            redisTemplate.delete(phone);
            return R.success(user);
        }

        return R.error("登录失败！");
    }

}
