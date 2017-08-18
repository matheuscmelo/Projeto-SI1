app.service("RESTService", function ($http) {

	var API_PATH = 'http://localhost:5000/SpringBootRestApi/api/';

	function registerComplaint(complaint) {
		return $http.post(API_PATH + "queixa/", JSON.stringify(complaint));
	}

	function searchAveragePerPatient(id) {
		return $http.get(API_PATH + "geral/medicos/" + id);
	}

	function searchComplaint(id) {
		return $http.get("http://localhost:5000/SpringBootRestApi/api/queixa/" + id);
	}

	function searchHU(neighborhood) {
		return $http.get("http://localhost:5000/SpringBootRestApi/api/unidade/busca?bairro=" + neighborhood);
	}

	function getGeneralSituationComplaints() {
		return $http.get("http://localhost:5000/SpringBootRestApi/api/geral/situacao");
	}

	var service = {
		registerComplaint: registerComplaint,
		searchAveragePerPatient: searchAveragePerPatient,
		searchComplaint: searchComplaint,
		searchHU: searchHU,
		getGeneralSituationComplaints: getGeneralSituationComplaints,
	}

	return service;

});