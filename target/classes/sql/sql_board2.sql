
drop table if exists board_tbl;

create table board_tbl(
 bno int auto_increment primary key,
 title varchar(300) not null,
 content text not null,
 writer varchar(50) not null,
 regDate timestamp default current_timestamp,
 updateDate timestamp default current_timestamp

);

insert into board_tbl(title,content,writer) 
values('게시물 제목입니다1' , '게시물내용1' , '작성자1');
insert into board_tbl(title,content,writer) 
values('게시물 제목입니다2' , '게시물내용2' , '작성자2');
insert into board_tbl(title,content,writer)
values('게시물 제목입니다3' , '게시물내용3' , '작성자3');
insert into board_tbl(title,content,writer) 
values('게시물 제목입니다4' , '게시물내용4' , '작성자4');

