/**
 * Created by ajkx_du on 2016/12/27.
 */

//显示职务类别的方法
// function showJobGroups(){
//     var ul = $('#jobgroup-list ul');
//     var span = $('#jobgroup-name').find('span');
//     if(ul.find('li').length > 0){
//         $('#jobgroup-list').css('display','block');
//     }else{
//         $.get("/jobgroup/list",function(result){
//             if(result.length > 0){
//                 $(result).each(function(index,element){
//                     $('<li><?li>').text(element.name).appendTo(ul)
//                         .click(function(event){
//                             //选择框赋值
//                             span.text(element.name);
//                             //input标签赋值
//                             $('#groupid').val(element.id);
//                             //显示remove图标
//                             $('.fa-times').css('display','inline');
//                             //隐藏显示列表
//                             $('#jobgroup-list').css('display','none');
//                         });
//                 });
//                 $('#jobgroup-list').css('display','block');
//             }else{
//                 console.log("无数据");
//             }
//         });
//     }
//
// }


function showSelectList(no,url,event){
    var node = $(no);
    var mainid = node.siblings('.mainid');
    var selectlist = node.siblings('.selectlist');
    var ul = selectlist.find('ul');
    var span = node.find('span');
    var deleteicon = node.find('.fa-times');
    if(ul.find('li').length > 0){
        selectlist.css('display','block');

    }else{
        $.get("/"+url+"/jsonlist",function(result){
            if(result.length > 0){
                $(result).each(function(index,element){
                    $('<li><?li>').text(element.name).appendTo(ul)
                        .click(function(event){
                            //选择框赋值
                            span.text(element.name);
                            //input标签赋值
                            mainid.val(element.id);
                            //显示remove图标
                            deleteicon.css('display','inline');
                            //隐藏显示列表
                            selectlist.css('display','none');
                            event.stopPropagation();
                        });
                });
                selectlist.css('display','block');

            }else{
                console.log("无数据");
            }
        });
    }
    //绑定windows对象 pjax不会重新加载，绑定document会重新加载
    $(window).bind('click',function(event){
        selectlist.css('display','none');
        $(window).unbind('click');
    });
    event.stopPropagation();
    console.log("click....");

}

function clearSelectList(no,event){
    var node = $(no);
    var li = node.siblings('li');
    var parent = node.parent();
    var mainid = parent.siblings('.mainid');
    var ul = parent.siblings('.selectlist');
    ul.find('li').css('display', 'list-item');
    li.remove();
    mainid.val("");
    node.css('display','none');
    event.stopPropagation();
}

function mulitReset(node,id){

    $(node).remove();
    var parent = $(node).parent();
    var mainid = parent.siblings('.mainid');
    var array = mainid.val().split(",")
}

function showMulitSelectList(no,url,event){
    var node = $(no);
    var mainid = node.siblings('.mainid');
    var selectlist = node.siblings('.selectlist');
    var ul = selectlist.find('ul');
    var deleteicon = node.find('.fa-times');
    if(ul.find('li').length > 0){
        selectlist.css('display','block');
    }else{
        $.get("/"+url+"/jsonlist",function(result){
            if(result.length > 0){
                var spans = node.find('li');
                $(result).each(function(index,element){
                        var li = $('<li></li>');
                        for(var i = 0; i < spans.length; i++){
                            if(element.id == $(spans[i]).attr("label-id")){
                                li.css('display','none');
                            }
                        }

                        li.text(element.name)
                        .attr('label-id',element.id)
                        .click(function(event){
                            var li1 = $("<li class='ant-tag ant-tag-blue' label-id='"+element.id+"'></li>");
                            var span1 = $("<span></span>").text(element.name);
                            var i1 = $("<i class='fa fa-times'></i>").click(function(){
                                clearChooseIcon(this,event);
                            });
                            li1.append(span1);
                            li1.append(i1);
                            li1.appendTo(node);
                            //input标签赋值
                            mainid.val(mainid.val()+","+element.id);
                            $(this).css('display','none');
                            //显示remove图标
                            deleteicon.css('display','inline');
                            //隐藏显示列表
                            selectlist.css('display','none');
                            event.stopPropagation();
                        }).appendTo(ul);
                });
                selectlist.css('display','block');

            }else{
                console.log("无数据");
            }
        });
    }
    $(window).bind('click',function(event){
        selectlist.css('display','none');
        $(window).unbind('click');
    });
    event.stopPropagation();
}
function clearChooseIcon(node,event) {
    var id = $(node).parent().attr("label-id");
    var parent = $(node).parent().parent();
    var mainid = parent.siblings(".mainid");
    var ul = parent.siblings(".selectlist");
    $(node).parent().remove();
    var array = mainid.val().split(",");
    var array1 = new Array();
    for(var i = 0; i < array.length; i++){
        if(array[i] != id && array[i] != ""){
            array1.push(array[i])
        }else{
            ul.find("li[label-id='"+id+"']").css("display","list-item");
        }
    }
    mainid.val(array1.join());
    event.stopPropagation();
}
function checkStr(main,value){
    var array = main.split(",");
    for(var i = 0; i < array.length;i++){
        if(array[i] == value){
            return false;
        }
    }
    return true;
}

function searchData(url){
    var input = $('#searchinput');
    var value = input.val();
    $.ajax({
        url:url,
        data:{"value" : value},
        dataType:"json",
        success:function(result){
            if(result.count > 1){
                console.log("无数据");
            }else{
                console.log("");
            }
        }
    });
}

//用于辨识打开的浏览按钮的返回值作用在哪些字段里，页面必须有currentNode这个input
function chooseModal(node,url) {
    currentPage = "";
    $('#currentNode').val($(node).attr("data-index"));
    showEditModal(url);
}