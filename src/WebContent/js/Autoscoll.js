
function AutoScroll(option){
	if(!option.scrollObj || $(option.scrollObj).length==0) return;
	var self = this;
	option = $.extend({
		toLeftBtn:"",//向左滚动的按钮
		toRightBtn:"",//向右滚动的按钮
		toTopBtn:"",//向上滚动的按钮
		toBottomBtn:"",//向下滚动的按钮
		mode:1,//滚动模式：0垂直，1：水平
		autoScroll:false,//自动滚动
		step:30,//每次滚动像素
		scrollObj:"",//滚动对象
		scrollObjItem:"",//滚动对象子项，
		afterScroll:"",//滚动结束后触发的回调
		beforeScroll:"",
		duration:"normal",//滚动一次耗时
		onReady:"",
		onScroll:"",
		delay:3000
		
	},option||{});
	
	for(var i in option){
		self[i] = option[i];
	}
	self.$scrollObj = $(option.scrollObj);
	self.visibleWidth = self.$scrollObj.parent().width();//可见宽度
	self.visibleHeight = self.$scrollObj.parent().height();//可见高度
	self.objWidth = self.$scrollObj.width();//obj宽度
	self.objHeight = self.$scrollObj.height();//obj高度
	self.curItemIndex = 0;//
	self.setMaxCount = function(){
		if(self.mode==1){
			//可滚动的次数
			if(self.objWidth%self.step ==0 && self.objWidth!=0){
				self.maxCount = self.objWidth/self.step;
			} else {
				self.maxCount = Math.floor(self.objWidth/self.step)+1;
			}
		} else {
			if(self.objHeight%self.step==0 && self.objHeight!=0){
				self.maxCount = self.objHeight/self.step;
			} else {
				self.maxCount = Math.floor(self.objHeight/self.step)+1;
			}
		}
	}
	self.setMaxCount();
	//找不到option.scrollObjItem则找scrollObj下的li 
	if($(option.scrollObjItem).get(0)){
		self.$scrollObjItem = $(option.scrollObjItem);
	} else {
		self.$scrollObjItem = self.$scrollObj.find(">li");
	}
	
	if(typeof option.setScrollObjItem == "function"){
		thselfis.$scrollObjItem = option.setScrollObjItem.call(self);
	}

	self.totalItems = self.$scrollObjItem.length;
	//var scrollObjCurMarginLeft = 0;
	self.scrollable = true;

	self.isAlignLeft = function(){
		return parseInt(self.$scrollObj.css("margin-left"))>=0;
	}

	self.isAlignRight = function(){
		var ml = parseInt(self.$scrollObj.css("margin-left"));
		return self.visibleWidth-ml >= self.objWidth;
	}

	self.isAlignTop = function(){
		return parseInt(self.$scrollObj.css("margin-top"))>=0;
	}

	self.isAlignBottom = function(){
		var mt = parseInt(self.$scrollObj.css("margin-top"));
		return self.visibleHeight-mt >= self.objHeight;
	}

	self.btnDisabled = function(){
		if(self.mode==1){
			if(self.isAlignLeft()){
				$(self.toRightBtn).addClass("disabled");
			} else {
				$(self.toRightBtn).removeClass("disabled");
			}
			if(self.isAlignRight()){
				$(self.toLeftBtn).addClass("disabled");
			} else {
				$(self.toLeftBtn).removeClass("disabled");
			}
		} else {
			if(self.isAlignTop()){
				$(self.toBottomBtn).addClass("disabled");
			} else {
				$(self.toBottomBtn).removeClass("disabled");
			}
			if(self.isAlignBottom()){
				$(self.toTopBtn).addClass("disabled");
			} else {
				$(self.toTopBtn).removeClass("disabled");
			}
		}
		self.$scrollObjItem.removeClass('selected').eq(self.curItemIndex).addClass("selected");
	}

	self.btnDisabled();

	//重新设置参数
	self.reflashParams = function(option){
		if(typeof option == "object"){
			for(var i in option){
				self[i] = option[i];
			}
		}
	}

	//水平滚动，distance>0向左滚动，distance<0向右滚动
	self.hScroll = function(distance,isItemClick){
		if(!self.scrollable||distance == 0||(distance>0 && self.curItemIndex>=self.maxCount-1)||(distance<0&&self.curItemIndex<0)) return;
		if(typeof self.beforeScroll == "function"){
			self.beforeScroll.call(self);
		}
		self.curItemIndex += distance;
		if(typeof self.onScroll == "function"){
			self.onScroll.call(self);
		}
		if((distance>0&&self.isAlignRight())||(distance<0&&self.isAlignLeft())||isItemClick){
			self.btnDisabled();
			if(typeof self.afterScroll == "function"){
				self.afterScroll.call(self);
			}
			return;//最后一个已经贴边
		}
		self.scrollable = false;
		var ml = parseInt(self.$scrollObj.css("margin-left"));
		self.$scrollObj.animate({"margin-left":(ml-self.step*distance)+"px"},self.duration,function(){
			self.scrollable = true;
			self.btnDisabled();
			if(typeof self.afterScroll == "function"){
				self.afterScroll.call(self);
			}
		});
	}
	
	//垂直滚动，distance>0向下滚动，distance<0向上滚动
	self.vScroll = function(distance,isItemClick){
		if(!self.scrollable||distance == 0||(distance>0 && self.curItemIndex>=self.maxCount-1)||(distance<0&&self.curItemIndex<0)) return;
		if(typeof self.beforeScroll == "function"){
			self.beforeScroll.call(self);
		}
		self.curItemIndex += distance;
		if(typeof self.onScroll == "function"){
			self.onScroll.call(self);
		}
		if((distance>0&&self.isAlignBottom())||(distance<0&&self.isAlignTop())||isItemClick){
			self.btnDisabled();
			if(typeof self.afterScroll == "function"){
				self.afterScroll.call(self);
			}
			return;//最后一个已经贴边
		}
		self.scrollable = false;
		self.$scrollObj.animate({"margin-top":"-="+self.step*distance+"px"},self.duration,function(){
			self.scrollable = true;
			self.btnDisabled();
			if(typeof self.afterScroll == "function"){
				self.afterScroll.call(self);
			}
		});
	}

	self.btnDisabled();
	if(self.mode == 1){
		$(self.toRightBtn).bind("click",function(e){
			e.preventDefault();
			self.hScroll(-1);});
		$(self.toLeftBtn).bind("click",function(e){
			e.preventDefault();
			self.hScroll(1);
		});
	} else {
		$(self.toTopBtn).bind("click",function(e){
			e.preventDefault();
			self.vScroll(1);
		});
		$(self.toBottomBtn).bind("click",function(e){
			e.preventDefault();
			self.vScroll(-1);
		});
	}
	self.$scrollObjItem.each(function(index){
		return;
		$(this).bind("click",function(e){
			e.preventDefault();
			self.hScroll(index-self.curItemIndex,true);
		});
	});
	//flag 表示不需要动画
	self.reset = function(flag,callback){
		if(flag){
			if(self.mode == 1){
				self.$scrollObj.css({"margin-left":0});
			} else {
				self.$scrollObj.css({"margin-top":0});
			}
			self.curItemIndex = 0;
			self.btnDisabled();
			if(typeof callback == "function"){
				callback.call(self);
			}
		} else {
			self.scrollable = false;
			if(self.mode == 1){
				self.$scrollObj.animate({"margin-left":0},self.duration,function(){
					self.scrollable = true;
					self.curItemIndex = 0;
					self.btnDisabled();
					if(typeof callback == "function"){
						callback.call(self);
					}
				});
			} else {
				self.$scrollObj.animate({"margin-top":0},self.duration,function(){
					self.scrollable = true;
					self.curItemIndex = 0;
					self.btnDisabled();
					if(typeof callback == "function"){
						callback.call(self);
					}
				});
			}
		}
	}

	function autoPlay(){
		if(self.interval) window.clearInterval(self.interval);
		return window.setInterval(function(){
			if(self.curItemIndex==self.maxCount-1){
				self.reset(false,self.afterScroll);
				return;
			}
			if(self.mode == 1){
				self.hScroll(1);
			} else {
				self.vScroll(1);
			}
		},self.delay);
	}
	
	self.start = function(){
		//self.forceStop强制停止
		if(self.forceStop) return;
		self.interval = autoPlay();
		self.status = "start";
	}
	
	self.stop = function(){
		if(self.interval) window.clearInterval(self.interval);
		self.status = "stop";
	}
	
	self.toggle = function(force){
		if(self.status == "start"){
			self.stop();
			return;
		} else {
			self.start(self.forceStop);
			return;
		}
	}
	
	self.remove = function(){
		if(self.interval) window.clearInterval(self.interval);
	}
	if(self.autoScroll){
		var delay = self.delay;
		if(self.duration == "normal"){
			delay += 400;
		} else if(option.duration == "fast"){
			delay += 200;
		} else if(option.duration == "slow"){
			delay += 600;
		}
		self.delay = delay;
			
		self.start();
		self.$scrollObj.parent().parent().hover(self.stop,self.start);
	}

	if(typeof self.onReady == "function"){
		self.onReady.call(self);
	}
	
}