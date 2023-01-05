var projectId, projectName;

function removeProjectDialog(el) {
	projectId = $(el).attr('data-project-id');
	projectName = $(el).attr('data-project-name');
	$('.remove-project-modal').find('#project-name').text(projectName);
}

function removeProject() {
	$('.remove-project-modal').modal('hide');
	window.location = "/project/remove/" + projectId;
}