import { addEventListenerToButton } from "../general/events.js";
import { searchByField } from "../general/api.js";
import { createTable } from "../general/utils.js";
import { searchAction, authorsRequestMapping } from "../general/constants.js";
import { displayAuthorDetails } from "./getAuthorById.js";

const resultDiv = document.getElementById("result");

export const displayAuthors = (authors) => {
	const authorsDiv = document.createElement("div");
	const h2 = document.createElement("h2");
	h2.innerText = "Authors";
	authorsDiv.appendChild(h2);
	if (!authors || authors.length === 0) {
		authorsDiv.innerHTML = "<p>No authors found.</p>";
		resultDiv.appendChild(authorsDiv);
		return;
	}
	const headers = ["ID", "First Name", "Last Name", "Nationality"];
	const table = createTable(headers, authors, (author) => {
		searchByField(
			authorsRequestMapping,
			"id",
			author.id,
			searchAction,
			displayAuthorDetails
		);
	});
	table.id = "authorsTable";
	authorsDiv.appendChild(table);
	resultDiv.appendChild(authorsDiv);
};

const firstNameField = {
	id: "firstNameInput",
	label: "First Name:",
	type: "text",
	placeholder: "First Name",
	required: true,
	fieldKey: "firstName",
};

const lastNameField = {
	id: "lastNameInput",
	label: "Last Name:",
	type: "text",
	placeholder: "Last Name",
	required: true,
	fieldKey: "lastName",
};

const genderField = {
	id: "genderTypeSelect",
	label: "Gender:",
	type: "select",
	placeholder: "",
	required: true,
	fieldKey: "gender",
	options: ["MAN", "WOMAN"],
};

const nationalityField = {
	id: "nationalityInput",
	label: "Nationality:",
	type: "text",
	placeholder: "Nationality",
	required: true,
	fieldKey: "nationality",
};

const yearOfBirthField = {
	id: "yearOfBirthInput",
	label: "Year Of Birth:",
	type: "number",
	placeholder: "Year Of Birth",
	required: true,
	fieldKey: "yearOfBirth",
};

addEventListenerToButton(
	"getAllAuthorsButton",
	[],
	authorsRequestMapping,
	null,
	searchAction,
	displayAuthors,
	true,
	null
);

addEventListenerToButton(
	"findByFirstNameButton",
	[firstNameField],
	authorsRequestMapping,
	"firstName",
	searchAction,
	displayAuthors,
	true,
	null
);

addEventListenerToButton(
	"findByLastNameButton",
	[lastNameField],
	authorsRequestMapping,
	"lastName",
	searchAction,
	displayAuthors,
	true,
	null
);

addEventListenerToButton(
	"findByGenderButton",
	[genderField],
	authorsRequestMapping,
	"gender",
	searchAction,
	displayAuthors,
	true,
	null
);

addEventListenerToButton(
	"findByNationalityButton",
	[nationalityField],
	authorsRequestMapping,
	"nationality",
	searchAction,
	displayAuthors,
	true,
	null
);

addEventListenerToButton(
	"findByYearOfBirthButton",
	[yearOfBirthField],
	authorsRequestMapping,
	"yearOfBirth",
	searchAction,
	displayAuthors,
	true,
	null
);
