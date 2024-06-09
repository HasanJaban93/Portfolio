import { displayInformationMessage } from "./elementsDisplayer.js";

export function saveTaskUpdates(taskId, fieldName, newValue) {
	return fetch(`http://localhost:8080/tasks/${taskId}/${fieldName}`, {
		method: "PATCH",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify({ [fieldName]: newValue }),
	})
		.then((response) => {
			if (!response.ok) {
				if (response.status === 409) {
					return response.text().then((text) => {
						throw new Error(text);
					});
				} else {
					throw new Error("Unexpected response: " + response.statusText);
				}
			}

			if (fieldName === "state") {
				const updatedRow = document.getElementById(taskId);
				updatedRow.className = newValue.toLowerCase();
			}
			displayInformationMessage("success", "Task updated successfully");

			return response;
		})
		.catch((error) => {
			displayInformationMessage("error", "Error: " + error.message);
			throw error;
		});
}
