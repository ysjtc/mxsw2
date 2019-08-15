<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>查看用户</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入后台管理部分的公共头部--%>
    <%@ include file="./../../common/backManage/headBase.jsp"%>
    <%--引入bt-table--%>
    <%@ include file="./../../common/bt_table.jsp"%>
    <%--引入showUser页的资源--%>
    <link rel="stylesheet" href="static/css/backManage/showUser.css">

</head>

<body>

<%--引入导航栏--%>
<%@ include file="./../../common/backManage/backNav.jsp"%>

<!-- 右侧主要内容开始 -->
<div class="right-content">
    <div class="right-content-wrap">



        <!-- 根据用户id查找用户 -->
        <!--一个折叠，帅选条件-->
        <div class="row">
            <div  class="row-wrap" style="padding-left:15px;padding-right:15px;">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default order-filter">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                    <span data-toggle="collapse" data-parent="#accordion"
                                          href="#userFlag" class="glyphicon glyphicon-filter">
                                    订单查找
                                    </span>
                            </h4>
                        </div>
                        <div id="userFlag" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <!-- 内联样式 -->
                                <div class="form-inline" role="form" id="findOrder">
                                    <div class="form-group">
                                        <label>用户ID：</label>
                                        <input name="u_id" type="text" class="form-control">
                                    </div>
                                    &emsp;&emsp;
                                    <div class="form-group">
                                        <label>订单编号：</label>
                                        <input name="number" type="text" class="form-control">
                                    </div>
                                    &emsp;
                                    <button type="submit" class="btn btn-default">提交</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!--一个表格，默认显示表的前10条记录-->
        <div class="panel panel-default order-info-table">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <span class="glyphicon glyphicon-eye-open"></span>
                    订单信息
                </h3>

            </div>
            <div class="panel-body">

                <!--一个隐藏的消息提示，与用户表格结果有关-->
                <div id="orderQueryIssu" hidden="hidden"  class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert"
                            aria-hidden="true">
                        &times;
                    </button>
                    <span id="orderQueryIssu-main"></span>
                </div>
                <!-- 几个分类按钮（将订单分类） -->
                <div class="order-btns">
                    <button onclick="orderBtns(this)" status="0" type="button" class="btn btn-default">未处理订单</button>
                    <button onclick="orderBtns(this)" status="1" type="button" class="btn btn-default">待收货订单</button>
                    <button onclick="orderBtns(this)" status="2" type="button" class="btn btn-default">退货中订单</button>
                    <button onclick="orderBtns(this)" status="3" type="button" class="btn btn-default">已完结订单</button>
                </div>



                <!-- 显示用户信息的表格 -->
                <table id="orderInfoTable"></table>


            </div>
        </div>


    </div>
</div>
<!-- 右侧主要内容结束 -->



<!-- 提交筛选 -->
<script type="text/javascript">
    // order信息表格，以及订单操作
    function doTable(url,queryData){
        // alert(url);
        $('#orderInfoTable').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格
        $("#orderInfoTable").bootstrapTable({ // 对应table标签的id
            url:url, // 获取表格数据的url
            method: "post",
            contentType:'application/json',
            cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
            striped: true,  //表格显示条纹，默认为false
            pagination: true, // 在表格底部显示分页组件，默认false
            pageList: [10, 20], // 设置每页可显示的数据条数
            pageSize: 10, // 页面数据条数
            pageNumber: 1, // 首页页码
            sidePagination: 'server', // 设置为服务器端分页
            sortable: true,          //列排序
            sortName: 'item_id', // 要排序的字段
            uniqueId:'item_id',
            sortOrder: 'asc', // 排序规则
            queryParams:function(params){
                //alert(JSON.stringify(queryData));
                var param = {
                    pageSize: params.limit, // 每页要显示的数据条数
                    offset: params.offset, // 每页显示数据的开始行号(0开始)
                    sort: params.sort, // 要排序的字段
                    sortOrder: params.order, // 排序规则
                };
                if(!jQuery.isEmptyObject(queryData)){
                    // alert(queryData);
                    param['queryData']=queryData;

                    //alert(JSON.stringify(param));
                }
                // alert(JSON.stringify(param));
                return param;
            },
            columns:
                [
                    {
                        field: 'o_id', // 返回json数据中的name
                        title: '订单ID', // 表格表头显示文字
                        align: 'center', // 左右居中
                        valign: 'middle', // 上下居中
                        width: '100',
                    }, {
                    field: 'u_id',
                    title: '用户ID',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'number',
                    title: '订单编号',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'create_time',
                    title: '创建时间',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    visible: false,
                    field: 'add_info',
                    title: '地址信息',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'note',
                    title: '备注',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'status',
                    title: '状态',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                    formatter:function(value, row, index){
                        return value=="0"? "未处理":value=="1"? "待收货":value=="2"? "退货中":value=="3"? "收货完成":"退货完成";
                    }
                }, {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    width: '70',
                    formatter:function(value, row, index){
                        var txt;
                        var status=row.status;
                        if(status=="0"){
                            txt="发货";
                        }else if(status=="1"||status=="2"){
                            txt="查看物流";
                        }else{
                            txt="无";
                        }
                        if(txt!="无"){
                            return "<button onclick='doOrder(this)' class='btn btn-default btn-xs' o_id='"+row.o_id+"' txt='"+txt+"'><span class='glyphicon glyphicon-exclamation-sign'></span>"+txt+"</button>";
                        }
                        return txt;
                    }
                }
                ],
            onLoadError: function(res){  //加载失败时执行
                console.info("加载数据失败"+res);
                $("#orderQueryIssu-main").html("查询为空！");
                $("#orderQueryIssu").removeAttr("hidden");

            }


        })
    }
    //未定位查询前先设定表格显示所有
    doTable("Items/query/AllItems");

    // 筛选提交ajax
    $("#findOrder button").click(function(){
        var u_id=$.trim($("#findOrder input[name='u_id']").val());
        var name=$.trim($("#findOrder input[name='number']").val());
        if(u_id!=""||name!=""){
            var data={};
            data['u_id']=u_id;
            data['number']=name;
            // 查询接口地址
            var url="dasd/dasd";
            // alert(JSON.stringify(data));
            doTable(url,data);

        }else{
            $("#orderQueryIssu-main").html("查询为空！");
            $("#orderQueryIssu").removeAttr("hidden");
        }
    });



    // 订单的操作
    function doOrder(obj){
        // 获取订单id（非订单编号）
        var o_id=$(obj).attr("o_id");
        //获取操作类型
        var txt=$(obj).attr("txt");
        //弹出模态框
    }


    // 订单筛选
    function orderBtns(obj){
        //获取筛选特征
        var status=$(obj).attr("status");
        // 根据订单状态筛选的接口
        var url="";
        var data={};
        data['status']=status;
        // 发送表格请求
        doTable(url,data);
    }


</script>

</body>

</html>
