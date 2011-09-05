package grails.comet.gpars

import chat.ChatChannel
import chat.ChatClient
import chat.GetNextMessage
import grails.converters.*

class ChatController {
	static allowedMethods = [create:'POST']
		
    def index() { redirect(action: "list", params: params)}
	
	def list() {
		render (view: "list", model: [channels: servletContext['channels'].keySet])
	}
	
	def subscribe = {
		def chatName = params.chatName
		if (!session.chatClient[chatName]){
			 session.chatClient[chatName] = new ChatClient()
			 session.chatClient[chatName].start()
			 session.chatClient[chatName].subscribeTo(servletContext['channels'][chatName])
		}
		
		def message = session.chatClient.getLastMessages()
		
		render(message)
	}
	
	def create = {
		def chatName = params.chatName
		def chatChannel = new ChatChannel(name: chatName);
		chatChannel.start()
		servletContext['channels'][chatName] = chatChannel
		
		redirect(action: "list", params: params)
	}
		
	def sendMessage = {
		chatChannel << params.chatMessage
		render ' ' 
	}

}
