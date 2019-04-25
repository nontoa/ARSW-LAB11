function getName(cine) {
	return cine[0].name;
}

function getFunctions(cine) {
	return cine[0].functions;
}

function createObjectname_asientos_fecha(funciones) {
	return funciones.map(function(funcion) {
		return { name: funcion.movie.name, seats: funcion.seats[0].length * funcion.seats.length, date: funcion.date };
	});
}

function generarTable(name, funcion) {
	$('#tableFunciones').find('tbody').empty();
	funcion.map(function(f) {
		$('#tableFunciones')
			.find('tbody')
			.append(
				'<tr> <th>' +
					f.name +
					'</th>' +
					'<th>' +
					name +
					'</th>' +
					'<th>' +
					f.seats +
					'</th>' +
					'<th>' +
					f.date +
					'</th></tr>'
			);
	});
}

function generarTable2(name, funcion) {
	$('#tableFunciones2')
			.append('<thead class="thead-dark">'+
			'<tr>'+
				'<th scope="col">Funcion</th>'+
				'<th scope="col">Sillas</th>'+
				'<th scope="col">Fecha</th>'+
				'<th scope="col">Reservar</th>'+
			'</tr>'+
		'</thead>');
	$('#tableFunciones2').find('tbody').empty();
	var cnt=1;
	funcion.map(function(f) {
		$('#tableFunciones2')
			.find('tbody')
			.append(
				'<tr> <th>' +
					f.name +
					'</th>' +
					'<th>' +
					f.seats +
					'</th>' +
					'<th>' +
					f.date +
					'</th><th><div class="form-check">' +
					'<input type="checkbox"  name="'+cnt+ '"class="form-check-input" id="'+f.name+'"/>' +
					'</div>' +
					'</th></tr>'
			);
			cnt++;

	});
	$('#tableFunciones2')
			.append('<button type="submit" class="btn btn-primary" onclick="run3();return false">Reservar</button>');


}
function run() {
	var nameCinema = $('#cuadroTexto').val();

	generarTable(
		apimock.getCinemaByName(nameCinema, getName),
		createObjectname_asientos_fecha(apimock.getCinemaByName(nameCinema, getFunctions))
	);
}

function run2() {
	var nameCinema = $('#cuadroTextoCine').val();

	generarTable2(
		apimock.getCinemaByName(nameCinema, getName),
		createObjectname_asientos_fecha(apimock.getCinemaByName(nameCinema, getFunctions))
	);
}
function run3(){
	var nameCinema = $('#cuadroTextoCine').val();
	var funciones = createObjectname_asientos_fecha(apimock.getCinemaByName(nameCinema, getFunctions));
	funciones.map(function(f) {
		console.log($("#"+f.name).prop("checked"));
	});

}
