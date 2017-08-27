app.config(function ($routeProvider) {
    $routeProvider.when("/",{
        templateUrl: "view/search.html"
    }).when("/complaint/register", {
        templateUrl: "view/registerComplaint.html",
        controller : "registerComplaintCtrl",
    }).when("/searchcomplaint", {
        templateUrl : "view/search_complaint.html",
        controller: "searchComplaintCtrl"
    }).when("/search_health_unit", {
        templateUrl: "view/searchHealthUnit.html",
        controller : "searchHealthUnitCtrl",
    }).when("/searchespeciality", {                     
        templateUrl: "view/searchEspeciality.html",
        controller : "searchEspecialityCtrl",
    }).when("/searchaverage",{
        templateUrl : "view/search_average_per_patient.html",
        controller: "searchAverangeCtrl"
    }).when("/createdcomplaint/:id", {
        templateUrl : "view/successPage.html",
        controller : "messageCreatedComplaintCtrl"
    }).when("/generalSituationComplaints", {
        templateUrl : "view/generalSituationComplaints.html",
        controller : "generalSituationComplaintsCtrl"
    }).when("/login", {
        templateUrl : "view/adminLogin.html",
        controller : "loginAdminCtrl"
    }).when("/adm",{    
        templateUrl : "view/admOptions.html",
        controller : "adminOptionsCtrl"
    }).otherwise({
        redirectTo: '/'
    });

});
