import { showImages, showDetails } from "./general.js";
import { fetchCityImage } from "./showCityImage.js";

export function displayWeather(data) {
	const tempDivInfo = document.getElementById("temp-div");
	const weatherInfoDiv = document.getElementById("weather-info");
	const weatherIcon = document.getElementById("weather-icon");
	const humidityP = document.getElementById("humidity");
	const windP = document.getElementById("wind");

	if (data.cod === "404") {
		weatherInfoDiv.innerHTML = `<p>${data.message}</p>`;
	} else {
		const cityName = data.name;
		const contryCode = data.sys.country;
		const temperature = Math.round(data.main.temp - 273.15); // Convert to Celsius
		const description = data.weather[0].description;
		const humidity = data.main.humidity;
		const wind = data.wind.speed;
		const iconCode = data.weather[0].icon;
		const iconUrl = `https://openweathermap.org/img/wn/${iconCode}@4x.png`;

		const temperatureHTML = `
            <p>${temperature}°C</p>
        `;

		const weatherHtml = `
		    <p>${cityName}, ${contryCode}</p>
            <p>${description}</p>
        `;

		fetchCityImage(cityName);
		tempDivInfo.innerHTML = temperatureHTML;
		weatherInfoDiv.innerHTML = weatherHtml;
		humidityP.innerText = `${humidity} %`;
		windP.innerText = `${wind} km/h`;
		weatherIcon.src = iconUrl;
		weatherIcon.alt = description;

		showImages();
		showDetails();
	}
}
