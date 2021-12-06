/**
 * read.jsp 스크립트
 */
$(function() {
	//List를 클릭하면 전체리스트 보여주기
	let form = $("#actionForm");

	//목록버튼 클릭시 목록화면 보여주기
	$(".btn-info").click(function() {
		//actionForm 에서 bno 제거
		form.find("input[name='bno']").remove();
		//actionForm action 수정  /board/list
		form.attr("action","/board/list");
		
		//actionForm 전송
		form.submit();
	})

	//Modify를 클릭하면 actionForm 보내기
	//  /board/modify + GET
	$(".btn-default").click(function() {
		form.attr("action", "/board/modify")
		form.submit();
	})
	
	
	//댓글 작업
	
	//댓글 삽입
	replyService.add(
		{bno:bno,replyer:'test',reply:'댓글 작성중...'},
				function(result){
					if(result){
						alert(result);
					}	
	}); // add end
	
	
	
})
























