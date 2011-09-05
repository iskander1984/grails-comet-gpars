<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="chat.label"/></title>
		<script type="text/javascript" >

		function longPoolRequest(){
			$.ajax({
				url: "pullMessages?chatName=${chatName}",
				success: function(data){
					$("#chatWindow").val(data);
					longPoolRequest();
				}
			});
		}
		$(document).ready(function() {
			setTimeout('longPoolRequest()', 1000);
		});
		
		function sendSuccess() {
			var chatMessageInput = document.getElementById("chatMessage");
			chatMessageInput.value = "";	
		}
		
	</script>
	</head>
	<body>
		<g:textArea id="chatWindow" name="chatWindow" rows="2" cols="20" value="Messages should be here..." readonly="readonly"/>
		<br/>
		<g:formRemote name="chatForm" url="[controller:'chat',action:'sendMessage']" onSuccess="sendSuccess()">
			<g:hiddenField name="chatName" value="${chatName}" />
			<input type="text" name="chatMessage" id="chatMessage" />&nbsp;
			<input type="submit" value="Send" />
		</g:formRemote >
	</body>
</html>
