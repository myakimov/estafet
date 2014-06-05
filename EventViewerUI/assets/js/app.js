 // Events Manager Marionette Application
 var EventsManager = new Marionette.Application();

 // Main Reagion - placeholder
 EventsManager.addRegions({
    mainRegion: "#main-region"
 });

  // Event on starting the application
  EventsManager.on("initialize:after", function(){
     EventsManager.EventsApp.List.Controller.listEvents();
  });