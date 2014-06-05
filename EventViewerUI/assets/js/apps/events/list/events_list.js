EventsManager.module("EventsApp.List", function(List, EventsManager,Backbone, Marionette, $, _){

    List.Layout = Marionette.Layout.extend({
        template: "#events-list-layout",

        regions: {
            panelRegion: "#panel-region",
            eventsRegion: "#events-region"
        }
     });

    List.Panel = Marionette.ItemView.extend({
        template: "#events-list-panel",

        events : {
            "change select" : function() {
                var optionSelected = $('#selectpicker option:selected').val();
                EventsManager.Entities.Show = optionSelected;
                this.trigger("event:reload", this.model);
            }
        }
    });

     // Some view - TheView
     List.Event = Marionette.ItemView.extend({
        tagName : "tr",
        template : "#different-template",

        events: {
            "click button.js-discard" : function(e) {
                e.stopPropagation();
                this.trigger("event:discard", this.model);
             },
            "click td": "highlightName"
        },

        highlightName: function(e){
            this.$el.toggleClass("warning");
        }

     });

     List.Events = Marionette.CompositeView.extend({
        tagName : "table",
        className: "table table-hover",
        itemView : List.Event,
        template : "#events-list",
        itemViewContainer : "tbody"
     });
});