$(function(){
	 let bnoValue =$('input[name="bno"]').val();
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
         				 <small class="pull-right text-muted">${reply.regDate}</small>
      				</div>
     				<p>${reply.reply}</p>
    			 </div>	
  				</li>`
				 
			 }
			 replyUL.html(str);
		 });
	 }
	 showList(1);
 })