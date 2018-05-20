var app = angular.module('nzOS', ['ngRoute']);

function readCookie(name) {
    var nameEQ = encodeURIComponent(name) + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ')
            c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0)
            return decodeURIComponent(c.substring(nameEQ.length, c.length));
    }
}

var language = readCookie("language");
var EN = {
    "nzos": "nzOS",
    "numberOfVersion": "1.0.0",
    "version": "Version",
    "release_notes": "Release notes",
    "dashboard": "Dashboard",
    "lighting": "Lighting",
    "settings": "Settings",
    "about_nzos": "About nzOS",
    "credits": "Credits",
    "about_1": "This product uses copyrighted code used under Apache License 2.0, Creative Commons Attribution 4.0, Eclipse Public License 2.0, MIT, and SIL Open Font License 1.1.",
    "about_2": "Program components under Apache License 2.0:",
    "about_3": "MIT-licensed program elements:",
    "about_4": "Program components under other licenses:",
    "about_5": "All licences are included in",
    "about_5a": "this place",
    "about_6": "The product is licensed under the GNU Lesser General Public License v3.0, available at ",
    "about_6a": "or in this statement.",
    "about_7": "Set of licences",
    "close": "Close",
    "settings_1": "Fan settings",
    "settings_2": "Pump settings",
    "settings_3": "In the input below one should enter the expected value, e.g. if you wants the fan to operate at 50% speed at 30°C, then in field  „Temp. 30°C” you should enter the value 50.",
    "warning": "Warning!",
    "settings_4": "Dependency graph",
    "settings_5": "Other settings",
    "settings_6": "Language:",
    "settings_6a": "English",
    "settings_6b": "Polski",
    "settings_7": "Notifications",
    "enable": "enabled",
    "on": "On",
    "settings_8": "Temperature warning in °C",
    "settings_0": "Value in percentage",
    "save": "Save settings",
    "lighting_1": "Lighting settings",
    "lighting_2": "Group colors #",
    "lighting_3": "Red",
    "lighting_4": "Green",
    "lighting_5": "Blue",
    "lighting_6": "Color mode",
    "lighting_7": "Solid",
    "lighting_8": "Breathing",
    "lighting_9": "Pulsation",
    "lighting_10": "Randomisation of colours",
    "lighting_11": "Alternative solid",
    "lighting_12": "Fixed one color (Date from color #2)",
    "lighting_13": "Wave - Slowest level",
    "lighting_14": "Wave - Slow level",
    "lighting_15": "Wave - Normal level",
    "lighting_16": "Wave - Fast level",
    "lighting_17": "Wave - Fastest level",
    "lighting_18": "Breathing - Slowest level",
    "lighting_19": "Breathing - Slow level",
    "lighting_20": "Breathing - Normal level",
    "lighting_21": "Breathing - Fast level",
    "lighting_22": "Breathing - Fastest level",
    "lighting_23": "Solid one colour alternative version",
    "home_1":"Temperatures",
    "home_2":"Load",
    "home_3":"Liquid temperature",
    "home_4":"Fan speed",
};

var PL = {
    "nzos": "nzOS",
    "numberOfVersion": "1.0.0",
    "version": "Wersja",
    "release_notes": "Informacje o wersji",
    "dashboard": "Główny panel",
    "lighting": "Oświetlenie",
    "settings": "Ustawienia",
    "about_nzos": "Informacje o nzOS",
    "credits": "Lista twórców",
    "about_1": "Niniejszy produkt wykorzystuje kod chroniony prawami autorskimi wykorzystywany na licencjach Apache License 2.0, Creative Commons Attribution 4.0, Eclipse Public License 2.0, MIT oraz SIL Open Font License 1.1.",
    "about_2": "Elementy programu objęte licencją Apache License 2.0:",
    "about_3": "Elementy programu objęte licencją MIT:",
    "about_4": "Elementy programu objęte pozostałymi licencjami:",
    "about_5": "Wszystkie licencje zostały zamieszczone w",
    "about_5a": "tym miejscu",
    "about_6": "Produkt został wydany na licencji GNU Lesser General Public License v3.0, której treść jest dostępna pod adresem",
    "about_6a": " lub w tym oświadczeniu.",
    "about_7": "Zbiór licencji",
    "close": "Zamknij",
    "settings_1": "Ustawienia wentylatorów",
    "settings_2": "Ustawienia pompy",
    "settings_3": "W pola poniżej należy wprowadzić wartość oczekiwaną, np. jeśli chcemy aby wentylator pracował na 50% prędkości obrotowej przy 30°C, to w polu „Temp. 30°C” należy wprowadzić wartość 50.",
    "warning": "Uwaga!",
    "settings_4": "Wykres zależności",
    "settings_5": "Pozostałe ustawienia",
    "settings_6": "Jezyk:",
    "settings_6a": "English",
    "settings_6b": "Polski",
    "settings_7": "Powiadomienia",
    "enable": "aktywny",
    "on": "aktywne",
    "settings_8": "Ostrzeżenie o temperaturze w °C",
    "settings_0": "Wartość wyrażona w procentach",
    "save": "Zapisz ustawienia",
    "lighting_1": "Ustawienia barw",
    "lighting_2": "Grupa kolorów #",
    "lighting_3": "Czerwony",
    "lighting_4": "Zielony",
    "lighting_5": "Niebieski",
    "lighting_6": "Tryb koloru",
    "lighting_7": "Stały",
    "lighting_8": "Oddychanie",
    "lighting_9": "Pulsowanie",
    "lighting_10": "Randomizacja barw",
    "lighting_11": "Stały alternatywny",
    "lighting_12": "Stały jedna barwa (Dane z koloru #2)",
    "lighting_13": "Fala - Najwolniejszy poziom",
    "lighting_14": "Fala - Wolny poziom",
    "lighting_15": "Fala - Normalny poziom",
    "lighting_16": "Fala - Szybki poziom",
    "lighting_17": "Fala - Najszybszy poziom",
    "lighting_18": "Oddychanie - Najwolniejszy poziom",
    "lighting_19": "Oddychanie - Wolny poziom",
    "lighting_20": "Oddychanie - Normalny poziom",
    "lighting_21": "Oddychanie - Szybki poziom",
    "lighting_22": "Oddychanie - Najszybszy poziom",
    "lighting_23": "Stały jedna barwa alternatywny",
    "home_1":"Temperatura",
    "home_2":"Obciążenie",
    "home_3":"Temperatura płynu",
    "home_4":"Prędkość wentylatora"


};
app.config(function ($routeProvider) {
    $routeProvider

        .when('/', {
            templateUrl: 'pages/home.html',
            controller: 'HomeController'
        })

        .when('/lighting', {
            templateUrl: 'pages/lighting.html',
            controller: 'LightingController'
        })

        .when('/settings', {
            templateUrl: 'pages/settings.html',
            controller: 'SettingsController'
        })

        .when('/about', {
            templateUrl: 'pages/about.html',
            controller: 'AboutController'
        })

        .otherwise({
            redirectTo: '/'
        });
});

app.controller('LightingController', function ($scope) {
    if (language === "PL") {
        $scope.nzos = PL.nzos;
        $scope.type = PL.lighting;
        $scope.lighting_1 = PL.lighting_1;
        $scope.lighting_2 = PL.lighting_2;
        $scope.lighting_3 = PL.lighting_3;
        $scope.lighting_4 = PL.lighting_4;
        $scope.lighting_5 = PL.lighting_5;
        $scope.lighting_6 = PL.lighting_6;
        $scope.lighting_7 = PL.lighting_7;
        $scope.lighting_8 = PL.lighting_8;
        $scope.lighting_9 = PL.lighting_9;
        $scope.lighting_10 = PL.lighting_10;
        $scope.lighting_11 = PL.lighting_11;
        $scope.lighting_12 = PL.lighting_12;
        $scope.lighting_13 = PL.lighting_13;
        $scope.lighting_14 = PL.lighting_14;
        $scope.lighting_15 = PL.lighting_15;
        $scope.lighting_16 = PL.lighting_16;
        $scope.lighting_17 = PL.lighting_17;
        $scope.lighting_18 = PL.lighting_18;
        $scope.lighting_19 = PL.lighting_19;
        $scope.lighting_20 = PL.lighting_20;
        $scope.lighting_21 = PL.lighting_21;
        $scope.lighting_22 = PL.lighting_22;
        $scope.lighting_23 = PL.lighting_23;
        $scope.save = PL.save;

    } else {
        $scope.nzos = EN.nzos;
        $scope.type = EN.lighting;
        $scope.lighting_1 = EN.lighting_1;
        $scope.lighting_2 = EN.lighting_2;
        $scope.lighting_3 = EN.lighting_3;
        $scope.lighting_4 = EN.lighting_4;
        $scope.lighting_5 = EN.lighting_5;
        $scope.lighting_6 = EN.lighting_6;
        $scope.lighting_7 = EN.lighting_7;
        $scope.lighting_8 = EN.lighting_8;
        $scope.lighting_9 = EN.lighting_9;
        $scope.lighting_10 = EN.lighting_10;
        $scope.lighting_11 = EN.lighting_11;
        $scope.lighting_12 = EN.lighting_12;
        $scope.lighting_13 = EN.lighting_13;
        $scope.lighting_14 = EN.lighting_14;
        $scope.lighting_15 = EN.lighting_15;
        $scope.lighting_16 = EN.lighting_16;
        $scope.lighting_17 = EN.lighting_17;
        $scope.lighting_18 = EN.lighting_18;
        $scope.lighting_19 = EN.lighting_19;
        $scope.lighting_20 = EN.lighting_20;
        $scope.lighting_21 = EN.lighting_21;
        $scope.lighting_22 = EN.lighting_22;
        $scope.lighting_23 = EN.lighting_23;
        $scope.save = EN.save;

    }

});

app.controller('SettingsController', function ($scope) {
    if (language === "PL") {
        $scope.nzos = PL.nzos;
        $scope.type = PL.settings;
        $scope.settings_0 = PL.settings_0;
        $scope.settings_1 = PL.settings_1;
        $scope.settings_2 = PL.settings_2;
        $scope.settings_3 = PL.settings_3;
        $scope.warning = PL.warning;
        $scope.settings_4 = PL.settings_4;
        $scope.settings_5 = PL.settings_5;
        $scope.settings_6 = PL.settings_6;
        $scope.settings_6a = PL.settings_6a;
        $scope.settings_6b = PL.settings_6b;
        $scope.settings_7 = PL.settings_7;
        $scope.enable = PL.enable;
        $scope.on = PL.on;
        $scope.settings_8 = PL.settings_8;
        $scope.save = PL.save;


    } else {
        $scope.nzos = EN.nzos;
        $scope.type = EN.settings;
        $scope.settings_0 = EN.settings_0;
        $scope.settings_1 = EN.settings_1;
        $scope.settings_2 = EN.settings_2;
        $scope.settings_3 = EN.settings_3;
        $scope.warning = EN.warning;
        $scope.settings_4 = EN.settings_4;
        $scope.settings_5 = EN.settings_5;
        $scope.settings_6 = EN.settings_6;
        $scope.settings_6a = EN.settings_6a;
        $scope.settings_6b = EN.settings_6b;
        $scope.settings_7 = EN.settings_7;
        $scope.enable = EN.enable;
        $scope.on = EN.on;
        $scope.settings_8 = EN.settings_8;
        $scope.save = EN.save;
    }
});

app.controller('AboutController', function ($scope) {
    if (language === "PL") {
        $scope.nzos = PL.nzos;
        $scope.type = PL.about_nzos;
        $scope.version = PL.version;
        $scope.numberOfVersion = PL.numberOfVersion;
        $scope.about_1 = PL.about_1;
        $scope.about_2 = PL.about_2;
        $scope.about_3 = PL.about_3;
        $scope.about_4 = PL.about_4;
        $scope.about_5 = PL.about_5;
        $scope.about_5a = PL.about_5a;
        $scope.about_6 = PL.about_6;
        $scope.about_6a = PL.about_6a;
        $scope.about_7 = PL.about_7;
        $scope.close = PL.close;
    } else {
        $scope.nzos = EN.nzos;
        $scope.type = EN.about_nzos;
        $scope.numberOfVersion = EN.numberOfVersion;
        $scope.about_1 = EN.about_1;
        $scope.about_2 = EN.about_2;
        $scope.about_3 = EN.about_3;
        $scope.about_4 = EN.about_4;
        $scope.about_5 = EN.about_5;
        $scope.about_5a = EN.about_5a;
        $scope.about_6 = EN.about_6;
        $scope.about_6a = EN.about_6a;
        $scope.close = EN.close;
    }
});


app.controller('HomeController', function ($scope) {
    if (language === "PL") {
        $scope.nzos = PL.nzos;
        $scope.type = PL.dashboard;
        $scope.home_1=PL.home_1;
        $scope.home_2=PL.home_2;
        $scope.home_3=PL.home_3;
        $scope.home_4=PL.home_4;
        $scope.home_5=PL.home_5;
        $scope.home_6=PL.home_6;
        $scope.home_7=PL.home_7;
        $scope.home_8=PL.home_8;
        $scope.home_9=PL.home_9;
        $scope.home_10=PL.home_10;
        $scope.home_11=PL.home_11;

    } else {
        $scope.nzos = EN.nzos;
        $scope.type = EN.dashboard;
        $scope.home_1=EN.home_1;
        $scope.home_2=EN.home_2;
        $scope.home_3=EN.home_3;
        $scope.home_4=EN.home_4;
        $scope.home_5=EN.home_5;
        $scope.home_6=EN.home_6;
        $scope.home_7=EN.home_7;
        $scope.home_8=EN.home_8;
        $scope.home_9=EN.home_9;
        $scope.home_10=EN.home_10;
        $scope.home_11=EN.home_11;
    }
});


app.controller('VersionController', function ($scope) {
    if (language === "PL") {
        $scope.version = PL.version;
        $scope.numberOfVersion = PL.numberOfVersion;
        $scope.releaseNotes = PL.release_notes;
    } else {
        $scope.version = EN.version;
        $scope.numberOfVersion = EN.numberOfVersion;
        $scope.releaseNotes = EN.release_notes;
    }

});


app.controller('MenuController', function ($scope) {
    if (language === "PL") {
        $scope.dashboard = PL.dashboard;
        $scope.lighting = PL.lighting;
        $scope.credits = PL.credits;
        $scope.settings = PL.settings;
        $scope.aboutNzOS = PL.about_nzos;

    } else {
        $scope.dashboard = EN.dashboard;
        $scope.lighting = EN.lighting;
        $scope.credits = EN.credits;
        $scope.settings = EN.settings;
        $scope.aboutNzOS = EN.about_nzos;
    }

});