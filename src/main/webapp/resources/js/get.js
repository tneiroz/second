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
			modal.find('input').val('')
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
		
		
	$('.chat').on('click','li',function(){
		//alert('클릭' + $(this).data('rno'));
		let rno= $(this).closest('li').data('rno');
		replyService.get(rno,function(reply){
			console.log(reply);
			modalInputReply.val(reply.reply);
			modalInputReplyer.val(reply.replyer);
			modalInputReplyDate.val(displayTime(reply.updateDate))
									.attr("readonly","readonly");
			modal.data("rno",reply.rno);
			modalInputReplyDate.closest('div').show();
			modalModBtn.show();
			modalRemoveBtn.show();
			modalRegisterBtn.hide();
			modal.modal("show");
		})
	})
	//댓글 수정 처리
	modalModBtn.on('click',function(){
		let reply = {
			rno : modal.data('rno'),
			reply: modalInputReply.val(),
		}
		replyService.update(reply,function(result){
			alert(result);
			modal.modal('hide');
			showList(1);
		})
	})
	
	modalRemoveBtn.on('click', function(){
		let rno = modal.data('rno');
		replyService.remove(rno,function(result){
			alert(result);
			showList(1);
		})
	})
	//첨부파일 불러오기
	$.getJSON(contextPath+"/board/getAttachList", {bno: bnoValue} , function(attachList){
		let str = "";
		$(attachList).each(function(i,obj) {
			if (!obj.fileType) { //이미지가 아닌 경우

				let fileCellPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
				str += "<li class='list-group-item' data-path='" + obj.uploadPath + "' ";
				str += "data-uuid='" + obj.uuid + "'data-filename='" + obj.fileName + "' data-type='" + obj.fileType + "'>";
				str += "<img src='"+contextPath+"/resources/img/attach.png'>"
				str += "<a href='"+contextPath+"/download?fileName=" + fileCellPath + "'>" + obj.fileName + "</a>"
				str += "</li>"

			} else {  //이미지인 경우
				let fileCellPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
				let originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
				originPath = originPath.replace(new RegExp(/\\/g), "/");

				str += "<li class='list-group-item' data-path='" + obj.uploadPath + "'";
				str += "data-uuid='" + obj.uuid + "'data-filename='" + obj.fileName + "' data-type='" + obj.fileType + "'>";
				str += "<img src='"+contextPath+"/display?fileName=" + fileCellPath + "'>";
				str += "<a href='"+contextPath+"/download?fileName=" + originPath +"'>" + obj.fileName + "</a>"
				str += "</li>"
			}

		})  //each 끝
		$('.uploadResult ul').append(str);
	})  //JSON 끝

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