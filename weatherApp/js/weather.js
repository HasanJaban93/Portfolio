import { clearPreviousContent } from "./general.js";
import { validateInput } from "./inputValidation.js";
import { displayWeather } from "./displayWeather.js";
import { displayHourlyForecast } from "./displayHourlyForecast.js";

document.getElementById("searchButton").onclick = getWeather;

function getWeather() {
	const apiKey = "your open weather map API key"; // Replace with your actual API key
	const city = document.getElementById("city").value;
	clearPreviousContent();
	if (validateInput()) {
		const currentWeatherUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}`;
		const forecastUrl = `https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${apiKey}`;
		fetch(currentWeatherUrl)
			.then((response) => response.json())
			.then((data) => {
				displayWeather(data);
			})
			.catch((error) => {
				console.error("Error fetching current weather data:", error);
			});

		fetch(forecastUrl)
			.then((response) => response.json())
			.then((data) => {
				displayHourlyForecast(data.list);
			})
			.catch((error) => {
				console.error("Error fetching hourly forecast data:", error);
			});
	}
}
