<html>
    <head>
        <meta charset="UTF-8">
        <title>成功提示</title>
        <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="alert alert-dismissable alert-success">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h4>
                            操作成功！！
                        </h4>
                        <strong>操作代码：${msg!""}</strong><br>
                        <a href="${url}" class="alert-link">3s后自动返回</a>
<#--                        </h4> <strong>${msg!""}</strong><a href="${url}" class="alert-link">3s后自动返回</a>-->
                    </div>
                </div>
            </div>
        </div>
    </body>

    <script>
        //设置3s后，自动返回指定url
        setTimeout('location.href="${url}"',3000)
    </script>

</html>