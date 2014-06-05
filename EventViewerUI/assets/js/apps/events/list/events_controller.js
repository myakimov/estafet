EventsManager.module("EventsApp.List", function(List, EventsManager, Backbone, Marionette, $, _){
    List.Controller = {
        listEvents: function(){
            var events = EventsManager.request("event:entities");

            var eventsView = new EventsManager.EventsApp.List.Events({
                collection : events
            });

            var eventsListLayout = new List.Layout();
            var eventsListPanel = new List.Panel();

            eventsListLayout.on("show", function(){
                eventsListLayout.panelRegion.show(eventsListPanel);
                eventsListLayout.eventsRegion.show(eventsView);
            });

            EventsManager.mainRegion.show(eventsListLayout);

            eventsView.on("itemview:event:discard", function(childView, model){
                model.destroy();
            });

            eventsListPanel.on("event:reload", function(childView, model){
                events.fetch({ data: $.param({ show: EventsManager.Entities.Show}) });
            });

       }
    }
});