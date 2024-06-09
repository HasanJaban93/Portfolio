import { saveTaskUpdates } from "./taskUpdatesSaver.js";

export function updateTask(taskId) {
	const row = document.querySelector(`tr[data-task-id='${taskId}']`);
	const cells = row.querySelectorAll("td");
	const editButton = row.querySelector(".edit-task-button");

	if (editButton.textContent === "Edit") {
		// Switch to edit mode
		cells.forEach((cell, index) => {
			if (index !== cells.length - 1) {
				// Skip the last cell (contains buttons)
				let inputElement;
				if (index === 0) {
					// Description field: text input
					inputElement = document.createElement("input");
					inputElement.type = "text";
					inputElement.value = cell.textContent;
				} else if (index === 1) {
					// Date field: date input
					inputElement = document.createElement("input");
					inputElement.type = "date";
					inputElement.value = cell.textContent;
				} else if (index === 2) {
					// Time field: time input
					inputElement = document.createElement("input");
					inputElement.type = "time";
					inputElement.value = cell.textContent;
				} else if (index === 3) {
					// State field: select dropdown
					inputElement = document.createElement("select");
					const options = ["INCOMPLETED", "COMPLETED"];
					options.forEach((option) => {
						const optionElement = document.createElement("option");
						optionElement.value = option;
						optionElement.textContent = option;
						inputElement.appendChild(optionElement);
					});
					inputElement.value = cell.textContent;
				}

				// Store initial value
				cell.dataset.initialValue = cell.textContent;

				// Replace cell content with input element
				cell.innerHTML = "";
				cell.appendChild(inputElement);
			}
		});
		editButton.textContent = "Save";
	} else {
		// Switch back to view mode and save changes
		cells.forEach((cell, index) => {
			if (index !== cells.length - 1) {
				// Skip the last cell (contains buttons)
				const input = cell.querySelector("input, select");
				if (input) {
					const newValue = input.value;
					const initialValue = cell.dataset.initialValue;
					if (newValue !== initialValue) {
						// Update the field
						const fieldName =
							index === 0
								? "description"
								: index === 1
								? "date"
								: index === 2
								? "time"
								: "state";
						saveTaskUpdates(taskId, fieldName, newValue)
							.then((response) => {
								if (response.ok) {
									// Update the cell value only if saveTaskUpdates is successful
									cell.textContent = newValue;
									editButton.textContent = "Edit";
								}
							})
							.catch((error) => {
								// Handle error if needed
								cell.textContent = initialValue;
							});
					} else {
						// If no changes, revert to initial value
						cell.textContent = initialValue;
						editButton.textContent = "Edit";
					}
				}
			}
		});
	}
}
