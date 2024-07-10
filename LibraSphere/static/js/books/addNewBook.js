import { addEventListenerToButton } from "../general/events.js";
import {
	genres,
	createAction,
	booksRequestMapping,
} from "../general/constants.js";

const titleField = {
	id: "titleInput",
	label: "Title:",
	type: "text",
	placeholder: "Title",
	required: true,
	fieldKey: "title",
};

const isbnField = {
	id: "isbnInput",
	label: "ISBN:",
	type: "text",
	placeholder: "ISBN",
	required: true,
	fieldKey: "isbn",
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

const publicationDateField = {
	id: "publicationDateInput",
	label: "Publication Date:",
	type: "date",
	required: true,
	fieldKey: "publicationDate",
};

addEventListenerToButton(
	"addNewBookButton",
	[titleField, isbnField, genreField, publicationDateField],
	booksRequestMapping,
	null,
	createAction,
	null,
	false,
	null
);
