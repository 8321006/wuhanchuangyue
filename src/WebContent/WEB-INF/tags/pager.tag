<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="curIndex" type="java.lang.Long" required="true"%>  
<%@ attribute name="pageSize" type="java.lang.Long" required="true"%>  
<%@ attribute name="pagerRange" type="java.lang.Long" required="true"%>  
<%@ attribute name="totalPage" type="java.lang.Long" required="true"%>  
<%@ attribute name="formId" type="java.lang.String" required="true"%>  
<style>
.fenye_style_con{
	width:100%;
	text-align:center;
}
.fenye_style_content{
	margin:20px auto 0 auto;
}
.fenye_style_content tr td{
	height:30px;
}
.fenye_arr_first a{
	display:inline-block;
	*display:inline;
	*zoom:1;
	width:54px;
	height:28px;
	line-height:28px;
	text-align:center;
	color:#9a9a9a;
	font-size:12px;
	border:1px solid #c9d2d1;
	cursor:pointer;
}
.fenye_arr_pre a{
	display:inline-block;
	*display:inline;
	*zoom:1;
	width:54px;
	height:28px;
	line-height:28px;
	text-align:center;
	color:#9a9a9a;
	font-size:12px;
	border:1px solid #c9d2d1;
	cursor:pointer;
}
.fenye_arr_next a{
	display:inline-block;
	*display:inline;
	*zoom:1;
	width:54px;
	height:28px;
	line-height:28px;
	text-align:center;
	color:#008572;
	font-size:12px;
	border:1px solid #c9d2d1;
	margin-left:5px;
	cursor:pointer;
}
.fenye_num_box a{
	display:inline-block;
	*display:inline;
	*zoom:1;
	width:28px;
	height:30px;
	line-height:30px;
	text-align:center;
	color:#008572;
	border:1px solid #c9d2d1;
	box-sizing:border-box;
	-moz-box-sizing:border-box;
	-webkit-box-sizing:border-box;
	-ms-box-sizing:border-box;
	-o-box-sizing:border-box;
	margin-left:5px;
	cursor:pointer;
}
.fenye_num_box a:hover{
	background:#46d6ff;	
	border:none;
	color:#fff;
}
.fenye_num_box_cuur{
	display:inline-block;
	*display:inline;
	*zoom:1;
	width:28px;
	height:30px;
	line-height:30px;
	text-align:center;
	color:#008572;
	border:1px solid #c9d2d1;
	box-sizing:border-box;
	-moz-box-sizing:border-box;
	-webkit-box-sizing:border-box;
	-ms-box-sizing:border-box;
	-o-box-sizing:border-box;
	margin-left:5px;
	cursor:pointer;
	background:#46d6ff;	
	border:none;
	color:#fff;
}
.fenye_num_box a:active{
	background:#46d6ff;	
	border:none;
	color:#fff;
}
.fenye_arr_last a{
	display:inline-block;
	*display:inline;
	*zoom:1;
	width:54px;
	height:28px;
	line-height:28px;
	text-align:center;
	color:#9a9a9a;
	font-size:12px;
	border:1px solid #c9d2d1;
	cursor:pointer;
}

.fenye_fenge{
	display:inline-block;
	*display:inline;
	*zoom:1;
	margin-left:5px;
	height:30px;
	line-height:30px;
}
.all_num_txt a{
	display:inline-block;
	*display:inline;
	*zoom:1;
	height:30px;
	line-height:28px;
	color:#9a9a9a;
	margin-left:8px;
}
.jump_num_txt{
	height:30px;
	line-height:30px;
	color:#9a9a9a;
}
.jump_num_input{
	width:28px;
	height:26px;
	line-height:26px;
	text-align:center;
	color:#686a69;
	border:1px solid #c9d2d1;
	margin:0 5px;
}
.fenye_confirm_btn{
	display:inline-block;
	*display:inline;
	*zoom:1;
	width:46px;
	height:28px;
	line-height:28px;
	text-align:center;
	background:none;
	color:#008572;
	font-size:12px;
	border:1px solid #c9d2d1;
	margin-left:8px;
	cursor:pointer;
}

</style>
<%  
    long begin = Math.max(1, curIndex - pagerRange/2);  
    long end = Math.min(begin + (pagerRange-1),totalPage);
      
    request.setAttribute("p_begin", begin);  
    request.setAttribute("p_end", end);  
%>  
    <table class="pager mt_20px" style="margin:20px auto 0 auto;">  
    <tr>  
         <% if (curIndex!=1){%>  
                <td class="fenye_arr_first"><a onclick="javascript:gotoPage('<%=formId%>',1)">首页</a></td>  
                <td class="fenye_arr_pre"><a onclick="javascript:gotoPage('<%=formId%>',<%=curIndex-1%>)">上一页</a></td>  
         <%}else{%>  
                <td class="fenye_arr_first"><a onclick="javascript:gotoPage('<%=formId%>',1)">首页</a></td>  
                <td class="fenye_arr_pre"><a onclick="#">上一页</a></td>  
         <%}%>  
   
        <c:forEach var="i" begin="${p_begin}" end="${p_end}">  
            <c:choose>  
                <c:when test="${i == curIndex}">  
                    <td class="fenye_num_box_cuur"><a onclick="#">${i}</a></td>  
                </c:when>  
                <c:otherwise>  
                    <td class="fenye_num_box"><a onclick="javascript:gotoPage('<%=formId%>',${i})">${i}</a></td>  
                </c:otherwise>  
            </c:choose>  
        </c:forEach>  
  
         <% if (curIndex==totalPage){%>  
                <td class="fenye_arr_next"><a onclick="#">下一页</a></td>  
                <td class="fenye_arr_last"><a onclick="javascript:gotoPage('<%=formId%>',<%=end%>)">末页</a></td>  
         <%}else{%>  
                <td class="fenye_arr_next"><a onclick="javascript:gotoPage('<%=formId%>',<%=curIndex+1%>)">下一页</a></td>  
                <td class="fenye_arr_last"><a onclick="javascript:gotoPage('<%=formId%>',<%=totalPage%>)">末页</a></td>  
         <%}%>  
         <td class="all_num_txt"><a>共${totalPage}页</a></td>  
         <td class="jump_num_txt">跳转到:<input type="text" class="jump_num_input" id="p_pageIndex" size="2" value="<c:out value="${pageIndex}"/>"/>页 </td>
         <td><input type="button" id="gotoBtn" class="fenye_confirm_btn" onclick="gotoPageByBtn('<%=formId%>')" value="GO"/></td>  
         <!-- 
         <td class="input_li"> 每页:  
         <select id="p_pageSizeSelect" onchange="gotoPage('<%=formId%>',<%=curIndex%>)">  
            <option value="10" <c:if test="${pageSize==10}">selected</c:if>>10条</option>  
            <option value="20" <c:if test="${pageSize==20}">selected</c:if>>20条</option>  
            <option value="50" <c:if test="${pageSize==50}">selected</c:if>>50条</option>  
         </select>  
         </td>  
          -->
    </tr>  
    </table>  
 <script type="text/javascript">

function gotoPage(formId,pageIndex){
    var action = document.getElementById(formId).action;
    //var pageSize = document.getElementById("p_pageSizeSelect").value;  
    //action += "?pageSize=" + pageIndex + "&pageSize=" + pageSize;
    action += "?pageIndex=" + pageIndex;
    //alert(action);  
    document.getElementById(formId).action = action;
    document.getElementById(formId).submit();  
}  
  
function gotoPageByBtn(formId){
    var pageIndex = document.getElementById("p_pageIndex").value;  
    var pageIndexInt = parseInt(pageIndex);  
    var totalPage = '${page.pages}';  
      
    if(pageIndexInt>0 && pageIndexInt<=totalPage){  
        gotoPage(formId,pageIndex);  
    }  
    else{  
        alert("输入页数超出范围!");  
    }  
}
</script>