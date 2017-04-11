mapboxgl.accessToken = 'pk.eyJ1Ijoib2tpZWJ1YmJhIiwiYSI6ImNpdHZscGs3ajAwNXYyb284bW4ydWUzbGsifQ.1PoNrSP0F65WolWgqKhV4g';
var map;
var kounter = 0;
var markers = [];
var lonlat = [-97.50732685771766, 35.47461778676444];
var dragAndDropped = false;

$(document).ready(function() {

    map = new mapboxgl.Map({
        container: 'map', // container id
        style: 'mapbox://styles/mapbox/streets-v9', //stylesheet location
        center: lonlat, // starting position
        zoom: 10.14 // starting zoom
    });
    map.on('rotate', function(e) {
        dragAndDropped = true;
    });
    map.on('drag', function(e) {
        dragAndDropped = true;
    });
    // map.on('mouseup', function(e) {
    //     closePopup();
    //     if (dragAndDropped) {
    //         dragAndDropped = false;
    //         // console.log('bailing');
    //         return;
    //     }
    //
    //     console.log('mouseup',e.originalEvent.which);
    //
    //     if (e.originalEvent.which == 1) { // left click
    //         console.log('calling createMarker', e.lngLat.lng, e.lngLat.lat);
    //         createMarker(e.lngLat.lng, e.lngLat.lat);
    //     } else { // not left click
    //         makePopupPicker(e);
    //     }
    //     // console.log(map.getZoom());
    // });

    map.on('mouseup', function(e) {
        closePopup();
        if (dragAndDropped) {
            dragAndDropped = false;
            console.log('bailing');
            return;
        }

        console.log('clickkkk', e.originalEvent.which);
        if (e.originalEvent.which != 1) { // not left click
            // createMarker(e.lngLat.lng, e.lngLat.lat);
        } else { // not left click
            console.log('calling makePopupPicker', e.lngLat.lng, e.lngLat.lat);
            makePopupPicker(e);
        }
        // console.log(map.getZoom());
    });
    
    map.addControl(new mapboxgl.NavigationControl());
    map.addControl(new mapboxgl.ScaleControl({
        position: 'bottom-right',
        maxWidth: 80,
        unit: 'imperial'
    }));
    
    var navigationHtml =
        '<button class="mapboxgl-ctrl-icon mapboxgl-ctrl-geolocate" type="button" onclick="flytolocation()" accesskey="h"' +
        ' title="Reset map back to original view. Hot key: <alt> h"><span class="arrow";"></span></button>';
    // adds a navigation button that resets the view back to where it started
    $('.mapboxgl-ctrl-group').append(navigationHtml);
    
    map.on('load', function() {
        doWebSocket();
    });
});

function createMarker(lng, lat, sendWS) {
    if(!lng || !lat) return;
    console.log('createMarker', lat, lng);
    let marker = getGeoJsonForMarker(lng, lat);
    let randomImg = 'images/a' + Math.floor((Math.random() * 8) + 1) + '.gif';
    // create a DOM element for the marker
    var el = document.createElement('div');
    el.className = 'marker';
    el.id = 'markerId_' + kounter;
    el.style.backgroundImage = 'url(' + randomImg + ')';
    el.style.width = '50px';
    el.style.height = '50px';
    // el.addEventListener('click', function() {
    //     // window.alert(marker.properties.message);
    //     console.log('click', this);
    // });

    // var popup = new mapboxgl.Popup({
    //         offset: 25
    //     })
    //     .setText('-' + kounter);

    // add marker to map
    let mkr = markers.push(new mapboxgl.Marker(el, {
            offset: [-25, -25]
        })
        .setLngLat([lng, lat])
        // .setPopup(popup)
        .addTo(map));
    $('#markerId_' + kounter).append(
        '<div class="markerLabel" id="markerLabel_' + kounter + '">' + kounter + '</div>');

    $('#markerId_' + kounter).mouseup(function(a, b, c) {
        console.log('aaa', a);
        console.log('bbb', b);
        console.log('ccc', c);
        console.log('mouseup on markderId_' + kounter + '    ' + a.originalEvent.which);
        
    });
    
    // $('#markerLabel_' + kounter).mouseup(function(a, b, c) {
    //     console.log('mouseup on markerLabel_' + kounter);
    // });
    closePopup();
    if(sendWS){
        sendNewMarkerToServer(lng, lat, kounter);
    }
    kounter++;
}

function getGeoJsonForMarker(lng, lat) {
    console.log('lng', lng, 'lat', lat);
    var geojson = {
        "type": "FeatureCollection",
        "features": [{
            "type": "Feature",
            // "properties": {
            //     "title": "Small astronaut",
            //     "message": "Foo"
            // },
            "geometry": {
                "type": "Point",
                "coordinates": [lng, lat]
            }
        }]
    };
    return geojson;
}

function closePopup() {
    $('#popupmain').remove();
}

function makePopupPicker(e) {
    closePopup();
    console.log(e.point.x, e.point.y, e);
    let lng = e.lngLat.lng;
    let lat = e.lngLat.lat;
    let x = e.point.x;
    if (x > 150) x = x - 150;
    let theHtml = '';
    theHtml += "<div id='popupmain' class='popupmain' ";
    theHtml += " style='left: " + x + "px; top: " + e.point.y + "px;'>";
    // theHtml += '<div class="sometext">some text goes here</div>';
    theHtml += "<button class='buttonx' onclick='createMarker(" + lng + "," + lat + ", true)'>Add Marker</button>"
    theHtml += "<button class='buttonx' onclick='closePopup()'>Close</button>"
    theHtml += "<br></div>";
    $('#popup').append(theHtml);
}


function flytolocation() {

    map.flyTo({
        center: lonlat,
        pitch: 0,
        bearing: 0,
        zoom: 10.14
    });
}

function sendNewMarkerToServer(lng, lat, kounter) {

    let newMarker = {
        id: kounter,
        lng: lng,
        lat: lat
    };

    console.log('newMarker', newMarker);
    websocket2.send(JSON.stringify(newMarker));

}

var websocket2;

function doWebSocket(){
    websocket2 = new WebSocket("ws://" + document.location.host + document.location.pathname + "newmarkerendpoint");

    websocket2.onerror = function(evt) {
        console.log('websocket2 onError', evt.data);
    };

    websocket2.onmessage = function(evt) {
        console.log('websocket2 onMessage', evt.data);
        var json = JSON.parse(evt.data);
        console.log('json',json);

        if(json.lng && json.lat){
            console.log('lnglattttt', json.lng, json.lat);

            createMarker(json.lng, json.lat, false);
        }else{
            for(var i = 0; i < json.length; i++){
                createMarker(json[i].lng, json[i].lat, false);
            }
        }

    };
}