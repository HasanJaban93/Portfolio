import { addEventListenerToButton } from "../general/events.js";
import {
	genres,
	searchAction,
	booksRequestMapping,
} from "../general/constants.js";
import { searchByField } from "../general/api.js";
import { displayBook } from "./getBookById.js";
import { createTable } from "../general/utils.js";

const resultDiv = document.getElementById("result");

const displayBooks = (books) => {
	const booksDiv = document.createElement("div");
	const h2 = document.createElement("h2");
	h2.innerText = "Books";
	booksDiv.appendChild(h2);
	if (!books || books.length === 0) {
		booksDiv.innerHTML = "<p>No books found.</p>";
		resultDiv.appendChild(booksDiv);
		return;
	}

	const headers = [
		"ID",
		"Title",
		"ISBN",
		"Genre",
		"Publication Date",
		"Authors",
	];
	const rows = books.map((book) => ({
		id: book.id,
		title: book.title,
		isbn: book.isbn,
		genre: book.genre,
		publicationDate: book.publicationDate,
		authors: book.authors
			.map((author) => `${author.firstName} ${author.lastName}`)
			.join(", "),
	}));

	const table = createTable(headers, rows, (book) => {
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
};

const titleField = {
	id: "titleInput",
	label: "Title:",
	type: "text",
	placeholder: "Title",
	required: true,
	fieldKey: "title",
};

const genreField = {
	id: "genreTypeSelect",
	label: "Genre:",
	type: "select",
	placeholder: "",
	required: true,
	fieldKey: "genre",
	options: genres,
};

const publicationYearField = {
	id: "publicationDateInput",
	label: "Publication Year:",
	type: "number",
	placeholder: "Publication Year",
	required: true,
	fieldKey: "publicationYear",
};

addEventListenerToButton(
	"getAllBooksBtn",
	[],
	booksRequestMapping,
	"",
	searchAction,
	displayBooks,
	true,
	null
);

addEventListenerToButton(
	"findByTitleButton",
	[titleField],
	booksRequestMapping,
	"title",
	searchAction,
	displayBooks,
	true,
	null
);

addEventListenerToButton(
	"findByGenreButton",
	[genreField],
	booksRequestMapping,
	"genre",
	searchAction,
	displayBooks,
	true,
	null
);

addEventListenerToButton(
	"findByPublicationYearButton",
	[publicationYearField],
	booksRequestMapping,
	"publicationYear",
	searchAction,
	displayBooks,
	true,
	null
);
