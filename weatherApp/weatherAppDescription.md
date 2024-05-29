# Weather App

Welcome to the Weather App! This application provides real-time weather updates for any location, accompanied by a picture of the city to enhance the user experience.

## Features

- Get current weather information for any city
- Get current picture of the city
- Detailed weather forecast including temperature, humidity and wind speed.
- Responsive design for seamless user experience across devices

## Technologies Used

- HTML5
- CSS3
- JavaScript
- OpenWeatherMap API for fetching weather data
- Unsplash API for sourcing city pictures

## Usage

1. Enter the name of the city in the input field.
2. Click the "Search" button to fetch the weather details.
3. Explore the weather forecast for the selected city and see a picture of it.

### API Keys
To use the Weather App, you'll need to obtain API keys for the Unsplash API and the OpenWeatherMap API. Follow the steps below to get your API keys:

- **Unsplash API**: Sign up for an account on [Unsplash](https://unsplash.com/developers) and generate an access key.
- **OpenWeatherMap API**: Sign up for an account on [OpenWeatherMap](https://openweathermap.org/api) and obtain an API key.

### Adding API Keys
Once you have obtained your API keys, you need to add them to the project. Here's how:

1. **Unsplash API**: Sign up for an account on [Unsplash](https://unsplash.com/developers) and generate an access key. Replace the placeholder text `"your access key for Unsplash API"` in the `js/showCityImage.js` file with your actual access key.

    ```javascript
    // In js/showCityImage.js

    // Add your access key for the Unsplash API below
    const accessKey = "your access key for Unsplash API"; // Replace with your actual access key
    ```

2. **OpenWeatherMap API**: Sign up for an account on [OpenWeatherMap](https://openweathermap.org/api) and obtain an API key. Replace the placeholder text `"your OpenWeatherMap API key"` in the `js/weather.js` file with your actual API key.

    ```javascript
    // In js/weather.js

    // Add your API key for the OpenWeatherMap API below
    const apiKey = "your OpenWeatherMap API key"; // Replace with your actual API key
    ```

By following these steps and adding your own API keys, you'll be able to use the Weather App to retrieve real-time weather information and city images.
