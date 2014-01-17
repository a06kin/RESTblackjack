var CasinoBlackJack = {
	welcomeArea: '.casino .casino-blackjack .welcome',
	notiferArea: '.casino .casino-blackjack .welcome .notiferArea',
	notiferSprite: '.casino .casino-blackjack .welcome .notiferArea .nAlert',
	gameArea: '.casino .casino-blackjack .welcome .area.game',
	stateMenu: '.casino .casino-blackjack .welcome .actions_row',
	DealerHand: '.casino .casino-blackjack .welcome .area.game .area.dealer',
	PlayerHand1: '.casino .casino-blackjack .welcome .area.game .area.player.first',
	PlayerHand2: '.casino .casino-blackjack .welcome .area.game .area.player.second',
	splitResultPanel: '.casino .casino-blackjack .welcome .split-result',
	tempStack: '.pickAcard_animation ul.stack',
	currentPlayerHand: '.casino .casino-blackjack .welcome .area.game .area.player.first',
	balanceTable:'.casino .casino-blackjack .welcome .corner-links .balance .fishki #fishki-balance-num',
	targetHand: '.casino .casino-blackjack .welcome .area.game .area.player.first',
	tempStack: '.pickAcard_animation ul.stack',
	travelingCard: '',
	cardDOM: '',
	c: '',
	PlayingAnimationNow: 0,
	tempArray:  new Array(),
	pickAcardCurrentPos: 0,
	// доработка
	steckLeft : new Array(),
	steckRight : new Array(),
	steckDealer : new Array(),
	BetRight : 0,
	BetLeft : 0,
	BetSecure:0,
	initGame:0,
	splitSet:0,
	doubleSet:0,
	secureSet:0,
	blocked:0,
	gameAllowed:0,
	gameState:0,
	gameResult:0,
	winState:0,   				// Состояние до завершения игры
	key1:0,
	key2:0,

	STATE_FINISHED : 1,			//Игра окончена
	STATE_STARTED : 2,			//Игра начата
	STATE_BET_DONE : 4,			//Ставка установленна
	STATE_DEALED : 8,			//раздача карт прошла успешно
	STATE_DOUBLED : 16,			//дубль единственной руки
	STATE_DOUBLED_RIGHT : 32,		//дубль второй  руки при сплите
	STATE_DOUBLED_LEFT : 64,		//дубль первой  руки при сплите
	STATE_SPLITED : 128,		//установленн сплит
	STATE_STOPED : 256,				//остановка набора карт в единственной руке
	STATE_STOPED_RIGHT : 512,		//остановка набора карт в правкой руке
	STATE_STOPED_LEFT : 1024,		//остановка набора карт в левой руке
	STATE_DEALED_AFTER_SPLIT : 2048,	//раздача карт после сплита
	STATE_SECURED : 4096,			//установка страховка 
	STATE_CARD_TAKEN : 8192,		//взятие одной карты
	STATE_DEALER_DEALED : 16384,	//диллер взял карты
	STATE_BLACKJACK_RIGHT : 32768,		//блекджек в начале игры
	STATE_DEALER_BLACKJACK : 65536,	//блекджек у диллера
	STATE_BLACKJACK_LEFT : 131072,	//блекджек у левой руки
	

	ALLOWED_SPLITED:1,				//Возможность сплита
	ALLOWED_SECURED:2,				//Возможность страховки
	ALLOWED_DOUBLED:4,				//Возможность дубля одной руки
	ALLOWED_DOUBLED_RIGHT:8,		//Возможность дубля 1 руки
	ALLOWED_DOUBLED_LEFT:16,		//Возможность дубля 2 руки
	ALLOWED_BLACKJACK_GET:32,		//возможность взятия выйгрыша при ничии 1к1 взять или дождатся окончания
	ALLOWED_SECURE_FALSE:64,		//упущеная возможность

	RESULT_LEFT_LOOSER:1,			//Проигрыш в левой
	RESULT_RIGHT_LOOSER:2,			//Проигрыш в правой
	RESULT_LEFT_WIN:4,				//Выйгрыш в левой
	RESULT_RIGHT_WIN:8,				//Выйгрыш в правой
	RESULT_LEFT_PUSH:16,	 		//Ничья в левой
	RESULT_RIGHT_PUSH:32,			//Ничья в правой
	RESULT_BLACKJACK:64,			//Ничья в правой
	RESULT_DEALER_BLACKJACK:128,	//Ничья в правой
    
	
	/**
	 *  Инициализация при загрузке
	 */
	init: function() {
		CasinoBlackJack.QueryAjaxSend('get');
		this.interfaceTransformation();
		this.gameOver = 0;
		this.cardCount = 0;
		// Назначение кнопок
		this.mainButtonBind();
	},

    
	//действие создания
	ActionNew: function(request){

		CasinoBlackJack.watchErrors(request,function(){
			CasinoBlackJack.unBlockedButoms();
		});
		CasinoBlackJack.NewHand(request,function(){ 
        		
			CasinoBlackJack.GameCheck(request,function(){
				CasinoBlackJack.unBlockedButoms();
				CasinoBlackJack.beforeProcess();
			});
			CasinoBlackJack.afterProcess();
    	        
		});



	},
    

	//действие загрузки
	ActionLoadGame: function(request){
		CasinoBlackJack.watchErrors(request,function(){
			CasinoBlackJack.unBlockedButoms();
		});
		CasinoBlackJack.loadGame(request,function(){
			CasinoBlackJack.GameCheck(request,function(){
				CasinoBlackJack.unBlockedButoms();
				CasinoBlackJack.beforeProcess();
			});
			CasinoBlackJack.ShowButtonSecure();
			CasinoBlackJack.afterProcess();
		});



	},
    

	//действие еще
	ActionHit: function(request){
		CasinoBlackJack.watchErrors(request,function(){
			CasinoBlackJack.unBlockedButoms();
		});
		CasinoBlackJack.beforeProcess(function(){
			CasinoBlackJack.hitAction(request,function(){
				CasinoBlackJack.changeHandSplit(function(){
					CasinoBlackJack.GameCheck(request,function(){
						CasinoBlackJack.unBlockedButoms();
					});
					CasinoBlackJack.ShowButtonSecure();
					CasinoBlackJack.afterProcess();	
				});
			});
		});
    	
	},
    
    
	//действие остановки
	ActionStop: function(request){
		CasinoBlackJack.watchErrors(request,function(){
			CasinoBlackJack.unBlockedButoms();
		});
		CasinoBlackJack.beforeProcess(function(){
			CasinoBlackJack.changeHandSplit(function(){

				CasinoBlackJack.stopAction(request,function(){		
					CasinoBlackJack.GameCheck(request,function(){
						CasinoBlackJack.unBlockedButoms();
					});
					CasinoBlackJack.ShowButtonSecure();
					CasinoBlackJack.afterProcess();	
				});

			});
		});

	},
    
    
	//действие сплита
	ActionSplit: function(request){
		CasinoBlackJack.watchErrors(request,function(){
			CasinoBlackJack.unBlockedButoms();
		});
		CasinoBlackJack.beforeProcess(function(){
			CasinoBlackJack.splitAction(request,function(){   			
				CasinoBlackJack.GameCheck(request,function(){
					CasinoBlackJack.unBlockedButoms();
				});
				CasinoBlackJack.ShowButtonSecure();
				CasinoBlackJack.afterProcess();	
			});
		});

	},

    
	//Окончание игры
	ActionExit: function(request){
		CasinoBlackJack.watchErrors(request,function(){
			CasinoBlackJack.unBlockedButoms();
		});
		CasinoBlackJack.beforeProcess(function(){
			CasinoBlackJack.ShowButtonSecure();
			//window.location.pathname='/casino/';
			//console.log("window.location.pathname='/casino/'");
			this.initGame=0;
			CasinoBlackJack.GameCheck(request,function(){
				CasinoBlackJack.unBlockedButoms();
			});
			CasinoBlackJack.ShowButtonSecure();
			CasinoBlackJack.afterProcess();
		});
    	
	},
    
	//Дубль
	ActionDouble: function(request){
		CasinoBlackJack.watchErrors(request,function(){
			CasinoBlackJack.unBlockedButoms();
		});
		CasinoBlackJack.beforeProcess(function(){
			CasinoBlackJack.doubleAction(request,function(){
				CasinoBlackJack.changeHandSplit(function(){
					CasinoBlackJack.GameCheck(request,function(){
						CasinoBlackJack.unBlockedButoms();
					});
					CasinoBlackJack.ShowButtonSecure();
					CasinoBlackJack.afterProcess();
				});
			});
		});
    	
	},
    
    
    
    
	//Застраховатся
	ActionSecure: function(request){
		CasinoBlackJack.watchErrors(request,function(){
			CasinoBlackJack.unBlockedButoms();
		});
		CasinoBlackJack.beforeProcess(function(){
			CasinoBlackJack.secureAction(request,function(){
				CasinoBlackJack.GameCheck(request,function(){
					CasinoBlackJack.unBlockedButoms();
				});
				CasinoBlackJack.ShowButtonSecure();
				CasinoBlackJack.afterProcess();
			});
		});
	},
    
    
	beforeProcess:function(callback){
		CasinoBlackJack.ShowButtonSplit();
		CasinoBlackJack.ShowButtonDouble();
        
        
		CasinoBlackJack.showSecureChips();
		CasinoBlackJack.ShowButtonSecure();
		if(callback!=undefined){
			callback();
			}
	},
    
    
	afterProcess: function(){
    	

		//CasinoBlackJack.ShowButtonSecure();
       
		if(this.initGame==1){
			$(CasinoBlackJack.stateMenu).addClass('ingame');
		}else{
			CasinoBlackJack.resetGame();
			$(CasinoBlackJack.stateMenu).removeClass('ingame');
		}
		//console.log('%o',CasinoBlackJack);
	},
	/**
	 * обработка всех ответов
	 */
	RequestQuery : function(request,action) {
    	
		//console.log('action- %s %o',action,request);
		CasinoBlackJack.CheckSession(request);
		CasinoBlackJack.setState(request);

		switch (action) {
			case 'new':
				CasinoBlackJack.ActionNew(request);
				break;
			case 'get':
				CasinoBlackJack.ActionLoadGame(request);
				break;
			case 'more':
				CasinoBlackJack.ActionHit(request);
				break;
			case 'stop':
				CasinoBlackJack.ActionStop(request);
				break;
			case 'split':
				CasinoBlackJack.ActionSplit(request);
				break;
			case 'double':
				CasinoBlackJack.ActionDouble(request);
				break;
			case 'secure':
				CasinoBlackJack.ActionSecure(request);
				break;
			case 'exit':
				CasinoBlackJack.ActionExit(request);
				break;
			}
        
	
	},
    

	
	/**
	 * проверка измененности сессии
	 */
	CheckSession: function (jsonSession){
		//console.log('Проверка сессии идет');
            
		if(CasinoBlackJack.key1==0){
			CasinoBlackJack.key1=jsonSession.newkey;
			//console.log('СЕССИЯ Установилась');
		}else{
			if(CasinoBlackJack.key1==jsonSession.oldkey){
				CasinoBlackJack.key1=jsonSession.newkey;
				//console.log('СЕССИЯ НОРМАЛЬНАЯ');
			}else{
				//console.log('СЕССИЯ ЛЕВАЯ');
            		
				var ret={
					status:'error',
					error:'SESSION_LEFT'
				};
				CasinoBlackJack.watchErrors(ret,function(){
					
				});
				//console.log('moswar.showAlert(\'SMTH_WRONG\', 0, \'refreshButton\');');
				$(CasinoBlackJack.welcomeArea).find('.button').addClass('disabled').attr('disabled', 'disabled');
				CasinoBlackJack.key1=jsonSession.newkey;
			/*CasinoBlackJack.interfaceTransformation();
            		CasinoBlackJack.resetGame();
            		CasinoBlackJack.clearTable();
            		CasinoBlackJack.QueryAjaxSend('get');*/
		}
            	
			}
        
	},
    
	RedirectSession: function (){
		AngryAjax.goToUrl('/casino/blackjack/');
	},
    
	/**
	 * устанавливает State и Allowed
	 */
	setState: function (jsonSession){
		if((jsonSession.state!==undefined )){
			this.gameState=jsonSession.state+0;
			//console.log('установка gameState %d ',this.gameState);
		}
		if((jsonSession.allowed!==undefined )){
			this.gameAllowed=jsonSession.allowed+0;
			//console.log('установка gameAllowed %d ',this.gameAllowed);
			}
        
		if((jsonSession.gameResult!==undefined )){
			this.gameResult=jsonSession.gameResult+0;
			//console.log('установка gameResult %d ',this.gameResult);
		}
		if((jsonSession.winState!==undefined )){
			this.winState=jsonSession.winState+0;
			//console.log('установка winState %d ',this.winState);
		}
        
		if(jsonSession.chipStart!==undefined ){
			$(CasinoBlackJack.balanceTable).text(formatNumber(jsonSession.chipStart, 0, "", ","));  	
		}

	},
	
	BlockedButoms:function (){
		//console.log('лок на кнопки');
		$(CasinoBlackJack.stateMenu+' button').attr('disabled','disabled').addClass('disabled');
		$(CasinoBlackJack.DealerHand+' button').attr('disabled','disabled').addClass('disabled');
		$(CasinoBlackJack.PlayerHand1+' button').attr('disabled','disabled').addClass('disabled');
		$(CasinoBlackJack.PlayerHand2+' button').attr('disabled','disabled').addClass('disabled');
		CasinoBlackJack.blocked=1;
	},
	
	unBlockedButoms:function (){
		//console.log('снятие лока на кнопки');
		$(CasinoBlackJack.stateMenu+' button').removeAttr('disabled').removeClass('disabled');
		$(CasinoBlackJack.DealerHand+' button').removeAttr('disabled').removeClass('disabled');
		$(CasinoBlackJack.PlayerHand1+' button').removeAttr('disabled').removeClass('disabled');
		$(CasinoBlackJack.PlayerHand2+' button').removeAttr('disabled').removeClass('disabled');
		CasinoBlackJack.blocked=0;
	},
    
	/**
	 * отправка всех запросов
*/
	QueryAjaxSend : function(query) { 
		//console.log('QueryAjaxSend -',query);
		if(CasinoBlackJack.blocked!=1){
			switch (query) {
				case 10:
					$.post('/casino/blackjack/', {
						action : 'new', 
						bet : 10
					}, function(data) {
						CasinoBlackJack.RequestQuery(data,'new');
					}, "json");
					break;
				case 20:
					$.post('/casino/blackjack/', {
						action : 'new', 
						bet : 20
					}, function(data) {
						CasinoBlackJack.RequestQuery(data,'new');
					}, "json");
					break;
				case 30:
					$.post('/casino/blackjack/', {
						action : 'new', 
						bet : 30
					}, function(data) {
						CasinoBlackJack.RequestQuery(data,'new');
					}, "json");
					break;
				case 'get':   //получение  данных
					$.post('/casino/blackjack/', {
						action : 'get'
					}, function(data) {
						CasinoBlackJack.RequestQuery(data,'get');
					}, "json");
					break;
				case 'secure': //страхование от блекджека
					$.post('/casino/blackjack/', {
						action : 'secure'
					}, function(data) {
						CasinoBlackJack.RequestQuery(data,'secure');
					}, "json");
					break;
				case 'more': //взять еще карту
					$.post('/casino/blackjack/', {
						action : 'more'
					}, function(data) {
						CasinoBlackJack.RequestQuery(data,'more');
					}, "json");
					break;
				case 'stop': //отсановка карт
					$.post('/casino/blackjack/', {
						action : 'stop'
					}, function(data) {
						CasinoBlackJack.RequestQuery(data,'stop');
					}, "json");
					break;
				case 'split': //введение 2 руки
					$.post('/casino/blackjack/', {
						action : 'split'
					}, function(data) {
						CasinoBlackJack.RequestQuery(data,'split');
					}, "json");
					break;
				case 'double': //двойная ставка
					$.post('/casino/blackjack/', {
						action : 'double'
					}, function(data) {
						CasinoBlackJack.RequestQuery(data,'double');
					}, "json");
					break;
				case 'exit': //окончание игры
					$.post('/casino/blackjack/', {
						action : 'exit'
					}, function(data) {
						CasinoBlackJack.RequestQuery(data,'exit');
					}, "json");
					break;
				case 'error': //окончание игры
					$.post('/casino/blackjack/', {
						action : 'get'
					}, function(data) {
						CasinoBlackJack.RequestQuery(data,'error');
					}, "json");
					break;
			}
			CasinoBlackJack.BlockedButoms();
		}

	},

	/**
	 * загрузка неоконченой игры
	 */ 
	loadGame : function(session,callback) {
		
		if ((session.state != null)&&(!(session.state & this.STATE_FINISHED)) && (session.status == 'ok')) {
			this.steckLeft = session.leftHand;
			this.steckRight = session.rightHand;
			this.steckDealer = session.dealerHand;
			this.BetRight = session.rightBet;
			this.BetLeft = session.leftBet;
			this.BetSecure=session.secureBet;
			this.initGame=1;
			CasinoBlackJack.showSecureChips();
			this.clearTable();
			$(this.gameArea).show();
			//console.log('left - %o dealer - %o right - %o',this.steckLeft,this.steckDealer,this.steckRight);
			//включение анимации
			var showRight=this.steckRight;
			var showDealer=this.steckDealer;
			var showLeft=this.steckLeft;
			if(this.gameState & this.STATE_SPLITED){
				//this.showSplit('player1');
				//console.log('сплит');
				CasinoBlackJack.FastSplit(function(){
					CasinoBlackJack.AnimateLoadCards(showRight,showDealer,showLeft,'fastsplit',callback);
				});
            	
				$(CasinoBlackJack.PlayerHand1).find('.info span.bet > span:first').text(this.BetRight);
				$(CasinoBlackJack.PlayerHand2).find('.info span.bet > span:first').text(this.BetLeft);
			//
			}else{
				CasinoBlackJack.AnimateLoadCards(showRight,showDealer,showLeft,'fast',callback);	
				$(CasinoBlackJack.PlayerHand1).find('.info span.bet > span:first').text(this.BetRight);
			}        
		}else{
			this.initGame=0;
			if(callback!=undefined){
				callback();
			}
		}
	},
	/**
	 * Обработка нажатия клавиши еще
	 */
	hitAction: function(object,callback) {
		if(object.cardRight!=undefined){
			CasinoBlackJack.AnimateLoadCards(object.cardRight,object.cards,0,'slow',callback);
			this.steckDealer.concat(object.cards);
			this.steckRight.concat(object.cardRight);
		}
		if(object.hand=='right'){
			var rightCard=new Array();
			rightCard=object.card;
			CasinoBlackJack.AnimateLoadCards(object.card,0,0,'slow',callback);
			this.steckRight.concat(object.card);
		}
		if(object.hand=='left'){
			var leftCard=new Array();
			leftCard=object.card;
			CasinoBlackJack.AnimateLoadCards(0,0,object.card,'slow',callback);
			this.steckLeft.concat(object.card);
		}
		if((object.hand!='right')&&(object.hand!='left') && (object.cardRight!=undefined)){
			if(callback!=undefined){
				callback();
			}
		}
	},

	/**
	 * обработка удвоения
	 */
	doubleAction: function(object,callback) {
		if(object.status=='ok'){
			leftCard=0;
			rightCard=0;
			if(object.hand=='right'){
				this.steckRight.concat(object.card);
				if(this.gameState & this.STATE_SPLITED){
					//console.log('удвоение ставки на правой руке %d',object.rightBet);
				}else{
					//console.log('удвоение ставки  %d',object.rightBet);
				}
				rightCard =object.card;
				this.BetRight=object.rightBet;
				//снимаем фишки с баланса
				CasinoBlackJack.changeBalance(0-(object.rightBet/2));
				CasinoBlackJack.showDouble(object.rightBet/20,'player1',object.rightBet/2);
				$(CasinoBlackJack.PlayerHand1).find('.info span.bet > span').text(this.BetRight);
			}
			if(object.hand == 'left'){
				this.steckLeft.concat(object.card);
				//console.log('удвоение ставки на левой руке %d',object.leftBet);
				//снимаем фишки с баланса
				CasinoBlackJack.changeBalance(0-(object.leftBet/2));
				CasinoBlackJack.showDouble(object.leftBet/20,'player2',object.leftBet/2);
				leftCard = object.card;
				this.BetLeft=object.leftBet;
				$(CasinoBlackJack.PlayerHand2).find('.info span.bet > span').text(this.BetLeft);
			}
			CasinoBlackJack.AnimateLoadCards(rightCard,0,leftCard,'slow',callback);

		}else{
			if(callback!=undefined){
				callback();
			}
		}
		
	},
    
	secureAction: function(object,callback) {
		if(object.status=='ok'){
			//снимаем фишки с баланса
			this.BetSecure=object.secureBet;
			
			//CasinoBlackJack.changeBalance(0-this.BetSecure);	
			var currentChips=parseInt($(CasinoBlackJack.balanceTable).html().replace(",",""));
			$(CasinoBlackJack.balanceTable).text(formatNumber(currentChips-this.BetSecure, 0, "", ","));  	
			
			
			CasinoBlackJack.showSecureChips();
			CasinoBlackJack.AnimateLoadCards(0,object.cards,0,'slow',callback);


		}
		
	},
    
    
	showSecureChips: function() {
		if ((this.BetSecure!=0)&&(this.BetSecure!=null)) {
			$(CasinoBlackJack.PlayerHand1).find('div.info span.bet').addClass('insurrance');
			$(CasinoBlackJack.PlayerHand1).find('div.info span.bet span.insurrance').text(this.BetSecure);	
		
			$(CasinoBlackJack.PlayerHand2).find('div.info span.bet').addClass('insurrance');
			$(CasinoBlackJack.PlayerHand2).find('div.info span.bet span.insurrance').text(this.BetSecure);	
		} else {
			$(CasinoBlackJack.PlayerHand1).find('div.info span.bet').removeClass('insurrance');
			$(CasinoBlackJack.PlayerHand1).find('div.info span.bet span.insurrance').text(0);		
			$(CasinoBlackJack.PlayerHand2).find('div.info span.bet').removeClass('insurrance');
			$(CasinoBlackJack.PlayerHand2).find('div.info span.bet span.insurrance').text(0);	
		}
		
	},	
	
	
	changeHandSplit: function (callback){
		if(this.gameState & this.STATE_SPLITED){
			if((this.gameState & this.STATE_STOPED_LEFT)&&(!(this.gameState & this.STATE_STOPED_RIGHT))){
				//console.log('Показ кнопок правой руки showSplit("right"); ');
				CasinoBlackJack.ShowButtonHit();
				CasinoBlackJack.showSplit('player1',callback);
			}else{
				if(callback!=undefined){
					callback();
				}
			}
		}else{
			if(callback!=undefined){
				callback();
			}
		}	
	},
	
	/**
	 * обработка действий остановки
	 */
	stopAction: function(object,callback){
		if(this.gameState & this.STATE_SPLITED){
			if((this.gameState & this.STATE_STOPED_RIGHT)&&(this.gameState & this.STATE_STOPED_LEFT)){
				CasinoBlackJack.AnimateLoadCards(0,object.cards,0,'slow',callback);
				this.steckDealer.concat(object.card);
			}else{
				if(callback!=undefined){
					callback();
				}
			}
		} else{
			if(object.cards!=undefined){
				CasinoBlackJack.AnimateLoadCards(0,object.cards,0,'slow',callback);
				this.steckDealer.concat(object.card);
			}else{
				if(callback!=undefined){
					callback();
				}
			}
		}
	},
	
	/**
	 * обработка действий сплита
	 */
	splitAction: function(object,callback){
		if(object.status=='ok'){
			if(this.gameState & this.STATE_SPLITED){
				var rightCard=new Array();
				if(object.right!=undefined){          
					rightCard=object.right;
					this.steckRight.concat(object.right);
				}
				var leftCard=new Array();
				if(object.left!=undefined){
					leftCard=object.left;
					this.steckLeft.concat(object.left);
				}
				this.BetLeft=this.BetRight;
				//снимаем фишки с баланса
				CasinoBlackJack.changeBalance(0-this.BetLeft);
				CasinoBlackJack.AnimateLoadCards(rightCard,0,leftCard,'split');
				//console.log('Показ кнопок правой руки showSplit("left"); ');
				CasinoBlackJack.showSplit('player2',callback);     
			}
		}
		
	},
	/**
	 * анмирование вывода.
	 * входные масивы с картами
	 */
	AnimateLoadCards : function(rightB,dealerB,leftB,speed,callback){
		
		var right=new Array();
		var dealer=new Array();
		var left=new Array();
		var buf=[];
		jQuery.extend(right,rightB);		
		jQuery.extend(dealer,dealerB);
		jQuery.extend(left,leftB)
	
		var currentcard='';
		var CardName='';
		while(!((right.length<1)&&(dealer.length<1)&&(left.length<1))){
			if(right.length!=0){
				currentcard=right.pop();
				CardName=CasinoBlackJack.getJsCardName(currentcard);
				//console.log('Карта игроку '+CardName);
				buf=[CardName,'player1',currentcard[2]];
				this.tempArray.push(buf);
                
			}
			if(left.length!=0){
				currentcard=left.pop();
				CardName=CasinoBlackJack.getJsCardName(currentcard);
				//console.log('Карта 2 руке '+CardName);
				buf=[CardName,'player2',currentcard[2]];
				this.tempArray.push(buf);
                
			}
			if(dealer.length!=0){
				currentcard=dealer.pop();
				CardName=CasinoBlackJack.getJsCardName(currentcard);
				//console.log('Карта Диллеру '+CardName);
				buf=[CardName,'dealer',currentcard[2]];
				this.tempArray.push(buf);
			}

		}
		if(speed=='fast'){
			CasinoBlackJack.BULP(CasinoBlackJack.tempArray.pop(),callback);
		}else if(speed=='slow'){
			CasinoBlackJack.showAnimationCards(CasinoBlackJack.tempArray.pop(),callback);
		}else if(speed=='split'){
		//ничего не запускать
		}else if(speed=='fastsplit'){
			CasinoBlackJack.StackFastFilling(CasinoBlackJack.tempArray.pop(),callback);
		}
       
		
	},

	//+ перевести php-карту в js-карту (которая также является названием
	// css-класса)
	getJsCardName: function(card) {
		var value = '';

		if(card[0] == '?'){
			return '?';	
		}
		if (card[0] == 'a') {
			value = 'ace';
		} else if (card[0] == 'k') {
			value = 'king';
		} else if (card[0] == 'q') {
			value = 'queen';
		} else if (card[0] == 'j') {
			value = 'jack';
		} else {
			value = 'v' + card[0];
		}
		var suit = '';
		if (card[1] == 's') {
			suit = 'spades';
		} else if (card[1] == 'c') {
			suit = 'clubs';
		} else if (card[1] == 'd') {
			suit = 'diamonds';
		} else if (card[1] == 'h') {
			suit = 'hearts';
		}
		return value + ' ' + suit;
	},

	
	/**
	 * показывание кнопки сплит
	 */
	ShowButtonSplit: function(callback){
		if((this.gameAllowed & this.ALLOWED_SPLITED )&&(!(this.gameState & this.STATE_SPLITED))&&(!(this.gameState & this.STATE_CARD_TAKEN)) && (!(this.gameState & this.STATE_DOUBLED))){
			//console.log('#button -  показ кнопки сплит');
			$('tr#blackjack-split').show().parents('table').show();
			$('tr#blackjack-split').find('.fishki').html(this.BetRight+'<i></i>');
			this.splitSet=1;
			if(callback!=undefined){
				callback();	
			}     
		} else {
			if(this.splitSet==1){
				//console.log('#button -  убирание кнопки сплит');
				if(this.doubleSet==1){
					$('tr#blackjack-split').hide();
				}else{
					$('tr#blackjack-split').hide().parents('table').hide();
		}

				this.splitSet=0;
			}
			if(callback!=undefined){
				callback();	
		}
		}
		
	},

	watchErrors: function (request,callback){
		var params = {
			"__title":"Ошибка"
		};
		var text = "";
		var buttons = [{
			"title":"ОК", 
			"callback":null
		}];
		
		if((request!==undefined) && (request.status=='error')){
			if(request.error=='NO_CHIP'){
				params = {
					"__title":"Ошибка"
				};
				text = "<div style=\"height:10px; width:10px;\"><img align='left' src='/@/images/pers/man112_thumb.png' style='margin:0 10px 5px 0;' /></div>"+"У Вас недостаточно фишек. Получите фишки в"+" <a href=\"/casino/#exchange\">"+"кассе"+"</a> "+"и возвращайтесь.";
				buttons = [{
					"title":"Закрыть", 
					"callback":null
				}];
				showConfirm(text,buttons,params);
		}
    		
			if(request.error=='SESSION_LEFT'){
				text = "Что то пошло не так. Зайдите еще раз";
				buttons = [{
					"title":"Обновить", 
					"callback": function(obj, params){
						CasinoBlackJack.RedirectSession();
		}
				}];
				params = {
					"__title":"Ошибка"
		};
				showConfirm(text,buttons,params);
			}
			callback();
		}
		
	},

	/**
	 * Действия после анимации
	 */
	postAnimate: function(){
		CasinoBlackJack.unBlockedButoms();
		CasinoBlackJack.ShowButtonSecure();
    	
	},

	/**
	 * показывание кнопки застраховатся
	 */
	ShowButtonSecure: function(callback){
		if((this.gameState & this.STATE_STARTED)&&(!(this.gameAllowed & this.ALLOWED_SECURE_FALSE))&&(this.gameAllowed & this.ALLOWED_SECURED)&&(!(this.gameState & this.STATE_SECURED))&&(!(this.gameState & this.STATE_CARD_TAKEN))){
			//console.log('#button -  показ кнопки застраховатся');
			$('.button#blackjack-secure').show();
			$('.button#blackjack-secure').find('.fishki').html((Math.round(this.BetRight/2))+'<i></i>');
			CasinoBlackJack.InsurranceTrigger(true);
			this.secureSet=1;
			if(callback!=undefined){
				callback();	
			}
			} else {
			if(this.secureSet==1){
				//console.log('#button -   убирание кнопки застраховатся');
				//$('.button#blackjack-secure').hide();
				CasinoBlackJack.InsurranceTrigger(false);
				this.secureSet=0;
			}
			if(callback!=undefined){
				callback();	
			}     
		}
		
	},
	
	/**
	 *  показывание кнопки удваивания
	 */
	ShowButtonDouble: function(callback){	
		
		if((this.gameAllowed & this.ALLOWED_DOUBLED)&&(!(this.gameState & this.STATE_DOUBLED))&&(!(this.gameState & this.STATE_SPLITED))&&(!(this.gameState & this.STATE_CARD_TAKEN))){
			//console.log('#button -  показ кнопки удвоения');
			$('tr#blackjack-double').show().parents('table').show();
			$('tr#blackjack-double').find('.fishki').html((this.BetRight)+'<i></i>');
			this.doubleSet=1;
			if(callback!=undefined){
				callback();	
			}      
			} else {
			if(this.doubleSet==1){
				//console.log('#button -  убирание кнопки удвоения');
				if(this.splitSet==1){
					$('tr#blackjack-double').hide();
				}else{
					$('tr#blackjack-double').hide().parents('table').hide();
			}
				this.doubleSet=0;
			}
			if(callback!=undefined){
				callback();	
			}     
		}
		
	},
	
	ShowButtonHit: function(callback){
		scoreRight=parseInt($(CasinoBlackJack.PlayerHand1).find('.info span.total span:first').html().replace(",", ""));
		scoreLeft=parseInt($(CasinoBlackJack.PlayerHand2).find('.info span.total span:first').html().replace(",", ""));
		/*
		if(	(scoreRight==21 && (!(this.gameState & this.STATE_SPLITED))) ||
			((scoreLeft==21) && (this.gameState & this.STATE_SPLITED) && (!(this.gameState & this.STATE_STOPED_LEFT)))
		){
		*/
		
		var hideButton = false;
		if ((this.gameState & this.STATE_SPLITED)) {
			if (scoreLeft==21 && !(this.gameState & this.STATE_STOPED_LEFT)) {
				var hideButton = true;
			} else if ((this.gameState & this.STATE_STOPED_LEFT) && scoreRight==21) {
				var hideButton = true;
			}
		} else {
			if (scoreRight==21) {
				var hideButton = true;
			}
		}
		
		if (hideButton) {
			$('.button#blackjack-hit').hide();
				} else {
			$('.button#blackjack-hit').show();
				}
		if(callback!=undefined){
			callback();	
			}
    	 
	},
	/**
     * Подсчет очков
     */
    
 
    
	resetGame: function(){
		this.steckLeft = new Array();
		this.steckRight = new Array();
		this.steckDealer = new Array();
		this.BetRight =0;
		this.BetLeft =0;
		this.BetSecure=0;
		this.initGame=0;
		
		scoreRight=
		scoreLeft=
		
		
		this.splitSet=1;
		this.doubleSet=1;
		this.secureSet=1;
		this.winState=0;

		this.blocked=0;
		this.gameAllowed=0;
		this.gameState=0;
		this.gameResult=0;
		CasinoBlackJack.showSecureChips();
		
	},
	
    
	GameCheck: function(obj,callback){
		if(this.gameState & this.STATE_FINISHED){
			var check=new Array();
			jQuery.extend(check,obj.check);
      
			var showWinLoose_flag = false;
			var winLeft=obj.winLeft;
			var winRight=obj.winRight;
			var winSecure=obj.winSecure;
			var winS=0;
			if(winSecure!==undefined){
				if(winSecure.win==1){
					winS=winSecure.chip;
					CasinoBlackJack.changeBalance(winS);	
				}
			}
			
			if((winLeft!==undefined)||(winRight!==undefined)){
				if((winLeft.win==0)&&(winRight.win==0)){
					//console.log('Диллер выиграл');	
					CasinoBlackJack.ShowWinLose(0,'left',callback);
					showWinLoose_flag = true;
				} else if(obj.winState & this.STATE_SPLITED ){
					var	winR=0;
					var winL=0;
					if(winRight.win==1){
						//console.log('Вы выиграли в правой руке');
						winR=winRight.chip;
					}
					if(winLeft.win==1){
						//console.log('Вы выиграли в левой руке');	
						winL=winLeft.chip;
					}
					CasinoBlackJack.ShowWinLose(winR+winL,0,callback);
					showWinLoose_flag = true;
				} else if(winRight.win==1){
					//console.log('Вы выиграли %o',winRight.chip);	
					CasinoBlackJack.ShowWinLose(winRight.chip,0,callback);
					showWinLoose_flag = true;
				}
				

				//this.clearTable();
				this.initGame=0;
			}


			if(check && !showWinLoose_flag){
				////console.log('%o   -  %o',check.winRight,check.winLeft);
				if((check.winRight=='loser')&&(check.winLeft=='')){
					//console.log('Вы проиграли (перебрали карты)');
					CasinoBlackJack.ShowWinLose(0,'right',callback);
					this.initGame=0;
				}
				
				if((check.winRight=='loser')&&(check.winLeft=='loser')){
					//console.log('Вы проиграли в 2 руках (перебрали в 2 руках)');
					CasinoBlackJack.ShowWinLose(0,'right',callback);
					this.initGame=0;
				}
			/*
			if((check.winLeft=='loser')&&(check.winRight=='')){
				this.clearTable();
				//console.log('Вы проиграли во второй руке');
				this.initGame=0;
				
		}
			*/
		}
            
		}else{
			if(callback!=undefined){
				callback();
			}
		}
		
	},

	/**
	 * Начало игры
	 */ 
	NewHand : function(request,callback) {
		
		if(request.status=='ok'){
			this.initGame=1;  
			this.clearTable();
		this.currentcardCount = 0;
		$(this.gameArea).show();
			//this.newBet(request.rightBet);
		this.dealerTotal = 0;
		this.currentplayerTotal = 0;
		this.currentcardCount = 0;
		this.gameOver = -1;
			this.BetSecure=0;
			this.steckRight = request.newRightHand;
			this.steckDealer = request.newDealerHand;
    	       
			this.BetRight = request.rightBet;
			this.BetLeft = request.leftBet;
			CasinoBlackJack.showDouble(this.BetRight/10,'player1',this.BetRight);
			var showRight=request.newRightHand;
			var showDealer=request.newDealerHand;

			CasinoBlackJack.AnimateLoadCards(showRight,showDealer,0,'slow',callback);
			//снимаем фишки с баланса
			CasinoBlackJack.changeBalance(0-request.rightBet);
			//console.log('Начало игры со ставкой  %d',request.rightBet);
		}
       
		
	},


	/**
	 * Назначение кнопок
	 */ 
	mainButtonBind: function () {
		//кнопка ставки
		$('.button#blackjack-bet-1').bind('click', function() {
			CasinoBlackJack.QueryAjaxSend(10);

		});
		//кнопка ставки
		$('.button#blackjack-bet-2').bind('click', function() {
			CasinoBlackJack.QueryAjaxSend(20);
		});
		//кнопка ставки
		$('.button#blackjack-bet-3').bind('click', function() {
			CasinoBlackJack.QueryAjaxSend(30);
		});
		//кнопка страхования
		$('.button#blackjack-secure').bind('click', function() {
			CasinoBlackJack.QueryAjaxSend('secure');
		});
		
		//кнопка  получения карт
		$('.button#blackjack-hit').bind('click',	function() {
			CasinoBlackJack.QueryAjaxSend('more');
		});
		//кнопка  остановки карт
		$('.button#blackjack-stand').bind('click', function() {
			CasinoBlackJack.QueryAjaxSend('stop');						
		});
		//кнопка  разбивания
		$('tr#blackjack-split button').bind('click', function() {
			CasinoBlackJack.QueryAjaxSend('split');
		});
		//кнопка  дубль
		$('tr#blackjack-double button').bind('click',	function() {
			CasinoBlackJack.QueryAjaxSend('double');
		});

		$('tr#blackjack-split').hide().parents('table').hide();
		//$('.button#blackjack-secure').hide();
		$('tr#blackjack-double').hide().parents('table').hide();

	},


	constructCardModel: function(strWho) { //Конструируем DOM структуры карты на основе прибавленного индекса currentCardFromPick. В случае с первой скрытой картой диллера ничего не делаем, мы создадим ее отдельно зная что это всегда getCard[1].
		if (strWho == "DealerFake") {
			return false;
		} else if (strWho != "DealerFake") {
			cardDOM = $("<li class='card " + CasinoBlackJack.c[0] + "'><span class='value'></span><div class='suit'></div></li>");
		}
	},

	BULP: function(val,callback) {
		CasinoBlackJack.c = val;
		if ($(CasinoBlackJack.DealerHand).find('.area.cards .stack li.card.undf').size() == 1 && val[1] == "dealer") {
			CasinoBlackJack.showHiddenDealerCard(val,callback);
		}
		if (CasinoBlackJack.c[0] != "?") {
			CasinoBlackJack.cardDOM = $("<li class='card " + CasinoBlackJack.c[0] + "'><span class='value'></span><div class='suit'></div></li>");
			} else {
			CasinoBlackJack.cardDOM = $("<li class='card undf' value='undefined'><span class='value'></span><div class='suit'></div></li>");
		}
		if(val[1] == "player1" || val[1] == "player2" || val[1] == "dealer") {
			if (val[1] == "player1") {
				CasinoBlackJack.targetHand = CasinoBlackJack.PlayerHand1;
				$(CasinoBlackJack.targetHand).find('.area.cards .stack').append(CasinoBlackJack.cardDOM);
			}
			if (val[1] == "player2") {
				CasinoBlackJack.targetHand = CasinoBlackJack.PlayerHand2;
				$(CasinoBlackJack.targetHand).find('.area.cards .stack').append(CasinoBlackJack.cardDOM);
			}
			if(val[1] == "dealer") {
				CasinoBlackJack.targetHand = CasinoBlackJack.DealerHand;
				$(CasinoBlackJack.targetHand).find('.area.cards .stack').append(CasinoBlackJack.cardDOM);
			}
			CasinoBlackJack.showScore(CasinoBlackJack.c[2],val[1]);
		}
		if (CasinoBlackJack.tempArray.length != 0) {
			CasinoBlackJack.BULP(CasinoBlackJack.tempArray.pop(),callback); 
				} else {
			if(callback!=undefined){
				callback();
				}
			}
	},

	showAnimationCards: function(val,callback) {
		//console.log(val);
		CasinoBlackJack.c = val;
		if (CasinoBlackJack.c != undefined) {
			if ($(CasinoBlackJack.DealerHand).find('.area.cards .stack li.card.undf').size() == 1 && val[1] == "dealer") {
				CasinoBlackJack.showHiddenDealerCard(val,callback);
			} else {
				CasinoBlackJack.StackAnimate(CasinoBlackJack.c[1],callback);
			}
		} else {
			if(callback!=undefined){
				callback();
			}
			return false;
		}
	},

	StackAnimate: function(strWho, callback) {
		this.PlayingAnimationNow = this.PlayingAnimationNow+1;
		if (CasinoBlackJack.pickAcardCurrentPos <= 530) {
			CasinoBlackJack.pickAcardCurrentPos = (CasinoBlackJack.pickAcardCurrentPos + 106);
			AngryAjax.setTimeout(function() {
				$('.pickAcard_animation').css("background-position", "0px -" + CasinoBlackJack.pickAcardCurrentPos + "px");
				CasinoBlackJack.StackAnimate(strWho,callback);
			}, 60);
		} else if (CasinoBlackJack.pickAcardCurrentPos > 530 && CasinoBlackJack.pickAcardCurrentPos < 1060) {
			if (strWho != "DealerFake") {
				CasinoBlackJack.pickAcardCurrentPos = (CasinoBlackJack.pickAcardCurrentPos + 106);
				AngryAjax.setTimeout(function() {
					$('.pickAcard_animation').css("background-position", "0px -" + CasinoBlackJack.pickAcardCurrentPos+"px");
					CasinoBlackJack.StackAnimate(strWho,callback);
				}, 60);
			} else  {
				CasinoBlackJack.pickAcardCurrentPos = 0;
				$('.pickAcard_animation').css("background-position", "0px 0px");
				CasinoBlackJack.PickACard(strWho,callback);
		}
		} else if (CasinoBlackJack.pickAcardCurrentPos >= 1060) {
			CasinoBlackJack.pickAcardCurrentPos = 0;
			$('.pickAcard_animation').css("background-position", "0px 0px");
			CasinoBlackJack.PickACard(strWho,callback);
		}
	},

	PickACard: function(strWho, callback) {//Функция вставляет после анимации карту для дальнешего перемещения, указывает куда ее перемещать, и переходит к непосредсвенно к функции добавления карты из виртуального шуза в наш/дилерский стэки. Так же в этот момент считаеться общее кол-во очков. Для пользователя это очки в текущей руке.
		if (CasinoBlackJack.c[0] != "?") {
			CasinoBlackJack.cardDOM = $("<li class='card " + CasinoBlackJack.c[0] + "'><span class='value'></span><div class='suit'></div></li>");
			} else {
			CasinoBlackJack.cardDOM = $("<li class='card undf' value='undefined'><span class='value'></span><div class='suit'></div></li>");
			}
		//console.log("Собираю карту для " + strWho);
		if(strWho == "player1" || strWho == "player2" || strWho == "dealer") {
			$(CasinoBlackJack.tempStack).append(CasinoBlackJack.cardDOM);
			if (strWho == "player1" ) {
				CasinoBlackJack.targetHand = CasinoBlackJack.PlayerHand1;
				CasinoBlackJack.Move2StackAnimation(callback);
		}
			if (strWho == "player2") {
				CasinoBlackJack.targetHand = CasinoBlackJack.PlayerHand2;
				CasinoBlackJack.Move2StackAnimation(callback);
			}
			if(strWho == "dealer") {
				CasinoBlackJack.targetHand = CasinoBlackJack.DealerHand;
				CasinoBlackJack.Move2StackAnimation(callback);
			}
		}
		CasinoBlackJack.showScore(CasinoBlackJack.c[2],strWho);
	},

	Move2StackAnimation: function (callback) {//функции добавления карты из виртуального шуза в наш/дилерский стэки.
		var targetCardPos = '';
		if ( $(CasinoBlackJack.targetHand).find('.area.cards ul.stack li').size() > 0) {
			targetCardPos = $(CasinoBlackJack.targetHand).find('.area.cards ul.stack li:last').offset();
		} else {
			targetCardPos = $(CasinoBlackJack.targetHand).find('.area.cards ul.stack').offset();
		}
		var travelingCardPos = $(CasinoBlackJack.tempStack).find('li').offset();
		CasinoBlackJack.travelingCard = $(CasinoBlackJack.tempStack).find('li').appendTo('body').css({
			zIndex: "10", 
			display: "block", 
			position: "absolute", 
			top: travelingCardPos.top, 
			left: travelingCardPos.left
		}).animate({
			"left": targetCardPos.left, 
			"top": targetCardPos.top
		}, "fast", function() {
			$(CasinoBlackJack.targetHand).find('.area.cards .stack').append(CasinoBlackJack.travelingCard);
			$(CasinoBlackJack.travelingCard).attr('style', '');
			if (CasinoBlackJack.tempArray.length < 1) {
				this.PlayingAnimationNow = this.PlayingAnimationNow-1;
				if(callback!=undefined){
					callback();
				}
				CasinoBlackJack.postAnimate();
			} else {
				CasinoBlackJack.c = CasinoBlackJack.tempArray.pop();
				CasinoBlackJack.StackAnimate(CasinoBlackJack.c[1],callback);
			}
		});
	},

	showHiddenDealerCard: function(val, callback) {//Показ первой скрытой карты дилера
		var card=new Array();
		jQuery.extend(card,CasinoBlackJack.c);	
		//console.log("Вскрытие диллеровской карты");
		if ($(CasinoBlackJack.DealerHand).find('.area.cards .stack li.card.undf').size() == 1) {
			this.PlayingAnimationNow = this.PlayingAnimationNow+1;
			$(CasinoBlackJack.DealerHand).find('.area.cards .stack li.card.undf').animate({
				marginRight: "-=50px"
			}, "fast", function(){
				$(this).removeClass('undf').addClass(val[0]).animate({
					marginRight: "+=50px"
				}, "fast",function(){
					CasinoBlackJack.showScore(card[2],'dealer');
				});
				if (CasinoBlackJack.tempArray.length < 1) {
					this.PlayingAnimationNow = this.PlayingAnimationNow-1;
					if(callback!=undefined){
						callback();
			}

				} else {
					CasinoBlackJack.c = CasinoBlackJack.tempArray.pop();
					CasinoBlackJack.StackAnimate(CasinoBlackJack.c[1],callback);
			}
			});
		} else {
			return true;
			}
	},
		
	showScore: function(score,hand){
		switch (hand) {
			case "dealer":
				hand = CasinoBlackJack.DealerHand;
				break;
			case "player1":
				hand = CasinoBlackJack.PlayerHand1;
				break;
			case "player2":
				hand = CasinoBlackJack.PlayerHand2;
				if (score > 21) {
					$(CasinoBlackJack.PlayerHand2).removeClass('win').addClass('fail');
			}
				break;
			}
		if (score != undefined){
			if (score.length == 2) {
				$(hand).find('.info span.total').addClass('variables');
				$(hand).find('.info span.total span:first').text(score[0]);
				$(hand).find('.info span.total span:last').text(score[1]);
			} else {
				$(hand).find('.info span.total').removeClass('variables');
				$(hand).find('.info span.total span:first').text(score);
			}
			CasinoBlackJack.ShowButtonHit();
			}
	},

	notiferAnm: function(t, callback) {//Функция показа оповещений ничья/двойная игра
		this.PlayingAnimationNow = this.PlayingAnimationNow+1;
		var lPos, tPos = 516;
		if (t == 'split') lPos = 0;
		if (t == 'push') lPos = -394;
		$(CasinoBlackJack.notiferArea).show();//-516;1
		AngryAjax.setTimeout(function() {
			tPos = (tPos-86);
			$(CasinoBlackJack.notiferSprite).css("background-position", lPos+"px -"+tPos+"px");
		}, 100);//-430;2
		AngryAjax.setTimeout(function() {
			tPos = (tPos-86);
			$(CasinoBlackJack.notiferSprite).css("background-position", lPos+"px -"+tPos+"px");
		}, 200);//-344;3
		AngryAjax.setTimeout(function() {
			tPos = (tPos-86);
			$(CasinoBlackJack.notiferSprite).css("background-position", lPos+"px -"+tPos+"px");
		}, 300);//-258;4
		AngryAjax.setTimeout(function() {
			tPos = 0;
			$(CasinoBlackJack.notiferSprite).css("background-position", lPos+"px "+tPos+"px");
		}, 400);//0;7
		AngryAjax.setTimeout(function() {
			tPos = 258;
			$(CasinoBlackJack.notiferSprite).css("background-position", lPos+"px -"+tPos+"px");
		}, 500);//-258;4
		AngryAjax.setTimeout(function() {
			tPos = 344;
			$(CasinoBlackJack.notiferSprite).css("background-position", lPos+"px -"+tPos+"px");
		}, 600);//-344;3
			
		AngryAjax.setTimeout(function() { 
			CasinoBlackJack.showNotiferCycle(t, 0, callback);
		}, 650);//show cycle 4-5-6-4-5-6-4-5-6-4-5-6-4-5-6-4-5-6...	
	},

	showSplit: function(hand, callback) {//Делаем вторую руку видимой, анимируем, переносим в нее карту, и назначаем все current переменные на нее, так чо бы игра обычными функциями работала со второй рукой как с первой.
		if (hand == "player2") {
			this.PlayingAnimationNow = this.PlayingAnimationNow+1;
			CasinoBlackJack.notiferAnm('split');
			//CasinoBlackJack.interfaceTransformation();
			$(CasinoBlackJack.PlayerHand2).show();
			$(CasinoBlackJack.PlayerHand1).animate({
				marginTop: "-=32px"
			});
			$(CasinoBlackJack.PlayerHand2).css({
				zIndex: "1"
			}).animate({
				top: "-=50px"
			});
			var targetCardPos = $(CasinoBlackJack.PlayerHand2).find('.area.cards ul.stack').offset();
			var travelingCardPos = $(CasinoBlackJack.PlayerHand1).find('li:first').offset();
			AngryAjax.setTimeout(function() {
				CasinoBlackJack.travelingCard = $(CasinoBlackJack.PlayerHand1).find('li:last').appendTo('body').css({
					display: "block", 
					position: "absolute", 
					top: travelingCardPos.top, 
					left: travelingCardPos.left
				}).animate({
					"top": "-=80px"
				}, "slow").animate({
					"left": targetCardPos.left - 22, 
					"top": targetCardPos.top - 82
				}, "fast",	function() {
					this.PlayingAnimationNow = this.PlayingAnimationNow-1;
					$(CasinoBlackJack.PlayerHand2).find('.area.cards .stack').append(CasinoBlackJack.travelingCard);
					$(CasinoBlackJack.travelingCard).removeAttr('style');
					$(CasinoBlackJack.gameArea).addClass('double');
					$(CasinoBlackJack.PlayerHand1).removeClass('current').css({
						zIndex: "0"
					});
					$(CasinoBlackJack.PlayerHand2).addClass('current').css({
						zIndex: "1"
					});
					CasinoBlackJack.showAnimationCards(CasinoBlackJack.tempArray.pop(),callback);
					CasinoBlackJack.showDouble(this.BetLeft/10,'player2',this.BetLeft);
				});
			}, 1500);
		} else if (hand == "player1") {
			$(CasinoBlackJack.PlayerHand2).removeClass('current').animate({
				top: "-100px", 
				marginTop: "0px"
			}, "fast", function() {
				$(CasinoBlackJack.PlayerHand2).css({
					zIndex: "0"
				});
				$(CasinoBlackJack.PlayerHand1).animate({
					top: "50px"
				}, "slow", function() {
					$(CasinoBlackJack.PlayerHand1).addClass('current').css({
						zIndex: "1"
					});
					if(callback!=undefined){
						callback();
					}
				});
			});

			
		} else {
			if(callback!=undefined){
				callback();
		}
			return false;
		}	
	},

	FastSplit: function(callback) {
		$(CasinoBlackJack.gameArea).addClass('double');
		if (this.gameState  & this.STATE_STOPED_LEFT) {	
			$(CasinoBlackJack.PlayerHand1).addClass('current').attr('style', '').show().css({
				top: "50px", 
				marginTop: "-32px", 
				zIndex: "1"
			});
			$(CasinoBlackJack.PlayerHand2).removeClass('current').attr('style', '').show().css({
				top: "-100px", 
				marginTop: "0", 
				zIndex: "0"
			});
		} else {
			$(CasinoBlackJack.PlayerHand1).removeClass('current').attr('style', '').show().css({
				marginTop: "-32px", 
				zIndex: "0"
			});
			$(CasinoBlackJack.PlayerHand2).addClass('current').attr('style', '').show().css({
				top: "-50px", 
				margin: "0", 
				zIndex: "1"
			});
		}
		if(callback!=undefined){
			callback();
		}
			
	},


		
	showNotiferCycle: function(t, s, callback) {//Цикл мерцания оповещения.
		var lPos, tPos = 344;
		var s = s;
		if (t == 'split') lPos = 0;
		if (t == 'push') lPos = -394;
		AngryAjax.setTimeout(function() {
			s = s+1;
			$(CasinoBlackJack.notiferSprite).css("background-position", lPos+"px -"+tPos+"px");
			//console.log(s);
		}, 0);//-258;4
		AngryAjax.setTimeout(function() {
			s = s+1;
			tPos = (tPos-86);
			$(CasinoBlackJack.notiferSprite).css("background-position", lPos+"px -"+tPos+"px");
			//console.log(s);
		}, 100);//-172;5
		AngryAjax.setTimeout(function() {
			s = s+1;
			tPos = (tPos-86);
			$(CasinoBlackJack.notiferSprite).css("background-position", lPos+"px -"+tPos+"px");
			if(s >= 9) {
				s = 0;
				CasinoBlackJack.notiferEnd(t,callback);
			} else {
				CasinoBlackJack.showNotiferCycle(t, s,callback);
			};
    
		}, 200);//-70;6
	},

	notiferEnd:function(t, callback) {//Скрытие оповещения.
		var lPos, tPos = 344;
		if (t == 'split') lPos = 0;
		if (t == 'push') lPos = -394;
		AngryAjax.setTimeout(function() {
			$(CasinoBlackJack.notiferSprite).css("background-position", lPos+"px -"+tPos+"px")
		}, 100);//-344;3
		AngryAjax.setTimeout(function() {
			tPos = (tPos+86);
			$(CasinoBlackJack.notiferSprite).css("background-position", lPos+"px -"+tPos+"px")
		}, 200);//-430;2
		AngryAjax.setTimeout(function() {
			tPos = (tPos+86);
			$(CasinoBlackJack.notiferSprite).css("background-position", lPos+"px -"+tPos+"px")
		}, 300);//-516;1
		AngryAjax.setTimeout(function() {
			$(CasinoBlackJack.notiferArea).hide();
			this.PlayingAnimationNow = this.PlayingAnimationNow-1;
			if(callback!=undefined){
				callback();
			}
		}, 400);//-516;1
	},

	showDouble: function(chips, hand, bet){
		this.PlayingAnimationNow = this.PlayingAnimationNow+1;
		var Iterations = chips;
		switch (hand) {
			case "player1":
				hand = CasinoBlackJack.PlayerHand1;
				bet	 = CasinoBlackJack.BetRight; 
				break;
			case "player2":
				hand = CasinoBlackJack.PlayerHand2; 
				bet  = CasinoBlackJack.BetLeft; 
				break;
			}
		var playersBetOffset = $(hand).find('.info span.bet i').offset();
		var newBetStarPos = $('.casino .casino-blackjack .welcome .actions_row').offset();
		$('body').append('<i></i>');
		$('body').children('i').css({
			display: "block", 
			zIndex: "19", 
			position: "absolute", 
			top: newBetStarPos.top + 6, 
			left: newBetStarPos.left + 314
		}).animate({
			"left": playersBetOffset.left - 4.5, 
			"top": playersBetOffset.top
		}, "slow", function() {
			$('body > i').remove();
			chips = chips-1;
			if (chips > 0) {
				CasinoBlackJack.showDouble(chips, hand, bet);
			} else {
				this.PlayingAnimationNow = this.PlayingAnimationNow-1;
				$(hand).find('.info span.bet > span:first').text(bet);
			}
		});
	},
	changeBalance: function(val){
		var currentChips=parseInt($(CasinoBlackJack.balanceTable).html().replace(",",""));
		$(CasinoBlackJack.balanceTable).text(moswar._format(currentChips+0+val));
	},
		
		
	StackFastFilling: function(val,callback) {
		CasinoBlackJack.c = val;
		if ($(CasinoBlackJack.DealerHand).find('.area.cards .stack li.card.undf').size() == 1 && val[1] == "dealer") {
			CasinoBlackJack.showHiddenDealerCard(val);
			}
		if (CasinoBlackJack.c[0] != "?") {
			CasinoBlackJack.cardDOM = $("<li class='card " + CasinoBlackJack.c[0] + "'><span class='value'></span><div class='suit'></div></li>");
		} else {
			CasinoBlackJack.cardDOM = $("<li class='card undf' value='undefined'><span class='value'></span><div class='suit'></div></li>");
		}
		if(val[1] == "player1" || val[1] == "player2" || val[1] == "dealer") {
			if (val[1] == "player1" || val[1] == "player2") {
				CasinoBlackJack.targetHand = CasinoBlackJack.PlayerHand1;
				$(CasinoBlackJack.targetHand).find('.area.cards .stack').append(CasinoBlackJack.cardDOM);
			}
			if (val[1] == "player2") {
				CasinoBlackJack.targetHand = CasinoBlackJack.PlayerHand2;
				$(CasinoBlackJack.targetHand).find('.area.cards .stack').append(CasinoBlackJack.cardDOM);
			}
			if(val[1] == "dealer") {
				CasinoBlackJack.targetHand = CasinoBlackJack.DealerHand;
				$(CasinoBlackJack.targetHand).find('.area.cards .stack').append(CasinoBlackJack.cardDOM);
			}
			CasinoBlackJack.showScore(CasinoBlackJack.c[2],val[1]);
		}
		if (CasinoBlackJack.tempArray.length != 0) {
			CasinoBlackJack.StackFastFilling(CasinoBlackJack.tempArray.pop(),callback);
		}else{
			if(callback!=undefined){
				callback();
			}
			CasinoBlackJack.postAnimate();
		}
	},

	ShowWinLose: function(prize,loser,callback) {
		if (this.winState  & this.STATE_SPLITED) {
			//console.log("Две руки, и что-то случилось с одной из них");
			if ((this.gameResult & this.RESULT_RIGHT_WIN) || (this.gameResult & this.RESULT_LEFT_WIN)) {
				CasinoBlackJack.splitResult(1, prize,callback);
			} else if ((this.gameResult & this.RESULT_RIGHT_PUSH) || (this.gameResult & this.RESULT_LEFT_PUSH)) {
				CasinoBlackJack.changeBalance(prize);
				CasinoBlackJack.notiferAnm('push',callback);
			//КОНЕЦ ИГРЫ
			} else if ((this.gameResult & this.RESULT_RIGHT_LOOSER) || (this.gameResult & this.RESULT_LEFT_LOOSER)) {
				$(CasinoBlackJack.gameArea).addClass('fail');
				if(callback!=undefined){
					callback();
			}
			} else if (loser=='right') {
				$(CasinoBlackJack.gameArea).addClass('fail');
				if(callback!=undefined){
					callback();
			}
			} else if (loser=='left') {
				$(CasinoBlackJack.gameArea).addClass('fail');
				if(callback!=undefined){
					callback();
			}
			}

			} else {
			//console.log("Завершение игры %d",prize);
			if ((this.gameResult & this.RESULT_RIGHT_WIN) && (this.winState  & this.STATE_BLACKJACK_RIGHT)) {
				$(CasinoBlackJack.gameArea).addClass('win blackjack');
				CasinoBlackJack.changeBalance(prize+0);
				$(CasinoBlackJack.PlayerHand1).find('.info span.desc.win span.type').text('Блэкджек!');
				$(CasinoBlackJack.PlayerHand1).find('.info span.prize').html('Выигрыш:<i></i>'+ prize);
				if(callback!=undefined){
					callback();
			}
			}else if (this.gameResult & this.RESULT_RIGHT_PUSH){
				CasinoBlackJack.changeBalance(prize);
				CasinoBlackJack.notiferAnm('push',callback);
					
			} else if  (this.gameResult & this.RESULT_RIGHT_WIN ) {
				$(CasinoBlackJack.PlayerHand1).find('.info span.desc.win span.type').text('Вы выиграли!');
				CasinoBlackJack.changeBalance(prize+0);
				$(CasinoBlackJack.gameArea).toggleClass('win');
				$(CasinoBlackJack.PlayerHand1).find('.info span.prize').html('Выигрыш:<i></i>'+ prize);	
				if(callback!=undefined){
					callback();
			}
			} else if (this.gameResult & this.RESULT_RIGHT_LOOSER) {
				$(CasinoBlackJack.gameArea).addClass('fail');
				$(CasinoBlackJack.PlayerHand1, CasinoBlackJack.PlayerHand2).find('.info span.bet').remove();
				if(callback!=undefined){
					callback();
				}
			} else if (loser=='right') {
				$(CasinoBlackJack.gameArea).addClass('fail');
					
				$(CasinoBlackJack.PlayerHand1, CasinoBlackJack.PlayerHand2).find('.info span.bet').remove();
				if(callback!=undefined){
					callback();
		}
			}

		}
	},

		
	//сообщение о выйгрыше в сплит режиме
	splitResult: function(onoff, result, callback) {
		this.PlayingAnimationNow = this.PlayingAnimationNow+1;
		if ((this.gameState  & this.STATE_BLACKJACK_LEFT) || (this.gameState  & this.STATE_BLACKJACK_RIGHT)) {
			$(CasinoBlackJack.splitResultPanel).find('.green span.bj').show();
			} else {
			$(CasinoBlackJack.splitResultPanel).find('.green span.bj').hide();
			}
		if (onoff) {
			$(CasinoBlackJack.splitResultPanel).show().animate({
				top: "50%"
			}, 1750, "easeOutElastic", function() {
				if(callback!=undefined){  
					callback();
		}
			});
			$(CasinoBlackJack.splitResultPanel).find('.green span.score').text(result);
			CasinoBlackJack.changeBalance(result);
			CasinoBlackJack.splitResultLamps = AngryAjax.setInterval(function() {
				var srlOffset = Math.floor($(CasinoBlackJack.splitResultPanel).css("background-position").replace(/px/g,"").split(' ')[1]);
				if (srlOffset <= -400) {
					srlOffset = 0;
			} else {
					srlOffset = Math.round(-srlOffset + 200);
			}
				$(CasinoBlackJack.splitResultPanel).css("background-position", "0 -" + srlOffset + "px");
			}, 350);
			} else {
			$(CasinoBlackJack.splitResultPanel).animate({
				top: "-20%"
			}, 1750, "easeOutElastic", function() {
				if(callback!=undefined){
					callback();
			}
				this.PlayingAnimationNow = this.PlayingAnimationNow-1;
				$(CasinoBlackJack.splitResultPanel).hide();
			});
			clearInterval(CasinoBlackJack.splitResultLamps);
		}
	},
		
		
	InsurranceTrigger: function(onoff){
		if (onoff) {
			$(CasinoBlackJack.DealerHand).find('.info').css({
				zIndex: "99"
			});
			$(CasinoBlackJack.DealerHand).find('.info span.total').hide();
			$(CasinoBlackJack.DealerHand).find('span.insurrance').show();
		} else {
			$(CasinoBlackJack.DealerHand).find('.info').css({
				zIndex: "0"
			});
			$(CasinoBlackJack.DealerHand).find('span.insurrance').hide();
			$(CasinoBlackJack.DealerHand).find('.info span.total').show();
		}
	},


	// Сбрасываем изменение интерфейса деления рук и удвоения ставок.
	interfaceTransformation : function() {
		var userActions = $(this.currentPlayerHand).find(
			'.info .after_deal_actions');
		userActions.hide();
		userActions.find('#double_button').remove();
		userActions.find('#split_button').remove();
	},
	
	
	// Очищает стол
	clearTable : function() {
		$(this.gameArea).removeClass('blackjack').removeClass('win')
		.removeClass('fail').removeClass('semiwin').removeClass(
			'double');
		$('.stack li').remove();
		$(this.PlayerHand2).removeClass('win').removeClass('fail');
		$(this.PlayerHand1).find('.info span.total span').text('0');
		$(this.DealerHand).find('.info span.total span').text('0')
		$(this.PlayerHand1).find('.info span.bet > span').text(0);
		$(this.PlayerHand2).find('.info span.bet > span').text(0);
		CasinoBlackJack.splitResult(0,0);
		$('.casino .casino-blackjack .welcome .area.game .area.player')
		.removeAttr('style');
		this.AceVariablesActive = 1;
		this.splitGameActive = 0;
		this.NumOfBigAces = 0;
		$('.button#blackjack-hit').show();
	},
	showRules: function(obj, state) {
		if (typeof(state) == "undefined") {
			if (!$('#blackjack-rules-hint1').is(":visible")) {
				var state = 1;
			} else {
				var state = 0;
			}
		}
		//console.log(state);
		switch (state) {
			case 0:
				$(obj).text('Подробнее');
				$('#blackjack-rules-hint1, #blackjack-rules-hint2').slideUp();
				break;
			case 1:
				$(obj).text('Свернуть правила');
				$('#blackjack-rules-hint1').slideToggle();
				break;
			case 2:
				$('#blackjack-rules-hint2').slideToggle();
				break;
	}
	}
};
