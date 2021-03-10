/* js/map.js */

var map = new ol.Map({

    target: 'map',  // 위 index.html에 div id가 map인 엘리먼트에 맵을 표출
    layers: [
        new ol.layer.Tile({
            source: new ol.source.OSM()
        })
    ],
    view: new ol.View({
        //center: ol.proj.fromLonLat([37.41, 8.82]),  // 맵이 로딩되었을 때 보여질 기본 위치(좌표) 설정
        center: ol.proj.fromLonLat([129, 35.5]),
        zoom: 6  // 줌 레벨은 말 그대로 확대 레벨 (숫자가 커질수록 확대 됨)
    })
    

});

var bikeFile = new ol.layer.Image({
    source:new ol.source.ImageWMS({
      url:'http://localhost:8080/geoserver/TestBike/wms',
      params:{'LAYERS':'TestBike:bike_link, TestBike:bike_rental'},
      serverType:'geoserver'
    })
  });
  // [투명도 조절]
//        bikeLine.setOpacity(0.3)

map.addLayer(bikeFile);
