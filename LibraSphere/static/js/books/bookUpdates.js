import { genres } from "../general/constants.js";
import { createFieldConfig } from "../general/utils.js";

const fields = {
	title: createFieldConfig(
		"newTitleInput",
		"New Title:",
		"text",
		"New Title",
		true,
		"title"
	),
	isbn: createFieldConfig(
		"newIsbnInput",
		"New ISBN:",
		"text",
		"New ISBN",
		true,
		"isbn"
	),
	genre: createFieldConfig(
		"newGenreTypeSelect",
		"Genre:",
		"select",
		"",
		true,
		"genre",
		genres
	),
	publicationDate: createFieldConfig(
		"newPublicationDateInput",
		"New Publication Date:",
		"date",
		"",
		true,
		"publicationDate"
	),
	authorId: createFieldConfig(
		"authorIdInput",
		"Author ID:",
		"number",
		"Author ID",
		true,
		"authorId"
	),
	barcode: createFieldConfig(
		"barcodeInput",
		"Barcode:",
		"text",
		"Barcode",
		true,
		"barcode"
	),
	location: createFieldConfig(
		"locationInput",
		"Location:",
		"text",
		"Location",
		true,
		"location"
	),
	shelfNumber: createFieldConfig(
		"shelfNumberInput",
		"Shelf Number:",
		"number",
		"Shelf Number",
		true,
		"shelfNumber"
	),
	fileHash: createFieldConfig(
		"fileHashInput",
		"File Hash:",
		"text",
		"File Hash",
		true,
		"fileHash"
	),
	fileFormat: createFieldConfig(
		"fileFormatInput",
		"File Format:",
		"text",
		"File Format",
		true,
		"fileFormat"
	),
	visitLink: createFieldConfig(
		"visitLinkInput",
		"Visit Link:",
		"text",
		"Visit Link",
		true,
		"visitLink"
	),
	copyId: createFieldConfig(
		"copyIdInput",
		"Copy ID:",
		"number",
		"Copy ID",
		true,
		"copyId"
	),
};

export const fieldsConfig = [
	{
		buttonId: "updateTitleButton",
		fields: [fields.title],
		fieldKey: "title",
		displayName: "Title",
	},
	{
		buttonId: "updateIsbnButton",
		fields: [fields.isbn],
		fieldKey: "isbn",
		displayName: "ISBN",
	},
	{
		buttonId: "updateGenreButton",
		fields: [fields.genre],
		fieldKey: "genre",
		displayName: "Genre",
	},
	{
		buttonId: "updatePublicationDateButton",
		fields: [fields.publicationDate],
		fieldKey: "publicationDate",
		displayName: "Publication Date",
	},
	{
		buttonId: "addExistingAuthorButton",
		fields: [fields.authorId],
		fieldKey: "addAuthor",
		displayName: "addAuthor",
	},
	{
		buttonId: "removeAuthorButton",
		fields: [fields.authorId],
		fieldKey: "removeAuthor",
		displayName: "removeAuthor",
	},
	{
		buttonId: "addDigitalCopyButton",
		fields: [fields.fileHash, fields.fileFormat, fields.visitLink],
		fieldKey: "addDigitalCopy",
		displayName: "addDigitalCopy",
	},
	{
		buttonId: "addPhysicalCopyButton",
		fields: [fields.barcode, fields.location, fields.shelfNumber],
		fieldKey: "addPhysicalCopy",
		displayName: "addPhysicalCopy",
	},
	{
		buttonId: "removeCopyButton",
		fields: [fields.copyId],
		fieldKey: "removeCopy",
		displayName: "removeCopy",
	},
];
