function toggleMasterSwitch(buttonId) {
	var button = $('#' + buttonId);
	
	if(button.text() === "START"){
		button.css('background-color', 'red');
		button.text('STOP');
	} else {
		button.removeAttr('style');
		button.text('START');
	}
}