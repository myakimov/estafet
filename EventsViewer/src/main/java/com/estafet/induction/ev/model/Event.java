package com.estafet.induction.ev.model;

import java.util.Date;

/**
 * Event model class that represents and event
 * 
 * @version 1.0
 * @author Marin Yakimov
 */
public class Event {
    
    private int ID;
    private String description;
    private int hubID;
    private int type;
    private String eventDate;
    private boolean handled;

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the hubID
     */
    public int getHubID() {
        return hubID;
    }

    /**
     * @param hubID the hubID to set
     */
    public void setHubID(int hubID) {
        this.hubID = hubID;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Event : ").append(ID);
        sb.append("|- HUB ID : ").append(hubID);
        sb.append("|- Description : ").append(description);
        
        return sb.toString();
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the eventDate
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the handled
     */
    public boolean isHandled() {
        return handled;
    }

    /**
     * @param handled the handled to set
     */
    public void setHandled(boolean handled) {
        this.handled = handled;
    }
    
}
