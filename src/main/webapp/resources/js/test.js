console.log("뽈롱");

(function myFunction(){
   console.log("안녕?");
})();

// 삭제

var obj = {
   "bno" : "1",
   "reply" : "안녕하세요?",
   "replyer" : "작성자",
   
   add:function(){
	console.log("?!?!?!?!?!?")
	}
}
console.log(obj);

//객체 접근
console.log(obj.bno);
console.log(obj.reply);
console.log(obj.replyer);

// 객체를 함수로 반환
function myObjFun(bno, reply, replyer){
   return {
      "bno" : bno,
      "reply" : reply,
      "replyer" : replyer
   };
}

let reply1 =  myObjFun(10, "뚱땅이", "누군가");
console.log(reply1);