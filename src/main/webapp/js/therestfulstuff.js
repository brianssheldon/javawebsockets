
function sendNewMarkerToServerUsingRest(jsonobj) {
    console.log('sendNewMarkerToServerUsingRest');

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