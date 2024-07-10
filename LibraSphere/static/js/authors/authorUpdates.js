import { createFieldConfig } from "../general/utils.js";

const fields = {
	firstName: createFieldConfig(
		"newFirstNameInput",
		"New First Name:",
		"text",
		"New firstName",
		true,
		"firstName"
	),
	lastName: createFieldConfig(
		"newLastNameInput",
		"New Last Name:",
		"text",
		"New lastName",
		true,
		"lastName"
	),
	biography: createFieldConfig(
		"newBiographyInput",
		"New Biography:",
		"textarea",
		"New Biography",
		true,
		"biography"
	),
	nationality: createFieldConfig(
		"newNationalityInput",
		"New Nationality:",
		"text",
		"New Nationality",
		true,
		"nationality"
	),
	yearOfBirth: createFieldConfig(
		"newYearOfBirthInput",
		"New Year Of Birth:",
		"number",
		"New Year Of Birth",
		true,
		"yearOfBirth"
	),
	gender: createFieldConfig(
		"newGenderTypeSelect",
		"Gender:",
		"select",
		"",
		true,
		"gender",
		["MAN", "WOMAN"]
	),
	awardName: createFieldConfig(
		"awardNameInput",
		"Award:",
		"text",
		"Award",
		true,
		"name"
	),
	awardYear: createFieldConfig(
		"awardYearInput",
		"Year:",
		"number",
		"Year",
		true,
		"year"
	),
};

export const fieldsConfig = [
	{
		buttonId: "updateAuthorFirstNameButton",
		fields: [fields.firstName],
		fieldKey: "firstName",
		displayName: "First Name",
	},
	{
		buttonId: "updateAuthorLastNameButton",
		fields: [fields.lastName],
		fieldKey: "lastName",
		displayName: "Last Name",
	},
	{
		buttonId: "updateBiographyButton",
		fields: [fields.biography],
		fieldKey: "biography",
		displayName: "Biography",
	},
	{
		buttonId: "updateNationalityButton",
		fields: [fields.nationality],
		fieldKey: "nationality",
		displayName: "Nationality",
	},
	{
		buttonId: "updateYearOfBirthButton",
		fields: [fields.yearOfBirth],
		fieldKey: "yearOfBirth",
		displayName: "Year Of Birth",
	},
	{
		buttonId: "updateGenderButton",
		fields: [fields.gender],
		fieldKey: "gender",
		displayName: "Gender",
	},
	{
		buttonId: "addAwardButton",
		fields: [fields.awardName, fields.awardYear],
		fieldKey: "addAward",
		displayName: "addAward",
	},
	{
		buttonId: "removeAwardButton",
		fields: [fields.awardName, fields.awardYear],
		fieldKey: "removeAward",
		displayName: "removeAward",
	},
];
