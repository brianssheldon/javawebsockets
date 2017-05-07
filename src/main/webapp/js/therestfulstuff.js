
function sendNewMarkerToServerUsingRest(jsonobj) {
    console.log('sendNewMarkerToServerUsingRest', jsonobj);
    
    if(jsonobj){
        console.log('sendNewMarkerToServerUsingRest', jsonobj);
    }else{
        jsonobj = {id: 0, lng: -97.41222141127662, lat: 35.54616857358488};
        console.log('sendNewMarkerToServerUsingRest 2', jsonobj);
    }
        
    $.ajax({
        type: "POST",
        url: "/javawebsockets/resources/greeting",
        data: jsonobj,
        dataType: "json",
        contentType: "application/json",
        success: function (a, b, c) {
            console.log('successss', a, b, c, 'eennnddd');
        }
    });
}