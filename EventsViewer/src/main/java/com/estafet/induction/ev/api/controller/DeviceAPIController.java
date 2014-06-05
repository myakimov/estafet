package com.estafet.induction.ev.api.controller;

import com.estafet.induction.ev.config.DataSourceConfiguration;
import com.estafet.induction.ev.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Restful controller accepting calls on /event/hub to be used by devices 
 * to report new events
 * 
 * @version 1.0
 * @author Marin Yakimov
 */
@RequestMapping(value="/event")
@Controller
public class DeviceAPIController {
    
    @Autowired DataSourceConfiguration dataSource;
    
    @RequestMapping(value = "/hub", method = RequestMethod.POST)
    public @ResponseBody String getHub(@RequestBody Event event) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource.dataSource());

        jdbcTemplate.update(
                    " INSERT INTO                                                  " + 
                    "   ESTAFET.events(ID, HUB_ID, EVENT_DATE, TYPE, IS_HANDLED)   " +
                    " VALUES(?,?, CURRENT_TIMESTAMP, ?, FALSE)                     ",
                    event.getID(), event.getHubID(), event.getType());
        
        return event.toString();
    }
}
