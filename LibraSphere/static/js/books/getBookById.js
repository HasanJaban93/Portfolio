import { addEventListenerToButton } from "../general/events.js";
import { searchAction, booksRequestMapping } from "../general/constants.js";
import { displayCopies } from "../copies/findCopies.js";
import { fieldsConfig } from "./bookUpdates.js";
import {
	addUpdateEventListeners,
	addDeleteEventListeners,
} from "../general/eventUtils.js";
import { displayAuthors } from "../authors/getAuthorsBy.js";

const resultDiv = document.getElementById("result");

const bookIdField = {
	id: "bookIdInput",
	label: "Book ID:",
	type: "number",
	placeholder: "Book ID",
	required: true,
	fieldKey: "bookId",
};

addEventListenerToButton(
	"findByIdButton",
	[bookIdField],
	booksRequestMapping,
	"id",
	searchAction,
	displayBook,
	true,
	null
);

export function displayBook(book) {
	resultDiv.innerHTML = "";

	const bookDetails = document.createElement("div");
	bookDetails.innerHTML = `
		<h2>Book Details</h2>
		<div>
			<h3>Book ID:</h3>
			<p>${book.id}</p>
			<button id="deleteButton" class="deleteButtons" >Delete Book</button>
		</div>
		<div>
			<h3>Title:</h3>
			<p>${book.title}</p>
			<button id="updateTitleButton">Update Title</button>
		</div>
		<div>
			<h3>ISBN:</h3>
			<p>${book.isbn}</p>
			<button id="updateIsbnButton">Update ISBN</button>
		</div>
		<div>
			<h3>Genre:</h3>
			<p>${book.genre}</p>
			<button id="updateGenreButton">Update Genre</button>
		</div>
		<div>
			<h3>Publication Date:</h3>
			<p>${book.publicationDate}</p>
			<button id="updatePublicationDateButton">Update Publication Date</button>
		</div>
        <br/>
        <button id="addExistingAuthorButton">Add Author</button>
        <button id="removeAuthorButton">Remove Author</button>
        <button id="addDigitalCopyButton">Add A Digital Copy</button>
		<button id="addPhysicalCopyButton">Add A Physical Copy</button>
		<button id="removeCopyButton">Remove A Copy</button>
	`;
	resultDiv.appendChild(bookDetails);
	displayAuthors(book.authors);
	displayCopies(book.copySummaries);
	addBookDetailsEventListeners(book.id);
}

function addBookDetailsEventListeners(bookId) {
	addDeleteEventListeners("deleteButton", bookId, booksRequestMapping);
	addUpdateEventListeners(bookId, booksRequestMapping, fieldsConfig);
}
