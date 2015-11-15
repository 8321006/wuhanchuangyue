<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>注册</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/agreement.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pop_block_login.js"></script>
</head>
<body>

<!----头部区域-->	
<div class="inner_header">
	<jsp:include page="../head.jsp"></jsp:include>
</div>

<!---中间内容区域  开始---->
<div class="container">	
	<div class="agreement_con">
		<h7>《易启学用户注册协议》</h7>
        <div class="agreement_list_title">首部及导言</div>
        <div class="agreement_list_txt"><b></b>欢迎您使用易启学的相关服务！为使用易启学的相关服务，您应当阅读并遵守本《用户协议》（以下简称“本协议”）。请您务必审慎阅读、充分理解各条款内容，特别是免除或者限制责任的条款，以及开通或使用某项服务的单独协议，并选择“同意”按钮。限制、免责条款可能以加粗形式提示您注意。</div>
        <div class="agreement_list_txt"><b></b>本协议是您（下称"用户"）与武汉创越科技之间在使用易启学服务之前，注册易启学帐号时签署的协议。</div>
        <div class="agreement_list_txt"><b></b>除非您已阅读并接受本协议所有条款，否则您无权使用易启学的相关服务。</div>
        <div class="agreement_list_txt"><b></b>如果您未满18周岁，请在法定监护人的陪同下阅读本协议及其他上述协议，并特别注意未成年人使用条款。</div>
        
        <div class="agreement_list_title">1、重要须知---在签署本协议之前，易启学正式提醒用户：</div>
        <div class="agreement_list_txt"><b></b>1.1用户完成全部注册程序的行为表示用户已经阅读本协议的全部条款，理解并同意本协议的条款所约定的权利及义务，并受本协议条款的约束。如用户对本协议的条款存有疑义可暂停注册程序并向武汉创越书面提出，否则，用户完成注册程序之后，应当遵守本协议的条款使用相关服务。</div>
        <div class="agreement_list_txt"><b></b>1.2用户一经注册易启学帐号，除非子频道要求单独开通权限，用户有权利用该账号使用易启学各个频道的单项服务，当用户使用各单项服务时，用户的使用行为视为其对该单项服务的服务条款以及易启学在该单项服务中发出的各类公告所声明的权利及义务的接受，即同意受此服务条款及公告权利义务的约束。用户应当对以其用户帐号进行的所有活动和事件负法律责任。</div>
        <div class="agreement_list_txt"><b></b>1.3易启学用户服务协议以及各个频道单项服务条款和公告可由易启学随时更新，可能会不断发布的关于本协议的相关补充协议、业务规则等内容。上述内容一经正式发布，即为本协议不可分割的组成部分，上述的更新或发布内容我们将不再另行通知。您在使用相关服务时,应关注并遵守其所适用的相关条款。</div>
        
        <div class="agreement_list_title">2、关于帐号</div>
        <div class="agreement_list_txt"><b></b>2.1、无论是手机账号还是QQ帐号，仅限于在易启学上注册用户本人使用，禁止赠与、借用、租用、转让或售卖。如果易启学发现使用者并非账号初始注册人，有权在未经通知的情况下回收该账号而无需向该账号使用人承担法律责任，由此带来的包括并不限于用户通讯中断、用户资料和道具等清空等损失由用户自行承担。</div>
        <div class="agreement_list_txt"><b></b>2.2、用户应当妥善保管自己的账号、密码，用户就其账号及密码项下之一切活动负全部责任，包括用户数据的修改，虚拟道具的损失以及其他所有的损失由用户自行承担。用户须重视账号密码保护。用户如发现他人未经许可使用其账号时应当立即通知易启学。</div>
        <div class="agreement_list_txt"><b></b>2.3、用户如账号在丢失或遗忘密码后，可遵照易启学的申诉途径及时申诉请求找回账号。用户应不断提供能增加账号安全性的个人密码保护资料。用户可以凭初始注册资料及个人密码保护资料填写申诉单向易启学申请找回账号，易启学的密码找回机制仅负责识别申诉单上所填资料与系统记录资料的正确性，而无法识别申诉人是否系真正账号权使用人。对用户因被他人冒名申诉而致的任何损失，易启学不承担任何责任，用户知晓账号及密码保管责任在于用户，易启学并不承诺账号丢失或遗忘密码后用户一定能通过申诉找回账号。</div>
        <div class="agreement_list_txt"><b></b>2.4、用户保证注册账号时填写的身份信息是真实的，任何非法、不真实、不准确的用户信息所产生的责任由用户承担。用户应不断更新注册资料，符合及时、详尽、真实、准确的要求。所有原始键入的资料将引用用户的账号注册资料。如果因用户的注册信息不真实而引起的问题，以及对问题发生所带来的后果，易启学不负任何责任。</div>
        <div class="agreement_list_txt"><b></b>2.5、如用户违反法律法规、易启学各服务协议的等规定，易启学有权根据相关规则进行违规判定，并采取相应限制或处罚措施，包括但不限于：限制或冻结用户对号码的使用，限制或停止某项单独服务（如视频直播）。</div>
        
        <div class="agreement_list_title">3、用户不得从事以下行为：</div>
        <div class="agreement_list_txt"><b></b>3.1、利用易启学服务产品发表、传送、传播、储存危害国家安全、国家统一、社会稳定的内容，或侮辱诽谤、色情、暴力、引起他人不安及任何违反国家法律法规政策的内容或者设置含有上述内容的网名、角色名。</div>
        <div class="agreement_list_txt"><b></b>3.2、利用易启学发表、传送、传播、储存侵害他人知识产权、商业机密权、肖像权、隐私权等合法权利的内容。</div>
        <div class="agreement_list_txt"><b></b>3.3、进行任何危害计算机网络安全的行为，包括但不限于：使用未经许可的数据或进入未经许可的服务器/账户；未经允许进入公众计算机网络或者他人计算机系统并删除、修改、增加存储信息；未经许可，企图探查、扫描、测试本“软件”系统或网络的弱点或其它实施破坏网络安全的行为；企图干涉、破坏本“软件”系统或网站的正常运行，故意传播恶意程序或病毒以及其他破坏干扰正常网络信息服务的行为；伪造TCP/IP数据包名称或部分名称。</div>
        <div class="agreement_list_txt"><b></b>3.4、进行任何破坏易启学服务公平性或者其他影响应用正常秩序的行为，如主动或被动刷分、合伙作弊、使用外挂或者其他的作弊软件、利用BUG（又叫“漏洞”或者“缺陷”）来获得不正当的非法利益，或者利用互联网或其他方式将外挂、作弊软件、BUG公之于众。</div>
        <div class="agreement_list_txt"><b></b>3.5、进行任何诸如发布广告、销售商品的商业行为，或者进行任何非法的侵害易启学利益的行为，如贩卖币、外挂、道具等。</div>
        <div class="agreement_list_txt"><b></b>3.6、进行其他任何违法以及侵犯其他个人、公司、社会团体、组织的合法权益的行为。</div>
        
        <div class="agreement_list_title">4、易启学声明</div>
        <div class="agreement_list_txt"><b></b>4.1、用户须明白，在使用易启学服务可能存在有来自任何他人的包括威胁性的、诽谤性的、令人反感的或非法的内容或行为或对他人权利的侵犯（包括知识产权）的匿名或冒名的信息的风险，用户须自行承担以上风险，易启学对服务不作担保，不论是明确的或隐含的，包括所有有关信息真实性、适当性、适于某一特定用途、所有权和非侵权性的默示担保和条件，对因此导致任何因用户不正当或非法使用服务产生的直接、间接、偶然、特殊及后续的损害，不承担任何责任。</div>
        <div class="agreement_list_txt"><b></b>4.2、使用易启学服务必须遵守国家有关法律和政策等，维护国家利益，保护国家安全，并遵守本条款，对于用户违法或违反本协议的使用(包括但不限于言论发表、传送等)而引起的一切责任，由用户负全部责任。</div>
        <div class="agreement_list_txt"><b></b>4.3、易启学将运用各种安全技术和程序建立完善的管理制度来保护用户的个人信息，以免遭受未经授权的访问、使用或披露。未经用户的同意，易启学不会对任何公司、组织和个人披露您的个人信息，但法律法规另有规定的除外。</div>
        <div class="agreement_list_txt"><b></b>4.4用户理解易启学的服务同大多数因特网产品一样，易受到各种安全问题的困扰，包括但不限于：</div>
        <div class="agreement_list_txt">1）在使用服务时透露详细个人资料，被不法分子利用，造成现实生活中的骚扰；</div>
        <div class="agreement_list_txt">2）用户泄露或遭哄骗、破译密码；</div>
        <div class="agreement_list_txt">3）下载安装的其它软件中含有“特洛伊木马”等病毒，威胁到个人计算机上信息和数据的安全，继而威胁对本服务的使用。对于发生上述情况的，用户应当自行承担责任。</div>
        <div class="agreement_list_txt"><b></b>4.5、用户明白，易启学为了服务整体运营的需要，有权在公告通知后修改或中断、中止或终止服务而不需通知您的权利，而无须向第三方负责或承担任何赔偿责任。</div>
        <div class="agreement_list_txt"><b></b>4.6、用户理解，互联网技术的不稳定性，可能导致政府政策管制、病毒入侵、黑客攻击、服务器系统崩溃或者其他现今技术无法解决的风险发生可能导致易启学服务中断或账号道具损失，用户对此非人为因素引起的损失由用户承担责任。</div>
        
        <div class="agreement_list_title">5、知识产权</div>
        <div class="agreement_list_txt"><b></b>5.1、易启学的服务包括易启学的网站、网页应用、软件以及内涵的文字、图片、视频、音频等元素，易启学标志、标识以及专利权，易启学对此享有上述知识产权。</div>
        <div class="agreement_list_txt"><b></b>5.2、用户不得对易启学服务涉及的相关网页、应用、软件等产品进行反向工程、反向汇编、反向编译等。</div>
        <div class="agreement_list_txt"><b></b>5.3、用户使用易启学服务只能在本《用户协议》以及相应的授权许可协议授权的范围使用易启学知识产权，未经授权超范围使用的构成对易启学的侵权。</div>
        <div class="agreement_list_txt"><b></b>5.4、用户在使用易启学服务时发表上传的文字、图片、视频、软件以及表演等用户原创的信息，此部分信息的知识产权归用户，但用户的发表、上传行为是对易启学服务平台的授权，用户确认其发表、上传的信息非独占性、永久性的授权，易启学有权对用户发表的、上传的信息或作品在易启学服务平台上使用，亦有权将该权利授于第三方使用。</div>
        
        <div class="agreement_list_title">6、隐私保护</div>
        <div class="agreement_list_txt"><b></b>6.1、请用户注意勿在使用易启学服务中透露自己的各类财产帐户、银行卡、信用卡、第三方支付账户及对应密码等重要资料，否则由此带来的任何损失由用户自行承担。</div>
        <div class="agreement_list_txt"><b></b>6.2、用户的账号、密码属于保密信息，易启学应当采取积极的措施保护用户账号、密码的安全。</div>
        <div class="agreement_list_txt"><b></b>6.3、用户的注册信息作为易启学的商业秘密进来保护。但用户同时明白，互联网的开放性以及技术更新非常快，非易启学可控制的因素导致用户泄漏的，易启学不承担责任。</div>
        <div class="agreement_list_txt"><b></b>6.4、用户在使用易启学服务时不可将自认为隐私的信息发表、上传至易启学，也不可将该等信息通过易启学的服务传播给其他人，若用户的行为引起的隐私泄漏，由用户承担责任。</div>
        <div class="agreement_list_txt"><b></b>6.5、易启学在提供服务时可能会搜集用户信息，易启学会明确告知用户，通常信息仅限于用户姓名、性别、年龄、出生日期、身份证号、家庭住址、教育程度、公司情况、所属行业、兴趣爱好等。</div>
        <div class="agreement_list_txt"><b></b>6.6、就下列相关事宜的发生，易启学不承担任何法律责任：</div>
        <div class="agreement_list_txt">1）易启学根据法律规定或相关政府、司法机关的要求提供您的个人信息；</div>
        <div class="agreement_list_txt">2）由于用户将用户密码告知他人或与他人共享注册账户，由此导致的任何个人信息的泄漏，或其他非因易启学原因导致的个人信息的泄漏；</div>
        <div class="agreement_list_txt">3）任何由于黑客攻击、电脑病毒侵入造成的信息泄漏；</div>
        <div class="agreement_list_txt">4）因不可抗力导致的信息泄漏；</div>
        <div class="agreement_list_txt">5）更多易启学隐私权保护政策，请用户查看公司网站。</div>
        
        <div class="agreement_list_title">7、其他条款</div>
        <div class="agreement_list_txt"><b></b>7.1、本协议的签署地点为武汉市洪山区，若用户与易启学发生争议的，双方同意将争议提交武汉市洪山区法院诉讼解决。</div>
        <div class="agreement_list_txt"><b></b>7.2、本协议所有条款的标题仅为阅读方便，本身并无实际涵义，不能作为本协议涵义解释的依据。</div>
        <div class="agreement_list_txt"><b></b>7.3、本协议条款无论因何种原因部分无效或不可执行，其余条款仍有效，对双方具有约束力。</div>
        
    </div>
</div>
</body>
</html>
