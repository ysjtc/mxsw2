$(document).ready(function() {

    //TODO:用不到淦哥就删了


    //打字机效果开始
    //使用方法：在需要打字的标签上添加id，然后调用typing('你的id');
    // var str;
    // var i = 0;
    // function typing(id){
    //   var s = document.getElementById(id);
    //   if(i==0){
    //     str=s.innerHTML.trim().replace(/\s/g,"");
    //     s.innerHTML="";
    //   }
    //   var myInterval=setInterval(show,300);
    //   i=0;
    //   function show(){
    //     //alert(i++);
    //     s.innerHTML=str.slice(0, i++)+'_';
    //     //alert(i);
    //     if(i>=str.length){
    //       clearInterval(myInterval);
    //       s.innerHTML=str;
    //     }
    //   }
    // }
    // typing('saohua');
    //打字机效果结束





    //设置RangeSlider插件的一些参数---开始
    $("#range_1").ionRangeSlider({
        min: 0,
        max: 100,
        from: 15,
        to: 50,
        type: 'double', //设置几个滑块
        step: 1,
        prefix: "", //设置数值前缀
        postfix: "元", //设置数值后缀
        prettify: true,
        hasGrid: true
    });
    //设置RangeSlider插件的一些参数---结束


});