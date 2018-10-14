package com.gehui.service.impl;

import com.gehui.entity.BookEntity;
import com.gehui.jpa.BookJPA;
import com.gehui.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Administrator on 2018/10/13 14:06.
 **/
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookJPA bookDao;

    @Override
    public Optional<BookEntity> findByBookId(Long id) {
        return bookDao.findById(id);
    }
}
