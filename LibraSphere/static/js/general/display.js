import { updateAction, deleteAction } from "./constants.js";
const resultDiv = document.getElementById("result");

const entityMapping = {
	books: "Book",
	authors: "Author",
	copies: "Copy",
	members: "Member",
};

const displayMessage = (message, action, isError = false) => {
	let messageParagraph = getMessageParagraph();
	setMessageProperties(messageParagraph, message, isError);

	switch (action) {
		case updateAction:
			handleUpdateActionMessage(messageParagraph);
			break;
		case deleteAction:
			handleDeleteActionMessage(messageParagraph);
			break;
		default:
			resultDiv.appendChild(messageParagraph);
			break;
	}

	clearMessageAfterTimeout(messageParagraph, 10000);
};

const getMessageParagraph = () => {
	let messageParagraph = document.getElementById("messageParagraph");
	if (!messageParagraph) {
		messageParagraph = document.createElement("p");
		messageParagraph.id = "messageParagraph";
	}
	return messageParagraph;
};

const setMessageProperties = (element, message, isError) => {
	element.innerText = message;
	element.style.color = isError ? "red" : "green";
};

const handleUpdateActionMessage = (messageParagraph) => {
	const updateInputsDiv = document.getElementById("updateInputs");
	if (updateInputsDiv) {
		updateInputsDiv.appendChild(messageParagraph);
	} else {
		resultDiv.appendChild(messageParagraph);
	}
};

const handleDeleteActionMessage = (messageParagraph) => {
	setTimeout(() => {
		const deleteButton = document.getElementById("deleteButton");
		if (deleteButton) {
			deleteButton.insertAdjacentElement("afterend", messageParagraph);
		} else {
			resultDiv.appendChild(messageParagraph);
		}
	}, 0);
};

const clearMessageAfterTimeout = (element, timeout) => {
	setTimeout(() => {
		element.innerText = "";
	}, timeout);
};

const handleFetchResponse = async (
	response,
	requestMapping,
	action,
	id,
	actionDescription
) => {
	if (!response.ok) {
		const errorData = await response.json().catch(() => ({}));
		const errorMessage = errorData.message || "Network response was not ok";
		displayMessage(`${errorMessage}`, action, true);
		throw new Error(errorMessage);
	}

	switch (action) {
		case "search":
			return response.json();
		case "create":
			return handleCreateResponse(response, requestMapping);
		case "update":
			return handleUpdateResponse(
				response,
				requestMapping,
				actionDescription,
				id
			);
		case "delete":
			displayMessage(
				`Successfully deleted ${entityMapping[requestMapping]} with ID: ${id}`,
				action
			);
			break;
		default:
			displayMessage(`Action ${action} completed successfully.`);
	}
};

const handleCreateResponse = async (response, requestMapping) => {
	const createData = await response.json();
	displayMessage(
		`Successfully created ${entityMapping[requestMapping]} with ID: ${createData}`
	);
	return createData;
};

const handleUpdateResponse = async (
	response,
	requestMapping,
	actionDescription,
	id
) => {
	switch (actionDescription) {
		case "addAward":
			displayMessage(
				`Successfully added Award to Author with ID: ${id}`,
				updateAction
			);
			break;
		case "removeAward":
			displayMessage(
				`Successfully removed Award from Author with ID: ${id}`,
				updateAction
			);
			break;
		case "addAuthor":
			displayMessage(
				`Successfully added Author to book with ID: ${id}`,
				updateAction
			);
			break;
		case "removeAuthor":
			displayMessage(
				`Successfully removed Author from book with ID: ${id}`,
				updateAction
			);
			break;
		case "addDigitalCopy":
			displayMessage(
				`Successfully added Digital Copy to book with ID: ${id}`,
				updateAction
			);
			break;
		case "addPhysicalCopy":
			displayMessage(
				`Successfully added Physical Copy to book with ID: ${id}`,
				updateAction
			);
			break;
		case "removeCopy":
			displayMessage(
				`Successfully removed Copy from book with ID: ${id}`,
				updateAction
			);
			break;
		case "borrowCopy":
			return response.json();
		case "returnBorrowedCopy":
			displayMessage(
				`Successfully returned borrowed copy with key: ${id}`,
				updateAction
			);
			break;
		default:
			displayMessage(
				`Successfully updated ${actionDescription} for ${entityMapping[requestMapping]} with ID: ${id}`,
				updateAction
			);
	}
};

const handleError = (error, action) => {
	console.error("Error:", error);
	displayMessage(error.message, action, true);
};

export { displayMessage, handleFetchResponse, handleError };
