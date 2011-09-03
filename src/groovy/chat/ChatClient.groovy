package chat
import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.DefaultActor

class ChatClient extends DefaultActor {
    def String login
	def out

    void act() {        
        loop {
			react {message ->
				out << '' + login + ': ' + message
			}
        }
    }  

	def subscribeTo(Actor channel) {
		channel << new SubscribeMessage(this)
	}
	
	def unsubscribeFrom(Actor channel) {
		channel << new UnsubscribeMessage(this)
	}
}