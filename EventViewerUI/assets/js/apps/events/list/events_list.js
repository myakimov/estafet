//Views
//All views definitions are in this source file
EventsManager.module("EventsApp.List", function(List, EventsManager,Backbone, Marionette, $, _){

    // Complex layout with more than one template
    List.Layout = Marionette.Layout.extend({
        template: "#events-list-layout",

        regions: {
            panelRegion: "#panel-region",
            eventsRegion: "#events-region"
        }
     });

    // Simple ItemView for the filter select
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

     // Main item view - one of this is created for each event
     List.Event = Marionette.ItemView.extend({
        tagName : "tr",
        template : "#different-template",

        // Events handlers
        // Handle discard and highlight
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

     // Complex composite view which handled collections
     List.Events = Marionette.CompositeView.extend({
        tagName : "table",
        className: "table table-hover",
        itemView : List.Event,
        template : "#events-list",
        itemViewContainer : "tbody"
     });
});