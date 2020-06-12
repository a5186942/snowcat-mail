<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<title>添加商品分类</title>
</head>
<body>
<div style="padding: 15px; background-color: #FFFFFF;height: 100%">
<button id = "initSolr" type="button" class="layui-btn layui-btn-normal">导入solr索引库</button>
</div>

    <script type="text/javascript">
        $(function () {
            var layer;
            layui.use('layer', function(){
                layer = layui.layer;


            });
            $("#initSolr").click(function () {
                $.ajax({
                    type: "get",
                    url: "/search/importSolr",
                    success: function(msg){
                        layer.alert(msg.msg);
                    },
                    error: function (msg) {
                        layer.alert(msg.msg)
                    }
                });

            });
        })



</script>
</body>
</html>