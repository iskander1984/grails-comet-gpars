package grails.comet.gpars

import chat.ChatChannel
import chat.ChatClient
import chat.GetNextMessage
import grails.converters.*

class ChatController {
	static ChatChannel chatChannel = new ChatChannel();
	static {
		chatChannel.start()
	}
    def index() { }
	
	def subscribe = {
		if (!session.chatClient){
			 session.chatClient = new ChatClient()
			 session.chatClient.start()
			 session.chatClient.subscribeTo(chatChannel)
		}
		
		def message = session.chatClient.getLastMessages(30000)
		
		render(message)
	}
		
	def sendMessage = {
		chatChannel << params.chatMessage
		render ' ' 
	}
}
