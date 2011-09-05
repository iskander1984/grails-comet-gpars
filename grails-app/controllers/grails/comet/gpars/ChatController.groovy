package grails.comet.gpars

import chat.ChatChannel
import chat.ChatClient
import chat.GetNextMessage
import grails.converters.*

class ChatController {
	static allowedMethods = [create:'POST']
		
    def index() { redirect(action: "list", params: params)}
	
	def list() {
		render (view: "list", model: [channels: servletContext['channels'].keySet()])
	}
	
	def subscribe = {
		def chatName = params.chatName
		if (!session.chatClient){
			session.chatClient = [:]
		}
		if (!session.chatClient[chatName]){
			 session.chatClient[chatName] = new ChatClient()
			 session.chatClient[chatName].start()
			 session.chatClient[chatName].subscribeTo(servletContext['channels'][chatName])
		}
		render ''
	}
	
	def create = {
		def chatName = params.chatName
		def chatChannel = new ChatChannel(name: chatName);
		chatChannel.start()
		servletContext['channels'][chatName] = chatChannel
		
		render (action: "list", view: "list")
	}
		
	def sendMessage = {
		servletContext['channels'][params.chatName] << params.chatMessage
		render ' ' 
	}
	
	def chat = {
		
		render(view: "chat", model: [chatName: params.chatName])
	}
	
	def pullMessages = {
		def messages = session.chatClient[params.chatName].getLastMessages(30000)
		render messages as JSON
	}

}
