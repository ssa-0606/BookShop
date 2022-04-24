package com.imikasa.service.impl;

import com.imikasa.mapper.BookMapper;
import com.imikasa.pojo.Book;
import com.imikasa.result.CommonResult;
import com.imikasa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
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
    public CommonResult<Integer> deleteBook(int id) {
        int i = bookMapper.deleteBook(id);
        if (i>0){
            return new CommonResult<>(200,"success",i);
        }else{
            return new CommonResult<>(400,"failed",i);
        }
    }

    @Override
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

    @Override
    public CommonResult<Book> findBook(int id) {
        if(id<=0){
            return new CommonResult<>(400,"id must dayu 0",null);
        }
        Book book = bookMapper.findBook(id);
        return new CommonResult<>(200,"success",book);
    }

    @Override
    public CommonResult<List<Book>> findAll() {
        return new CommonResult<>(200,"success",bookMapper.findAllBook());
    }
}
