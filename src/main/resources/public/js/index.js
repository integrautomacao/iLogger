

function teste() {
	/* 	httpGetAsync('http://localhost:8082/public/status', function (data) {
			console.log(data)
			fillTable(data) */


	getJSON('http://query.yahooapis.com/v1/public/yql?q=select%20%2a%20from%20yahoo.finance.quotes%20WHERE%20symbol%3D%27WRC%27&format=json&diagnostics=true&env=store://datatables.org/alltableswithkeys&callback',
		function (err, data) {
			if (err !== null) {
				alert('Something went wrong: ' + err);
			} else {
				alert('Your query count: ' + data.query.count);
			}
		});
}


var getJSON = function (url, callback) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.responseType = 'json';
	xhr.onload = function () {
		var status = xhr.status;
		if (status === 200) {
			callback(null, xhr.response);
		} else {
			callback(status, xhr.response);
		}
	};
	xhr.send();
};

function httpGetAsync(theUrl, callback) {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function () {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
			callback(xmlHttp.responseText);
	}
	xmlHttp.open("GET", theUrl, true); // true for asynchronous 
	xmlHttp.send(null);
}

function fillTable(data) {
	{

		let table = document.getElementById('table');

		data.forEach((e) => {
			let row = document.createElement("tr");
			let tdName = document.createElement('td')
			let tdDesc = document.createElement('td');
			let tdStatus = document.createElement('td');
			let nodeName = document.createTextNode(data.deviceName);
			let nodeDesc = document.createTextNode(data.statusDesc);
			let nodeStatus = document.createTextNode(data.status);
			tdName.appendChild(nodeName);
			row.appendChild(tdName);

			tdDesc.appendChild(nodeDesc);
			row.appendChild(tdDesc)
			table.appendChild(row);
			tdStatus.appendChild(nodeStatus);
			row.appendChild(nodeStatus);
		});
	}
}