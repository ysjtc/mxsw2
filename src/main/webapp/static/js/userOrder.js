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
            sortName: 'add_id', // 要排序的字段
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
                        field: 'name', // 返回json数据中的name
                        title: '收货人', // 表格表头显示文字
                        align: 'center', // 左右居中
                        valign: 'middle', // 上下居中
                        width: '100',
                    }, {
                    field: 'tel',
                    title: '电话号码',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'postcode',
                    title: '邮编',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'province',
                    title: '省份',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'addr',
                    title: '地址',
                    align: 'center',
                    valign: 'middle',
                    width: '200',
                },{
                    field: 'add_id',
                    title: '地址id',
                    align: 'center',
                    valign: 'middle',
                    visible:false,
                }, {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    width: '70',
                    formatter:function(value, row, index){

                        return "<button class='btn btn-default btn-xs delAddr' add_id='"+row.add_id+"'><span class='glyphicon glyphicon-exclamation-sign'></span>删除</button><br/><button class='btn btn-default btn-xs editAddr' add_id='"+row.add_id+"'><span class='glyphicon glyphicon-pencil'></span>修改</button>";
                    }
                }
                ],

        })
    }
    //未定位查询前先设定表格显示所有
    doTable("FrontManageOrder/seeAllOrder");
});