// Entities together with some initialization code
EventsManager.module("Entities", function(Entities, EventsManager,Backbone, Marionette, $, _){

 // Model for the events
 Entities.Event = Backbone.Model.extend({
    urlRoot: "ui/events"
 });

 // Events collection
 Entities.EventsCollection = Backbone.Collection.extend({
    url : "ui/events",
    model : Entities.Event,
    comparator : "id"
 });

  Entities.Show = "handled";

  var events;

  var initialize = function() {

    events = new EventsManager.Entities.EventsCollection();
    events.fetch({ data: $.param({ show: Entities.Show}) });
 }

 var API = {
    getEvents : function() {
        if (events == undefined) {
            initialize();
        }

        return events;
    },
    reload : function() {
        initialize();
        return events;
    }
 }

 EventsManager.reqres.setHandler("event:entities", function() {
    return API.getEvents();
 });

});