package com.feihu.api.dao;

import com.feihu.api.model.po.Book;
import com.feihu.api.model.vo.BookParamsVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/* 持久层   @map   每一个都要加      可以在启动类上加 @mapscan （扫描dao层）  */
public interface BookDao {

    /*  通过注解来完成 查询   */

    /*  通过mapper映射文件   */

    public Integer queryBookCount(BookParamsVO vo);
     /*  这里的list 必须加泛型 */
    public List<Book> queryBookDataByParams(BookParamsVO vo);
    @Insert("insert into t_book (name,price,type,peoples,createdate,area) value (#{name},#{price},#{type},#{peoples},#{createDate},#{area})")
    public void insertBook(Book book);
    @Update("update t_book set name=#{name},price=#{price},type=#{type},peoples=#{peoples},createdate=#{createDate},area=#{area} where id=#{id}")
    public void updateBook(Book book);
    @Select("select * from t_book where id =#{id}")
    public Book queryBookById(Integer id);
    @Delete("delete from t_book where id=#{1}")
    public void delBookById(Integer id);

}
