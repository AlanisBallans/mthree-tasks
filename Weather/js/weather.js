function submitData() {
    var zipCode = document.getElementById("zipCode").value;
    if (zipCode.length !== 5) {
        alert('Zip code must be 5 characters');
        return;
    }

    var units = document.getElementById("units").value;

    var locationName;
    var lat;
    var lon;
    var country;

    var weather;

    var key;

    // get api key
    fetch("config.json").then(function (config) {
        key = config.apiKey;
    })

    // get lat + long of the zip code provided
    $.ajax( {
        type: 'GET',
        url: 'http://api.openweathermap.org/geo/1.0/zip?zip=' + zipCode + '&appid=' + key,
        success: function (location) {
            locationName = location.name;
            lat = location.lat;
            lon = location.lon;
            country = location.country;
        },
        error: function () {
            alert('Invalid zip code');
        }
    })

    if (lat == null || lon == null) {
        return;
    }

    $.ajax( {
        type: 'GET',
        url: 'https://api.openweathermap.org/data/2.5/weather?lat=' + lat + '&lon=' + lon + '&appid=' + key,
        success: function (weatherResponse) {
            weather = weatherResponse;
        },
        error: function () {
            alert('Could not get weather for this zip code')
        }
    })
}