package grails.comet.gpars

import gpars.Component
import gpars.ComponentClient
import grails.converters.*

class ChatController {
	static allowedMethods = [create:'POST']
		
	Component chat
	
	def subscribe = {
		if (!session.chatClient){
			 session.chatClient = new ComponentClient()
			 session.chatClient.start()
			 chat.register(session.chatClient)
		}
		render ''
	}
		
	def sendMessage = {
		def state = new ComponentState()
		state.login = session.id.substring(0, 4)
		state.message = params.chatMessage
		chat.update(state)
		render ' '
	}
	
	def pullMessages = {
		def states = session.chatClient.getStates(30000)
		render states as JSON
	}
	
//    def index() { redirect(action: "list", params: params)}
	
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
	
//	def chat = {
//		render(view: "chat", model: [chatName: params.chatName])
//	}
	
//	def pullMessages = {
//		def messages = session.chatClient[params.chatName].getLastMessages(30000)
//		render messages as JSON
//	}
}
