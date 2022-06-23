$(function(){
	 	
	 	let bnoValue =$('input[name="bno"]').val();
	 
		//모달창
		let modal = $('.modal');
		let modalInputReply = modal.find('input[name="reply"]');
		let modalInputReplyer =modal.find('input[name="replyer"]');
		let modalInputReplyDate = modal.find('input[name="regDate"]');
		
		let modalModBtn = $('#modalModBtn')
		let modalRemoveBtn = $('#modalRemoveBtn')
		let modalRegisterBtn = $('#modalRegisterBtn')
		let modalCloseBtn = $('#modalCloseBtn')
		
		//댓글 등록 모달창
		$('#addReplyBtn').click(function (){
			modalInputReplyDate.closest('div').hide();      //closest 는 조상 검색
			modalModBtn.hide();
		    modalRemoveBtn.hide();
		});
	
		//댓글 등록 이벤트 처리 =등록버튼 누를때 뜨는 메세지 
		modalRegisterBtn.on('click',function(){
			let reply = {
					reply : modalInputReply.val(),
					replyer : modalInputReplyer.val(),
					bno : bnoValue
			}
			replyService.add(reply,function(result){
				alert(result);
				modal.modal('hide');
				showList(1);
			})
			
		})
	 showList(1);
	
	 let replyUL = $('.chat');
	 function showList(page){
		 replyService.getList({bno:bnoValue, page:page},function(list){
			 let str ="";
			 for(let reply of list) {
				str += `
				<li data-rno="${reply.rno}">
   				 <div>
    			  	<div class="header">
         				 <strong class="primary-font">${reply.replyer}</strong>
         				 <small class="pull-right text-muted">${displayTime(reply.regDate)}</small>
      				</div>
     				<p>${reply.reply}</p>
    			 </div>	
  				</li>`
				 
			 }
			 replyUL.html(str);
		 });
	 }
	 
	 function displayTime(timeValue){
		let test = "abcdefg";
		console.log(test.substr(1));
		
		let timeArr = JSON.stringify(timeValue).substr(1).split(",");
		console.log(timeArr[0]); //년
		console.log(timeArr[1]); //월
		console.log(timeArr[2]); //일
		return `${timeArr[0]}년 ${timeArr[1]}월${timeArr[2]}일`;
	}
	 
	 
 })
 