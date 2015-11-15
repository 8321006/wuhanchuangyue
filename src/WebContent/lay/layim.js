/*

 @Name: layui WebIM 1.0.0
 @Author：贤心
 @Date: 2014-04-25
 @Blog: http://sentsin.com
 
 */
 
;!function(win, undefined){

var BOSH_SERVICE = 'http://192.168.1.124:7070/http-bind/';
var SERVICE_Url = "@192.168.1.124";
var connection = null;
var msglist = new Array();

var username = $("#username").val()+SERVICE_Url;

$(document).ready(function() {
	
	connection = new Strophe.Connection(BOSH_SERVICE);

	connection.connect(username,$("#username").val(),onConnect);
	
});

function onConnect(status)
{
    if (status == Strophe.Status.CONNECTING) {
	
    } else if (status == Strophe.Status.CONNFAIL) {
	
	$('#connect').get(0).value = 'connect';
    } else if (status == Strophe.Status.DISCONNECTING) {
	
    } else if (status == Strophe.Status.DISCONNECTED) {
	
	$('#connect').get(0).value = 'connect';
    } else if (status == Strophe.Status.CONNECTED) {
		connected = true;
	//上线
	connection.send($pres().tree());
	
	// 当接收到<message>节，调用onMessage回调函数
    connection.addHandler(onMessage, null, 'message', null, null, null);
    
    var iq = $iq({type: 'get',from: username,}).c('query', {xmlns: 'jabber:iq:roster'});
	
	connection.sendIQ(iq,xmltojson);
    }
}
	
var config = {
    msgurl: '私信地址',
    chatlogurl: '聊天记录url前缀',
    aniTime: 200,
    right: -232,
    api: {
        //friend: './webchat/userfriend.action', //好友列表接口
    	friend: 'friend.json', //好友列表接口
        group: 'group.json', //群组列表接口 
        chatlog: 'chatlog.json', //聊天记录接口
        groups: 'groups.json', //群组成员接口
        sendurl: './webchat/sendmes.action' //发送消息接口
    },
    user: { //当前用户信息
        name: '游客',
        face: 'http://tp1.sinaimg.cn/1571889140/180/40030060651/1'
    },
    
    //自动回复内置文案，也可动态读取数据库配置
    autoReplay: [
        '您好，我现在有事不在，一会再和您联系。', 
        '你没发错吧？',
        '洗澡中，请勿打扰，偷窥请购票，个体四十，团体八折，订票电话：一般人我不告诉他！',
        '你好，我是主人的美女秘书，有什么事就跟我说吧，等他回来我会转告他的。',
        '我正在拉磨，没法招呼您，因为我们家毛驴去动物保护协会把我告了，说我剥夺它休产假的权利。',
        '<（@￣︶￣@）>',
        '你要和我说话？你真的要和我说话？你确定自己想说吗？你一定非说不可吗？那你说吧，这是自动回复。',
        '主人正在开机自检，键盘鼠标看好机会出去凉快去了，我是他的电冰箱，我打字比较慢，你慢慢说，别急……',
        '(*^__^*) 嘻嘻，是贤心吗？'
    ],
    
    
    chating: {},
    hosts: (function(){
        var dk = location.href.match(/\:\d+/);
        dk = dk ? dk[0] : '';
        return 'http://' + document.domain + dk + '/';
    })(),
    json: function(url, data, callback, error){
        return $.ajax({
            type: 'POST',
            url: url,
            data: data,
            dataType: 'json',
            success: callback,
            error: error
        });
    },
    stopMP: function(e){
        e ? e.stopPropagation() : e.cancelBubble = true;
    }
}, dom = [$(window), $(document), $('html'), $('body')], xxim = {};

//主界面tab
xxim.tabs = function(index){
    var node = xxim.node;
    node.tabs.eq(index).addClass('xxim_tabnow').siblings().removeClass('xxim_tabnow');
    node.list.eq(index).show().siblings('.xxim_list').hide();
    if(node.list.eq(index).find('li').length === 0){
    	//目前不做群组
        xxim.getDates(index);
    }
};

//节点
xxim.renode = function(){
    var node = xxim.node = {
        tabs: $('#xxim_tabs>span'),
        list: $('.xxim_list'),
        online: $('.xxim_online'),
        setonline: $('.xxim_setonline'),
        onlinetex: $('#xxim_onlinetex'),
        xximon: $('#xxim_on'),
        layimFooter: $('#xxim_bottom'),
        xximHide: $('#xxim_hide'),
        xximSearch: $('#xxim_searchkey'),
        searchMian: $('#xxim_searchmain'),
        closeSearch: $('#xxim_closesearch'),
        layimMin: $('#layim_min')
    }; 
};

//主界面缩放
xxim.expend = function(){
    var node = xxim.node;
    if(xxim.layimNode.attr('state') !== '1'){
        xxim.layimNode.stop().animate({right: config.right}, config.aniTime, function(){
            node.xximon.addClass('xxim_off');
            try{
                localStorage.layimState = 1;
            }catch(e){}
            xxim.layimNode.attr({state: 1});
            node.layimFooter.addClass('xxim_expend').stop().animate({marginLeft: config.right}, config.aniTime/2);
            node.xximHide.addClass('xxim_show');
        });
    } else {
        xxim.layimNode.stop().animate({right: 1}, config.aniTime, function(){
            node.xximon.removeClass('xxim_off');
            try{
                localStorage.layimState = 2;
            }catch(e){}
            xxim.layimNode.removeAttr('state');
            node.layimFooter.removeClass('xxim_expend');
            node.xximHide.removeClass('xxim_show');
        });
        node.layimFooter.stop().animate({marginLeft: 0}, config.aniTime);
    }
};

//初始化窗口格局
xxim.layinit = function(){
    var node = xxim.node;
    
    //主界面
    try{
        if(!localStorage.layimState){       
            config.aniTime = 0;
            localStorage.layimState = 1;
        }
        if(localStorage.layimState === '1'){
            xxim.layimNode.attr({state: 1}).css({right: config.right});
            node.xximon.addClass('xxim_off');
            node.layimFooter.addClass('xxim_expend').css({marginLeft: config.right});
            node.xximHide.addClass('xxim_show');
        }
    }catch(e){
        layer.msg(e.message, 5, -1);
    }
};

//聊天窗口
xxim.popchat = function(param){
    var node = xxim.node, log = {};
    
    log.success = function(layero){
        layer.setMove();
     
        xxim.chatbox = layero.find('#layim_chatbox');
        log.chatlist = xxim.chatbox.find('.layim_chatmore>ul');
        
        log.chatlist.html('<li data-id="'+ param.id +'" type="'+ param.type +'"  id="layim_user'+ param.type + param.id +'"><span>'+ param.name +'</span><em>×</em></li>')
        xxim.tabchat(param, xxim.chatbox);
        
        //最小化聊天窗
        xxim.chatbox.find('.layer_setmin').on('click', function(){
            var indexs = layero.attr('times');
            layero.hide();
            node.layimMin.text(xxim.nowchat.name).show();
        });
        
        //关闭窗口
        xxim.chatbox.find('.layim_close').on('click', function(){
            var indexs = layero.attr('times');
            layer.close(indexs);
            delete xxim.nowchat;
            xxim.chatbox = null;
            config.chating = {};
            config.chatings = 0;
        });
        
        //关闭某个聊天
        log.chatlist.on('mouseenter', 'li', function(){
            $(this).find('em').show();
        }).on('mouseleave', 'li', function(){
            $(this).find('em').hide();
        });
        log.chatlist.on('click', 'li em', function(e){
            var parents = $(this).parent(), dataType = parents.attr('type');
            var dataId = parents.attr('data-id'), index = parents.index();
            var chatlist = log.chatlist.find('li'), indexs;
            
            config.stopMP(e);
            
            delete config.chating[dataType + dataId];
            config.chatings--;
            
            parents.remove();
            $('#layim_area'+ dataType + dataId).remove();
            if(dataType === 'group'){
                $('#layim_group'+ dataType + dataId).remove();
            }
            
            if(parents.hasClass('layim_chatnow')){
                if(index === config.chatings){
                    indexs = index - 1;
                } else {
                    indexs = index + 1;
                }
                xxim.tabchat(config.chating[chatlist.eq(indexs).attr('type') + chatlist.eq(indexs).attr('data-id')]);
            }
            
            if(log.chatlist.find('li').length === 1){
                log.chatlist.parent().hide();
            } 
        });
        
        //聊天选项卡
        log.chatlist.on('click', 'li', function(){
            var othis = $(this), dataType = othis.attr('type'), dataId = othis.attr('data-id');
            xxim.tabchat(config.chating[dataType + dataId]);
        });
        
        //发送热键切换
        log.sendType = $('#layim_sendtype'), log.sendTypes = log.sendType.find('span');
        $('#layim_enter').on('click', function(e){
            config.stopMP(e);
            log.sendType.show();
        });
        log.sendTypes.on('click', function(){
            log.sendTypes.find('i').text('')
            $(this).find('i').text('√');
        });
        
        xxim.transmit();
    };
    
    log.html = '<div class="layim_chatbox" id="layim_chatbox">'
            +'<h6>'
            +'<span class="layim_move"></span>'
            +'    <a href="'+ param.url +'" class="layim_face" target="_blank"><img src="'+ param.face +'" ></a>'
            +'    <a href="'+ param.url +'" class="layim_names" target="_blank">'+ param.name +'</a>'
            +'    <span class="layim_rightbtn">'
            +'        <i class="layer_setmin"></i>'
            +'        <i class="layim_close"></i>'
            +'    </span>'
            +'</h6>'
            +'<div class="layim_chatmore" id="layim_chatmore">'
            +'    <ul class="layim_chatlist"></ul>'
            +'</div>'
            +'<div class="layim_groups" id="layim_groups"></div>'
            +'<div class="layim_chat">'
            +'    <div class="layim_chatarea" id="layim_chatarea">'
            +'        <ul class="layim_chatview layim_chatthis"  id="layim_area'+ param.type + param.id +'"></ul>'
            +'    </div>'
            +'    <div class="layim_tool">'
            +'        <i class="layim_addface" title="发送表情"></i>'
            +'        <a href="javascript:;"><i class="layim_addimage" title="上传图片"></i></a>'
            +'        <a href="javascript:;"><i class="layim_addfile" title="上传附件"></i></a>'
            +'        <a href="" target="_blank" class="layim_seechatlog"><i></i>聊天记录</a>'
            +'    </div>'
            +'    <textarea class="layim_write" id="layim_write"></textarea>'
            +'    <div class="layim_send">'
            +'        <div class="layim_sendbtn" id="layim_sendbtn">发送<span class="layim_enter" id="layim_enter"><em class="layim_zero"></em></span></div>'
            +'        <div class="layim_sendtype" id="layim_sendtype">'
            +'            <span><i>√</i>按Enter键发送</span>'
            +'            <span><i></i>按Ctrl+Enter键发送</span>'
            +'        </div>'
            +'    </div>'
            +'</div>'
            +'</div>';

    if(config.chatings < 1){
        $.layer({
            type: 1,
            border: [0],
            title: false,
            shade: [0],
            area: ['620px', '493px'],
            move: ['.layim_chatbox .layim_move', true],
            moveType: 1,
            closeBtn: false,
            offset: [(($(window).height() - 493)/2)+'px', ''],
            page: {
                html: log.html
            }, success: function(layero){
                log.success(layero);
            }
        })
    } else {
        log.chatmore = xxim.chatbox.find('#layim_chatmore');
        log.chatarea = xxim.chatbox.find('#layim_chatarea');
        
        log.chatmore.show();
        
        log.chatmore.find('ul>li').removeClass('layim_chatnow');
        log.chatmore.find('ul').append('<li data-id="'+ param.id +'" type="'+ param.type +'" id="layim_user'+ param.type + param.id +'" class="layim_chatnow"><span>'+ param.name +'</span><em>×</em></li>');
        
        log.chatarea.find('.layim_chatview').removeClass('layim_chatthis');
        log.chatarea.append('<ul class="layim_chatview layim_chatthis" id="layim_area'+ param.type + param.id +'"></ul>');
        
        xxim.tabchat(param);
    }
    
    //群组
    log.chatgroup = xxim.chatbox.find('#layim_groups');
    if(param.type === 'group'){
        log.chatgroup.find('ul').removeClass('layim_groupthis');
        log.chatgroup.append('<ul class="layim_groupthis" id="layim_group'+ param.type + param.id +'"></ul>');
        xxim.getGroups(param);
    }
    //点击群员切换聊天窗
    log.chatgroup.on('click', 'ul>li', function(){
        xxim.popchatbox($(this));
    });
};

//定位到某个聊天队列
xxim.tabchat = function(param){
    var node = xxim.node, log = {}, keys = param.type + param.id;
    xxim.nowchat = param;
    
    xxim.chatbox.find('#layim_user'+ keys).addClass('layim_chatnow').siblings().removeClass('layim_chatnow');
    xxim.chatbox.find('#layim_area'+ keys).addClass('layim_chatthis').siblings().removeClass('layim_chatthis');
    xxim.chatbox.find('#layim_group'+ keys).addClass('layim_groupthis').siblings().removeClass('layim_groupthis');
    
    xxim.chatbox.find('.layim_face>img').attr('src', param.face);
    xxim.chatbox.find('.layim_face, .layim_names').attr('href', param.href);
    xxim.chatbox.find('.layim_names').text(param.name);
    
    xxim.chatbox.find('.layim_seechatlog').attr('href', config.chatlogurl + param.id);
   
    log.groups = xxim.chatbox.find('.layim_groups');
    if(param.type === 'group'){
        log.groups.show();
    } else {
        log.groups.hide();
    }
    
    $('#layim_write').focus();
    
};

//弹出聊天窗
xxim.popchatbox = function(othis){
    var node = xxim.node, dataId = othis.attr('data-id'), param = {
        id: dataId, //用户ID
        type: othis.attr('type'),
        name: othis.find('.xxim_onename').text(),  //用户名
        face: othis.find('.xxim_oneface').attr('src'),  //用户头像
        fid: othis.find(".xxim_onename").attr('fid'),
        href: config.hosts + 'user/' + dataId //用户主页
    }, key = param.type + dataId;
    if(!config.chating[key]){
        xxim.popchat(param);
        config.chatings++;
    } else {
        xxim.tabchat(param);
    }
    config.chating[key] = param;
    
    var chatbox = $('#layim_chatbox');
    if(chatbox[0]){
        node.layimMin.hide();
        chatbox.parents('.xubox_layer').show();
    }
    
    //收到消息
    for(var i =0;i<msglist.length;i++){
    	if(param.fid == msglist[i].split(":|")[0]){
    		var keys = xxim.nowchat.type + xxim.nowchat.id;
    	    var location = xxim.chatbox.find('#layim_area'+ keys);
    	    var nowdate = new Date();
    	    location.append(gethtml({
    	    	time : nowdate.getFullYear() + "-" + (nowdate.getMonth()+1) + "-" + nowdate.getDate() + " " + nowdate.getHours() + ":" + nowdate.getMinutes(),
    	        name: msglist[i].split(":|")[1],
    	        face: "http://tp1.sinaimg.cn/1571889140/180/40030060651/1",
    	        content: msglist[i].split(":|")[2]
    	    }, msglist[i].split(":|")[1]));
    	    $('#layim_write').val('').focus();
    	    xxim.chatbox.find('#layim_area'+ keys).scrollTop(xxim.chatbox.find('#layim_area'+ keys)[0].scrollHeight);
    	    
    	  //取消闪烁
    	    twinkle.clear($(".xxim_onename[fid='"+param.fid+"']"));
    	    var groupid = $(".xxim_onename[fid='"+param.fid+"']").parent().parent().parent().find(".xxim_parentname").attr("groupid");
    		twinkle.clear($(".xxim_parentname[groupid="+groupid+"]"));
    		twinkle.clear($("#newinform"));
    		
    	}
    	
    	//删除未读消息
    	var temparray = new Array();
    	for(var i =0;i<msglist.length;i++){
    		if(param.fid != msglist[i].split(":|")[0]){
    			temparray.push(msglist[i]);
    		}
		}
    	msglist = temparray;
    	var arr = "";
		for(var i=0;i<msglist.length;i++){
			arr = arr + msglist[i]+",|";
		}
		var date = new Date();
		date.setTime(date.getTime() - 10000);
		document.cookie = "msg" + "=a; expires=" + date.toGMTString();
		if(arr != ""){
			document.cookie = "msg="+arr;
		}
    }
    
};

//请求群员
xxim.getGroups = function(param){
    var keys = param.type + param.id, str = '',
    groupss = xxim.chatbox.find('#layim_group'+ keys);
    groupss.addClass('loading');
    config.json(config.api.groups, {}, function(datas){
        if(datas.status === 1){
            var ii = 0, lens = datas.data.length;
            if(lens > 0){
                for(; ii < lens; ii++){
                    str += '<li data-id="'+ datas.data[ii].id +'" type="one"><img src="'+ datas.data[ii].face +'"><span class="xxim_onename">'+ datas.data[ii].name +'</span></li>';
                }
            } else {
                str = '<li class="layim_errors">没有群员</li>';
            }
            
        } else {
            str = '<li class="layim_errors">'+ datas.msg +'</li>';
        }
        groupss.removeClass('loading');
        groupss.html(str);
    }, function(){
        groupss.removeClass('loading');
        groupss.html('<li class="layim_errors">请求异常</li>');
    });
};

//消息传输
xxim.transmit = function(){
    var node = xxim.node, log = {};
    node.sendbtn = $('#layim_sendbtn');
    node.imwrite = $('#layim_write');
    
    //发送
    log.send = function(){
        var data = {
            content: node.imwrite.val(),
            id: xxim.nowchat.fid,
            sign_key: '', //密匙
            _: +new Date
        };

        if(data.content.replace(/\s/g, '') === ''){
            layer.tips('说点啥呗！', '#layim_write', 2);
            node.imwrite.focus();
        } else {
            //此处皆为模拟
            var keys = xxim.nowchat.type + xxim.nowchat.id;
            
            //聊天模版
            log.html = function(param, type){
                return '<li class="'+ (type === 'me' ? 'layim_chateme' : '') +'">'
                    +'<div class="layim_chatuser">'
                        + function(){
                            if(type === 'me'){
                                return '<span class="layim_chattime">'+ param.time +'</span>'
                                       +'<span class="layim_chatname">'+ $("#realName").val() +'</span>'
                                       +'<img src="'+ param.face +'" >';
                            } else {
                                return '<img src="'+ param.face +'" >'
                                       +'<span class="layim_chatname">'+ param.name +'</span>'
                                       +'<span class="layim_chattime">'+ param.time +'</span>';      
                            }
                        }()
                    +'</div>'
                    +'<div class="layim_chatsay">'+ param.content +'<em class="layim_zero"></em></div>'
                +'</li>';
            };
            
            log.imarea = xxim.chatbox.find('#layim_area'+ keys);
            
            var nowdate = new Date();
            log.imarea.append(log.html({
            	time : nowdate.getFullYear() + "-" + (nowdate.getMonth()+1) + "-" + nowdate.getDate() + " " + nowdate.getHours() + ":" + nowdate.getMinutes(),
                name: config.user.name,
                face: config.user.face,
                content: data.content
            }, 'me'));
            node.imwrite.val('').focus();
            log.imarea.scrollTop(log.imarea[0].scrollHeight);
            
            //自动回复
//            setTimeout(function(){
//                log.imarea.append(log.html({
//                    time: '2014-04-26 0:38',
//                    name: xxim.nowchat.name,
//                    face: xxim.nowchat.face,
//                    content: config.autoReplay[(Math.random()*config.autoReplay.length) | 0]
//                }));
//                log.imarea.scrollTop(log.imarea[0].scrollHeight);
//            }, 500);
            
            //发消息
            var reply = $msg({to: data.id, from:username , type: 'chat'}).cnode(Strophe.xmlElement('body', '' ,data.content));  
            connection.send(reply.tree());
            
            /*
            that.json(config.api.sendurl, data, function(datas){
            
            });
            */
        }
       
    };
    node.sendbtn.on('click', log.send);
    
    node.imwrite.keyup(function(e){
        if(e.keyCode === 13){
            log.send();
        }
    });
};

//事件
xxim.event = function(){
    var node = xxim.node;
    
    //主界面tab
    node.tabs.eq(0).addClass('xxim_tabnow');
    node.tabs.on('click', function(){
        var othis = $(this), index = othis.index();
        xxim.tabs(index);
    });
    
    //列表展收
    node.list.on('click', 'h5', function(){
        var othis = $(this), chat = othis.siblings('.xxim_chatlist'), parentss = othis.parent();
        if(parentss.hasClass('xxim_liston')){
            chat.hide();
            parentss.removeClass('xxim_liston');
        } else {
            chat.show();
            parentss.addClass('xxim_liston');
        }
    });
    
    //设置在线隐身
    node.online.on('click', function(e){
        config.stopMP(e);
        node.setonline.show();
    });
    node.setonline.find('span').on('click', function(e){
        var index = $(this).index();
        config.stopMP(e);
        if(index === 0){
            node.onlinetex.html('在线');
            node.online.removeClass('xxim_offline');
            
        } else if(index === 1) {
            node.onlinetex.html('隐身');
            node.online.addClass('xxim_offline');
        }
        node.setonline.hide();
    });
    
    node.xximon.on('click', xxim.expend);
    node.xximHide.on('click', xxim.expend);
    
    //搜索
    node.xximSearch.keyup(function(){
        var val = $(this).val().replace(/\s/g, '');
        if(val !== ''){
            node.searchMian.show();
            node.closeSearch.show();
            //此处的搜索ajax参考xxim.getDates
            node.list.eq(3).html('<li class="xxim_errormsg">没有符合条件的结果</li>');
        } else {
            node.searchMian.hide();
            node.closeSearch.hide();
        }
    });
    node.closeSearch.on('click', function(){
        $(this).hide();
        node.searchMian.hide();
        node.xximSearch.val('').focus();
    });
    
    //弹出聊天窗
    config.chatings = 0;
    node.list.on('click', '.xxim_childnode', function(){
        var othis = $(this);
        xxim.popchatbox(othis);
    });
    
    //点击最小化栏
    node.layimMin.on('click', function(){
        $(this).hide();
        $('#layim_chatbox').parents('.xubox_layer').show();
    });
    
    
    //document事件
    dom[1].on('click', function(){
        node.setonline.hide();
        $('#layim_sendtype').hide();
    });
};

//请求列表数据
xxim.getDates = function(index){
    //var api = [config.api.friend, config.api.group, config.api.chatlog],
	var api = [config.api.friend, config.api.chatlog],
        node = xxim.node, myf = node.list.eq(index);
    myf.addClass('loading');
    config.json(api[index], {}, function(datas){
        if(datas.status === 1){
            var i = 0, myflen = datas.data.length, str = '', item;
            if(myflen > 1){
                //if(index !== 2){
            	if(index !== 1){
                    for(; i < myflen; i++){
                        str += '<li data-id="'+ datas.data[i].id +'" class="xxim_parentnode">'
                            +'<h5><i></i><span class="xxim_parentname">'+ datas.data[i].name +'</span><em class="xxim_nums">（'+ datas.data[i].nums +'）</em></h5>'
                            +'<ul class="xxim_chatlist">';
                        item = datas.data[i].item;
                        for(var j = 0; j < item.length; j++){
                            str += '<li data-id="'+ item[j].id +'" class="xxim_childnode" type="'+ (index === 0 ? 'one' : 'group') +'"><img src="'+ item[j].face +'" class="xxim_oneface"><span class="xxim_onename">'+ item[j].name +'</span></li>';
                        }
                        str += '</ul></li>';
                    }
                } else {
                    str += '<li class="xxim_liston">'
                        +'<ul class="xxim_chatlist">';
                    for(; i < myflen; i++){
                        str += '<li data-id="'+ datas.data[i].id +'" class="xxim_childnode" type="one"><img src="'+ datas.data[i].face +'"  class="xxim_oneface"><span  class="xxim_onename">'+ datas.data[i].name +'</span><em class="xxim_time">'+ datas.data[i].time +'</em></li>'; 
                    }
                    str += '</ul></li>';
                }
                myf.html(str);
            } else {
                myf.html('<li class="xxim_errormsg">没有任何数据</li>');
            }
            myf.removeClass('loading');
        } else {
            myf.html('<li class="xxim_errormsg">'+ datas.msg +'</li>');
        }
    }, function(){
        myf.html('<li class="xxim_errormsg">请求失败</li>');
        myf.removeClass('loading');
    });
};

//渲染骨架
xxim.view = (function(){
    var xximNode = xxim.layimNode = $('<div id="xximmm" class="xxim_main">'
            +'<div class="xxim_top" id="xxim_top">'
            //+'  <div class="xxim_search"><i></i><input id="xxim_searchkey" /><span id="xxim_closesearch">×</span></div>'
            +'  <div class="xxim_tabs" id="xxim_tabs"><span class="xxim_tabfriend" title="好友"><i></i></span></div>'
            //+'  <div class="xxim_tabs" id="xxim_tabs"><span class="xxim_tabfriend" title="好友"><i></i></span><span class="xxim_tabgroup" title="群组"><i></i></span><span class="xxim_latechat"  title="最近聊天"><i></i></span></div>'
            +'  <ul class="xxim_list" style="display:block"></ul>'
            +'  <ul class="xxim_list"></ul>'
            +'  <ul class="xxim_list"></ul>'
            +'  <ul class="xxim_list xxim_searchmain" id="xxim_searchmain"></ul>'
            +'</div>'
            +'<ul class="xxim_bottom" id="xxim_bottom">'
            +'<li class="xxim_online" id="xxim_online">'
                +'<i class="xxim_nowstate"></i><span id="xxim_onlinetex">在线</span>'
                +'<div class="xxim_setonline">'
                    +'<span><i></i>在线</span>'
                    +'<span class="xxim_setoffline"><i></i>隐身</span>'
                +'</div>'
            +'</li>'
            +'<li class="xxim_mymsg" id="xxim_mymsg" title="我的私信"><i id="newinform"></i></li>'
            //+'<li class="xxim_mymsg" id="xxim_mymsg" title="我的私信"><i id="newinform"></i><a href="'+ config.msgurl +'" target="_blank"></a></li>'
            +'<li class="xxim_seter" id="xxim_seter" title="设置">'
                +'<i></i>'
                +'<div class="">'
                
                +'</div>'
            +'</li>'
            +'<li class="xxim_hide" id="xxim_hide"><i></i></li>'
            +'<li id="xxim_on" class="xxim_icon xxim_on"></li>'
            +'<div class="layim_min" id="layim_min"></div>'
        +'</ul>'
    +'</div>');
    dom[3].append(xximNode);
    
    xxim.renode();
    //xxim.getDates(0);
    xxim.event();
    xxim.layinit();
}());

function xmltojson(msg) {
	//好友
	var themyf = xxim.node.list.eq(0);
	themyf.addClass('loading');
	var items = msg.getElementsByTagName('item');
	if(items.length <=0){
		themyf.html('<li class="xxim_errormsg">没有任何数据</li>');
	}else{
		//拼装好友的array
		//Friends:|b@192.168.1.115.|周凡,|
		var allgroups = new Array();
		for(var i=0;i<items.length;i++){
			var group = items[i].getElementsByTagName('group');
			if(group.length >0){
				var thegroup = Strophe.getText(group[0]);
				if(allgroups.length == 0){
					if(items[i].attributes[1].value == "both")
						allgroups.push(thegroup+":|"+items[i].attributes[2].value+".|"+items[i].attributes[0].value+",|");
					else
						allgroups.push(thegroup+":|"+items[i].attributes[0].value+".|"+items[i].attributes[1].value+",|");
				}else{
					var ifexisted = false;
					var index=0;
					for(var j=0;j<allgroups.length;j++){
						var thegroupname = allgroups[j].split(":|")[0];
						if(thegroupname == thegroup){
							ifexisted = true;
							index = j;
						}
					}
					if(ifexisted){
						if(items[i].attributes[1].value == "both")
						allgroups[index]=allgroups[index]+items[i].attributes[2].value+".|"+items[i].attributes[0].value+",|";
						else
							allgroups[index]=allgroups[index]+items[i].attributes[0].value+".|"+items[i].attributes[1].value+",|";
					}else{
						if(items[i].attributes[1].value == "both")
						allgroups.push(thegroup+":|"+items[i].attributes[2].value+".|"+items[i].attributes[0].value+",|");
						else
						allgroups.push(thegroup+":|"+items[i].attributes[0].value+".|"+items[i].attributes[1].value+",|");
					}
				}
				
				
			}
		}
		
		//html 语句
		var str = '';
		for(var k=0;k<allgroups.length;k++){
			var groupname = allgroups[k].split(":|")[0];
			var num = allgroups[k].split(":|")[1].split(",|").length-1;
			//群组的展现
			str += '<li data-id="'+ i +'" class="xxim_parentnode">'
            +'<h5><i></i><span groupid="'+k+'" class="xxim_parentname">'+ groupname +'</span><em class="xxim_nums">（'+ num +'）</em></h5>'
            +'<ul class="xxim_chatlist">';
			//群组的人员展现
			for(var l=0;l<num;l++){
				var friendname = allgroups[k].split(":|")[1].split(",|")[l].split(".|")[1];
				var fid = allgroups[k].split(":|")[1].split(",|")[l].split(".|")[0];
				str += '<li data-id="'+ (k+""+l) +'" class="xxim_childnode" type="one"><img src="'+ "http://tp1.sinaimg.cn/1571889140/180/40030060651/1" +'" class="xxim_oneface"><span fid="'+fid+'" class="xxim_onename">'+ friendname +'</span></li>';
			}
			str += '</ul></li>';
		}
		themyf.html(str);
		themyf.removeClass('loading');
		
		
		
//		var index = 1;
//		for(var i=0;i<items.length;i++){
//			var group = items[i].getElementsByTagName('group');
//			if(group !=null && group.length >0){
//				var thegroup = Strophe.getText(group[0]);
//				//获取总数
//				var num = 0;
//				for(var j=0;j<items.length;j++){
//					var countgroup = items[j].getElementsByTagName('group');
//					if(countgroup.length >0 && thegroup == Strophe.getText(countgroup[0])){
//						num++;
//					}
//				}
//				if(allgroups.length==0 ){
//					//群组的展现
//					str += '<li data-id="'+ i +'" class="xxim_parentnode">'
//                    +'<h5><i></i><span class="xxim_parentname">'+ thegroup +'</span><em class="xxim_nums">（'+ num +'）</em></h5>'
//                    +'<ul class="xxim_chatlist">';
//					//群组的人员展现
//					str += '<li data-id="'+ i +'" class="xxim_childnode" type="one"><img src="'+ "http://tp1.sinaimg.cn/1571889140/180/40030060651/1" +'" class="xxim_oneface"><span class="xxim_onename">'+ items[i].attributes[0].value +'</span></li>';
//					if(index == num){
//						str += '</ul></li>';
//						index=1;
//					}else{
//						index++;
//					}
//				}else if(lastgroup == thegroup){
//					str += '<li data-id="'+ i +'" class="xxim_childnode" type="one"><img src="'+ "http://tp1.sinaimg.cn/1571889140/180/40030060651/1" +'" class="xxim_oneface"><span class="xxim_onename">'+ items[i].attributes[0].value +'</span></li>';
//					if(index == num){
//						str += '</ul></li>';
//						index=1;
//					}else{
//						index++;
//					}
//				}else if(lastgroup != thegroup){
//					//群组的展现
//					str += '<li data-id="'+ i +'" class="xxim_parentnode">'
//                    +'<h5><i></i><span class="xxim_parentname">'+ thegroup +'</span><em class="xxim_nums">（'+ num +'）</em></h5>'
//                    +'<ul class="xxim_chatlist">';
//					//群组的人员展现
//					str += '<li data-id="'+ i +'" class="xxim_childnode" type="one"><img src="'+ "http://tp1.sinaimg.cn/1571889140/180/40030060651/1" +'" class="xxim_oneface"><span class="xxim_onename">'+ items[i].attributes[0].value +'</span></li>';
//					if(index == num){
//						str += '</ul></li>';
//						index=1;
//					}else{
//						index++;
//					}
//				}
//				
//				lastgroup = thegroup;
//				themyf.html(str);
//				themyf.removeClass('loading');
//			}
//		}
		
		//从cookie取得上次未读的消息并显示
		var msgleft = document.cookie.split("msg=")[1];
		msglist = new Array();
		if(msgleft != ""){
			for(var i=0;i<msgleft.split(",|").length-1;i++){
				msglist.push(msgleft.split(",|")[i]);
			}
			twinkle.show($(".xxim_onename[fid='"+msglist[0].split(":|")[0]+"']"));
			var groupid = $(".xxim_onename[fid='"+msglist[0].split(":|")[0]+"']").parent().parent().parent().find(".xxim_parentname").attr("groupid");
			twinkle.show($(".xxim_parentname[groupid="+groupid+"]"));
			twinkle.show($("#newinform"));
		}
		
		
	}
}


//接收到<message>
function onMessage(msg) {
  // 解析出<message>的from、type属性，以及body子元素
  var from = msg.getAttribute('from');
  var type = msg.getAttribute('type');
  var elems = msg.getElementsByTagName('body');
  if (type == "chat" && elems.length > 0) {
    var body = elems[0];
    //此处皆为模拟
	if(!xxim.hasOwnProperty('nowchat')){
		//闪烁
		if(twinkle.geteventByelem($(".xxim_onename[fid='"+from.split("/")[0]+"']")) == undefined){
			twinkle.show($(".xxim_onename[fid='"+from.split("/")[0]+"']"));
		}
		var groupid = $(".xxim_onename[fid='"+from.split("/")[0]+"']").parent().parent().parent().find(".xxim_parentname").attr("groupid");
		
		if(twinkle.geteventByelem($(".xxim_parentname[groupid="+groupid+"]")) == undefined){
			twinkle.show($(".xxim_parentname[groupid="+groupid+"]"));
		}
		twinkle.show($("#newinform"));
		
		//a@192.168.1.155:|周凡:|你在说什么此种
		var fid = from.split("/")[0];
		var name = $(".xxim_onename[fid='"+from.split("/")[0]+"']").html();
		//消息存入msglist
		msglist.push(fid+":|"+name+":|"+Strophe.getText(body));
		//alert(Strophe.getText(body))
		var arr = "";
		for(var i=0;i<msglist.length;i++){
			arr = arr + msglist[i]+",|";
		}
		var date = new Date();
		date.setTime(date.getTime() - 10000);
		document.cookie = "msg" + "=a; expires=" + date.toGMTString();
		document.cookie = "msg="+arr;
	}else{
		  var keys = xxim.nowchat.type + xxim.nowchat.id;
		  var nowdate = new Date();
		  var fid = from.split("/")[0];
		  var name = $(".xxim_onename[fid='"+from.split("/")[0]+"']").html();
		    var location = xxim.chatbox.find('#layim_area'+ keys);
		    location.append(gethtml({
		    	time : nowdate.getFullYear() + "-" + (nowdate.getMonth()+1) + "-" + nowdate.getDate() + " " + nowdate.getHours() + ":" + nowdate.getMinutes(),
		        name: name,
		        face: "http://tp1.sinaimg.cn/1571889140/180/40030060651/1",
		        content: Strophe.getText(body)
		    }, name));
		    $('#layim_write').val('').focus();
		    xxim.chatbox.find('#layim_area'+ keys).scrollTop(xxim.chatbox.find('#layim_area'+ keys)[0].scrollHeight);
	}
    
  }
  return true;
}

//聊天模版
function gethtml(param, type){
    return '<li class="'+ (type === 'me' ? 'layim_chateme' : '') +'">'
        +'<div class="layim_chatuser">'
            + function(){
                if(type === 'me'){
                    return '<span class="layim_chattime">'+ param.time +'</span>'
                           +'<span class="layim_chatname">'+ param.name +'</span>'
                           +'<img src="'+ param.face +'" >';
                } else {
                    return '<img src="'+ param.face +'" >'
                           +'<span class="layim_chatname">'+ param.name +'</span>'
                           +'<span class="layim_chattime">'+ param.time +'</span>';      
                }
            }()
        +'</div>'
        +'<div class="layim_chatsay">'+ param.content +'<em class="layim_zero"></em></div>'
    +'</li>';
};


var twinkle={
		show : function(elem) { //有新消息时在title处闪烁提示
				var step=0, _title = document.title;
			    var event = setInterval(function(){ elem.fadeOut(200).fadeIn(200); },400);
				this.elem.push(elem);
			    this.event.push(event);
		},
		clear : function(elem) {
				clearInterval(this.geteventByelem(elem));
				
		},
	    geteventByelem : function(elem){
	    	for(var i =0 ; i < this.elem.length ; i++){
				if(this.elem[i].selector == elem.selector){
					var event = this.event[i];
					this.elem[i]="none";
					this.event[i]="none";
					return event;
				}
			}
		},
		elem : new Array(),
		event : new Array()
}

function getnameByJid(Jid){
	return Jid.split("@")[0];
}

}(window);
