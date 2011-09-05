<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="chat.label"/></title>
		<script type="text/javascript" >

		function longPoolRequest(){
			$.ajax({
				url: "subscribe",
				success: function(data){
					$("#chatWindow").val(data);
					longPoolRequest();
				}
			});
		}
		$(document).ready(function() {
			longPoolRequest();
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
			<input type="text" name="chatMessage" id="chatMessage" />&nbsp;
			<input type="submit" value="Send" />
		</g:formRemote >
	</body>
</html>
