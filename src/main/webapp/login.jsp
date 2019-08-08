<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈
  Date: 2019/7/4
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>


<%--<%--%>
    <%--String path = request.getContextPath();--%>
    <%--String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;--%>
<%--%>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<base href="<%=basePath%>">--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    登陆
    <form action="SuperAdmin/login">
        id<input type="text" name="superId" value=""><br>
        name<input type="text" name="name" value=""><br>
        password<input type="password" name="password" value=""><br>
        <input type="submit" value="提交">

    </form>
</center>

<center>
    注册
    <form action="SuperAdmin/addSuperadmin" method="post">

        name<input type="text" name="name" value=""><br>
        password<input type="password" name="password" value=""><br>
        <input type="submit" value="提交">

    </form>
</center>
<center>
    查询商品
    <form action="Items/queryAll">

        <input type="submit" value="查询">

    </form>
</center>
<center>
    查询name商品
    <form action="Items/queryItemsByName">
        <input type="text" name="name">
        <input type="submit" value="名字查询">

    </form>
</center>
<center>
    查询ISBN商品
    <form action="Items/ToQueryItemsByIsbn">
        <input type="text" name="isbn">
        <input type="submit" value="ISBN查询">

    </form>
</center>
<center>
    查询价格区间商品
    <form action="Items/queryItemsByPrice">
        fir<input type="text" name="fir">
        last<input type="text" name="last"><br>
        <input type="submit" value="查询">
    </form>
</center>
<form action="Items/AddItems" method="post" enctype="multipart/form-data">
   name: <input type="text"name="name">
   cid <input type="text"name="cId">
    price<input type="text"name="price">
    count<input type="text"name="count">
    author<input type="text"name="author">
    isbn<input type="text"name="isbn">
    oldlevel<input type="text"name="oldLevel">
    compare<input type="text"name="compare">
    describe<input type="text"name="describe">
    pubTime<input type="text"name="publishTime">
    pub<input type="text"name="publish">
    place<input type="text"name="place">
    label<input type="text"name="label">
    文件;<input type="file" name="file"><br>
    文件;<input type="file" name="file"><br>
    <input type="submit">
</form>
itemPicturePath:
<c:forEach items="${itemPicturePath}" var="obj">
    ${obj}
</c:forEach>
<form action="Items/updateItem">
    id<input type="text" name="itemId"><br>
    count<input type="text" name="author">
    <input type="submit" value="更新">
</form>
<form action="Items/deleteItem">
    id<input type="text" name="itemId"><br>

    <input type="submit" value="删除">
</form>
<form action="Items/queryCid">
    查询cid
    <input type="submit" value="查询">
</form>


<c:forEach items="${errorMap}" var="obj">
    ${obj}<br>
</c:forEach>


</body>
</html>
