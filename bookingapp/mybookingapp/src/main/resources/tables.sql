/*********************/
--- postgresssql not support varchar2, postgress sql support varchar(4000) | text 
-- oracle support varchar2
create table consumer(
 consumer_id VARCHAR(4000),
 consumer_name VARCHAR(4000),
 consumer_password VARCHAR(4000),
 consumer_identity_no VARCHAR(4000),
 constraint pk_consumer_id PRIMARY KEY (consumer_id)
);

create table theater(
theater_id VARCHAR(4000),
theater_name VARCHAR(4000) not null,
theater_city VARCHAR(4000),
theater_state VARCHAR(4000),
theater_pincode VARCHAR(4000),
constraint pk_theater_id PRIMARY KEY (theater_id)
);
create table movie(
movie_id VARCHAR(4000),
movie_name VARCHAR(4000) not null,
constraint pk_movie_id PRIMARY KEY (movie_id)
);
create table screen(
screen_id VARCHAR(4000),
theater_id VARCHAR(4000),
screen_no int not null,
constraint pk_screen_id PRIMARY KEY (screen_id),
constraint fk_theater_id foreign key(theater_id) references theater (theater_id)
);
-- seat always tied or bind with screen
--Seats are static physical entities tied to a screen. Booking is a time-based event that records seat usage per show. History must be stored in booking records, not by duplicating seats.
create table seat(
seat_id VARCHAR(4000),
screen_id VARCHAR(4000),
seat_no int not null,
seat_type VARCHAR(4000) not null,
constraint pk_seat_id PRIMARY KEY (seat_id),
constraint fk_screen_id foreign key(screen_id) references screen (screen_id)
);
--- always remember show on next day of same movie is new show not older one so do not reuse it -- it is time based
create table show(
show_id VARCHAR(4000),
movie_id VARCHAR(4000),
screen_id VARCHAR(4000),
show_date DATE,
from_time TIMESTAMP,
to_time TIMESTAMP,
constraint pk_show_id PRIMARY KEY (show_id),
constraint fk_screen_id foreign key(screen_id) references screen (screen_id),
constraint fk_movie_id foreign key(movie_id) references movie (movie_id)
)
--(:booking_id,:screen_id,:show_id,:user_id,:booking_date,:booking_status)
create table booking
(
booking_id VARCHAR(4000),
show_id VARCHAR(4000),
consumer_id VARCHAR(4000),
booking_date DATE,
booking_status VARCHAR(4000),
constraint pk_booking_id PRIMARY KEY (booking_id),
constraint fk_show_id foreign key(show_id) references show (show_id),
constraint fk_consumer_id foreign key(consumer_id) references consumer (consumer_id)
)

--- have multiple seat against one booking so normalized
create table booking_seat
(
booking_id VARCHAR(4000),
seat_id VARCHAR(4000),
constraint pk_booking_seat_id PRIMARY KEY (booking_id,seat_id),
constraint fk_booking_id foreign key(booking_id) references booking (booking_id),
constraint fk_seat_id foreign key(seat_id) references seat (seat_id)
)


commit
--/****
---- INSERT INTO consumer (
----     consumer_id,
----     consumer_name,
----     consumer_password,
----     consumer_identity_no
---- ) VALUES (
----     'C001',
----     'Ravi Patel',
----     'hashed_password_123',
----     'AADHAR123456'
---- );
--
---- INSERT INTO consumer (
----     consumer_id,
----     consumer_name,
----     consumer_password,
----     consumer_identity_no
---- ) VALUES (
----     'C002',
----     'Patel',
----     'hashed_password_122',
----     'AADHAR123457'
---- );
--
---- INSERT INTO theater (
----     theater_id,
----     theater_name,
----     theater_city,
----     theater_state,
----     theater_pincode
---- ) VALUES (
----     'T001',
----     'PVR Cinemas',
----     'Ahmedabad',
----     'Gujarat',
----     '380015'
---- );
--
---- INSERT INTO movie (
----     movie_id,
----     movie_name
---- ) VALUES (
----     'M001',
----     'Inception'
---- );
--
---- INSERT INTO screen (
----     screen_id,
----     theater_id,
----     screen_no
---- ) VALUES (
----     'S001',
----     'T001',
----     1
---- );
--
---- INSERT INTO seat (
----     seat_id,
----     screen_id,
----     seat_no,
----     seat_type
---- ) VALUES
---- ('SEAT1', 'S001', 1, 'REGULAR'),
---- ('SEAT2', 'S001', 2, 'REGULAR'),
---- ('SEAT3', 'S001', 3, 'PREMIUM');
--
---- INSERT INTO show (
----     show_id,
----     movie_id,
----     screen_id,
----     show_date,
----     from_time,
----     to_time
---- ) VALUES (
----     'SH001',
----     'M001',
----     'S001',
----     DATE '2025-12-25',
----     TIMESTAMP '2025-12-25 18:00:00',
----     TIMESTAMP '2025-12-25 21:00:00'
---- );
--
--ALTER TABLE booking_seat ADD show_id VARCHAR(4000) NOT NULL;
--
--ALTER TABLE booking_seat
--ADD CONSTRAINT uq_show_seat UNIQUE (show_id, seat_id);
--
--select * from consumer;
--select * from theater;
--select * from movie;
--select * from seat;
--select * from show;
--select * from screen;
--select * from booking;
--select * from booking_seat;
--
--SELECT SHOW_ID,SEAT_ID,B.CONSUMER_ID FROM BOOKING B
--INNER JOIN BOOKING_SEAT BS ON ( B.BOOKING_ID = BS.BOOKING_ID)
--WHERE B.SHOW_ID = 'SH001'
--AND BS.SEAT_ID = 'SEAT1';
--
--SELECT count(seat_id) FROM BOOKING B
--INNER JOIN BOOKING_SEAT BS ON ( B.BOOKING_ID = BS.BOOKING_ID)
--WHERE B.SHOW_ID = 'SH001'
--AND BS.SEAT_ID in ('SEAT1','SEAT2');
--
--delete from BOOKING_SEAT;
--delete from BOOKING;
--commit;
--
---- rollback;
--****/
--
