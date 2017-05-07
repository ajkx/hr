$('#treeview').ready(function(){
    $(document).pjax('tree a','#detail-content');
    $.get("/organization/tree",function(data1){
        $('#treeview').treeview({
            //开通超链接效果
            enableLinks:true,
            backColor: '#F7F6F2',
            showBorder: false,
            levels: 1,
            showIcon:true,
            onhoverColor:'#F2F1ED',
            selectedBackColor:'#E4E3DF',
            selectedColor:'#414140',
            expandIcon:'fa fa-caret-right',
            collapseIcon:'fa fa-caret-down',
            color:'#666',
            data:data1
            //右边显示提示信息
        });
    });
})