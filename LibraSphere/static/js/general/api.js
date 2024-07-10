import { updateAction } from "./constants.js";
import { handleFetchResponse, handleError } from "./display.js";
import { formatBodyContent } from "./utils.js";

export const createEntity = (requestMapping, action, value) => {
	fetch(`http://localhost:8080/${requestMapping}`, {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(value),
	})
		.then((response) =>
			handleFetchResponse(response, requestMapping, action, null)
		)
		.catch(handleError);
};

export const updateField = (
	id,
	requestMapping,
	field,
	value,
	action,
	actionDescription,
	displayResultsFunction
) => {
	const bodyContent = formatBodyContent(field, value);
	fetch(`http://localhost:8080/${requestMapping}/${id}/${field}`, {
		method: "PATCH",
		headers: {
			"Content-Type": "application/json",
		},
		body: bodyContent,
	})
		.then((response) =>
			handleFetchResponse(
				response,
				requestMapping,
				action,
				id,
				actionDescription
			)
		)
		.then(displayResultsFunction)
		.catch((error) => handleError(error, updateAction, true));
};

export const deleteField = (requestMapping, action, id) => {
	fetch(`http://localhost:8080/${requestMapping}/${id}`, {
		method: "DELETE",
	})
		.then((response) =>
			handleFetchResponse(response, requestMapping, action, id)
		)
		.catch(handleError);
};

export const searchByField = (
	requestMapping,
	field,
	value,
	action,
	displayResultsFunction
) => {
	const baseUrl = `http://localhost:8080/${requestMapping}`;
	let endpoint = baseUrl;

	switch (field) {
		case "":
			endpoint = baseUrl;
			break;
		case "id":
			endpoint = `${baseUrl}/${value}`;
			break;
		case "bookIdAndAvailability":
			endpoint = `${baseUrl}?bookId=${value.bookId}&available=${value.availability}`;
			break;
		default:
			endpoint = `${baseUrl}?${field}=${encodeURIComponent(value)}`;
			break;
	}
	console.log(endpoint);
	fetch(endpoint)
		.then((response) =>
			handleFetchResponse(response, requestMapping, action, null)
		)
		.then(displayResultsFunction)
		.catch(handleError);
};
