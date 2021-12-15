/**
 * reply.jsp 스크립트
 */

// 자바스크립트 모듈화
let replyService = (function(){
	
	function add(reply,callback){
		console.log("add method 실행");
	
		$.ajax({
			url:'/replies/new',
			type:'post',
			contentType:'application/json',
			data:JSON.stringify(reply),
			success:function(result){
				if(callback){ // 비동기식으로 움직이는 것이고 
					callback(result); //success가 넘어오고
				}
			}, 
			error:function(xhr,status,err){                                     
					error(err);
			}
		})
	} // add end
	
	function getList(param,callback){
		
		let bno = param.bno;
		let page = param.page || 1;
		
		$.getJSON({                    
			url:'/replies/pages/'+bno+'/'+page,
			success:function(data){
			 	callback(data.replyCnt,data.list);	
			}
		})
	}// getList end
	
	//댓글 삭제
	function remove(rno,callback,error){
		
		$.ajax({
			url:'/replies/'+rno,
			type:'delete',
			success:function(result){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,err){
				if(error){
					error(xhr.responseText);
				}
			}
		})
	} // remove end
	
	function update(reply,callback,error){
		
		$.ajax({
			url:'/replies/'+reply.rno,
			type:'put',
			contentType:'application/json',
			data:JSON.stringify(reply),
			success:function(data){
				if(callback){
					callback(data);
				}
			},
			error:function(xhr,status,err){
				if(error){
					error(xhr.responseText);
				}
			}
		})	
	} // update end
	
	
	function get(rno,callback){
		
		$.getJSON({
			url:'/replies/'+rno,
			success:function(data){
				if(callback){
					callback(data);
				}
			}
		})			
	}//get end
	
	//  1638774398000
	function displayTime(timeValue){
		
		var today = new Date();
		
		var gap = today.getTime() - timeValue;
		
	    var dateObj = new Date(timeValue);

		var str = "";


		// 댓글 단 날짜가 24시간보다 작다면 댓글 작성일 시분초 처리
		if(gap < (1000 * 60 * 60 * 24)){
			var hh = dateObj.getHours(); // 1,9,10
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			// 01:02:09
			return [(hh>9?'':'0')+hh, ':', (mi>9?'':'0')+mi, ':', (ss>9?'':'0')+ss].join('');
		}else{ //년월일 처리
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth()+1; // 월 0부터 시작
			var dd = dateObj.getDate();
			
			// 2021/07/12
			return [yy, '/', (mm>9?'':'0')+mm, '/', (dd>9?'':'0')+dd].join('');
		}
	} // displayTime end
	
	return {
		add:add,
		getList:getList,
		remove:remove,
		update:update,
		get:get,
		displayTime:displayTime
	};
	
})();

