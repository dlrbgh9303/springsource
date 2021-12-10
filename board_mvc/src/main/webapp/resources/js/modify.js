/**
 * modify.jsp 스크립트
 */

$(function() {
	// remove, list 일 떄 전송될 Form
	let formObj = $("#actionForm");

	$("button").click(function(e) {
		e.preventDefault(); //submit막기

		// 어느 버튼에서 명령이 왔는가? 확인을 해줘야한다 
		// data-* 왓더 퍽... 이런게 있엉?
		let oper = $(this).data("oper");

		if (oper == 'modify') {
			formObj = $("form[role='form']");
			
			//첨부 파일 정보 수집하기
			var str = "";
			$(".uploadResult ul li").each(function(i,obj){
				var ele = $(obj);
				
				str += "<input type='hidden' name='attachList["+ i +"].uuid' value='"+ele.data('uuid')+"'>";
				str += "<input type='hidden' name='attachList["+ i +"].uploadPath' value='"+ele.data('path')+"'>";
				str += "<input type='hidden' name='attachList["+ i +"].fileName' value='"+ele.data('filename')+"'>";
				str += "<input type='hidden' name='attachList["+ i +"].fileType' value='"+ele.data('type')+"'>";
			})
			console.log("form");
			console.log(str);
			
			//현재 폼에 첨부파일 정보 추가하기
			formObj.append(str);
			
			
			
			
		} else if (oper == 'remove') {
			formObj.attr("action", "/board/remove").attr("method", "post");
		} else {
			formObj.attr("action", "/board/list").attr("method", "get").find("input[name='bno']").remove();
		}

		formObj.submit();
	})

	// 첨부파일 가져오기
	// 게시물에 달려있는 전체 첨부 파일 가져오기  

	let uploadResult = $(".uploadResult ul");
	let str = "";
	$.getJSON({
		url: 'getAttachList', // == /board/getAttachList 
		data: {
			bno: bno
		},
		success: function(data) {
			console.log(data);

			// 도착한 첨부파일을 보여주기
			showUploadedFile(data);


		}
	})//전체 첨부물 가져오기 종료 

	// 첨부된 파일 목록 보여주기 
	function showUploadedFile(uploadResultArr) {

		// 첨부파일 결과를 보여줄 영역 가져오기 
		var uploadResult = $(".uploadResult ul");

		var str = "";
		$(uploadResultArr).each(function(idx, obj) {
			if (obj.fileType) {

				// 썸네일 이미지 경로 생성
				var fileCallPath = encodeURIComponent(obj.uploadPath + "\\s_" + obj.uuid + "_" + obj.fileName);

				// 원본이미지 경로 생성
				var originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
				originPath = originPath.replace(new RegExp(/\\/g), "/");

				str += "<li data-path ='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "'";
				str += " data-filename='" + obj.fileName + "' data-type='" + obj.fileType + "'>";
				str += "<a href=\"javascript:showImage(\'" + originPath + "\')\">";
				str += "<img src='/display?fileName=" + fileCallPath + "'>";
				str += "<div>" + obj.fileName + "</a> ";
				str += "<button type='button' class='btn btn-warning btn-circle btn-sm' data-file='" + fileCallPath + "' data-type='image'><i class='fa fa-times'></i></button>";
				str += "</div></li>";

			} else {

				// 다운로드 경로 설정
				var fileCallPath = encodeURIComponent(obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName);

				str += "<li data-path ='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "'";
				str += " data-filename='" + obj.fileName + "' data-type='" + obj.fileType + "'>";
				str += "<a href='/download?fileName=" + fileCallPath + "'>";
				str += "<img src='/resources/img/attach.png'><div>" + obj.fileName + "</a> "
				str += "<button type='button'class='btn btn-warning btn-circle btn-sm' data-file='" + fileCallPath + "' data-type='file'><i class='fa fa-times'></i></button>";
				str += "</div></li>";
			}
		})
		console.log(str);

		uploadResult.append(str);


	}


	function checkExtension(fileName, fileSize) {
		var regex = new RegExp("(.*?)\.(txt|jpg|png|gif|hwp|exe)$"); // regexp 확장명 확인 
		var maxSize = 2097152; // 2MB

		if (fileSize > maxSize) {
			alert("파일 사이즈 초과");
			return false;
		}
		if (!regex.test(fileName)) {
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}// checkExtension END

	// 첨부파일 목록에서 X 누르면 목록에서 지우기 
	$(".uploadResult").on("click", "button", function() { // 이벤트 위임 방식 click이 되면 span에 넘기겠다.

		//x 버튼의 부모 li 가져오기
		var targetLi = $(this).closest("li");

		if (confirm("정말로 파일을 삭제하시겠습니까?")) {
			// 목록에서 지우기 
			targetLi.remove();
		}
	}) // X버튼 END   
	$("input[type='file']").change(function(e) {
		e.preventDefault();

		console.log("업로드 요청");

		// FormData 객체 생성 - ajax 형태로 데이터를 보낼때
		// key/value 형태로 쌍을 생성해 줌
		var formData = new FormData();

		//첨부 파일 목록 가져오기
		var inputFile = $("input[name='uploadFile']");

		//console.log(inputFile);

		var files = inputFile[0].files;

		//가져온 목록을 formData 에 추가하기
		for (var i = 0; i < files.length; i++) {
			if (!checkExtension(files[i].name, files[i].size)) {
				return false;
			}

			formData.append("uploadFile", files[i]);
		}


		//query String : http://localhost:8080/upload?name=hong&age=15 --이런거를 칭함
		//processData:false : 데이터를 query String 형태로 변환할 것인가? 
		//contentType:false : (application/x-www-form-urlencoded 로 보낼 것인가?)



		$.ajax({
			url: '/uploadAjax',
			type: 'post',
			processData: false,
			contentType: false,
			data: formData,
			success: function(result) {
				console.log(result);
				showUploadedFile(result);
				$("input[name='uploadFile']").val("");

			},
			error: function(xhr, status, error) {
				console.log(xhr.responseText);
			}
		})
	}) //uploadBtn end








	// 원본 이미지 창 닫기 
	$(".bigPictureWrapper").on("click", function() {
		$(".bigPicture")
			.animate({
				width: '0%',
				height: '0%'
			}, 1000);

		setTimeout(function() {
			$(".bigPictureWrapper").hide();
		}, 1000);

	})

})
function showImage(fileCallPath) {
	console.log(fileCallPath);

	// 안보였던 영역 보이기
	$(".bigPictureWrapper").css("display", "flex").show();

	$(".bigPicture").html("<img src='/display?fileName=" + fileCallPath + "'>")
		.animate({
			width: '100%',
			height: '100%'
		}, 1000);

}