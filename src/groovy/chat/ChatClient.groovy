package chat
import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.DefaultActor

class ChatClient extends DefaultActor {
    def String login

    void act() {        
        loop {
			react {message ->
				println '' + login + ': ' + message
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