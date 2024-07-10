import { addEventListenerToButton } from "../general/events.js";
import { createAction, authorsRequestMapping } from "../general/constants.js";

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

const yearOfBirthField = {
	id: "yearOfBirthInput",
	label: "Year Of Birth:",
	type: "number",
	placeholder: "Year Of Birth",
	required: true,
	fieldKey: "yearOfBirth",
};

const nationalityField = {
	id: "nationalityInput",
	label: "Nationality:",
	type: "text",
	placeholder: "Nationality",
	required: true,
	fieldKey: "nationality",
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

addEventListenerToButton(
	"addNewAuthorButton",
	[
		firstNameField,
		lastNameField,
		yearOfBirthField,
		nationalityField,
		genderField,
	],
	authorsRequestMapping,
	null,
	createAction,
	null,
	false,
	null
);
