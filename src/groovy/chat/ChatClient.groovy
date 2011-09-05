package chat

import javax.servlet.http.HttpServletResponse
import groovyx.gpars.actor.DefaultActor
import groovyx.gpars.actor.Actor
import chat.GetNextMessage
import chat.PutMessage
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

class ChatClient {
	def login
	private messages = new LinkedBlockingQueue<String>()
	private client = new ClientActor()	
	
	def start() {
		client.start()	
	}
	
	def shutdown() {
		client.stop()
	}
	
	def subscribeTo(channel) {
		channel << new SubscribeMessage(client: client)
	}
	
	def unsubscribeFrom(channel) {
		channel << new UnsubscribeMessage(client: client)
	}
	
	def getLastMessages(timeout) {		
		def result = new ArrayList<String>()
		if (messages.size() > 0) {		
			while (messages.size() > 0) {
				result << messages.take()
			}
		} else {		
			def message = messages.poll(timeout, TimeUnit.MILLISECONDS)
			if (message != null) {
				result << message
			}
			
		} 
		return result
	}
	
	private class ClientActor extends DefaultActor {	    
	    void act() {        
	        loop {
				react {message ->
					messages.put(message)
				}
	        }
	    }
	}
}