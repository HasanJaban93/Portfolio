export const validateInput = () => {
	const city = document.getElementById("city");
	const cityValue = city.value.trim();
	let isValid = true;

	if (cityValue === "") {
		setError(city, "Please enter a city");
		isValid = false;
	} else {
		setSuccess(city);
	}

	return isValid;
};

const setError = (element, message) => {
	const inputControl = element.parentElement;
	const errorDisplay = inputControl.querySelector(".error");

	errorDisplay.innerText = message;
	inputControl.classList.add("error");
	inputControl.classList.remove("success");
};

const setSuccess = (element) => {
	const inputControl = element.parentElement;
	const errorDisplay = inputControl.querySelector(".error");

	errorDisplay.innerText = "";
	inputControl.classList.add("success");
	inputControl.classList.remove("error");
};
