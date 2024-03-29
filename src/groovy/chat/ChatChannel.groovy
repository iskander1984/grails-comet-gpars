package chat

import groovyx.gpars.actor.DefaultActor

class ChatChannel extends DefaultActor { 
	def name   
    def clients = []
    
    void act() {        
        loop {
            react {message ->
                switch (message) {
                    case SubscribeMessage:
                        clients << message.client
                        break
                    case UnsubscribeMessage:
                        clients.remove(message.client)
                        break
                    default:
						clients.each {it << message}
                }   
            }
        }
    }
	
	def containsClient(client){
		return clients.contains(client)
	}
}