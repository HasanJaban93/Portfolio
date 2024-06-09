import { setError, setSuccess } from "./inputValidator.js";
import { fetchTasks } from "./taskFetcher.js";

export function findTasks() {
	const searchTypeValue = searchType.value;

	let query = "";
	switch (searchTypeValue) {
		case "all":
			query = `/all`;
			break;
		case "word":
			const searchWord = document.getElementById("searchWord");
			if (!searchWord.value) {
				setError(searchWord, "A word is required.");
			} else {
				setSuccess(searchWord);
				query = `?description=${searchWord.value}`;
			}
			break;
		case "date":
			const searchDate = document.getElementById("searchDate");
			if (!searchDate.value) {
				setError(searchDate, "A date is required.");
			} else {
				setSuccess(searchDate);
				query = `?date=${searchDate.value}`;
			}
			break;
		case "period":
			const startDate = document.getElementById("startDate");
			const endDate = document.getElementById("endDate");
			if (!startDate.value) {
				setError(startDate, "A start date is required.");
			} else {
				setSuccess(startDate);
			}
			if (!endDate.value) {
				setError(endDate, "An end date is required.");
			} else {
				setSuccess(endDate);
			}
			if (startDate.value && endDate.value) {
				query = `?startDate=${startDate.value}&endDate=${endDate.value}`;
			}
			break;
		case "state":
			query = `?state=${document.getElementById("searchState").value}`;
			break;
	}
	if (query !== "") {
		fetchTasks(query);
	}
}
