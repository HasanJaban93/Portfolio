import { displayInformationMessage } from "./elementsDisplayer.js";
import { validateInputs } from "./inputValidator.js";

export function createTask() {
	if (validateInputs()) {
		const description = document.getElementById("description").value;
		const date = document.getElementById("date").value;
		const time = document.getElementById("time").value;
		fetch("http://localhost:8080/tasks", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify({ description, date, time }),
		})
			.then((response) => {
				if (response.ok) {
					// Handle success response
					return response.json();
				} else if (response.status === 409) {
					// Handle 409 Conflict response
					return response.text();
				} else {
					// Handle other error responses
					throw new Error("Unexpected response from server");
				}
			})
			.then((data) => {
				if (typeof data === "number") {
					displayInformationMessage("success", "Task added successfully");
					document.getElementById("description").value = "";
					document.getElementById("date").value = "";
					document.getElementById("time").value = "";
				} else {
					displayInformationMessage("error", "Error: " + data);
				}
			});
	}
}
