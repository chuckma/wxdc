package com.wechatorder.demo.dataobject.mapper;
import com.wechatorder.demo.dataobject.ProductCategory;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 *  ================== Mybatis测试 ====================
 * 使用Mybatis，进行数据存储（原视频，使用的是JPA存储，相对简单）
 * 这也就是写了一个简单的Mybatis使用测试，并没有实际在项目中使用
 *
 *  * 使用了两种方式来使用mybatirs：
 *  * （1）注解
 *  * （1）xml配置
 */
public interface ProductCategoryMapper {
    /*************************************** 注解方式 ********************************************/
    //通过map
    @Insert("insert into product_category(category_name, category_type) values (#{category_name, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    //通过对象
    @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),       //把属性名，转成数据库中的列名
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(String categoryName, Integer categoryType);
    //别的版本可能需要加@Param
//    int updateByCategoryType(@Param("categoryName") String categoryName,
//                             @Param("categoryType") Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);


    /*************************************** XML方式 ********************************************/
    //sql语句写在xml文件中
    ProductCategory selectByCategoryType(Integer categoryType);

    ProductCategory selectByCategoryId(Integer categoryId);
}
