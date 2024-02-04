create table Student (
    SId int primary key not null,
    SName char(10) not null,
    Password varchar(20) not null,
    Grade int not null, -- 格式: 20XX
    Gender tinyint not null -- 1 男 2 女
);

create table Teacher (
    TId int primary key not null,
    TName char(10) not null,
    Password varchar(20) not null,

    Privilege tinyint not null -- comment 权限等级 1 - 根管理员 2 - 超级管理员 3 - 普通管理员
)