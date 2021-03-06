<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value= "${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<h2>파일 업로드 : AJAX</h2>
<div class="uploadDiv">
	<input type="file" name="uploadFile" multiple="multiple">
</div>
<button id = "uploadBtn">업로드</button>
<div class="uploadResult">
	<ul></ul>
</div>

<div class="oImg"></div>

</body>
<script>

	$(function(){
	   let contextPath = '${pageContext.request.contextPath}';
	   let regex = new RegExp("(.*?)\.(exe|sh|js|alz)$")
	   let maxSize = 5242880;
	   
	   function checkExtension(fileName, fileSize){
	      if(fileSize >= maxSize){
	         alert('파일 크기 초과');
	         return false;
	      }
	      if(regex.test(fileName)){
	         alert('허용되지 않는 확장자');
	         return false;
	      }
	      return true;
	   }
	   
	   let cloneObj = $('.uploadDiv').clone();
	   $('#uploadBtn').on('click', function(){
	      let formData = new FormData();
	      
	      let inputFile = $('input[name="uploadFile"]');
	      let files = inputFile[0].files
	      console.log(files)
	      
	      for (let i = 0; i <files.length; i++) {
	    	  if(!checkExtension(files[i].name,files[i].size)){
	    		  return false;
	    	  }
	         formData.append("uploadFile", files[i])
	      }
	      
	      $.ajax({
	         url : contextPath + '/uploadAjaxAction',
	         processData : false,
	         contentType: false,
	         data : formData,
	         type : 'post',
	         success : function(result){
	            alert("Uploaded");
	            $('.uploadDiv').html(cloneObj.html());
	            showUploadFile(result);
	          
	         }
	      })
	   })
	   
	   let uploadResult = $('.uploadResult ul');
	   function showUploadFile(uploadResultArr){
		   let str = "";
		   $(uploadResultArr).each(function(i,obj) {
			   if(!obj.image) { //이미지가 아닌 경우
				   let fileCellPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
			       let fileLink = fileCellPath.replace(new RegExp(/\\/g),"/");
			   	   console.log(fileCellPath);
				   str+= "<li><img src='${contextPath}/resources/img/attach.png'>"
				   str+= "<a href='${contextPath}/download?fileName="+fileCellPath+"'>"+obj.fileName+"</a>"
				   str+= "<span data-file='" +fileLink+"' data-type='file'>삭제</span>"
				
				   str+="</li>"
				   
			   } else {  //이미지인 경우
				   let fileCellPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName); 
			   	   let originPath = obj.uploadPath+"\\" +obj.uuid+ "_" + obj.fileName;
			   	   originPath = originPath.replace(new RegExp(/\\/g),"/");
			   	   
			   	   str += "<li><img src='${contextPath}/display?fileName="+fileCellPath+"'>";
			   	   str += "<a href='javascript:showImage(\""+originPath+"\")'>이미지 원본보기</a>"
			   	   str += "<br><span data-file='"+fileCellPath+"'data-type ='image'>삭제</span>"
			   	   str += "</li>"
			   }
			   
		})
	  uploadResult.append(str);
   } //uploadResult 끝
	   
	  uploadResult.on('click','span',function(){
		let targetFile = $(this).data('file');
		let type = $(this).data('type');
		
		
	$.ajax({
		url: contextPath + '/deleteFile',
		type: 'post',
		data: {
			fileName : targetFile,
			type: type
			
		},
		dataType : 'text',
		success: function(result){
			alert(result)
		}
		
		
		
	})
	  })   
			
})  //document.ready end;
	
	function showImage (path) {
		let imgTag = "<img src='${contextPath}/display?fileName="+encodeURI(path)+"'>";
   		$('.oImg').html(imgTag);
	}
</script>
<style>
.omg img {width:300px;}
</style>
</html>