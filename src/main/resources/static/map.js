


var wmsSource = new ol.source.TileWMS({
	url: 'http://localhost:8080/geoserver/TestBike/wms',
	params: { 'LAYERS': ['TestBike:bike_link', 'TestBike:bike_rental'], 'TILED': true },
	serverType: 'geoserver',
	//crossOrigin: 'anonymous'

});

var wmsLayer = new ol.layer.Tile({
	source: wmsSource
});


/* select 하기 위한 지도 좌표값*/
var vector = new ol.layer.Vector({
	source: new ol.source.Vector({
		url: 'countries.geojson',
		format: new ol.format.GeoJSON(),
	}), 
});


var view = new ol.View({
	center: ol.proj.fromLonLat([127, 37]),
	zoom: 6,
});

var map = new ol.Map({
	layers: [
		new ol.layer.Tile({
			source: new ol.source.OSM()
		}),
		vector, wmsLayer,

	],
	target: 'map',
	view: view,
});


/* bike rental 클릭 시 info 표출*/

map.on('singleclick', function(evt) {
	document.getElementById('info').innerHTML = '';
	var viewResolution = view.getResolution();
	var url = wmsSource.getFeatureInfoUrl(
		evt.coordinate,
		viewResolution,
		'EPSG:3857',
		{ 'INFO_FORMAT': 'text/html' }
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

map.on('pointermove', function(evt) {
	if (evt.dragging) {
		return;
	}
	var pixel = map.getEventPixel(evt.originalEvent);
	var hit = map.forEachLayerAtPixel(pixel, function() {
		return true;
	});
	map.getTargetElement().style.cursor = hit ? 'pointer' : '';
});





/* 지도 feature select 하는 부분*/

var select = null; //ref to currently selected interaction

// select interaction working on "singleclick"
var selectSingleClick = new ol.interaction.Select();

// select interaction working on "click"
var selectClick = new ol.interaction.Select({
	condition: ol.events.condition.click,
});

// select interaction working on "pointermove"
var selectPointerMove = new ol.interaction.Select({
	condition: ol.events.condition.pointerMove,
});

var selectAltClick = new ol.interaction.Select({
	condition: function(mapBrowserEvent) {
		return ol.events.condition.click(mapBrowserEvent) && ol.events.condition.altKeyOnly(mapBrowserEvent);
	},
});

var selectElement = document.getElementById('type');

var changeInteraction = function() {

	if (select !== null) {
		map.removeInteraction(select);
	}
	var value = selectElement.value;
	if (value == 'singleclick') {
		select = selectSingleClick;
	} else if (value == 'click') {
		select = selectClick;
	} else if (value == 'pointermove') {
		select = selectPointerMove;
	} else if (value == 'altclick') {
		select = selectAltClick;
	} else {
		select = null;
	}
	if (select !== null) {
		map.addInteraction(select);
		select.on('select', function(e) {
			document.getElementById('status').innerHTML =
				'&nbsp;' +
				e.target.getFeatures().getLength() +
				' selected features (last operation selected ' +
				e.selected.length +
				' and deselected ' +
				e.deselected.length +
				' features)';
		});
	}
};

/**
 * onchange callback on the select element.
 */
selectElement.onchange = changeInteraction;
changeInteraction();


/* ajax를 이용한 주소 호출*/

$.ajax({
	url: "/sample/list", // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소 
	//data: { organizationName: "강원도 강릉시" }, // HTTP 요청과 함께 서버로 보낼 데이터 
	method: "GET", // HTTP 요청 메소드(GET, POST 등) 
	dataType: "json" // 서버에서 보내줄 데이터의 타입
}) // HTTP 요청이 성공하면 요청한 데이터가 done() 메소드로 전달됨. 
	.done(function(json) {
		console.log(json);
		var html = '';
		for (var i in json) {
			html += '<li>' + json[i].ctpKorNm + '</li>';
			$('#ajpage').find('ul').html(html);
		}
	})
	// HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨. 
	.fail(function(xhr, status, errorThrown) {
		$("#text").html("오류가 발생했다.<br>")
			.append("오류명: " + errorThrown + "<br>")
			.append("상태: " + status);
	}) // 
	.always(function(xhr, status) {
		$("#text").html("요청이 완료되었습니다!");
	});


$('#area').on('click', 'li', function() {
	var view = map.getView();
	var zoom = view.getZoom();
	view.setZoom(zoom + 1);
	$(this).text();

	console.log($(this).text());



});


function pageAction() {
	$('#getInfo').show()
}

