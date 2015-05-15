<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="${contextPath}/assets/js/jquery2.1.3/jquery.min.js"></script>
<script src="${contextPath}/assets/js/bootstrap3.3.2/js/bootstrap.min.js"></script>

<!--jquery bootstrap pagination-->
<script src="${contextPath}/assets/js/jquery.twbsPagination.min.js"></script>
<!--form validation-->
<script src="${contextPath}/assets/js/parsley/zh_cn.js"></script>
<script src="${contextPath}/assets/js/parsley/zh_cn.extra.js"></script>
<script src="${contextPath}/assets/js/parsley/parsley.min.js"></script>
<script src="${contextPath}/assets/js/scrollup/2.4.0/jquery.scrollUp.min.js"></script>

<script>
    $(function(){
        //延迟加载图片
        //$("img").unveil();

        //回到顶部
        $.scrollUp({
            animation: 'fade',
            //activeOverlay: '#00FFFF',//测试基线颜色
            scrollImg: {
                active: false,
                activeOverlay: false,
                type: 'background',
                src: '${contextPath}/statics/images/top.png'
            }
        });
    });
</script>