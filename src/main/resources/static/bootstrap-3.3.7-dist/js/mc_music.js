function btn(id) {
    var music = JSON.parse(id)
    var musicId,musicname,musicimg,url;
    musicimg=music.musicimg;
    musicname=music.musicname;
    url = music.url
    singer = music.singer;
    localStorage.setItem("musicname",musicname);
    localStorage.setItem("singer",singer);
    localStorage.setItem("musicimg",musicimg);
    localStorage.setItem("url",url);
}
// function getCollection(){
//     var msg = [[${session.msg}]];
//     if(msg === "收藏成功!"){
//         confirm("收藏成功");
//     }
//     if(msg==="已收藏!"){
//         confirm("已收藏");
//     }
// }
window.onload=function () {
    var date=new Date();
    var year=date.getFullYear();
    var month=date.getMonth()+2;
    var day=date.getDate();
    var hour=date.getHours();
    var minute=date.getMinutes();
    var second=date.getSeconds();
    var time=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
    document.getElementById("date").value=time;
}
function btn3(name) {
    var music = name;
    var musicname = document.getElementById("musicn");
    musicname.value=music;
}
//鼠标点击特效
var mouseSum = 0;
jQuery(document).ready(function($) {
    $("body").click(function(e) {
        var a = new Array();
        a.push("🎵")
        a.push("❄️")
        a.push("🎤")
        var $i = $("<span/>").text(a[mouseSum]);
        mouseSum = (mouseSum + 1) % a.length;
        var x = e.pageX,
            y = e.pageY;
        $i.css({
            "z-index": 9999999,
            "top": y - 20,
            "left": x,
            "position": "absolute",
            "font-weight": "bold",
            "color": "#6651ff"
        });
        $("body").append($i);
        $i.animate({
                "top": y - 180,
                "opacity": 0
            },
            1500,
            function() {
                $i.remove();
            });
    });
});

//轮播图
var $a=$(".buttons a");
var $s=$(".buttons span");
var cArr=["img7","img6","img5","img4","img3","img2","img1"];
var index=0;
$(".next").click(
    function(){
        nextimg();
    }
)
$(".prev").click(
    function(){
        previmg();
    }
)
//上一张
function previmg(){
    cArr.unshift(cArr[6]);
    cArr.pop();
    //i是元素的索引，从0开始
    //e为当前处理的元素
    //each循环，当前处理的元素移除所有的class，然后添加数组索引i的class
    $(".list li").each(function(i,e){
        $(e).removeClass().addClass(cArr[i]);
    })
    index--;
    if (index<0) {
        index=6;
    }
    show();
}

//下一张
function nextimg(){
    cArr.push(cArr[0]);
    cArr.shift();
    $(".list li").each(function(i,e){
        $(e).removeClass().addClass(cArr[i]);
    })
    index++;
    if (index>6) {
        index=0;
    }
    show();
}

//通过底下按钮点击切换
$a.each(function(){
    $(this).click(function(){
        var myindex=$(this).index();
        var b=myindex-index;
        if(b==0){
            return;
        }
        else if(b>0) {
            /*
             * splice(0,b)的意思是从索引0开始,取出数量为b的数组
             * 因为每次点击之后数组都被改变了,所以当前显示的这个照片的索引才是0
             * 所以取出从索引0到b的数组,就是从原本的这个照片到需要点击的照片的数组
             * 这时候原本的数组也将这部分数组进行移除了
             * 再把移除的数组添加的原本的数组的后面
             */
            var newarr=cArr.splice(0,b);
            cArr=$.merge(cArr,newarr);
            $("li").each(function(i,e){
                $(e).removeClass().addClass(cArr[i]);
            })
            index=myindex;
            show();
        }
        else if(b<0){
            /*
             * 因为b<0,所以取数组的时候是倒序来取的,也就是说我们可以先把数组的顺序颠倒一下
             * 而b现在是负值,所以取出索引0到-b即为需要取出的数组
             * 也就是从原本的照片到需要点击的照片的数组
             * 然后将原本的数组跟取出的数组进行拼接
             * 再次倒序,使原本的倒序变为正序
             */
            cArr.reverse();
            var oldarr=cArr.splice(0,-b)
            cArr=$.merge(cArr,oldarr);
            cArr.reverse();
            $("li").each(function(i,e){
                $(e).removeClass().addClass(cArr[i]);
            })
            index=myindex;
            show();
        }
    })
})

//改变底下按钮的背景色
function show(){
    $($s).eq(index).addClass("blue").parent().siblings().children().removeClass("blue");
}

//点击class为img2的元素触发上一张照片的函数
$(document).on("click",".img2",function(){
    previmg();
    return false;//返回一个false值，让a标签不跳转
});

//点击class为img4的元素触发下一张照片的函数
$(document).on("click",".img4",function(){
    nextimg();
    return false;
});

//			鼠标移入box时清除定时器
$(".box").mouseover(function(){
    clearInterval(timer);
})

//			鼠标移出box时开始定时器
$(".box").mouseleave(function(){
    timer=setInterval(nextimg,4000);
})

//			进入页面自动开始定时器
timer=setInterval(nextimg,4000);