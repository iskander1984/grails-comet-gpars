package grails.comet.gpars

import chat.ChatChannel
import chat.ChatClient

class ChatController {
	static ChatChannel chatChannel = new ChatChannel();
    def index() { }
	
	def subscribe = {
		def chatClient = new ChatClient().subscribeTo(chatChannel)
		render 'You have been subscribed'
	}
		
}
