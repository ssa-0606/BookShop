package com.imikasa.service;

import com.imikasa.pojo.Book;
import com.imikasa.result.CommonResult;

import java.util.List;

/**
 * @author Mikasa
 */
public interface BookService {

    CommonResult<Book> addBook(Book book);
    CommonResult<Integer> deleteBook(int id);
    CommonResult<Book> updateBook(Book book);
    CommonResult<Book> findBook(int id);
    CommonResult<List<Book>> findAll();
}
