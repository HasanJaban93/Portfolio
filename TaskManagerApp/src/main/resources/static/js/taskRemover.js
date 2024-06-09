export function removeTask(id) {
	fetch(`http://localhost:8080/tasks/${id}`, {
		method: "DELETE",
	})
		.then((response) => {
			if (!response.ok) {
				throw new Error(`Network response was not ok: ${response.statusText}`);
			}
			return response;
		})
		.then(() => {
			// Remove the table row corresponding to the deleted task
			const row = document.querySelector(`tr[data-task-id='${id}']`);
			if (row) {
				row.remove();
			}
		});
}
