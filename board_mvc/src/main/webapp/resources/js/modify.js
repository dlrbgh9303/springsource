/**
 *  modifu.jsp 스크립트
 */
$(function() {

	//remove,list 일때 전송될 폼
	let formObj = $("#actionForm");



	$("button").click(function(e) {
		e.preventDefault(); // modify.jsp 버튼의 submit 막기

		//어느버튼에서 명령이 왔는가?
		// data-*
		let oper = $(this).data("oper");

		if (oper == 'modify') {
			formObj = $("form[role='form']");
		} else if (oper == 'remove') {
			formObj.attr("action", "/board/remove")
				.attr("method", "post");
		} else {
			formObj.attr("action", "/board/list")
				.attr("method", "get")
				.find("input[name='bno']").remove();
		}


		formObj.submit();

	})
})