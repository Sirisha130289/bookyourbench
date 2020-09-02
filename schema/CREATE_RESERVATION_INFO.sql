create table reservation_info
(
	USER_ID int null,
	DATE_OF_RESERVATION date null,
	BUILDING_NAME varchar(100) null,
	FLOOR_NO varchar(20) null,
	STATUS varchar(20) null,
	LAST_UPDATED_DATE date null,
	constraint reservation_info_ibfk_1
		foreign key (USER_ID) references user_information (USER_ID)
);

create index FK_RESERVATION_INFO
	on reservation_info (BUILDING_NAME, FLOOR_NO);

create index USER_ID
	on reservation_info (USER_ID);

