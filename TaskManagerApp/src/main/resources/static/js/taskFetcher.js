import { displayInformationMessage } from "./elementsDisplayer.js";
import { removeTask } from "./taskRemover.js";
import { updateTask } from "./taskUpdater.js";

export function fetchTasks(query) {
	fetch(`http://localhost:8080/tasks${query}`)
		.then((response) => response.json())
		.then((tasks) => {
			// Debugging: log the entire response
			console.log("Received tasks:", tasks);

			const taskTable = document.getElementById("taskTable");
			const tbody = taskTable.querySelector("tbody");

			tbody.innerHTML = ""; // Clear existing rows

			if (Array.isArray(tasks)) {
				if (tasks.length === 0) {
					displayInformationMessage("error", "There are no tasks to display.");
					return; // Exit the function if the array is empty
				}
				tasks.forEach((task) => {
					console.log("Task:", task); // Log each task for debugging

					const row = document.createElement("tr");
					row.className =
						task.state === "COMPLETED" ? "completed" : "incompleted";
					row.setAttribute("data-task-id", task.id);
					row.setAttribute("id", task.id);

					row.innerHTML = `
				<td>${task.description}</td>
				<td>${task.date}</td>
				<td>${task.time}</td>
				<td>${task.state}</td>
				<td>
					<button class="edit-task-button" data-task-id="${task.id}">Edit</button>
					<button class="delete-task-button" data-task-id="${task.id}">Delete</button>
				</td>
			`;

					tbody.appendChild(row);
				});
				taskTable.style.display = "table";
				// Add event listeners for delete buttons
				const deleteButtons = document.querySelectorAll(".delete-task-button");
				deleteButtons.forEach((button) => {
					button.addEventListener("click", function () {
						const taskId = button.getAttribute("data-task-id");
						removeTask(taskId);
					});
				});
				// Add event listeners for edit buttons
				const editButtons = document.querySelectorAll(".edit-task-button");
				editButtons.forEach((button) => {
					button.addEventListener("click", function () {
						const taskId = button.getAttribute("data-task-id");
						updateTask(taskId);
					});
				});
			} else {
				console.error("Expected an array of tasks but got:", tasks);
			}
		})
		.catch((error) => {
			console.error("Fetch error:", error);
			displayInformationMessage("error", "Error: " + error.message);
		});
}
