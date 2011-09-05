package chat

import javax.servlet.http.HttpServletResponse
import groovyx.gpars.actor.DefaultActor
import groovyx.gpars.actor.Actor
import chat.GetNextMessage
import chat.PutMessage

class ChatClient extends DefaultActor {
    String login
	def messages = new ArrayList()
    void act() {        
        loop {
			react {message ->
				messages << message
			}
        }
    }  
	
	def getLastMessages(){
		messages
	}

	def subscribeTo(Actor channel) {
		channel << new SubscribeMessage(client: this)
	}
	
	def unsubscribeFrom(Actor channel) {
		channel << new UnsubscribeMessage(client: this)
	}
	
}