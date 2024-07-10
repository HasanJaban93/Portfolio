import { addEventListenerToButton } from "../general/events.js";
import { searchByField } from "../general/api.js";
import { searchAction, copiesRequestMapping } from "../general/constants.js";
import { displayBorrowers } from "./findBorrowers.js";
import {
	physicalCopyFieldsConfig,
	digitalCopyfieldsConfig,
} from "./copyUpdates.js";
import { addUpdateEventListeners } from "../general/eventUtils.js";

const resultDiv = document.getElementById("result");

const barcodeField = {
	id: "barcodeInput",
	label: "Barcode:",
	type: "text",
	placeholder: "Barcode",
	required: true,
	fieldKey: "barcode",
};

const fileHashField = {
	id: "fileHashInput",
	label: "File Hash:",
	type: "text",
	placeholder: "File Hash",
	required: true,
	fieldKey: "fileHash",
};

addEventListenerToButton(
	"findByBarcodeButton",
	[barcodeField],
	copiesRequestMapping,
	"barcode",
	searchAction,
	displayPhysicalCopy,
	true,
	null
);

addEventListenerToButton(
	"findByFileHashButton",
	[fileHashField],
	copiesRequestMapping,
	"fileHash",
	searchAction,
	displayDigitalCopy,
	true,
	null
);
export function displayPhysicalCopy(copy) {
	resultDiv.innerHTML = "";
	const physicalCopyDetails = document.createElement("div");
	physicalCopyDetails.innerHTML = `
		<h2>Physical Copy Details</h2>
		<div>
			<h3>Title:</h3>
			<p>${copy.title}</p>
		</div>
		<div>
			<h3>Available:</h3>
			<p>${copy.available ? "Yes" : "No"}</p>
		</div>
		<div>
			<h3>Copy ID:</h3>
			<p>${copy.id}</p>
		</div>
		<div>
			<h3>Barcode:</h3>
			<p>${copy.barcode}</p>
			<button id="updateBarcodeButton">Update Barcode</button>
		</div>
		<div>
			<h3>Location:</h3>
			<p>${copy.location}</p>
			<button id="updateLocationButton">Update Location</button>
		</div>
		<div>
			<h3>Shelf Number:</h3>
			<p>${copy.shelfNumber}</p>
			<button id="updateShelfNumberButton">Update Shelf Number</button>
		</div>
		<br/>
		<button id="findBorrowersButton">Get Borrowers</button>
	`;
	resultDiv.appendChild(physicalCopyDetails);

	addCopyDetailsEventListeners(copy.id);
	addUpdateEventListeners(
		copy.id,
		copiesRequestMapping,
		physicalCopyFieldsConfig
	);
}

export function displayDigitalCopy(copy) {
	resultDiv.innerHTML = "";
	const digitalCopyDetails = document.createElement("div");
	digitalCopyDetails.innerHTML = `
		<h2>Digital Copy Details</h2>
		<div>
			<h3>Title:</h3>
			<p>${copy.title}</p>
		</div>
		<div>
			<h3>Available:</h3>
			<p>${copy.available ? "Yes" : "No"}</p>
		</div>
		<div>
			<h3>Copy ID:</h3>
			<p>${copy.id}</p>
		</div>
		<div>
			<h3>File Hash:</h3>
			<p>${copy.fileHash}</p>
			<button id="updateFileHashButton">Update File Hash</button>
		</div>
		<div>
			<h3>File Format:</h3>
			<p>${copy.fileFormat}</p>
			<button id="updateFileFormatButton">Update File Format</button>
		</div>
		<div>
			<h3>Visit Link:</h3>
			<p><a href="${copy.visitLink}" target="_blank">${copy.visitLink}</a></p>
			<button id="updateVisitLinkButton">Update Visit Link</button>
		</div>
		<br/>
		<button id="findBorrowersButton">Get Borrowers</button>
	`;
	resultDiv.appendChild(digitalCopyDetails);

	addCopyDetailsEventListeners(copy.id);
	addUpdateEventListeners(
		copy.id,
		copiesRequestMapping,
		digitalCopyfieldsConfig
	);
}

function addCopyDetailsEventListeners(copyId) {
	document
		.getElementById("findBorrowersButton")
		.addEventListener("click", () => {
			searchByField(
				"copies/borrowersOf",
				"id",
				copyId,
				searchAction,
				displayBorrowers
			);
		});
}
