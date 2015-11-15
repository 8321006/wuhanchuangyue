<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>个人中心——教学调查问卷</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/questionnaire_student.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/footer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/questionnaire_student.js"></script>
</head>
<body>

<!----头部区域-->	
<div class="inner_header">
    <jsp:include page="../head.jsp"></jsp:include>
     <!---banner 轮询 开始---->
    <div class="flexslider_01">
       <jsp:include page="../user/personalcenterhead.jsp"></jsp:include>
     </div>
    <!---banner 轮询 结束---->     
</div>
<!---中间内容区域  开始---->
<div class="container">		
	<div class="questionnaire_con">
    	<div class="questionnaire_title">《摄影基础入门》调查问卷</div>
        <div class="questionnaire_info clearfloat">
        	<div class="questionnaire_info_course_img_left"><img class="questionnaire_info_course_img" src="images/questionnaire/questionnaire_couse_img_01.jpg" alt=""/></div>
            <div class="questionnaire_info_content_degree">
            	<span class="questionnaire_info_content_degree_txt">课程整体满意度</span>
                <ul class="questionnaire_info_content_degree_star">
                	<li><a href="javascript:;">1</a></li>
                    <li><a href="javascript:;">2</a></li>
                    <li><a href="javascript:;">3</a></li>
                    <li><a href="javascript:;">4</a></li>
                    <li><a href="javascript:;">5</a></li>
                </ul>
            </div>
        </div>
        <div class="questionnaire_subject">
        	<div class="questionnaire_sub_title">1、你理想中的通识课是什么样子的：（  多选   ） 30分</div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox_selected"></span>
                    <div class="questionnaire_sub_chose_tit">A．课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">A选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_ffda81" style="width:50%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">34人</span>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">B．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">B选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_9cd554" style="width:50%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">34人</span>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">C．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">C选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_ff8f81" style="width:25%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">17人</span>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">D．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">D选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_73bdec" style="width:30%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">20人</span>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">E．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">E选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_d479ec" style="width:75%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">56人</span>
                </div>
            </div> 
        </div>
        <div class="questionnaire_subject">
        	<div class="questionnaire_sub_title">1、你理想中的通识课是什么样子的：（  多选   ） 30分</div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox_selected"></span>
                    <div class="questionnaire_sub_chose_tit">A．课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">A选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_ffda81" style="width:50%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">34人</span>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">B．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">B选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_9cd554" style="width:50%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">34人</span>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">C．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">C选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_ff8f81" style="width:25%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">17人</span>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">D．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">D选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_73bdec" style="width:30%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">20人</span>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">E．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">E选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_d479ec" style="width:75%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">56人</span>
                </div>
            </div> 
        </div>
        <div class="questionnaire_subject">
        	<div class="questionnaire_sub_title">1、你理想中的通识课是什么样子的：（  多选   ） 30分</div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox_selected"></span>
                    <div class="questionnaire_sub_chose_tit">A．课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">A选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_ffda81" style="width:50%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">34人</span>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">B．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">B选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_9cd554" style="width:50%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">34人</span>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">C．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">C选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_ff8f81" style="width:25%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">17人</span>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">D．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">D选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_73bdec" style="width:30%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">20人</span>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">E．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="questionnaire_sub_right_progess">
                	<span class="questionnaire_sub_right_progess_name">E选项：</span>
                    <div class="questionnaire_sub_right_progess_line">
                    	<div class="questionnaire_sub_right_progess_line_used bg_d479ec" style="width:75%;"></div>
                    </div>
                    <span class="questionnaire_sub_right_progess_total">56人</span>
                </div>
            </div> 
        </div>
        <div class="questionnaire_subject">
        	<div class="questionnaire_sub_title">1、你理想中的通识课是什么样子的：（  多选   ） 30分</div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox_selected"></span>
                    <div class="questionnaire_sub_chose_tit">A．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">B．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">C．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
                    <span class="questionnaire_sub_chosebox"></span>
                    <div class="questionnaire_sub_chose_tit">D．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
            </div>
            <div class="questionnaire_sub_chose_con clearfloat">
                <div class="questionnaire_sub_chose">
            	<span class="questionnaire_sub_chosebox"></span>
                <div class="questionnaire_sub_chose_tit">E．课堂自由、有趣，内容丰富，能增长见识</div>
            </div>
            </div> 
        </div>
        <div class="questionnaire_subject">
        	<div class="questionnaire_sub_title">1、你理想中的通识课是什么样子的：（  问答   ） 30分</div>
            <textarea class="questionnaire_ask_textarea"></textarea>
        </div>
        <div class="questionnaire_submit_btn_con"><a>提交结果</a></div>
    </div>
</div>
<!---底部区域---->
<div class="footer_con">
	<div class="footer clearfloat">
    	<div class="footer_left">
        	<div class="footer_left_top">
            	<img class="ewm_ico" src="images/inner/ewm_ico.png" alt=""/>
                <div class="tel_connect">
                	<img class="mobile_ico" src="images/inner/mobile_ico.png" alt=""/>
                    <span>400-1234-2568</span>
                </div>
                <div class="clear"></div>
            </div>
            <div class="footer_left_bottom">京ICP备09043258号-2  京公网安备1101052730</div>
        </div>
        <div class="footer_right">
        	<div class="footer_right_txt_con">
            	<p class="first">关于logo名</p>
                <p>关于我们</p>
                <p>加入我们</p>
                <p>联系我们</p>
            </div>
            <div class="footer_right_txt_con">
            	<p class="first">学习咨询</p>
                <p>课程学习</p>
                <p>就业指南</p>
                <p>考试认证</p>
            </div>
            <div class="footer_right_txt_con end">
            	<p class="first">合作加盟</p>
                <p>推广加盟</p>
                <p>渠道加盟</p>
                <p>名师加盟</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>