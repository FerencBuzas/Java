//
// hello.js
//
// Consume a REST Service with Angular, JS
//

// index.html states:
//   <html ng-app="feriTest">       // the module
//   <body>
//     <div ng-controller="Hello">
//
var app = angular.module('feriTest', []);
app.controller('Hello', function($scope, $http) {

    $http.get('http://localhost:8080/person?id=1')
        .then(function(response) {
            console.log("## hello.js, get() has arrived");
            console.log(response);
            $scope.person = response.data;
        }, function(myError) {
            console.log("## error: " + myError.statusText);
        });
});
