/**
 * read.jsp 와 관련된 스크립트임!
 */

$(function(){
	
	let form = $("#actionForm");
	
	//목록버튼 클릭시 목록화면 보여주기
	$(".btn-secondary").click(function(){
		location.href="/book/list";
	})
	
	//삭제버튼 클릭시 폼 전송(get)
	$(".btn-danger").click(function(){
		form.attr("action","/book/remove");
		form.submit();
	})
	
	//수정버튼 클릭시 폼 전송(get)
	$(".btn-primary").click(function(){
		form.attr("action","/book/modify");
		form.submit();
		
	})
})











