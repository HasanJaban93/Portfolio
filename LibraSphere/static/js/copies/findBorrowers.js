import { searchByField } from "../general/api.js";
import { searchAction, membersRequestMapping } from "../general/constants.js";
import { displayMemberDetails } from "../members/findMemberById.js";
import { createTable } from "../general/utils.js";

const resultDiv = document.getElementById("result");

export function displayBorrowers(copyIdAndBorrowers) {
	const borrowersDiv = document.createElement("div");
	if (copyIdAndBorrowers.borrowerSummaries.length === 0) {
		const p = document.createElement("p");
		p.innerText = "No borrowers found for this copy.";
		borrowersDiv.appendChild(p);
		resultDiv.appendChild(borrowersDiv);
		return;
	}
	const h2 = document.createElement("h2");
	h2.innerText = `Borrowers of Copy With ID: ${copyIdAndBorrowers.copyId}`;
	borrowersDiv.appendChild(h2);
	const headers = [
		"Borrow Date",
		"Return Date",
		"Member ID",
		"First Name",
		"Last Name",
		"Email",
	];
	const rows = copyIdAndBorrowers.borrowerSummaries.map((summary) => ({
		borrowDate: summary.borrowDate,
		returnDate: summary.returnDate,
		id: summary.memberSummary.id,
		firstName: summary.memberSummary.firstName,
		lastName: summary.memberSummary.lastName,
		email: summary.memberSummary.email,
	}));

	const table = createTable(headers, rows, (summary) => {
		console.log(summary);
		searchByField(
			membersRequestMapping,
			"id",
			summary.id,
			searchAction,
			displayMemberDetails
		);
	});
	table.id = "membersTable";
	borrowersDiv.appendChild(table);
	resultDiv.appendChild(borrowersDiv);
}
