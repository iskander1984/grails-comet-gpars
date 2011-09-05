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
					$.each(data, function(){
						$("#chatWindow").val($("#chatWindow").val() + "\n" + this);
					});
					
					longPoolRequest();
				}
			});
		}
		$(document).ready(function() {
			setTimeout('longPoolRequest()', 1000);
		});
		
		function clearChatMessageBox(){
			$("#chatMessage").val("");
		}
		
	</script>
	</head>
	<body>
		<g:textArea id="chatWindow" name="chatWindow" rows="2" cols="20" value="Messages should be here..." readonly="readonly"/>
		<br/>
		<g:formRemote name="chatForm" url="[controller:'chat',action:'sendMessage']" onSuccess="clearChatMessageBox()" before="if (!\$('#chatMessage').val()) {return false;}">
			<g:hiddenField name="chatName" value="${chatName}" />
			<input type="text" name="chatMessage" id="chatMessage" />&nbsp;
			<input type="submit" value="Send" />
		</g:formRemote >
	</body>
</html>
