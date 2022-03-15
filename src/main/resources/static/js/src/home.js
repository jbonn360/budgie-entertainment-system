function toggleMasterSwitch(buttonId) {
	var button = $('#' + buttonId);
	
	if(button.text() === "START"){
		postMasterSwitchState(true);
		button.css('background-color', 'red');
		button.text('STOP');
	} else {
		postMasterSwitchState(false);
		button.removeAttr('style');
		button.text('START');
	}
}

function postMasterSwitchState(state){
	$.post('setAudioPlayerState', { state });
}

function postVolumeChange(value){
	console.log(value);
	
	var newVolume = value / 100;
	
	console.log(newVolume);
	
	$.post('changeVolume', { 'volume' : newVolume });
}