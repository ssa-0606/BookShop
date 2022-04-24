package com.imikasa.mapper;

import com.imikasa.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Mikasa
 */
@Mapper
public interface BookMapper {

    int addBook(Book book);
    int deleteBook(int id);
    int updateBook(Book book);
    Book findBook(int id);
    List<Book> findAllBook();
}
