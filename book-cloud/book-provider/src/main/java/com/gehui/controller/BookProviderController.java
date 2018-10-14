package com.gehui.controller;

import com.gehui.entity.BookEntity;
import com.gehui.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by Administrator on 2018/10/13 14:15.
 **/
@RestController
public class BookProviderController {

    @Autowired
    private BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookProviderController.class);

    @RequestMapping("book/{id}")
    public Optional<BookEntity> findBookById(@PathVariable Long id) {
        Optional<BookEntity> book = bookService.findByBookId(id);
        logger.info("查询出来的图书信息" + book.get().toString());
        return book;
    }
}
