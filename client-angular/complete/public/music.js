//
// music.js
//
// Get data from REST Service for Angular
//

// index.html states:
//   <html ng-app="feriTest">       // the module
//   <body>
//     <div ng-controller="Hello">
//

var app = angular.module('MusicModule', []);
app.controller('MusicCtrl', function($scope, $http) {

    $http.get('http://localhost:8080/music/composer')
        .then(function(response) {
            console.log("## music.js, get() has arrived");
            console.log(response);
            $scope.composer = response.data;
        }, function(myError) {
            console.log("## error: " + myError.statusText);
        });
});
