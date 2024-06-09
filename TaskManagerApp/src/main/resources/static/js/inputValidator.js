export const validateInputs = () => {
	const description = document.getElementById("description");
	const date = document.getElementById("date");
	const time = document.getElementById("time");
	const descriptionValue = description.value.trim();
	const dateValue = date.value;
	const timeValue = time.value;
	let isValid = true;

	if (!descriptionValue) {
		setError(description, "Description is required.");
		isValid = false;
	} else {
		setSuccess(description);
	}

	if (!dateValue) {
		setError(date, "Date is required");
		isValid = false;
	} else if (!futureOrPresentDate(dateValue)) {
		setError(date, "Date must be today or in the future.");
		isValid = false;
	} else {
		setSuccess(date);
	}

	if (!timeValue) {
		setError(time, "Time  is required.");
		isValid = false;
	} else {
		setSuccess(time);
	}

	return isValid;
};

const futureOrPresentDate = (givenDate) => {
	let isValidDate = true;
	const enteredDate = new Date(givenDate); // Validate date (should be present or future date)
	const currentDate = new Date();
	currentDate.setHours(0, 0, 0, 0); // Remove the time part of the current date to only compare the date
	if (enteredDate < currentDate) {
		isValidDate = false;
	}
	return isValidDate;
};

export const setError = (element, message) => {
	const inputControl = element.parentElement;
	const errorDisplay = inputControl.querySelector(".error");

	errorDisplay.innerText = message;
	inputControl.classList.add("error");
	inputControl.classList.remove("success");
};

export const setSuccess = (element) => {
	const inputControl = element.parentElement;
	const errorDisplay = inputControl.querySelector(".error");

	errorDisplay.innerText = "";
	inputControl.classList.add("success");
	inputControl.classList.remove("error");
};
