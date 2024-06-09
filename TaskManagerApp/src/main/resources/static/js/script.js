import {
	displayTasksCreationModal,
	displayTasksDetectionModal,
	displaySearchInput,
} from "./elementsDisplayer.js";
import { createTask } from "./taskCreator.js";
import { findTasks } from "./taskFinder.js";

const barButtons = document.querySelectorAll(".button-bar button");

barButtons.forEach((button) => {
	button.addEventListener("click", () => {
		barButtons.forEach((btn) => btn.classList.remove("active"));
		button.classList.add("active");
	});
});

const buttonBarAdd = document.getElementById("button-bar-add");
buttonBarAdd.addEventListener("click", displayTasksCreationModal);

const buttonBarSearch = document.getElementById("button-bar-search");
buttonBarSearch.addEventListener("click", displayTasksDetectionModal);

const addTaskButton = document.getElementById("addTaskButton");
addTaskButton.addEventListener("click", createTask);

const searchType = document.getElementById("searchType");
searchType.addEventListener("change", displaySearchInput);

const searchTaskButton = document.getElementById("searchTaskButton");
searchTaskButton.addEventListener("click", findTasks);
