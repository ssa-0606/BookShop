package com.imikasa.controller;

import com.imikasa.pojo.Book;
import com.imikasa.result.CommonResult;
import com.imikasa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mikasa
 */
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public CommonResult<Book> addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @DeleteMapping("/book/{id}")
    public CommonResult<Integer> deleteBook(@PathVariable("id") int id){
        return bookService.deleteBook(id);
    }

    @PutMapping("/book")
    public CommonResult<Book> updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @GetMapping("/book/{id}")
    public CommonResult<Book> findById(@PathVariable("id") int id){
        return bookService.findBook(id);
    }

    @GetMapping("/book")
    public CommonResult<List<Book>> findAll(){
        return bookService.findAll();
    }

}
