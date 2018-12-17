

function getActivePlc() {
	httpGetAsync('http://localhost:8080/iLogger/public/status'
		, function (data) {
			fillTablePlc(data)
		})
}

function getActiveTags() {
	httpGetAsync('http://localhost:8080/iLogger/public/tagValue',
		function (data) {
			fillTableTag(data)
		})
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

function fillTableTag(param) {
	let table = document.getElementById('tableTag');
	table.innerHTML = ""; 
	let paramJson = JSON.parse(param);
	console.log(paramJson);

	let row = document.createElement("tr")
	let thDevice = document.createElement("th")
	let thTagName = document.createElement("th")
	let thValue = document.createElement("th")
	let thType = document.createElement("th")

	let nodethDevice = document.createTextNode("Controlador")
	let nodethTagName = document.createTextNode("Nome do Tag")
	let nodethValue = document.createTextNode("Valor")
	let nodethType = document.createTextNode("Tipo")



	thDevice.appendChild(nodethDevice);
	row.appendChild(thDevice);

	thTagName.appendChild(nodethTagName);
	row.appendChild(thTagName);

	thType.appendChild(nodethType);
	row.appendChild(thType);

	thValue.appendChild(nodethValue);
	row.appendChild(thValue);

	table.appendChild(row);

	paramJson.forEach(element => {
		let row2 = document.createElement("tr")
		let thDevice = document.createElement("th")
		let thTagName = document.createElement("th")
		let thValue = document.createElement("th")
		let thType = document.createElement("th")

		let nodethDevice = document.createTextNode(element.plc)
		let nodethTagName = document.createTextNode(element.tagName)
		let nodethValue = document.createTextNode(element.value)
		let nodethType = document.createTextNode(element.type)

		thDevice.appendChild(nodethDevice);
		row2.appendChild(thDevice);

		thTagName.appendChild(nodethTagName);
		row2.appendChild(thTagName);

		thType.appendChild(nodethType);
		row2.appendChild(thType);

		thValue.appendChild(nodethValue);
		row2.appendChild(thValue);

		table.appendChild(row2);
	})

}


function fillTablePlc(param) {
	{
		let table = document.getElementById('tablePlc');
		table.innerHTML = "";
		let paramJson = JSON.parse(param);
		console.log(paramJson);
		let row2 = document.createElement("tr")
		let thDevice = document.createElement("th")
		let thDescStatus = document.createElement("th")
		let thStatus = document.createElement("th")

		let nodethDevice = document.createTextNode("Dispositivo")
		let nodethDescStatus = document.createTextNode("Descrição")
		let nodethStatus = document.createTextNode("Status da Conexão")

		thDevice.appendChild(nodethDevice);
		row2.appendChild(thDevice);

		thDescStatus.appendChild(nodethDescStatus);
		row2.appendChild(thDescStatus);

		thStatus.appendChild(nodethStatus);
		row2.appendChild(thStatus);

		table.appendChild(row2);

		paramJson.forEach(element => {
			let row = document.createElement("tr");
			let tdName = document.createElement("td");
			let tdDesc = document.createElement("td");
			let tdStatus = document.createElement("td");
			let nodeName = document.createTextNode(element.deviceName);
			let nodeDesc = document.createTextNode(element.statusDesc);

			let nodeStatus;
			if (element.status == true) {

				nodeStatus = document.createTextNode('Ok');
				tdStatus.classList.add("okRow")
			} else {
				nodeStatus = document.createTextNode('Erro')
				tdStatus.classList.add("errorRow")
			}


			tdName.appendChild(nodeName);
			row.appendChild(tdName)

			tdDesc.appendChild(nodeDesc);
			row.appendChild(tdDesc);

			tdStatus.appendChild(nodeStatus);
			row.appendChild(tdStatus);
			table.appendChild(row);
		});
	}
}


getActivePlc();
getActiveTags();