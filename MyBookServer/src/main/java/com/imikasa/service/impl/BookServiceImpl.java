package com.imikasa.service.impl;

import com.imikasa.mapper.BookMapper;
import com.imikasa.pojo.Book;
import com.imikasa.result.CommonResult;
import com.imikasa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "book-cache")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    @CacheEvict(key = "'LIST'")
    public CommonResult<Book> addBook(Book book) {
        if(book == null){
            return new CommonResult(400,"you must send args...",null);
        }
        if(book.getId()!=null){
            return new CommonResult(400,"id must null",null);
        }
        if(book.getAuthor().isBlank()||book.getCover().isBlank()||book.getName().isBlank()){
            return new CommonResult(400,"args is wrong",null);
        }

        int i = bookMapper.addBook(book);
        if(i>0){
            return new CommonResult<>(200,"success",book);
        }else{
            return new CommonResult<>(500,"have a error",null);
        }
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = "#id"),
            @CacheEvict(key = "'LIST'")
    })
    public CommonResult<Integer> deleteBook(int id) {
        int i = bookMapper.deleteBook(id);
        if (i>0){
            return new CommonResult<>(200,"success",i);
        }else{
            return new CommonResult<>(400,"failed",i);
        }
    }

    @Override
    @CachePut(key = "#book.getId()")
    @CacheEvict(key = "'LIST'")
    public CommonResult<Book> updateBook(Book book) {
        if(book==null){
            return new CommonResult<>(400,"you must send args ...",null);
        }
        if(book.getPrice().isBlank()||book.getName().isBlank()||book.getCover().isBlank()||book.getAuthor().isBlank()){
            return new CommonResult<>(400,"args is wrong",book);
        }
        int i = bookMapper.updateBook(book);
        if (i>0){
            return new CommonResult<>(200,"success",book);
        }else{
            return new CommonResult<>(400,"failed",book);
        }
    }


    public CommonResult<Book> findBook1(int id) {
        if(id<=0){
            return new CommonResult<>(400,"id must dayu 0",null);
        }
        Book result = (Book) redisTemplate.opsForValue().get("book"+id);
        if(result != null){
            return new CommonResult<>(200,"success",result);
        }
        Book book = bookMapper.findBook(id);
        redisTemplate.opsForValue().set("book"+id,book);
        return new CommonResult<>(200,"success",book);
    }
    @Cacheable(key = "#id")
    @Override
    public CommonResult<Book> findBook(int id){
        if(id<=0){
            return new CommonResult<>(400,"id must dayu 0",null);
        }
        Book book = bookMapper.findBook(id);
        return new CommonResult<>(200,"success",book);
    }

    @Override
    @Cacheable(key = "'LIST'")
    public CommonResult<List<Book>> findAll() {
        return new CommonResult<>(200,"success",bookMapper.findAllBook());
    }




}
