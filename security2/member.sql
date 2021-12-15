select * from SPRING_MEMBER;

select * from SPRING_MEMBER_AUTH;

drop table spring_member;
create table spring_member(
	userid varchar2(50) not null primary key,
	userpw varchar2(100) not null,
	username varchar2(100) not null,
	regdate date default sysdate,
	updatedate date default sysdate,
	enabled char(1) default '1'
);

drop table spring_member_auth;

create table spring_member_auth(
	userid varchar(50) not null,
	auth varchar2(50) not null,
	constraint fk_member_auth1 foreign key(userid) references spring_member(userid)
);



	
select s1.userid, userpw, username, regdate,updatedate, enabled, auth
from spring_member s1 left outer join spring_member_auth s2 on s1.userid = s2.userid
where s1.userid = 'admin90';

-- join, 서브 쿼리 면접 질문
-- left outer join에서 처음에 오는게 left join이고 나중에 오는게 right join


insert into spring_member_auth(userid,auth) values('admin90','ROLE_MEMBER');

select * from SPRING_MEMBER_auth;








