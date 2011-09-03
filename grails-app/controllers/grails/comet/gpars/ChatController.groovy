package grails.comet.gpars

import chat.ChatChannel
import chat.ChatClient
import grails.converters.*

class ChatController {
	static ChatChannel chatChannel = new ChatChannel();
    def index() { }
	
	def subscribe = {
		def chatClient = new ChatClient(out:response).subscribeTo(chatChannel)
		render 'You have been subscribed'
	}
		
	def sendMessage = {
		chatChannel << params.chatMessage
		println params.chatMessage
		render ' ' 
	}
}
