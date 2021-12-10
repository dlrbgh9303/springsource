create table spring_board(
	bno number(10,0),				 	-- 글번호
	title varchar2(200) not null,		-- 제목
	content varchar2(2000) not null,	-- 내용 
	writer varchar2(50) not null,		-- 작성자
	regdate date default sysdate,  		-- 작성날짜
	updatedate date default sysdate 	-- 수정날짜 
);
 
alter table spring_board add constraint pk_spring_board primary key(bno);
create sequence seq_board;  
select * from SPRING_BOARD;

delete from spring_attach;

select * from spring_attach;
update spring_attach set uploadpath='2021\12\09';