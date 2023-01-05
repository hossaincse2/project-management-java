$(document).ready(function() {
	$('#gotoListBtn').on('click', function() {
		window.location = "/project/list";
	});
	$(".js-example-tokenizer").select2({
		tags: true,
		maximumSelectionLength: 5,
		tokenSeparators: [',', ' ']
	})
});