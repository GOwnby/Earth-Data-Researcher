var dataCO2Past;
var dataOne = $.getJSON("pastCO2Data.json", function (data){
    dataCO2Past = dataOne.responseJSON;
});

var dataCO2Current;
var dataTwo = $.getJSON("currentCO2Data.json", function (data){
    dataCO2Current = dataTwo.responseJSON;
});

var dataCO2Future;
var dataThree = $.getJSON("futureCO2Data.json", function (data){
    dataCO2Future = dataThree.responseJSON;
});

var dataGlobalTempPast;
var dataFour = $.getJSON("pastGlobalTempData.json", function (data){
    dataGlobalTempPast = dataFour.responseJSON;
});

var dataGlobalTempCurrent;
var dataFive = $.getJSON("currentGlobalTempData.json", function (data) {
    dataGlobalTempCurrent = dataFive.responseJSON;
});

var dataGlobalTempFuture;
var dataSix = $.getJSON("futureGlobalTempData.json", function (data) {
    dataGlobalTempFuture = dataSix.responseJSON;
});

var dataArcticIceExtentPast;
var dataSeven = $.getJSON("pastArcticIceExtentData.json", function (data) {
    dataArcticIceExtentPast = dataSeven.responseJSON;
});

var dataArcticIceExtentCurrent;
var dataEight = $.getJSON("currentArcticIceExtentData.json", function (data) {
    dataArcticIceExtentCurrent = dataEight.responseJSON;
});

var dataArcticIceExtentFuture;
var dataNine = $.getJSON("futureArcticIceExtentData.json", function (data) {
    dataArcticIceExtentFuture = dataNine.responseJSON;
});

var dataArcticIceAreaPast;
var dataTen = $.getJSON("pastArcticIceAreaData.json", function (data) {
    dataArcticIceAreaPast = dataTen.responseJSON;
});

var dataArcticIceAreaCurrent;
var dataEleven = $.getJSON("currentArcticIceAreaData.json", function (data) {
    dataArcticIceAreaCurrent = dataEleven.responseJSON;
});

var dataArcticIceAreaFuture;
var dataTwelve = $.getJSON("futureArcticIceAreaData.json", function (data) {
    dataArcticIceAreaFuture = dataTwelve.responseJSON;
});