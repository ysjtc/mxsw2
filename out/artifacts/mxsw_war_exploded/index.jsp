<%--
  Created by IntelliJ IDEA.
  User: 10904
  Date: 2019/8/6
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--引入basePath--%>
    <%@ include file="./WEB-INF/jsp/common/basePath.jsp"%>
    <%--引入前台展示页部分的公共头部--%>
    <%@ include file="./WEB-INF/jsp/common/frontShow/headBase.jsp"%>
    <link rel="stylesheet" type="text/css" href="static/css/articleMain.css">
</head>
<body>
<div class="wrapper">
<!-- 外层包装开始 -->
    <%--引入两个导航栏--%>
    <%@ include file="./WEB-INF/jsp/common/frontShow/topNav.jsp"%>
    <%@ include file="./WEB-INF/jsp/common/frontShow/leftNav.jsp"%>


</div>
</body>

</html>
