# javawebsockets
Playing with javax.websockets - java server emits, browser client receives

<strong>Stick a fork in the websocket, it's done</strong>

Stick a fork in the websocket, it's done.

tl;dr; javax.websocket, mapbox-gl.js, and sqlite-jdbc

I've wanted to make an app in the cloud (aka someone else's server) that uses several technologies.
This is an indolent walk through of what I've come up with so far.

The list of technology I plan on using is, javax.websocket, json, javascript, mapbox-gl.js (vector tile map), sqlite-jdbc, and Rest.
If there is time, deploy it to gcloud (App Engine). I may have to use mapbox.com or node.js to serve the map tiles instead of sqlite-jdbc if I don't have time.

My plan is to display a map of the world, click on the map to create a marker which then gets broadcast to anyone else on the web page.
Clicking on a marker deletes it from everyones map.
---

Push data to clients - alert 
Java websockets
Javascript
Jquery
json
Mapbox-gl.js / Openstreetmap / Osm2vectortiles
Node.js
Sqlite
Rest?
------------------------


Download map https://openmaptiles.org/downloads/

http://osm2vectortiles.org/downloads/ is no longer maintained…



Mapbox -
https://www.mapbox.com/mapbox-gl-js/examples/


http://fuzzytolerance.info/blog/2016/02/09/Vector-Tiles-Part-1/


https://console.cloud.google.com/appengine?project=firstnodejs-165813&serviceId=default&duration=PT1H


Sqlite tutorial  http://www.sqlitetutorial.net/sqlite-java/select/




---
Buttons -
     Trigger websocket msgs
      Store lnglat/bearing/pitch
      
      random flow rate with updates...
       
