<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈
  Date: 2019/7/14
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<%--%>
    <%--String path = request.getContextPath();--%>
    <%--String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";--%>
<%--%>--%>
<head>
    <%--<base href="<%=basePath%>">--%>
    <script src="static/js/jquery-3.3.1.js"></script>
</head>
<body>

<button id="b1">点我发送数据到后台</button>
<button id="b2">点我发送数据到后台2</button>
<form action="Items/AddItems" method="post" enctype="multipart/form-data">
    文件;<input type="file" name="file"><br>
    文件;<input type="file" name="file"><br>
    isbn: <input type="text" name="isbn"><br>
    <input type="submit">

</form>

<form action="Items/query/itemById" method="post">
    itemID: <input type="text" name="itemID"><br>
    <input type="submit">
</form>
<div>${item}</div>
<div>${extraData}</div>
<div>${errorMap}</div>
<div>${filename}</div>
<div>
    itemPicturePath2:
    <c:forEach items="${itemPicturePath2}" var="obj">
        ${obj}
    </c:forEach>
</div>
<div>
    itemPicturePath:${itemPicturePath}

</div>

<script>
        $("#b2").click(function () {
            var obj={
                'name':'a',
                "cId":"22",
                "price":"",
                "count":"",
                "author":"1",
                "isbn":"1111111111",
                "oldLevel":"1",
                "compare":"0.5",
                "describe":"123123",
                "publishTime":"189",
                "publish":"1",
                "place":"湖南",
                "label":""
            };
            var obj2=${extraData};
            $.ajax({
                url:'Items/addItems',
                dataType:'json',
                type:'post',
                contentType:'application/json',
                // data:JSON.stringify(obj),
                data:JSON.stringify(obj2),
                success:function (data){
                    alert("成功")

                  },error:function () {
                    alert("失败")
                }
                //   ,error:function () {
                //     alert("error");
                // }


            })
        })



</script>

</body>
</html>
