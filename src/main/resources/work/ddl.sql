create table usertable(
    username varchar(128) NOT NULL CONSTRAINT USER_PK PRIMARY KEY,
    mailaddress varchar(128) NOT NULL,
    password varchar(128) NOT NULL
);

create table grouptable(
    username varchar(128) NOT NULL,
    groupid varchar(128) NOT NULL,
    CONSTRAINT GROUP_PK PRIMARY KEY(username, groupid),
    CONSTRAINT USER_FK FOREIGN KEY(username)
    REFERENCES usertable(username) ON DELETE
    CASCADE ON UPDATE RESTRICT
);