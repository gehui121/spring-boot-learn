package com.gehui.service;

import com.gehui.entity.BookEntity;

import java.util.Optional;

/**
 * Created by Administrator on 2018/10/13 14:05.
 **/
public interface BookService {
    //定义根据ID查询图书信息
    Optional<BookEntity> findByBookId(Long id);
}
