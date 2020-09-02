create table user_information
(
	USER_ID int not null
		primary key,
	FIRST_NAME text null,
	LAST_NAME char(30) null,
	PASSWORD varchar(20) null,
	LAST_LOGGED_IN datetime null
);

