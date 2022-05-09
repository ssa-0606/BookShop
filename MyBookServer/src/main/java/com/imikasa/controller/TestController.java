package com.imikasa.controller;

import com.imikasa.mapper.BookMapper;
import com.imikasa.pojo.Book;
import com.imikasa.result.CommonResult;
import com.imikasa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mikasa
 * msg: 测试用的
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private BookService bookService;

    @GetMapping("/test1/{id}")
    public CommonResult<Book> test1(@PathVariable("id") int id){
        CommonResult<Book> book = bookService.findBook(id);
        return book;
    }


}
