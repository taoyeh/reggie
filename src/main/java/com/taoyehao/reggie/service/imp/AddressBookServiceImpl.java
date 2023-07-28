package com.taoyehao.reggie.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyehao.reggie.entity.AddressBook;
import com.taoyehao.reggie.mapper.AddressBookMapper;
import com.taoyehao.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
