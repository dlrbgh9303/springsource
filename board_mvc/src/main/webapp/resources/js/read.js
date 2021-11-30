/**
 * read.jsp 스크립트
 */
$(function(){
	//List를 클릭하면 전체리스트 보여주기
	let form = $("#actionForm");
	
	//목록버튼 클릭시 목록화면 보여주기
	$(".btn-info").click(function(){
		location.href="/board/list";
	})
	//Modify를 클릭하면 actionForm 보내기
	//  /board/modify + GET
	$(".btn-default").click(function(){
		form.attr("action","/board/modify")
		form.submit();
	})
})