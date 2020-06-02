<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--内容-->
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <#--表单提交的地址-->
                <form role="form" method="post" action="/sell/seller/product/save">
                    <div class="form-group">
                        <label for="商品ID">商品ID</label>
                        <input name="productId" type="text" class="form-control" value="${(productInfo.productId)!''}" />
                    </div>
                    <div class="form-group">
                        <label for="商品名称">商品名称</label>
                        <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}" />
                    </div>
                    <div class="form-group">
                        <label for="价格">价格</label>
                        <input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!''}" />
                    </div>
                    <div class="form-group">
                        <label for="库存">库存</label>
                        <input name="productStock" type="number" class="form-control" value="${(productInfo.productName)!''}" />
                    </div>
                    <div class="form-group">
                        <label for="描述">描述</label>
                        <input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!''}" />
                    </div>
                    <div class="form-group">
                        <label for="图片">库存</label>
                        <img height="100" width="100" src="${(productInfo.productIcon)!''}" alt="">
                        <input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!''}" />
                    </div>
                    <div class="form-group">
                        <label for="类目">类目</label>
                        <select name="categoryType" class="form-control">
                            <#list categoryList as category>
                                <option value="${category.categoryType}"
                                        <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                            selected
                                        </#if>
                                        >${category.categoryName}
                                </option>
                            </#list>
                        </select>
                    </div>
                    <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                    <button type="submit" class="btn btn-default btn-success">提交</button>
                </form>
            </div>
        </div>
    </div>

</div>
</body>
</html>