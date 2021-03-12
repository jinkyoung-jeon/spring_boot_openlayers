


var wmsSource = new ol.source.TileWMS({
  url: 'http://localhost:8080/geoserver/TestBike/wms',
  params: {'LAYERS': 'TestBike:bike_link, TestBike:bike_rental', 'TILED' : true},
  serverType: 'geoserver',
  //crossOrigin: 'anonymous'

});

var wmsLayer = new ol.layer.Tile({
  source: wmsSource,
});

var view = new ol.View({
  center: [0, 0],
  //center: ol.proj.fromLonLat([37.41, 8.82]),
  zoom: 1,
});

var map = new ol.Map({
  layers: [
    new ol.layer.Tile({
      source: new ol.source.OSM()
  }),
    wmsLayer,

  ],
  target: 'map',
  view: view,
});

map.on('singleclick', function (evt) {
  document.getElementById('info').innerHTML = '';
  var viewResolution = view.getResolution();
  var url = wmsSource.getFeatureInfoUrl(
    evt.coordinate,
    viewResolution,
    'EPSG:3857',
    {'INFO_FORMAT': 'text/html'}
  );
  if (url) {
    fetch(url)
      .then(function(response) { return response.text(); })
      .then(function(html) {
        document.getElementById('info').innerHTML = html;
      })
      .catch((error) => console.log("error:", error));
  }
});

map.on('pointermove', function (evt) {
  if (evt.dragging) {
    return;
  }
  var pixel = map.getEventPixel(evt.originalEvent);
  var hit = map.forEachLayerAtPixel(pixel, function () {
    return true;
  });
  map.getTargetElement().style.cursor = hit ? 'pointer' : '';
});
