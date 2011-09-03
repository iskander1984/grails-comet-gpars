<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="chat.label"/></title>
		<script type="text/javascript" >
		
		$(document).ready(function() {
			var req = new XMLHttpRequest(); 
			req.multipart = true;
			req.open("GET","subscribe", true);
			req.onload = function(event) {
				var result = event.target.responseText
				var chatWindow = document.getElementById("chatWindow");
				chatWindow.innerHTML = result;
			}
			req.send(null);
		});
		
		function sendSuccess() {				
			document.getElementById("chatMessage").value = "";			
		}
		
	</script>
	</head>
	

	<body>
		<g:textArea id="chatWindow" name="chatWindow" rows="2" cols="20" value="Messages should be here..." readonly="readonly"/>
		<br/>
		
		<g:formRemote name="chatForm" url="[controller:'chat',action:'sendMessage']" onSuccess="sendSuccess();">
			<input type="text" name="chatMessage" id="chatMessage" />&nbsp;
			<input type="submit" value="Send" />
		</g:formRemote >
	</body>
</html>
