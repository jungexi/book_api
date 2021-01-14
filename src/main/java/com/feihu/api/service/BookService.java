package com.feihu.api.service;

import com.feihu.api.model.po.Book;
import com.feihu.api.model.vo.BookParamsVO;

import java.util.Map;

public interface BookService {

    public Map queryBookPageData(BookParamsVO vo);
    public void addBook(Book book);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public void delBookById(Integer id);
}
