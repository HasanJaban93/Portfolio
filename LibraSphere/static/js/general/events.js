import { createEntity, searchByField } from "./api.js";
import { searchAction } from "./constants.js";
import { createInputHTML } from "./utils.js";

const resultDiv = document.getElementById("result");
const inputDiv = document.getElementById("inputDiv");

export const addEventListenerToButton = (
	buttonId,
	inputFields,
	requestMapping,
	field,
	action,
	displayResultsFunction,
	singleField
) => {
	const button = document.getElementById(buttonId);
	button.addEventListener("click", () => {
		resultDiv.innerHTML = "";
		resultDiv.style.display = "block";
		inputDiv.innerHTML = createInputHTML(inputFields, action);

		if (!inputDiv.innerHTML) {
			handleSearch();
			return;
		}

		inputDiv.style.display = "block";
		const button =
			action === searchAction
				? document.getElementById("searchButton")
				: document.getElementById("createButton");
		button.addEventListener("click", handleButtonClick);
	});

	function handleButtonClick() {
		resultDiv.innerHTML = "";
		switch (action) {
			case "search":
				handleSearchSubmit();
				break;
			case "create":
				handleCreateSubmit();
				break;
		}
	}

	function handleSearch() {
		inputDiv.style.display = "none";
		searchByField(requestMapping, "", "", action, displayResultsFunction);
	}

	function handleSearchSubmit() {
		const value = singleField
			? document.getElementById(inputFields[0].id).value
			: inputFields.reduce((acc, field) => {
					acc[field.fieldKey] = document.getElementById(field.id).value;
					return acc;
			  }, {});
		searchByField(requestMapping, field, value, action, displayResultsFunction);
	}

	function handleCreateSubmit() {
		const value = inputFields.reduce((acc, field) => {
			acc[field.fieldKey] = document.getElementById(field.id).value;
			return acc;
		}, {});
		createEntity(requestMapping, action, value);
	}
};
