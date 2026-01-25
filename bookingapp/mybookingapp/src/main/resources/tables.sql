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
);
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
);

--- have multiple seat against one booking so normalized
create table booking_seat
(
booking_id VARCHAR(4000),
seat_id VARCHAR(4000),
constraint pk_booking_seat_id PRIMARY KEY (booking_id,seat_id),
constraint fk_booking_id foreign key(booking_id) references booking (booking_id),
constraint fk_seat_id foreign key(seat_id) references seat (seat_id)
);


commit
/****
 INSERT INTO consumer (
     consumer_id,
     consumer_name,
     consumer_password,
     consumer_identity_no
 ) VALUES (
     'C001',
     'Ravi Patel',
     'hashed_password_123',
     'AADHAR123456'
 );

 INSERT INTO consumer (
     consumer_id,
     consumer_name,
     consumer_password,
     consumer_identity_no
 ) VALUES (
     'C002',
     'Patel',
     'hashed_password_122',
     'AADHAR123457'
 );

 INSERT INTO theater (
     theater_id,
     theater_name,
     theater_city,
     theater_state,
     theater_pincode
 ) VALUES (
     'T001',
     'PVR Cinemas',
     'Ahmedabad',
     'Gujarat',
     '380015'
 );

 INSERT INTO movie (
     movie_id,
     movie_name
 ) VALUES (
     'M001',
     'Inception'
 );

 INSERT INTO screen (
     screen_id,
     theater_id,
     screen_no
 ) VALUES (
     'S001',
     'T001',
     1
 );

 INSERT INTO seat (
     seat_id,
     screen_id,
     seat_no,
     seat_type
 ) VALUES
 ('SEAT1', 'S001', 1, 'REGULAR'),
 ('SEAT2', 'S001', 2, 'REGULAR'),
 ('SEAT3', 'S001', 3, 'PREMIUM');

 INSERT INTO show (
     show_id,
     movie_id,
     screen_id,
     show_date,
     from_time,
     to_time
 ) VALUES (
     'SH001',
     'M001',
     'S001',
     DATE '2025-12-25',
     TIMESTAMP '2025-12-25 18:00:00',
     TIMESTAMP '2025-12-25 21:00:00'
 );

ALTER TABLE booking_seat ADD show_id VARCHAR(4000) NOT NULL;

ALTER TABLE booking_seat
ADD CONSTRAINT uq_show_seat UNIQUE (show_id, seat_id);

select * from consumer;
select * from theater;
select * from movie;
select * from seat;
select * from show;
select * from screen;
select * from booking;
select * from booking_seat;

SELECT SHOW_ID,SEAT_ID,B.CONSUMER_ID FROM BOOKING B
INNER JOIN BOOKING_SEAT BS ON ( B.BOOKING_ID = BS.BOOKING_ID)
WHERE B.SHOW_ID = 'SH001'
AND BS.SEAT_ID = 'SEAT1';

SELECT count(seat_id) FROM BOOKING B
INNER JOIN BOOKING_SEAT BS ON ( B.BOOKING_ID = BS.BOOKING_ID)
WHERE B.SHOW_ID = 'SH001'
AND BS.SEAT_ID in ('SEAT1','SEAT2');

delete from BOOKING_SEAT;
delete from BOOKING;
commit;

-- rollback;
****/

/* =========================================================
   MyBookingApp - Database Schema
   Supports Movies + Events + Multi-seat Booking
   Compatible: PostgreSQL / MySQL
   ========================================================= */


/* =========================================================
   VENUE SERVICE TABLES
   ========================================================= */

-- 1. VENUE
CREATE TABLE venue (
    venue_id      BIGSERIAL PRIMARY KEY,
    venue_name    VARCHAR(200) NOT NULL,
    venue_city    VARCHAR(100) NOT NULL,
    venue_type    VARCHAR(50)  NOT NULL
);

-- 2. CONTENT (Movie / Event)
CREATE TABLE content (
    content_id        BIGSERIAL PRIMARY KEY,
    content_type      VARCHAR(50)  NOT NULL,   -- MOVIE, CONCERT, SPORTS
    title             VARCHAR(200) NOT NULL,
    language          VARCHAR(50),
    duration_minutes  INT
);

-- 3. SPACE (Screen / Stage / Ground)
CREATE TABLE space (
    space_id     BIGSERIAL PRIMARY KEY,
    venue_id     BIGINT NOT NULL,
    space_name   VARCHAR(100) NOT NULL,
    capacity     INT NOT NULL,

    CONSTRAINT fk_space_venue
        FOREIGN KEY (venue_id)
        REFERENCES venue (venue_id)
);

-- 4. SEAT (Seat Layout Only)
CREATE TABLE seat (
    seat_id     BIGSERIAL PRIMARY KEY,
    space_id    BIGINT NOT NULL,
    row_label   VARCHAR(10) NOT NULL,
    seat_no     VARCHAR(10) NOT NULL,
    seat_type   VARCHAR(50),

    CONSTRAINT fk_seat_space
        FOREIGN KEY (space_id)
        REFERENCES space (space_id),

    CONSTRAINT uk_space_seat
        UNIQUE (space_id, seat_no)
);

-- 5. SCHEDULE (Show / Event Slot)
CREATE TABLE schedule (
    schedule_id    BIGSERIAL PRIMARY KEY,
    content_id     BIGINT NOT NULL,
    space_id       BIGINT NOT NULL,
    schedule_date  DATE NOT NULL,
    from_time      TIME NOT NULL,
    to_time        TIME NOT NULL,
    base_price     DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_schedule_content
        FOREIGN KEY (content_id)
        REFERENCES content (content_id),

    CONSTRAINT fk_schedule_space
        FOREIGN KEY (space_id)
        REFERENCES space (space_id)
);


/* =========================================================
   BOOKING SERVICE TABLES
   ========================================================= */

-- 6. BOOKING
CREATE TABLE booking (
    booking_id    VARCHAR(50) PRIMARY KEY,
    schedule_id   BIGINT NOT NULL,   -- logical reference (no FK)
    user_id       VARCHAR(50) NOT NULL,
    status        VARCHAR(30) NOT NULL,  -- CREATED, CONFIRMED, CANCELLED
    created_at    TIMESTAMP   NOT NULL
);

-- 7. BOOKING_SEAT (Multi-seat mapping)
CREATE TABLE booking_seat (
    booking_id   VARCHAR(50) NOT NULL,
    schedule_id  BIGINT NOT NULL,
    seat_id      BIGINT NOT NULL,

    PRIMARY KEY (booking_id, seat_id)
);

-- CRITICAL CONCURRENCY CONSTRAINT
-- Prevents double booking of same seat for same schedule
CREATE UNIQUE INDEX uk_schedule_seat
ON booking_seat (schedule_id, seat_id);


/* =========================================================
   OPTIONAL PERFORMANCE INDEXES
   ========================================================= */

-- Fast schedule lookup
CREATE INDEX idx_schedule_date
ON schedule (schedule_date);

-- Fast booking lookup
CREATE INDEX idx_booking_user
ON booking (user_id);

-- Fast seat lookup by space
CREATE INDEX idx_seat_space
ON seat (space_id);


/* =========================================================
   END OF SCHEMA
   ========================================================= */


/* =========================================================
   MyBookingApp - Seed Data (PostgreSQL)
   ========================================================= */


/* =========================================================
   VENUE
   ========================================================= */

INSERT INTO venue (venue_name, venue_city, venue_type)
VALUES
('PVR Phoenix Mall', 'Bangalore', 'THEATER'),
('INOX Forum Mall', 'Bangalore', 'THEATER'),
('DY Patil Stadium', 'Mumbai', 'STADIUM');


/* =========================================================
   CONTENT (Movie / Event)
   ========================================================= */

INSERT INTO content (content_type, title, language, duration_minutes)
VALUES
('MOVIE', 'Inception', 'EN', 148),
('MOVIE', 'Interstellar', 'EN', 169),
('CONCERT', 'Arijit Singh Live', 'HI', 180);


/* =========================================================
   SPACE (Screen / Stage)
   ========================================================= */

-- PVR Phoenix Mall (venue_id assumed by order: 1)
INSERT INTO space (venue_id, space_name, capacity)
VALUES
(1, 'Screen 1', 200),
(1, 'Screen 2', 180);

-- INOX Forum Mall (venue_id: 2)
INSERT INTO space (venue_id, space_name, capacity)
VALUES
(2, 'Screen A', 220);

-- DY Patil Stadium (venue_id: 3)
INSERT INTO space (venue_id, space_name, capacity)
VALUES
(3, 'Main Stage', 50000);


/* =========================================================
   SEAT (Layout Only)
   ========================================================= */

-- Screen 1 (space_id: 1)
INSERT INTO seat (space_id, row_label, seat_no, seat_type)
VALUES
(1, 'A', 'A1', 'REGULAR'),
(1, 'A', 'A2', 'REGULAR'),
(1, 'A', 'A3', 'REGULAR'),
(1, 'B', 'B1', 'PREMIUM'),
(1, 'B', 'B2', 'PREMIUM');

-- Screen 2 (space_id: 2)
INSERT INTO seat (space_id, row_label, seat_no, seat_type)
VALUES
(2, 'A', 'A1', 'REGULAR'),
(2, 'A', 'A2', 'REGULAR');

-- Screen A (space_id: 3)
INSERT INTO seat (space_id, row_label, seat_no, seat_type)
VALUES
(3, 'A', 'A1', 'REGULAR'),
(3, 'A', 'A2', 'REGULAR');


/* =========================================================
   SCHEDULE (Show / Event Slot)
   ========================================================= */

-- Inception in PVR Screen 1
INSERT INTO schedule
(content_id, space_id, schedule_date, from_time, to_time, base_price)
VALUES
(1, 1, '2026-02-01', '18:30', '21:00', 250.00);

-- Interstellar in INOX Screen A
INSERT INTO schedule
(content_id, space_id, schedule_date, from_time, to_time, base_price)
VALUES
(2, 3, '2026-02-01', '19:00', '22:00', 300.00);

-- Arijit Singh Concert at DY Patil Stadium
INSERT INTO schedule
(content_id, space_id, schedule_date, from_time, to_time, base_price)
VALUES
(3, 4, '2026-02-10', '19:00', '22:30', 1500.00);


/* =========================================================
   BOOKING
   ========================================================= */

-- INSERT INTO booking
-- (booking_id, schedule_id, user_id, status, created_at)
-- VALUES
-- ('BKG-1001', 1, 'USER-101', 'CONFIRMED', CURRENT_TIMESTAMP),
-- ('BKG-1002', 1, 'USER-102', 'CREATED', CURRENT_TIMESTAMP),
-- ('BKG-2001', 2, 'USER-201', 'CONFIRMED', CURRENT_TIMESTAMP);


-- /* =========================================================
--    BOOKING_SEAT
--    ========================================================= */

-- -- Booking BKG-1001 (2 seats)
-- INSERT INTO booking_seat (booking_id, schedule_id, seat_id)
-- VALUES
-- ('BKG-1001', 1, 1),
-- ('BKG-1001', 1, 2);

-- -- Booking BKG-2001 (1 seat)
-- INSERT INTO booking_seat (booking_id, schedule_id, seat_id)
-- VALUES
-- ('BKG-2001', 2, 6);


-- /* =========================================================
--    END OF SEED DATA
--    ========================================================= */

