package com.wechatorder.demo.controller;

import com.wechatorder.demo.VO.ProductInfoVO;
import com.wechatorder.demo.VO.ProductVO;
import com.wechatorder.demo.VO.ResultVO;
import com.wechatorder.demo.dataobject.ProductCategory;
import com.wechatorder.demo.dataobject.ProductInfo;
import com.wechatorder.demo.service.CategoryService;
import com.wechatorder.demo.service.ProductService;
import com.wechatorder.demo.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 买家商品
 * Created by Chris on 2020/3/2.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    //注意：自动装配需要在对应的类上添加注解，eg：@Service， 否则会出现无法自动装配的错误
    //装配的是 ProductServiceImpl（类），不是ProductServiceI（接口）
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询已上架的商品信息：
     * 测试链接：http://localhost:8080/sell/buyer/product/list
     * @return
     */
    //===== 用于测试redis缓存功能 =====
    // 数据缓存在redis中，刷新后返回的数据是从redis里面取出来的
    @Cacheable(cacheNames = "product", key = "123")
    @GetMapping("/list")
    public ResultVO list(){
        //1、查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2、查询所有的类目(一次性查清)：获取已上架商品 --> 获取对应的商品类目编号 --> 根据类目编号获取商品类目表
        List<Integer> categoryTypeList = new ArrayList<>();
        for(ProductInfo productInfo : productInfoList){
            //获取已上架商品的类别
            categoryTypeList.add(productInfo.getCategoryType());
        }
        //根据类别获取商品的类目的详情
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3、数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        //根据商品的类目详情，罗列每个类目下的商品详情 （只针对已上架商品）
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        //在页面显示的数据
        return ResultVOUtil.success(productVOList);
    }

}
