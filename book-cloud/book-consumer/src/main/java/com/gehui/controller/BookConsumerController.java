package com.gehui.controller;

import com.gehui.vo.BookVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/10/13 15:56.
 **/
@RestController
public class BookConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(BookConsumerController.class);

    @RequestMapping("wrap/book/{bookId}")
    public BookVo findBookById(@PathVariable Long bookId) {

        logger.info("查询的ID是：" + bookId);
        BookVo book = restTemplate.getForObject("http://localhost:7003/book/" + bookId, BookVo.class);
        return book;
    }
}
