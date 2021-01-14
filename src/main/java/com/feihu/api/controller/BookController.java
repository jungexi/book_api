package com.feihu.api.controller;

import com.feihu.api.model.po.Book;
import com.feihu.api.model.vo.BookParamsVO;
import com.feihu.api.model.vo.ResultData;
import com.feihu.api.service.BookService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/book/")
public class BookController {
    @Autowired  //autoware 和resource区别
    private BookService bookService;

    /*
    * 查询书籍信息
    * 路径： http://localhost:8082/api/book/list
    * 请求方式 get
    *
    * 参数
     * currPage （当前页）  （必选）
     *
     * size （每页条数） （必选）
     *
     * name   (按照名字准确查询)  （可选项）
    *
    * bdate   出版时间  最小值  （可选）
    *
    * ddate   出版时间 最大值  （可选）
    *
    *
    * 返回值
    *  code  message  data
    *
    * */
    @PostMapping("list")
    @CrossOrigin
    public ResultData list(BookParamsVO vo){
        /*  校验参数   可以用枚举  */
        if(vo.getCurrPage()==null){
            return ResultData.error(400,"非法请求");
        }

        if(vo.getSize()==null){
            return ResultData.error(400,"非法请求");
        }
        //处理参数
        if(!StringUtils.isEmpty(vo.getPeoples())){
            String[] split = vo.getPeoples().split(",");
            //数组直接转为list
            List<String> ps = Arrays.asList(split);
            vo.setPs(ps);
        }
        //处理业务了   查询书籍信息    书籍的currPage的数据   书籍的总条数  map  （list  count）
        Map rs = bookService.queryBookPageData(vo);
        return  ResultData.success(rs);
    }
    /*
        添加书籍信息

    * 路径： http://localhost:8082/api/book/add
    * 请求方式 post
    *
    * 参数

    * 返回值
    *
     */
    @PostMapping("add")
    public ResultData addBook(Book book){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(book.getCreateDate()));
        bookService.addBook(book);
        return ResultData.success("");
    }

    /*
       修改书籍信息

   * 路径： http://localhost:8082/api/book/update
   * 请求方式 post
   *
   * 参数

   * 返回值
   *
    */
    @PostMapping("update")
    public ResultData update(Book book){
        bookService.updateBook(book);
        return ResultData.success("");
    }
    /*
     修改书籍信息

 * 路径： http://localhost:8082/api/book/queryBookById
 * 请求方式 get
 *
 * 参数
        id
 * 返回值
 *
  */
    @GetMapping("queryBookById")
    public ResultData queryBookById(Integer id){
        if(id==null){
            return ResultData.error(400,"非法请求");
        }
        Book book = bookService.queryBookById(id);
        return ResultData.success(book);
    }
    /*
     删除书籍信息

 * 路径： http://localhost:8082/api/book/delBookById
 * 请求方式 delete
 *
 * 参数
        id
 * 返回值
 *
  */
    @DeleteMapping("delBookById")
    public ResultData delBookById(Integer id){
        if(id==null){
            return ResultData.error(400,"非法请求");
        }
         bookService.delBookById(id);
        return ResultData.success("");
    }

    @RequestMapping("upload")
    public ResultData upload(MultipartFile img){
            return  ResultData.success("www.bai.com");
    }
}
