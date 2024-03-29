<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<title><g:message code="chat.label" />
</title>
<gcomet:includes/>
<script type="text/javascript">
	function updateChat(data){
		var state = $.parseJSON(data);
		$("#chatWindow").val($("#chatWindow").val() + '\n' + state.login + ' - ' + state.message);
	}
	function clearChatMessageBox(){
		$("#chatMessage").val("");
	}
</script>
</head>
<body>
	<gcomet:component id="chat" updateHandler="updateChat">
		<g:textArea id="chatWindow" name="chatWindow" rows="2" cols="20"
			value="Messages should be here..." readonly="readonly" />
	</gcomet:component>
	<br />
	<g:formRemote name="chatForm"
		url="[controller:'chat',action:'sendMessage']"
		onSuccess="clearChatMessageBox()"
		before="if (!\$('#chatMessage').val()) {return false;}">
		<input type="text" name="chatMessage" id="chatMessage" />&nbsp;
			<input type="submit" value="Send" />
	</g:formRemote>
</body>
</html>
