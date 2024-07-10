import { addEventListenerToButton } from "../general/events.js";
import { searchByField } from "../general/api.js";
import { searchAction, copiesRequestMapping } from "../general/constants.js";
import { displayPhysicalCopy, displayDigitalCopy } from "./findByIds.js";
import { createTable } from "../general/utils.js";

const resultDiv = document.getElementById("result");

addEventListenerToButton(
	"loadCopiesButton",
	[],
	copiesRequestMapping,
	"",
	searchAction,
	displayCopies,
	true,
	null
);

addEventListenerToButton(
	"loadDigitalCopiesButton",
	[],
	"copies/digital",
	"",
	searchAction,
	displayDigitalCopies,
	true,
	null
);

addEventListenerToButton(
	"loadPhysicalCopiesButton",
	[],
	"copies/physical",
	"",
	searchAction,
	displayPhysicalCopies,
	true,
	null
);

export function displayCopies(copies) {
	const digitalCopies = [];
	const physicalCopies = [];
	copies.forEach((copy) =>
		copy.fileHash ? digitalCopies.push(copy) : physicalCopies.push(copy)
	);
	displayDigitalCopies(digitalCopies);
	displayPhysicalCopies(physicalCopies);
}

function displayDigitalCopies(copies) {
	const digitalCopiesDiv = document.createElement("div");
	const h2 = document.createElement("h2");
	h2.innerText = "Digital Copies";
	digitalCopiesDiv.appendChild(h2);

	if (!copies || copies.length === 0) {
		const p = document.createElement("p");
		p.innerText = "No Digital Copies found.";
		digitalCopiesDiv.appendChild(p);
		resultDiv.appendChild(digitalCopiesDiv);
		return;
	}

	const headers = ["ID", "Available", "File Hash", "File Format", "Visit Link"];
	const table = createTable(headers, copies, (copy) => {
		searchByField(
			copiesRequestMapping,
			"fileHash",
			copy.fileHash,
			searchAction,
			displayDigitalCopy
		);
	});
	table.id = "digitalCopiesTable";
	digitalCopiesDiv.appendChild(table);
	resultDiv.appendChild(digitalCopiesDiv);
}

function displayPhysicalCopies(copies) {
	const physicalCopiesDiv = document.createElement("div");
	const h2 = document.createElement("h2");
	h2.innerText = "Physical Copies";
	physicalCopiesDiv.appendChild(h2);

	if (copies.length === 0) {
		const p = document.createElement("p");
		p.innerText = "No Physical Copies found.";
		physicalCopiesDiv.appendChild(p);
		resultDiv.appendChild(physicalCopiesDiv);
		return;
	}

	const headers = ["ID", "Available", "Barcode", "Location", "Shelf Number"];
	const table = createTable(headers, copies, (copy) => {
		searchByField(
			copiesRequestMapping,
			"barcode",
			copy.barcode,
			searchAction,
			displayPhysicalCopy
		);
	});
	table.id = "physicalCopiesTable";
	physicalCopiesDiv.appendChild(table);
	resultDiv.appendChild(physicalCopiesDiv);
}
