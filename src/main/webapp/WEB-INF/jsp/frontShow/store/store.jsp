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
        <div class="col-lg-11 col-md-12 col-sm-12 col-xs-12 right-content">
            <div class="row">
                <div class="container-fluid">
                    <!-- 商展顶部--start-- -->
                    <div class="row title">
                        <div class="title-img">
                            <img src="static/images/store_top.jpg" alt="">
                        </div>
                        <div class="search">
                            <input type="text" class="form-control" placeholder="请输入查询的内容">
                            <button type="button"></button>
                        </div>
                    </div>
                    <!-- 商展顶部--end-- -->

                    <!--商展页面的主体--start-->
                    <div class="row main-wrapper">

                        <!--左侧商品展示--start-->
                        <div class="col-lg-9 left-show">
                            <!--一个显示总共find商品的div--->
                            <div class="row item-count">
                                我们总共查到了<span style="color:#4D4D4D">89</span>件商品
                            </div>

                            <div class="row">
                                <!--一个商品展示框---start-->
                                <div class="item-box">
                                    <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 item-box-img">
                                        <div class="img-wrapper">
                                            <img src="static/images/b1.jpg" />
                                            <!--一个遮罩层-->
                                            <div class="item-box-img-mask"></div>
                                            <!--一个查看更多-->
                                            <a href="" data-toggle="modal" data-target="#myModal">
                                                <div class="item-box-img-mask-readMore">查看详细</div>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 item-box-text">
                                        <div class="text-des">
                                            <h3>《Metasploit渗透测试魔鬼训练营》</h3>

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

                                            <!--加入购物车等功能按钮-->
                                            <div class="text-des-buttom">
                                                <a type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#myModal">查看详细</a>
                                                <a type="button" class="btn btn-warning btn-xs">加入购物车</a>
                                                <a type="button" class="btn btn-warning btn-xs">立即购买</a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <!--一个商品展示框---end-->
                            </div>

                            <div class="row">
                                <!--一个商品展示框---start-->
                                <div class="item-box">
                                    <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 item-box-img">
                                        <div class="img-wrapper">
                                            <img src="static/images/b1.jpg" />
                                            <!--一个遮罩层-->
                                            <div class="item-box-img-mask"></div>
                                            <!--一个查看更多-->
                                            <a href="">
                                                <div class="item-box-img-mask-readMore">查看详细</div>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 item-box-text">
                                        <div class="text-des">
                                            <h3>《Metasploit渗透测试魔鬼训练营》</h3>

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

                                            <!--加入购物车等功能按钮-->
                                            <div class="text-des-buttom">
                                                <a type="button" class="btn btn-warning btn-xs">查看详细</a>
                                                <a type="button" class="btn btn-warning btn-xs">加入购物车</a>
                                                <a type="button" class="btn btn-warning btn-xs">立即购买</a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <!--一个商品展示框---end-->
                            </div>

                            <div class="row">
                                <!--一个商品展示框---start-->
                                <div class="item-box">
                                    <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 item-box-img">
                                        <div class="img-wrapper">
                                            <img src="static/images/b1.jpg" />
                                            <!--一个遮罩层-->
                                            <div class="item-box-img-mask"></div>
                                            <!--一个查看更多-->
                                            <a href="">
                                                <div class="item-box-img-mask-readMore">查看详细</div>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 item-box-text">
                                        <div class="text-des">
                                            <h3>《Metasploit渗透测试魔鬼训练营》</h3>

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

                                            <!--加入购物车等功能按钮-->
                                            <div class="text-des-buttom">
                                                <a type="button" class="btn btn-warning btn-xs">查看详细</a>
                                                <a type="button" class="btn btn-warning btn-xs">加入购物车</a>
                                                <a type="button" class="btn btn-warning btn-xs">立即购买</a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <!--一个商品展示框---end-->
                            </div>

                            <div class="row">
                                <!--一个商品展示框---start-->
                                <div class="item-box">
                                    <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 item-box-img">
                                        <div class="img-wrapper">
                                            <img src="static/images/b1.jpg" />
                                            <!--一个遮罩层-->
                                            <div class="item-box-img-mask"></div>
                                            <!--一个查看更多-->
                                            <a href="">
                                                <div class="item-box-img-mask-readMore">查看详细</div>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 item-box-text">
                                        <div class="text-des">
                                            <h3>《Metasploit渗透测试魔鬼训练营》</h3>

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

                                            <!--加入购物车等功能按钮-->
                                            <div class="text-des-buttom">
                                                <a type="button" class="btn btn-warning btn-xs">查看详细</a>
                                                <a type="button" class="btn btn-warning btn-xs">加入购物车</a>
                                                <a type="button" class="btn btn-warning btn-xs">立即购买</a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <!--一个商品展示框---end-->
                            </div>

                            <div class="row">
                                <!--一个商品展示框---start-->
                                <div class="item-box">
                                    <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 item-box-img">
                                        <div class="img-wrapper">
                                            <img src="static/images/b1.jpg" />
                                            <!--一个遮罩层-->
                                            <div class="item-box-img-mask"></div>
                                            <!--一个查看更多-->
                                            <a href="">
                                                <div class="item-box-img-mask-readMore">查看详细</div>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 item-box-text">
                                        <div class="text-des">
                                            <h3>《Metasploit渗透测试魔鬼训练营》</h3>

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

                                            <!--加入购物车等功能按钮-->
                                            <div class="text-des-buttom">
                                                <a type="button" class="btn btn-warning btn-xs">查看详细</a>
                                                <a type="button" class="btn btn-warning btn-xs">加入购物车</a>
                                                <a type="button" class="btn btn-warning btn-xs">立即购买</a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <!--一个商品展示框---end-->
                            </div>

                            <div class="row">
                                <!-- 一个分页 -->
                                <ul class="pagination store-paging">
                                    <li><a href="#"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
                                    <li><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
                                </ul>
                            </div>

                        </div>
                        <!--左侧商品展示---end-->




                        <!--右侧过滤栏--start-->
                        <div class="col-lg-3 right-filter">

                            <!--过滤器-->
                            <div class="right-filter-item filter-item">
                                <h4>
                                    <span class="glyphicon glyphicon-filter"></span> 价格过滤器
                                    <div class="item-line"></div>
                                </h4>
                                <!--提交过滤信息到后台-->
                                <form action="#" method="post">
                                    <!--后台接受的参数的形式为：10;50这样（封号隔开）-->
                                    <input type="text" id="range_1" name="priceRange" />
                                    <button type="submit">过滤</button>
                                </form>

                            </div>


                            <!--分类-->
                            <div class="right-filter-item cate-item">
                                <h4>
                                    <span class="glyphicon glyphicon-list"></span> 商品分类
                                    <div class="item-line"></div>
                                </h4>
                                <!--分类项-->
                                <ul>
                                    <li><a href="">计算机与网络<span>(6)</span></a></li>
                                    <li><a href="">管理<span>(10)</span></a></li>
                                    <li><a href="">经济金融<span>(4)</span></a></li>
                                    <li><a href="">语言学习<span>(4)</span></a></li>
                                    <li><a href="">文学艺术<span>(3)</span></a> </li>
                                    <li><a href="">科技工程<span>(6)</span></a> </li>
                                    <li><a href="">成功励志<span>(10)</span></a> </li>
                                    <li><a href="">心理<span>(4)</span></a></li>
                                    <li><a href="">法律<span>(4)</span></a></li>
                                    <li><a href="">社会科学<span>(3)</span></a></li>
                                    <li><a href="">教育考试<span>(6)</span></a></li>
                                    <li><a href="">建筑<span>(10)</span></a></li>
                                    <li><a href="">旅游地理<span>(4)</span></a></li>
                                    <li><a href="">医学卫生<span>(4)</span></a></li>
                                    <li><a href="">自然科学<span>(3)</span></a></li>
                                    <li><a href="">政治军事<span>(6)</span></a></li>
                                    <li><a href="">宗教哲学<span>(10)</span></a></li>
                                    <li><a href="">工具书<span>(4)</span></a></li>
                                    <li><a href="">教育考试<span>(4)</span></a></li>
                                </ul>
                            </div>

                            <!--标签-->
                            <div class="right-filter-item label-item">
                                <h4>
                                    <span class="glyphicon glyphicon-tags"></span> 商品标签
                                    <div class="item-line"></div>
                                </h4>
                                <!--具体的标签项-->
                                <ul>
                                    <li>
                                        <a href="">成色好</a>
                                    </li>
                                    <li>
                                        <a href="">价格低</a>
                                    </li>
                                    <li>
                                        <a href="">稀有</a>
                                    </li>
                                    <li>
                                        <a href="">性价比高</a>
                                    </li>
                                    <li>
                                        <a href="">全新书籍</a>
                                    </li>
                                    <li>
                                        <a href="">有收藏价值</a>
                                    </li>
                                    <li>
                                        <a href="">破烂书</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!--右侧过滤栏--end-->
                    </div>
                </div>
                <!--商展页面的主体--end-->



            </div>
        </div>
        <!--右侧的显示内容结束-->
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

    </div>
    <!-- 外层包装结束 -->






</body>

</html>