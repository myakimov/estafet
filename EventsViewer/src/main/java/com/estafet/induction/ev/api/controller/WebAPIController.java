package com.estafet.induction.ev.api.controller;

import com.estafet.induction.ev.config.DataSourceConfiguration;
import com.estafet.induction.ev.model.Event;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Restful controller accepting calls on :
 *  /ui/events 
 *  /ui/handle/{eventID}
 * to be used by the WebUI to list and discard handled events
 * 
 * @version 1.0
 * @author Marin Yakimov
 */
@RequestMapping(value = "/ui")
@Controller
public class WebAPIController {

    @Autowired
    DataSourceConfiguration dataSource;

    /**
     * The method reads from the database all available events. The show filter
     * can limit the events returned. Possible values :
     *  - handled (default)
     *  - unhandled
     * 
     * @param show limits the events returned
     * @return list of events
     */
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public @ResponseBody
    List<Event> getEvents(@RequestParam(value="show", required=false) String show) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource.dataSource());

        String query = 
                "SELECT                                                      " +
                "    e.id, e.hub_id, e.event_date,                           " +
                "    e.type, e.is_handled, et.description                    " +
                "FROM estafet.events e                                       " +
                "JOIN estafet.event_type et ON e.type = et.id                ";
        
        // Since being optional show parameter can be null
        if (show != null) {
            switch (show) {
                case "handled":
                    query += "WHERE e.is_handled = true ";
                    break;
                case "unhandled":
                    query += "WHERE e.is_handled = false ";
                    break;
            }
        }
        
        query += "ORDER BY ID";
        
        // Spring JDBC Template used to map each row to an Event instance
        List<Event> events = jdbcTemplate.query(query, new Object[]{},
                new RowMapper<Event>() {
                    @Override
                    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Event event = new Event();
                        event.setID(rs.getInt("ID"));
                        event.setHubID(rs.getInt("HUB_ID"));
                        event.setDescription(rs.getString("DESCRIPTION"));
                        event.setType(rs.getInt("TYPE"));
                        event.setEventDate(rs.getTimestamp("EVENT_DATE").toString());
                        event.setHandled(rs.getBoolean("IS_HANDLED"));

                        return event;
                    }
                });

        return events;
    }
    
    /**
     * Marks an event as handled. Handled events will be marked again, and in 
     * practice the function will not have any effect on them
     * 
     * @param eventID the event to be marked
     */
    @RequestMapping(value = "/events/{eventID}", method = RequestMethod.DELETE)
    public @ResponseBody
    void handleEvent(@PathVariable int eventID) {
        System.out.println("Removing event : " + eventID);
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource.dataSource());

       jdbcTemplate.update(
                    " UPDATE estafet.events SET is_handled = true   " +
                    " WHERE id = ?                                  ",
                    eventID);
    }
}
