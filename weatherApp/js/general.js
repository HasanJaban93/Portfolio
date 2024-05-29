export function clearPreviousContent() {
	getElementsById().forEach((Element) => (Element.innerHTML = ""));
	hideDetails();
	hideImages();
	if (document.querySelector(".scrolIcon") !== null) {
		document.querySelector(".scrolIcon").style.display = "none";
	}
}

export function showImages() {
	const weatherIcon = document.getElementById("weather-icon");
	weatherIcon.style.display = "block"; // Make the image visible once it's loaded
	const cityImage = document.getElementById("city-image");
	cityImage.style.display = "block";
}

function hideImages() {
	const weatherIcon = document.getElementById("weather-icon");
	weatherIcon.style.display = "none";
	const cityImage = document.getElementById("city-image");
	cityImage.style.display = "none";
}

export function showDetails() {
	document.querySelector(".details").style.display = "flex";
}

function hideDetails() {
	document.querySelector(".details").style.display = "none";
}

function getElementsById() {
	const tempDivInfo = document.getElementById("temp-div");
	const weatherInfoDiv = document.getElementById("weather-info");
	const hourlyForecastDiv = document.getElementById("hourly-forecast");
	const cityLocation = document.getElementById("cityLocation");
	const imageDate = document.getElementById("imageDate");
	const elements = [];
	elements.push(
		tempDivInfo,
		weatherInfoDiv,
		hourlyForecastDiv,
		cityLocation,
		imageDate
	);
	return elements;
}
