<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="chat.label"/></title>
		
	</head>
	<body>
		Channels:
		<g:each in="${channels}" var="channel">
     		<p>Channel Name: ${channel}&nbsp; 
     		<g:remoteLink action="subscribe" params="[chatName: channel]">Subscribe</g:remoteLink>
     		&nbsp; <a href="<g:createLink action="chat" params="[chatName: channel]"/>">Enter Chat Room</a></p>
		</g:each>
		<br/>
		Create another chat:
		<g:form name="chatRegistration" >	
			<g:textField name="chatName" />
			<g:actionSubmit value="Create Chat" action="create"/>
		</g:form>
	</body>
</html>
