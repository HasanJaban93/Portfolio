import { addEventListenerToButton } from "../general/events.js";
import { searchAction, copiesRequestMapping } from "../general/constants.js";
import { displayCopies } from "./findCopies.js";

const bookIdField = {
	id: "bookIdInput",
	label: "Book ID:",
	type: "number",
	placeholder: "Book ID",
	required: true,
	fieldKey: "bookId",
};

const availabilityField = {
	id: "availabilityTypeSelect",
	label: "Availability:",
	type: "select",
	placeholder: "",
	required: true,
	fieldKey: "availability",
	options: [true, false],
};

addEventListenerToButton(
	"findCopyByBookIdButton",
	[bookIdField],
	copiesRequestMapping,
	"bookId",
	searchAction,
	displayCopies,
	true,
	null
);

addEventListenerToButton(
	"findByAvailabilityAndBookIdButton",
	[bookIdField, availabilityField],
	copiesRequestMapping,
	"bookIdAndAvailability",
	searchAction,
	displayCopies,
	false,
	null
);
