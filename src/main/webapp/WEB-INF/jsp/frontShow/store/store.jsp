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
                                
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox">
                                
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
                        <h1 id="modal_item_name"></h1>
                        <div class="base-info">
                            <!--采用表格的方式展示商品的信息-->
                            <table>
                                <tr>
                                    <td>价格：<span id="modal_item_price"></span>元</td>
                                    <td>邮费：<span id="modal_item_postage"></span>元</td>
                                </tr>
                                <tr>
                                    <td>新旧程度：<span id="modal_item_oldLevel"></span>成新</td>
                                    <td>新书比价：<span id="modal_item_compare"></span></td>
                                </tr>
                                <tr>
                                    <td>库存：<span id="modal_item_count"></span></td>
                                    <td>发货地点：<span id="modal_item_place"></span></td>
                                </tr>
                                <tr>
                                    <td>作者：<span id="modal_item_author"></span> </td>
                                    <td>出版社：<span id="modal_item_publish"></span></td>
                                </tr>
                                <tr>
                                    <td>出版时间：<span id="modal_item_publishTime"></span></td>
                                    <td>ISBN:<span id="modal_item_ISBN"></span></td>
                                </tr>
                            </table>


                        </div>
                        <div class="detail-info">
                            <h2>概述</h2>
                            <p id="modal_item_desc">
                                
                            </p>
                        </div>
                        <div class="opera">
                            <button class="btn btn-primary buyNow">立即购买</button>
                            <button class="btn btn-success">加入购物车</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 模态框结束 -->

    <!--一个隐藏的有关支付的提交input-->
    <div style="display: none;">
       <input type="text" name="out_trade_no" id="out_trade_no" value=""><br>
       <input type="text" name="subject" id="subject" value=""><br>
       <input type="text" name="total_amount" id="total_amount" value=""><br>
       <input type="text" name="body" id="body" value=""><br>
    </div>




</body>

</html>