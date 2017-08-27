app.service("RESTService", function ($http) {

	var API_PATH = 'http://localhost:5000/SpringBootRestApi/api/';

	function registerFoodComplaint(complaint) {
		return $http.post(API_PATH + "queixa/alimentar/", JSON.stringify(complaint));
	}

	function registerAnimalComplaint(complaint) {
		return $http.post(API_PATH + "queixa/animal/", JSON.stringify(complaint));
	}

	function registerGeneralComplaint(complaint) {
		return $http.post(API_PATH + "queixa/geral/", JSON.stringify(complaint));
	}

	function searchAveragePerPatient(id) {
		return $http.get(API_PATH + "geral/medicos/" + id);
	}

	function searchComplaint(id) {
		return $http.get(API_PATH + "queixa/" + id);
	}

	function searchHU(neighborhood) {
		return $http.get(API_PATH + "unidade/busca?bairro=" + neighborhood);
	}

	function searchEspeciality(especiality) {     
		return $http.get(API_PATH + "unidade/" + especiality);
	}

	function getGeneralSituationComplaints() {
		return $http.get(API_PATH + "geral/situacao");
	}

	function logAdmin(adm) {
		return $http.post(API_PATH + "prefeitura/login", adm);
	}

	function changeToNormal() {
		return $http.put(API_PATH + "/prefeitura/situacao/normal");
	}

	function changeToCaos() {
		return $http.put(API_PATH + "/prefeitura/situacao/caos");
	}

	function changeToExtra() {
		return $http.put(API_PATH + "/prefeitura/situacao/extra");
	}

	function changeToExtra() {
		return $http.put(API_PATH + "/prefeitura/situacao/extra");
	}

	function registerUS(us) {
		return $http.post(API_PATH + "/unidade/", us);
	}

	function changeComplaintToEmAndamento(id) {
		return $http.put(API_PATH + "queixa/emAndamento/" + id);
	}

	function closeComplaint(queixa) {
		return $http.post(API_PATH + "queixa/fechamento/" + queixa.id, queixa.comentario);
	}

	var service = {
		closeComplaint: closeComplaint,
		changeComplaintToEmAndamento: changeComplaintToEmAndamento,
		registerUS: registerUS,
		changeToExtra: changeToExtra,
		changeToNormal: changeToNormal,
		changeToCaos: changeToCaos,
		registerFoodComplaint: registerFoodComplaint,
		registerGeneralComplaint: registerGeneralComplaint,
		registerAnimalComplaint: registerAnimalComplaint,
		searchAveragePerPatient: searchAveragePerPatient,
		searchComplaint: searchComplaint,
		searchHU: searchHU,
		searchEspeciality: searchEspeciality,
		getGeneralSituationComplaints: getGeneralSituationComplaints,
		logAdmin: logAdmin,
	}

	return service;

});