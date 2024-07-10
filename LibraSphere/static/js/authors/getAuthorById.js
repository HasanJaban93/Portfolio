import { addEventListenerToButton } from "../general/events.js";
import { searchByField } from "../general/api.js";
import { displayBook } from "../books/getBookById.js";
import {
	searchAction,
	authorsRequestMapping,
	booksRequestMapping,
} from "../general/constants.js";
import { fieldsConfig } from "./authorUpdates.js";
import {
	addUpdateEventListeners,
	addDeleteEventListeners,
} from "../general/eventUtils.js";
import { createTable } from "../general/utils.js";

const resultDiv = document.getElementById("result");

export function displayAuthorDetails(author) {
	resultDiv.innerHTML = "";
	const authorDetails = document.createElement("div");
	authorDetails.innerHTML = `
	<h2>Author Details</h2>
	<div>
		<h3>Author ID:</h3>
		<p>${author.id}</p>
		<button id="deleteButton" class="deleteButtons" >Delete Author</button>
	</div>
	<div>
		<h3>First Name:</h3>
		<p>${author.firstName}</p>
		<button id="updateAuthorFirstNameButton">Update First Name</button>
	</div>
	<div>
		<h3>Last Name:</h3>
		<p>${author.lastName}</p>
		<button id="updateAuthorLastNameButton">Update Last Name</button>
	</div>
	<div>
		<h3>Year of Birth:</h3>
		<p>${author.yearOfBirth}</p>
		<button id="updateYearOfBirthButton">Update Year of Birth</button>
	</div>
	<div>
		<h3>Gender:</h3>
		<p>${author.gender}</p>
		<button id="updateGenderButton">Update Gender</button>
	</div>
	<div>
		<h3>Nationality:</h3>
		<p>${author.nationality}</p>
		<button id="updateNationalityButton">Update Nationality</button>
	</div>
	<div>
		<h3>Biography:</h3>
		<p>${author.biography ? author.biography : ""}</p>
		<button id="updateBiographyButton">Update Biography</button>
	</div>
	<br/>
	<button id="addAwardButton">Add Award</button>
	<button id="removeAwardButton">Remove Award</button>
    `;
	resultDiv.appendChild(authorDetails);

	if (author.awards && author.awards.length > 0) {
		const awardsDiv = document.createElement("div");
		awardsDiv.innerHTML = `<h3>Awards</h3>`;
		const awardsList = document.createElement("ul");
		author.awards.forEach((award) => {
			const li = document.createElement("li");
			li.textContent = `${award.name} (${award.year})`;
			awardsList.appendChild(li);
		});
		awardsDiv.appendChild(awardsList);
		resultDiv.appendChild(awardsDiv);
	}

	if (author.bookSummaryList && author.bookSummaryList.length > 0) {
		const booksDiv = document.createElement("div");
		booksDiv.innerHTML = `<h3>Books</h3>`;

		const headers = ["ID", "Title", "ISBN", "Genre", "Publication Date"];
		const books = author.bookSummaryList;
		const table = createTable(headers, books, (book) => {
			searchByField(
				booksRequestMapping,
				"id",
				book.id,
				searchAction,
				displayBook
			);
		});
		table.id = "booksTable";
		booksDiv.appendChild(table);
		resultDiv.appendChild(booksDiv);
	}
	addAuthorDetailsEventListeners(author.id);
}

function addAuthorDetailsEventListeners(authorId) {
	addDeleteEventListeners("deleteButton", authorId, authorsRequestMapping);
	addUpdateEventListeners(authorId, authorsRequestMapping, fieldsConfig);
}

const authorIdField = {
	id: "authorIdInput",
	label: "Author ID:",
	type: "number",
	placeholder: "Author ID",
	required: true,
	fieldKey: "authorId",
};

addEventListenerToButton(
	"findAuthorByIdButton",
	[authorIdField],
	authorsRequestMapping,
	"id",
	searchAction,
	displayAuthorDetails,
	true,
	null
);
