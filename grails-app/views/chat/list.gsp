<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="chat.label"/></title>
		
	</head>
	<body>
		Channels:
		<g:each in="${channels}">
     		<p>Channel Name: ${it}</p>
		</g:each>
		<br/>
		Create another chat:
		<g:form name="chatRegistration" url="[controller:'chat',action:'create']">	
			<g:textField name="chatName" />
			<g:actionSubmit value="Create Chat" />
		</g:form>
	</body>
</html>
