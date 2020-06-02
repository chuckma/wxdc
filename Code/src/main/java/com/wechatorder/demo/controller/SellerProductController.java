package com.wechatorder.demo.controller;

import com.wechatorder.demo.dataobject.ProductCategory;
import com.wechatorder.demo.dataobject.ProductInfo;
import com.wechatorder.demo.exception.SellException;
import com.wechatorder.demo.form.ProductForm;
import com.wechatorder.demo.service.CategoryService;
import com.wechatorder.demo.service.ProductService;
import com.wechatorder.demo.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 卖家商品控制端
 * Created by Chris on 2020/4/13.
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 显示商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "5") Integer size,
                       ModelMap map){
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return "product/list";
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/on_sale")
    public String onSale(@RequestParam("productId") String productId,
                         ModelMap map){
        try{
            productService.onSale(productId);
        }
        catch(SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "sell/seller/product/list");
            return "common/error";
        }
        map.put("url", "/sell/seller/product/list");
        return "common/success";
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sale")
    public String offSale(@RequestParam("productId") String productId,
                          ModelMap map){
        try{
            productService.offSale(productId);
        }
        catch(SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return "common/error";
        }
        map.put("url", "/sell/seller/product/list");
        return "common/success";
    }

    /**
     * 修改商品
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public String index(@RequestParam(value = "productId", required = false) String productId,
                        ModelMap map) {
        if (!StringUtils.isEmpty(productId)) {
            Optional<ProductInfo> productInfo = productService.findOne(productId);
            //这个productInfo 会传入/product/index中使用
            map.put("productInfo", productInfo.get());

        }
        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return "product/index";
    }

    /**
     * 保存更改的商品信息
     * @return
     */
    @PostMapping("/save")
    //CachePut更新缓存 每次都会执行 CacheEvict清除缓存
    //由于返回类型ModelAndView不能序列化，故save后出错，所以用CacheEvict
//    @CachePut(cacheNames = "product", key = "123")
    @CacheEvict(cacheNames = "product", key = "123")
    public String save(@Valid ProductForm form,
                       BindingResult bindingResult,
                       ModelMap map){
        //当要存入的数据类型不正确时，返回错误界面
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return "common/error";
        }
        //保存商品信息
        Optional<ProductInfo> productInfo = Optional.of(new ProductInfo());
        try{
            //不知道为啥，浏览器提交的商品id后会跟上","符号
            if(form.getProductId().contains(",")){
                String id = form.getProductId().replace(",", "");
                form.setProductId(id);
            }
            //若productId为空，说明是新产品
            if(productService.findOne(form.getProductId()).isPresent()){
                productInfo = productService.findOne(form.getProductId());
            }
            else{   //新产品
                form.setProductId(form.getProductId());
                productInfo.get().setCreateTime(new Date());
            }
            productInfo.get().setUpdateTime(new Date());
            BeanUtils.copyProperties(form, productInfo.get());
            productService.save(productInfo.get());
        }
        catch(SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return "common/error";
        }
        map.put("url", "/sell/seller/product/index");
        return "common/success";
    }
}
