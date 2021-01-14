package com.feihu.api.service.impl;

import com.feihu.api.dao.BookDao;
import com.feihu.api.model.po.Book;
import com.feihu.api.model.vo.BookParamsVO;
import com.feihu.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Override
    public Map queryBookPageData(BookParamsVO vo) {
        Map rs=new HashMap();
        //查询总条数
        Integer integer = bookDao.queryBookCount(vo);
        rs.put("count",integer);
        //查询分页数据
        List<Book> books = bookDao.queryBookDataByParams(vo);
        rs.put("list",books);
        return rs;
    }

    @Override
    public void addBook(Book book) {
        bookDao.insertBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public void delBookById(Integer id) {
        bookDao.delBookById(id);
    }

}
