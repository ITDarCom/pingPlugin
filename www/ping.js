var ping = {
    executePing: function(ipList, successCallback,errorCallback) {
        cordova.exec(
        	successCallback, // success callback function
        	errorCallback, // error callback function
            'Ping', // mapped to our native Java class called "CalendarPlugin"
            'ping', // with this action name
            ipList
        ); 
     }
}

module.exports = ping;