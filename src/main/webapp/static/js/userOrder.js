$(document).ready(function() {

    //渲染显示订单的表格
    function doTable(url){
        // alert(url);
        $('#userOrderInfoTable').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格
        $("#userOrderInfoTable").bootstrapTable({ // 对应table标签的id
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
            sortName: 'o_id', // 要排序的字段
            sortOrder: 'asc', // 排序规则
            queryParams:function(params){
                //alert(JSON.stringify(queryData));
                var param = {
                    pageSize: params.limit, // 每页要显示的数据条数
                    offset: params.offset, // 每页显示数据的开始行号(0开始)
                    sort: params.sort, // 要排序的字段
                    sortOrder: params.order, // 排序规则
                };
                return param;
            },
            columns:
                [
                {
					field: 'oId',
					title: 'ID', 
					align: 'center', 
					valign: 'middle', 
					width: '80',
					visible:false
                    }, {
                    field: 'oName',
                    title: '订单名称',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'Number',
                    title: '订单编号',
                    align: 'center',
                    valign: 'middle',
                    width: '150',
                }, {
                    field: 'ItemName',
                    title: '商品',
                    align: 'center',
                    valign: 'middle',
                    width: '150',
                }, {
                    field: 'Address',
                    title: '地址',
                    align: 'center',
                    valign: 'middle',
                    width: '200',
                },{
                    field: 'Note',
                    title: '备注',
                    align: 'center',
                    valign: 'middle',
					width: '200',
                },{
                    field: 'createTime',
                    title: '创建时间',
                    align: 'center',
                    valign: 'middle',
					width:'80',
                },{
                    field: 'totalPrice',
                    title: '订单总价',
                    align: 'center',
                    valign: 'middle',
                },{
                    field: 'Status',
                    title: '状态',
                    align: 'center',
                    valign: 'middle',
					width:'80',
                    formatter:function(value, row, index){
                        return value=="0"? "未支付":value=="1"? "未处理":value=="2"? "待收货":value=="3"? "收货完成":value=="4"? "退货中":"退货完成";
                    }
                }, {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    width: '70',
                    formatter:function(value, row, index){
						var statusId=row.Status;
						if(statusId==0){
							return "<button class='btn btn-default btn-xs delOrder' oId='"+row.oId+"'><span class='glyphicon glyphicon-exclamation-sign'></span>订单取消</button><br/><button style='margin-top:7px' class='btn btn-default btn-xs payOrder' body='"+row.Note+"' total_amount='"+row.totalPrice+"' subject='"+row.oName+"' Number='"+row.Number+"'><span class='glyphicon glyphicon-ok'></span>订单付款</button>";
						}else if(statusId==2){
							return "<button class='btn btn-default btn-xs showLog' oId='"+row.oId+"'><span class='glyphicon glyphicon-eye-open'></span>查看物流</button><br/><button style='margin-top:7px' class='btn btn-default btn-xs confirmOrder' oId='"+row.oId+"'><span class='glyphicon glyphicon-ok'></span>确认收货</button><br/><button style='margin-top:7px' class='btn btn-default btn-xs rejectLog' oId='"+row.oId+"'><span class='glyphicon glyphicon-ban-circle'></span>拒收订单</button>";
						}else if(statusId==1||statusId==3){
							return "<button class='btn btn-default btn-xs applayLog' oId='"+row.oId+"'><span class='glyphicon glyphicon-edit'></span>退换申请</button>";
						}else{
							return "<button class='btn btn-default btn-xs' oId='"+row.oId+"'><span class='glyphicon glyphicon-exclamation-sign'></span>无操作</button>";
						}

                        
                    }
                }
                ],

        })
    }
    //未定位查询前先设定表格显示所有
    doTable("FrontManageOrder/seeAllOrder");
	
	
	//取消订单时
	$("#userOrderInfoTable").on("click",".delOrder",function(){
		var oId=$(this).attr("oId");
		var data={};
		data['oId']=oId;
		var confirmPost=confirm("确定取消此订单？");
		if(confirmPost==true){
			//ajax发送订单取消的请求
			$.ajax({
					url : 'FrontManageOrder/cancelOrder',
					data:data,
					type : 'POST',
					success : function(data) {
						data=JSON.parse(data);
						if(data['result']){
							//取消成功后
							alert("订单取消成功！");
							doTable("FrontManageOrder/seeAllOrder");
						}else{
							if(data['isLogin']==false){
								window.location.href="FrontForward/loginMain";
							}
							alert("取消失败，请重试！");
						}
					},
					error : function(data){
						alert("请检查网络！");
					}
				});
		}
	});
	
	
	//结算订单时
	$("#userOrderInfoTable").on("click",".payOrder",function(){
		var data={};
		data['out_trade_no']=$(this).attr("Number");
		data['subject']=$(this).attr("subject");
		data['total_amount']=$(this).attr("total_amount");
		data['body']=$(this).attr("body");
		var confirmPost=confirm("确定取消此订单？");
		if(confirmPost==true){
			//ajax发送订单取消的请求
			$.ajax({
					url : 'Pay/AliPay',
					data:data,
					type : 'POST',
					success : function(data) {
						// data=JSON.parse(data);
						if(data['result']){
							//请求支付api成功
							$(".main-content").append(data['form']);
						}else{
							alert("意外错误！请重试！");
						}
					},
					error : function(data){
						alert("请检查网络！");
					}
			});
		}
		
	});
	
	
	
});