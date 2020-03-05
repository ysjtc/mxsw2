<%--
  Created by IntelliJ IDEA.
  User: ysjba
  Date: 2020/3/4
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
我发布的文章：<a href="<%=request.getContextPath()%>/Article/query/myInfo?id=<%=request.getSession().getAttribute("uId")%>">${my_a_c}篇</a><br/>

<a href="<%=request.getContextPath()%>/Article/query/uId?uId=<%=request.getSession().getAttribute("uId")%>" name="<%=request.getSession().getAttribute("uId")%>">我发布的文章</a>
累计获得点赞：${my_p_c}次<br/>
<a href="<%=request.getContextPath()%>/Article/query/myPraise?id=<%=request.getSession().getAttribute("uId")%>">我点赞的文章</a>
</body>
</html>
