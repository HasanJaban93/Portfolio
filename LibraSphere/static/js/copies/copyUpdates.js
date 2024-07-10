import { createFieldConfig } from "../general/utils.js";

const fields = {
	barcode: createFieldConfig(
		"newBarcodeInput",
		"New Barcode:",
		"text",
		"New Barcode",
		true,
		"barcode"
	),
	location: createFieldConfig(
		"newLocationInput",
		"New Location:",
		"text",
		"New Location",
		true,
		"location"
	),
	shelfNumber: createFieldConfig(
		"newShelfNumberInput",
		"New Shelf Number:",
		"number",
		"New Shelf Number",
		true,
		"shelfNumber"
	),
	fileHash: createFieldConfig(
		"newFileHashInput",
		"New File Hash:",
		"text",
		"New File Hash",
		true,
		"fileHash"
	),
	fileFormat: createFieldConfig(
		"newFileFormatInput",
		"New File Format:",
		"text",
		"New File Format",
		true,
		"fileFormat"
	),
	visitLink: createFieldConfig(
		"newVisitLinkInput",
		"New Visit Link:",
		"text",
		"New Visit Link",
		true,
		"visitLink"
	),
};

export const physicalCopyFieldsConfig = [
	{
		buttonId: "updateBarcodeButton",
		fields: [fields.barcode],
		fieldKey: "barcode",
		displayName: "Barcode",
	},
	{
		buttonId: "updateLocationButton",
		fields: [fields.location],
		fieldKey: "location",
		displayName: "Location",
	},
	{
		buttonId: "updateShelfNumberButton",
		fields: [fields.shelfNumber],
		fieldKey: "shelfNumber",
		displayName: "Shelf Number",
	},
];

export const digitalCopyfieldsConfig = [
	{
		buttonId: "updateFileHashButton",
		fields: [fields.fileHash],
		fieldKey: "fileHash",
		displayName: "FileHash",
	},
	{
		buttonId: "updateFileFormatButton",
		fields: [fields.fileFormat],
		fieldKey: "fileFormat",
		displayName: "FileFormat",
	},
	{
		buttonId: "updateVisitLinkButton",
		fields: [fields.visitLink],
		fieldKey: "visitLink",
		displayName: "Visit Link",
	},
];
