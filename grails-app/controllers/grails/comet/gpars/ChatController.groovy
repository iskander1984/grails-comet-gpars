package grails.comet.gpars

import gcomet.Component
import gcomet.ComponentClient
import gcomet.ComponentState
import grails.converters.*

class ChatController {
	static allowedMethods = [create:'POST']
		
	Component chatComponent
	
	def subscribe = {
		if (!session.chatClient){
			 session.chatClient = new ComponentClient()
			 session.chatClient.start()
			 chatComponent.register(session.chatClient)
		}
		render ' '
	}
		
	def sendMessage = {
		def state = new ComponentState()
		state.login = session.id.substring(0, 4)
		state.message = params.chatMessage
		chatComponent.update(state)
		render ' '
	}
	
	def pullMessages = {
		def states = session.chatClient.getStates(30000)
		render (states as JSON).toString()
	}
	
    def index() { redirect(action: "chat", params: params)}
	
	def chat = {
		if (!chatComponent.isActive()) chatComponent.start()
		render(view: "chat")
	}

	
//	def list() {
//		render (view: "list", model: [channels: servletContext['channels'].keySet()])
//	}
	
//	def subscribe = {
//		def chatName = params.chatName
//		if (!session.chatClient){
//			session.chatClient = [:]
//		}
//
//		if (!session.chatClient[chatName]){
//			 session.chatClient[chatName] = new ChatClient(login: session.id.substring(0, 4))
//			 session.chatClient[chatName].start()
//			 session.chatClient[chatName].subscribeTo(servletContext['channels'][chatName])
//		}
//		render ''
//	}
	
//	def create = {
//		def chatName = params.chatName
//		def chatChannel = new ChatChannel(name: chatName);
//		chatChannel.start()
//		servletContext['channels'][chatName] = chatChannel
//		
//		render (action: "list", view: "list")
//	}
	
	
//	def pullMessages = {
//		def messages = session.chatClient[params.chatName].getLastMessages(30000)
//		render messages as JSON
//	}
}
