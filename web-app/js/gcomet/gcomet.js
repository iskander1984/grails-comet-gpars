function GCometComponent(id, updateHandler){
	this.id = id;
	this.updateHandler = updateHandler;
	this.version = 0;
	
	function update(version, changeSet){
		this.version = version;
		this.updateHandler(changeSet);
	}
}

var components = new Array()

function longPoolRequest(){
	$.ajax({
		url: "retrieveChangeSet",
		success: function(data){
			updateComponents(data);
			longPoolRequest();
		}
	});
}

function updateComponents(data){
	var changes = $.parseJSON(data);
	$.each(changes, function(index, value) {
		var currentComponentId = value;
		var currentVersion = value.version;
		var changeSet = value.data;
		components[currentComponentId].update(currentVersion, changeSet);
	}
	);
}

function registerGCometComponents(){
	var components = $("span[type='gcomet']");
	$.each(components, function(index, value){
		var id = value.attr("id");
		var updateHandler = value.attr("updateHandler");
		registerComponent(id, updateHandler);
	});
}

function registerGCometComponent(id, updateHandler){
	var component = new GCometComponent(id, updateHandler);
	components.push(component);
}

$(document).ready(function() {
	registerGCometComponents();
	setTimeout('longPoolRequest()', 1000);
});