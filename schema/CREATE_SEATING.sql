create table seating
(
	BUILDING_NAME char(100) not null,
	FLOOR_NO varchar(20) not null,
	DATE date not null,
	TOTAL_SEATS int null,
	TOTAL_RESERVABLE_SEATS int null,
	SEATS_BOOKED int null,
	RESERVABLE_SEATS_REMAINING int null,
	LAST_UPDATED_DATE datetime null,
	primary key (BUILDING_NAME, FLOOR_NO, DATE)
);

