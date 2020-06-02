package com.wechatorder.demo.controller;

import com.wechatorder.demo.dataobject.ProductCategory;
import com.wechatorder.demo.dataobject.ProductInfo;
import com.wechatorder.demo.exception.SellException;
import com.wechatorder.demo.form.CategoryForm;
import com.wechatorder.demo.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 卖家商品类目控制端
 * Created by Chris on 2020/4/14.
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 类目表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public String list(ModelMap map){
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return "category/list";
    }

    /**
     * 根据id显示类目
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public String index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                        ModelMap map){
        if(categoryId != null){
            Optional<ProductCategory> productCategory = categoryService.findOne(categoryId);
            map.put("category", productCategory.get());
        }
        return "category/index";
    }

    @PostMapping("/save")
    public String save(@Valid CategoryForm form,        //浏览器传入的数据
                       BindingResult bindingResult,             //传入的参数
                       ModelMap map){                           //使用模板显示
        //当要存入的数据类型不正确时，返回错误界面
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return "common/error";
        }
        //保存类目信息
        Optional<ProductCategory> productCategory = Optional.of(new ProductCategory());
        try{
            //若productId为空，说明是新产品
            if(categoryService.findOne(form.getCategoryId()).isPresent()){
                productCategory = categoryService.findOne(form.getCategoryId());
            }
            else{   //新产品
                form.setCategoryId(form.getCategoryId());
                productCategory.get().setCreateTime(new Date());
            }
            productCategory.get().setUpdateTime(new Date());
            BeanUtils.copyProperties(form, productCategory.get());
            categoryService.save(productCategory.get());
        }
        catch(SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return "common/error";
        }
        map.put("url", "/sell/seller/category/list");
        return "common/success";
    }
}
