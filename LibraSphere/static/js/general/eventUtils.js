import { displayField } from "./utils.js";
import { updateField, deleteField } from "./api.js";
import { updateAction, deleteAction } from "./constants.js";

export const setupEventListeners = (handleSubmit, handleCancel) => {
	const updateButton = document.getElementById("updateButton");
	const cancelButton = document.getElementById("cancelButton");

	cancelButton.addEventListener("click", handleCancel);

	updateButton.addEventListener("click", handleSubmit);
};

export const handleFieldUpdate = (
	entityId,
	buttonId,
	fields,
	requestMapping,
	fieldKey,
	displayName,
	displayResultsFunction
) => {
	displayField(buttonId, fields);

	const handleSubmit = () => {
		console.log(`Submit button for ${buttonId} clicked`);
		console.log(fields);
		const value =
			fields.length === 1
				? document.getElementById(fields[0].id).value
				: fields.reduce((acc, field) => {
						acc[field.fieldKey] = document.getElementById(field.id).value;
						return acc;
				  }, {});
		console.log(value);
		if (entityId) {
			updateField(
				entityId,
				requestMapping,
				fieldKey,
				value,
				updateAction,
				displayName,
				displayResultsFunction
			);
		} else {
			updateField(
				value,
				requestMapping,
				fieldKey,
				"",
				updateAction,
				displayName,
				displayResultsFunction
			);
		}
	};

	const handleCancel = () => {
		const updateInputsDiv = document.getElementById("updateInputs");
		updateInputsDiv.innerHTML = "";
		updateInputsDiv.style.display = "none";
	};

	setupEventListeners(handleSubmit, handleCancel);
};

export const addUpdateEventListeners = (
	entityId,
	requestMapping,
	fieldsConfig
) => {
	fieldsConfig.forEach(
		({ buttonId, fields, fieldKey, displayName, displayResultsFunction }) => {
			const button = document.getElementById(buttonId);
			button.addEventListener("click", () =>
				handleFieldUpdate(
					entityId,
					buttonId,
					fields,
					requestMapping,
					fieldKey,
					displayName,
					displayResultsFunction
				)
			);
		}
	);
};

export const addDeleteEventListeners = (buttonId, entityId, requestMapping) => {
	const button = document.getElementById(buttonId);
	button.addEventListener("click", () =>
		deleteField(requestMapping, deleteAction, entityId)
	);
};
