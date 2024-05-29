export async function fetchCityImage(cityName) {
	const accessKey = "your access key for unsplash API"; // Replace with your actual access key
	const response = await fetch(
		`https://api.unsplash.com/photos/random?query=${cityName}&client_id=${accessKey}`
	);

	if (response.ok) {
		const data = await response.json();
		const cityName =
			data.location.city !== null && data.location.country !== null
				? `${data.location.city}, ${data.location.country}`
				: "";
		const cityLocaction = document.getElementById("cityLocation");
		cityLocaction.innerHTML = cityName;

		const cityImage = document.getElementById("city-image");
		const cityImageUrl = data.urls.small;
		const cityImageAlt = data.alt_description;
		const cityImageWidth = data.width;
		const cityImageHeight = data.height;
		cityImage.src = cityImageUrl;
		cityImage.alt = cityImageAlt;
		cityImage.width = cityImageWidth;
		cityImage.height = cityImageHeight;

		const imageDate = document.getElementById("imageDate");
		const photographerName = data.user.name;
		const photographerProfileUrl = data.user.links.html;
		const createdAt = new Date(data.created_at);
		const formattedDate = createdAt.toLocaleDateString(undefined, {
			year: "numeric",
			month: "long",
			day: "numeric",
		});
		imageDate.innerHTML = `Image taken on: ${formattedDate} 
		by <a href="${photographerProfileUrl}" target="_blank">${photographerName}</a> on Unsplash.`;
	} else {
		console.error("Error fetching image:", response.statusText);
	}
}
