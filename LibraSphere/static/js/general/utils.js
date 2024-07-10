import { createAction, searchAction, updateAction } from "./constants.js";

export const formatBodyContent = (field, value) => {
	const jsonFields = [
		"gender",
		"addAward",
		"removeAward",
		"newAuthor",
		"genre",
		"publicationDate",
		"addDigitalCopy",
		"addPhysicalCopy",
		"address",
		"dateOfBirth",
		"borrowCopy",
		"membershipDate",
	];
	const intFields = [
		"yearOfBirth",
		"addAuthor",
		"removeAuthor",
		"removeCopy",
		"shelfNumber",
		"postcode",
	];

	if (jsonFields.includes(field)) {
		return JSON.stringify(value);
	}
	if (intFields.includes(field)) {
		return parseInt(value, 10);
	}
	return value;
};

export const createInputHTML = (fields, action) => {
	if (!fields.length) return "";
	return (
		fields.map((field) => createFieldHTML(field)).join("") +
		`
		${
			action === searchAction
				? '<button id="searchButton">Search</button>'
				: action === createAction
				? '<button id="createButton">Create</button>'
				: '<button id="updateButton">Update</button>'
		}
		`
	);
};

const createFieldHTML = (field) => {
	const commonAttributes = `id="${field.id}" ${
		field.required ? "required" : ""
	}`;
	switch (field.type) {
		case "select":
			return `
                <label for="${field.id}">${field.label}</label>
                <select ${commonAttributes}>
                    ${field.options
											.map(
												(option) =>
													`<option value="${option}">${option}</option>`
											)
											.join("")}
                </select><br>
            `;
		case "textarea":
			return `
                <label for="${field.id}">${field.label}</label>
                <textarea ${commonAttributes} placeholder="${field.placeholder}" rows="5"></textarea><br>
            `;
		default:
			return `
                <label for="${field.id}">${field.label}</label>
                <input type="${field.type}" ${commonAttributes} placeholder="${field.placeholder}"><br>
            `;
	}
};

export function displayField(buttonId, fields) {
	const button = document.getElementById(buttonId);

	let updateInputsDiv = document.getElementById("updateInputs");
	if (!updateInputsDiv) {
		updateInputsDiv = document.createElement("div");
		updateInputsDiv.id = "updateInputs";
	}
	updateInputsDiv.innerHTML = createInputHTML(fields, updateAction);
	const cancelButtonHtml = '<button id="cancelButton">Cancel</button>';
	updateInputsDiv.innerHTML += cancelButtonHtml;
	updateInputsDiv.style.display = "block";
	button.insertAdjacentElement("afterend", updateInputsDiv);
}

export const createFieldConfig = (
	id,
	label,
	type,
	placeholder,
	required,
	fieldKey,
	options = []
) => ({
	id,
	label,
	type,
	placeholder,
	required,
	fieldKey,
	options,
});

export const createTable = (headers, rows, rowClickHandler) => {
	const table = document.createElement("table");
	const thead = document.createElement("thead");
	const trHeader = document.createElement("tr");

	headers.forEach((header) => {
		const th = document.createElement("th");
		th.textContent = header;
		trHeader.appendChild(th);
	});
	thead.appendChild(trHeader);
	table.appendChild(thead);

	const tbody = document.createElement("tbody");
	rows.forEach((row) => {
		const tr = document.createElement("tr");
		tr.addEventListener("click", () => rowClickHandler(row));

		Object.values(row).forEach((value, index) => {
			const td = document.createElement("td");
			td.textContent = value;
			td.setAttribute("data-label", headers[index]);
			tr.appendChild(td);
		});
		tbody.appendChild(tr);
	});
	table.appendChild(tbody);

	return table;
};
