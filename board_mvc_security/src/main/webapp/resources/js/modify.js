/**
 *  modify.jsp 스크립트
 */
$(function(){
	
	//remove. list 일때 전송될 폼
	let formObj = $("#actionForm");
	
	$("button").click(function(e){
		e.preventDefault(); // submit 막기
		
		//어느 버튼에서 명령이 왔는가? 확인을 해줘야한다
		// data-*
		//modify 일때 
		let oper = $(this).data("oper");
		
		if(oper=='modify'){
			formObj = $("form[role='form']");
			
			//첨부 파일 정보 수집하기
			var str = "";
			$(".uploadResult ul li").each(function(i,obj){
				var ele = $(obj);
			
				str += "<input type='hidden' name='attachList["+ i +"].uuid' value='"+ ele.data('uuid')+"'>";
				str += "<input type='hidden' name='attachList["+ i +"].uploadPath' value='"+ ele.data('path')+"'>";
				str += "<input type='hidden' name='attachList["+ i +"].fileName' value='"+ ele.data('filename')+"'>";
				str += "<input type='hidden' name='attachList["+ i +"].fileType' value='"+ ele.data('type')+"'>";
			})
			console.log("form");
			console.log(str);
				
			//현재 폼에 첨부파일 정보 추가하기
			formObj.append(str);
			
			
		}else if(oper=='remove'){
			formObj.attr("action","/board/remove")
				   .attr("method","post");
		}else{
			formObj.attr("action","/board/list")
				   .attr("method","get")
				   .find("input[name='bno']").remove();
		}
		formObj.submit();
	})
	// 첨부파일 가져오기
	
	//첨부파일 보여줄 영역 가져오기
	let uploadResult = $(".uploadResult ul");
	let str="";
	
	//게시물에 달려있는 첨부 파일 가져오기
	$.getJSON({
		url:'getAttachList', //  /board/getAttachList
		data:{
			bno:bno
		},
		success:function(data){
			console.log(data);
	
			//도착한 첨부파일을 보여주기 
			showUploadedFile(data);
	} 
  })//전체 첨부물 가져오기 종료
		
	function showUploadedFile(uploadResultArr){
            
      //첨부파일 결과를 보여줄 영역 가져오기
      var uploadResult = $(".uploadResult ul");
      
      var str="";
      $(uploadResultArr).each(function(idx,obj){
		if(obj.fileType){ //이미지 파일인 경우
			
			//썸네일 이미지 경로 생성
			var fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
					
			//원본 이미지 경로 생성
			var originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
			originPath = originPath.replace(new RegExp(/\\/g),"/");
								
         	str += "<li data-path='"+ obj.uploadPath +"' data-uuid='"+ obj.uuid +"'";
			str += " data-filename='"+ obj.fileName +"' data-type='"+ obj.fileType +"'>";	
         	str += "<a href=\"javascript:showImage(\'"+ originPath +"\')\">";
			str += "<img src='/display?fileName="+ fileCallPath +"'>";
			str += "<div>"+obj.fileName+"</a>";
			str += "<button type='button' class='btn btn-warning btn-circle btn-sm' data-file='"+ fileCallPath +"' data-type='image'><i class='fa fa-times'></i></button>";
			str += "</div></li>"; 
		}else{			

			// 다운로드 경로 설정(button 안에 속성으로 "data-"가 있어야 함), 일반 파일
			var fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
         	str += "<li data-path='"+ obj.uploadPath +"' data-uuid='"+ obj.uuid +"'";
			str += " data-filename='"+ obj.fileName +"' data-type='"+ obj.fileType +"'>";	
         	str += "<a href='/download?fileName="+fileCallPath+"'>";                       
			str += "<img src='/resources/img/attach.png'><div>"+obj.fileName+"</a> ";
			str += "<button type='button' class='btn btn-warning btn-circle btn-sm' data-file='"+ fileCallPath +"' data-type='file'><i class='fa fa-times'></i></button>";
			str += "</div></li>"; 
		}
      })
	
	  console.log(str);
	
      uploadResult.append(str);
   }

	function checkExtension(fileName,fileSize){
		var regex = new RegExp("(.*?)\.(txt|jpg|gif|png)$");
		var maxSize = 2097152;  //약 2 MB
		
		if(fileSize > maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		
		if(!regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	} //checkExtensive end		
			
	// 첨부파일 목록에서 X 누르면 목록에서 지우기 
   $(".uploadResult").on("click","button",function(){ // 이벤트 위임 방식 click이 되면 span에 넘기겠다.
   
      //x 버튼의 부모 li 가져오기
      var targetLi = $(this).closest("li");
      
      if(confirm("정말로 파일을 삭제하시겠습니까?")){
         // 목록에서 지우기 
         targetLi.remove();
      }
   }) // X버튼 END

	$("input[type='file']").change(function(e){
		e.preventDefault();
	
		console.log("업로드 요청");	
	
		// FormData 객체 생성 - ajax 형태로 데이터를 보낼 때 
		// key/value 형태로 쌍을 생성해 줌 
		var formData = new FormData();
		
		// 첨부 파일 목록 가져오기
		var inputFile = $("input[name='uploadFile']");
		
		//console.log(inputFile);
		
		var files = inputFile[0].files;
		
	// 가져온 목록을 formdata에 추가하기
	for(var i =0;i<files.length;i++){
		if(!checkExtension(files[i].name,files[i].size)){
			return false;
		}
		
		formData.append("uploadFile",files[i]);
	}
	
	// query string(주소줄에 따라가는 의미) : http://localhost:8080/upload?name=hong&age=15
	// processData:false (데이터를 query string(주소줄에 따라가는 의미) 형태로 변환할 것인가?)
	// contentType:false (application/x-www-form-urlencoded 로 보낼 것인가?) 
		 
	
	$.ajax({
		url:'/uploadAjax',
		type:'post',
		processData:false, // 꼭 들어와야하는 코드
		contentType:false,
		data:formData,
		success:function(result){
			console.log(result);
			showUploadedFile(result);
			$("input[name='uploadFile']").val("");
		},
		error:function(xhr,status,err){
			console.log(xhr.responseText);
		}
	  })
   })// uploadBtn end

			
			
	//원본 이미지 창 닫기
	$(".bigPictureWrapper").on("click",function(){
		$(".bigPicture").animate({
						width:'0%',
						height:'0%'
						}, 1000);
			setTimeout(function(){
				$(".bigPicturewrapper").hide();
				},1000);
			})


function showImage(fileCallPath){ //이미지를 크게 띄우는 부분
	console.log(fileCallPath);
	
	//안보였던 영역 보이기
	$(".bigPictureWrapper").css("display","flex").show();
	
	$(".bigPicture").html("<img src='/display?fileName="+ fileCallPath + "'>")
					.animate({
						width:'100%',
						height:'100%'
					}, 1000);
		
		}
})