<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>导航栏demo</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入前台展示页部分的公共头部--%>
    <%@ include file="./../../common/frontShow/headBase.jsp"%>
    <%--引入商展页的资源文件--%>
    <link rel="stylesheet" type="text/css" href="static/css/store.css">
    <script src="static/js/store.js"></script>
    <!--插件引入-->
    <!--一个滑块插件---开始0-->
    <link rel="stylesheet" type="text/css" href="static/css/plugins/normalize.min.css">
    <link rel="stylesheet" type="text/css" href="static/css/plugins/ion.rangeSlider.css">
    <link rel="stylesheet" type="text/css" href="static/css/plugins/ion.rangeSlider.skinFlat.css">
    <script type="text/javascript" src="static/js/plugins/ion.rangeSlider.js"></script>
    <!--一个滑块插件---结束0-->
</head>

<body>
    <!-- 外层包装开始 -->
    <div class="wrapper">

        <%--引入两个导航栏--%>
        <%@ include file="./../../common/frontShow/topNav.jsp"%>
        <%@ include file="./../../common/frontShow/leftNav.jsp"%>

        <!--右侧的显示内容开始-->
        <%@ include file="./../../common/frontShow/storeMain.jsp"%>
        <!--右侧的显示内容结束-->
    </div>
    <!-- 外层包装结束 -->
    <!-- 模态框开始 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="good-info-img">
                        <!-- 模态框轮播部分 -->
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox">
                                <div class="item active">
                                    <img src="static/images/book1.jpg" alt="...">
                                </div>
                                <div class="item">
                                    <img src="static/images/book1.jpg" alt="...">
                                </div>
                                <div class="item">
                                    <img src="static/images/book1.jpg" alt="...">
                                </div>
                            </div>

                            <!-- Controls -->
                            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                    <div class="good-info-document">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h1>Metasploit渗透测试魔鬼训练营</h1>
                        <div class="base-info">
                            <!--采用表格的方式展示商品的信息-->
                            <table>
                                <tr>
                                    <td>价格：15元</td>
                                    <td>邮费：5元</td>
                                </tr>
                                <tr>
                                    <td>新旧程度：8成新</td>
                                    <td>新书比价：0.8</td>
                                </tr>
                                <tr>
                                    <td>库存：25</td>
                                    <td>发货地点：天津</td>
                                </tr>
                                <tr>
                                    <td>作者：诸葛建伟/陈力波/田繁等 </td>
                                    <td>出版社：机械工业出版社</td>
                                </tr>
                                <tr>
                                    <td>出版时间：2015-5-15</td>
                                    <td>ISBN:9787111434993 </td>
                                </tr>
                            </table>


                        </div>
                        <div class="detail-info">
                            <h2>概述</h2>
                            <p>《三国志·魏志·王粲传》：“﹝蔡邕﹞闻 粲 在门，倒屣迎之。粲 至，年既幼弱，容状短小，一坐尽惊。邕 曰：‘此王公孙也，有异才，吾不如也。吾家书籍文章，尽当与之。’” 　　宋苏轼《论高丽进奉状》：“使者所至，图画山川，购买书籍。” 　　清王士禛《池北偶谈·谈异六·焦桂花》：“曹升六 舍人，曾於内库检视书籍。” 　　冰心《超人》：“他略略的点一点头，便回身去收拾他的书籍。”
                            </p>
                        </div>
                        <div class="opera">
                            <button class="btn btn-primary">立即购买</button>
                            <button class="btn btn-success">加入购物车</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 模态框结束 -->





</body>

</html>