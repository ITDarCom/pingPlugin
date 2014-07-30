var ping = {
    executePing: function(ipList, callback) {
        cordova.exec(
        	callback, // success callback function
        	callback, // error callback function
            'Ping', // mapped to our native Java class called "CalendarPlugin"
            'ping', // with this action name
            ipList
        ); 
     }
}
