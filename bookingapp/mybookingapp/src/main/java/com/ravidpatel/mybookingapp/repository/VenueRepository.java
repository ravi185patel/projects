package com.ravidpatel.mybookingapp.repository;

import com.ravidpatel.mybookingapp.constant.*;
import com.ravidpatel.mybookingapp.entity.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Repository
public class VenueRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public VenueRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Long insertVenue(Venue venue) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(
                VenueSql.INSERT,
                new BeanPropertySqlParameterSource(venue),
                kh,
                new String[]{"venue_id"}
        );
        return kh.getKey().longValue();
    }

    public int updateVenue(Venue venue) {
        return jdbc.update(
                VenueSql.UPDATE,
                new BeanPropertySqlParameterSource(venue)
        );
    }

    public Venue getVenue(Long venueId) {
        return jdbc.queryForObject(
                VenueSql.FIND_BY_ID,
                Map.of("venueId", venueId),
                VENUE_ROW_MAPPER
        );
    }

    /* ===================== CONTENT ===================== */

    public Long insertContent(Content content) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(
                ContentSql.INSERT,
                new BeanPropertySqlParameterSource(content),
                kh,
                new String[]{"content_id"}
        );
        return kh.getKey().longValue();
    }

    public int updateContent(Content content) {
        return jdbc.update(
                ContentSql.UPDATE,
                new BeanPropertySqlParameterSource(content)
        );
    }

    public Content getContent(Long contentId) {
        return jdbc.queryForObject(
                ContentSql.FIND_BY_ID,
                Map.of("contentId", contentId),
                CONTENT_ROW_MAPPER
        );
    }

    /* ===================== SPACE ===================== */

    public Long insertSpace(Space space) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(
                SpaceSql.INSERT,
                new BeanPropertySqlParameterSource(space),
                kh,
                new String[]{"space_id"}
        );
        return kh.getKey().longValue();
    }

    public int updateSpace(Space space) {
        return jdbc.update(
                SpaceSql.UPDATE,
                new BeanPropertySqlParameterSource(space)
        );
    }

    public Space getSpace(Long spaceId) {
        return jdbc.queryForObject(
                SpaceSql.FIND_BY_ID,
                Map.of("spaceId", spaceId),
                SPACE_ROW_MAPPER
        );
    }

    /* ===================== SEAT (BULK) ===================== */

    public void insertSeats(List<Seat> seats) {
        SqlParameterSource[] batch = seats.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);

        jdbc.batchUpdate(SeatSql.INSERT, batch);
    }

    public List<Seat> getSeatsBySpace(Long spaceId) {
        return jdbc.query(
                SeatSql.FIND_BY_SPACE,
                Map.of("spaceId", spaceId),
                SEAT_ROW_MAPPER
        );
    }

    /* ===================== SCHEDULE ===================== */

    public Long insertSchedule(Schedule schedule) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(
                ScheduleSql.INSERT,
                new BeanPropertySqlParameterSource(schedule),
                kh,
                new String[]{"schedule_id"}
        );
        return kh.getKey().longValue();
    }

    public int updateSchedulePrice(Long scheduleId, BigDecimal price) {
        return jdbc.update(
                ScheduleSql.UPDATE_PRICE,
                Map.of("scheduleId", scheduleId, "price", price)
        );
    }

    public Schedule getSchedule(Long scheduleId) {
        return jdbc.queryForObject(
                ScheduleSql.FIND_BY_ID,
                Map.of("scheduleId", scheduleId),
                SCHEDULE_ROW_MAPPER
        );
    }

    /* ===================== ROW MAPPERS ===================== */

    private static final RowMapper<Venue> VENUE_ROW_MAPPER = (rs, i) -> {
        Venue v = new Venue();
        v.setVenueId(rs.getLong("venue_id"));
        v.setVenueName(rs.getString("venue_name"));
        v.setVenueCity(rs.getString("city"));
        v.setVenueType(VenueType.valueOf(rs.getString("venue_type")));
        return v;
    };

    private static final RowMapper<Content> CONTENT_ROW_MAPPER = (rs, i) -> {
        Content c = new Content();
        c.setContentId(rs.getLong("content_id"));
        c.setContentType(ContentType.valueOf(rs.getString("content_type")));
        c.setContentTitle(rs.getString("title"));
        c.setDurationMinutes(rs.getInt("duration_minutes"));
        return c;
    };

    private static final RowMapper<Space> SPACE_ROW_MAPPER = (rs, i) -> {
        Space s = new Space();
        s.setSpaceId(rs.getLong("space_id"));
        s.setVenueId(rs.getLong("venue_id"));
        s.setSpaceName(rs.getString("space_name"));
        s.setCapacity(rs.getInt("capacity"));
        return s;
    };

    private static final RowMapper<Seat> SEAT_ROW_MAPPER = (rs, i) -> {
        Seat s = new Seat();
        s.setSeatId(rs.getLong("seat_id"));
        s.setSpaceId(rs.getLong("space_id"));
        s.setRowLabel(rs.getString("row_label"));
        s.setSeatNo(rs.getString("seat_no"));
        s.setSeatType(rs.getString("seat_type"));
        return s;
    };

    private static final RowMapper<Schedule> SCHEDULE_ROW_MAPPER = (rs, i) -> {
        Schedule s = new Schedule();
        s.setScheduleId(rs.getLong("schedule_id"));
        s.setContentId(rs.getLong("content_id"));
        s.setSpaceId(rs.getLong("space_id"));
        s.setScheduledDate(rs.getDate("schedule_date").toLocalDate());
        s.setFromTime(rs.getTime("from_time").toLocalTime());
        s.setToTime(rs.getTime("to_time").toLocalTime());
        s.setBasePrice(rs.getBigDecimal("base_price"));
        return s;
    };
}
