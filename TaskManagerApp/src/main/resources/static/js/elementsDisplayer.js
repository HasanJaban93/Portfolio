export function displayTasksCreationModal() {
	document.getElementById("addTask").style.display = "block";
	document.getElementById("searchTask").style.display = "none";
	document.getElementById("taskTable").style.display = "none";
}

export function displayTasksDetectionModal() {
	document.getElementById("addTask").style.display = "none";
	document.getElementById("searchTask").style.display = "block";
	document.getElementById("taskTable").style.display = "none";
}

export function displaySearchInput() {
	const searchType = document.getElementById("searchType").value;
	const searchInput = document.getElementById("searchInput");
	const taskTable = document.getElementById("taskTable");

	let inputHtml = "";
	switch (searchType) {
		case "word":
			taskTable.style.display = "none"; // hide table table
			inputHtml = `<div class="input-control">
							<label for="searchWord">Word</label>
							<input type="text" id="searchWord" placeholder="Enter word" />
							<div class="error"></div>
						</div>`;
			break;

		case "date":
			taskTable.style.display = "none";
			inputHtml = `<div class="input-control">
							<label for="searchDate">Date</label>
							<input type="date" id="searchDate" />
							<div class="error"></div>
						</div>`;
			break;

		case "period":
			taskTable.style.display = "none";
			inputHtml = `<div class="input-control">
								<label for="startDate">Start Date</label>
								<input type="date" id="startDate"/>
								<div class="error"></div>
						</div>
						<div class="input-control">
								<label for="endDate">End Date</label>
								<input type="date" id="endDate"/>
								<div class="error"></div>
						</div>`;
			break;
		case "state":
			taskTable.style.display = "none";
			inputHtml =
				'<label for="searchState">State</label><select id="searchState"><option value="COMPLETED">Completed</option><option value="INCOMPLETED">Incompleted</option></select>';
			break;
		default:
			taskTable.style.display = "none";
			inputHtml = "";
			break;
	}
	searchInput.innerHTML = inputHtml;
	searchInput.style.display = searchType === "all" ? "none" : "block";
}

export function displayInformationMessage(className, message) {
	const messageDiv = document.getElementById("message");
	messageDiv.className = `message ${className}`;
	messageDiv.textContent = message;
	messageDiv.style.display = "block";
	setTimeout(function () {
		messageDiv.style.display = "none"; // Hide the message after 5 seconds
	}, 10000);
}
