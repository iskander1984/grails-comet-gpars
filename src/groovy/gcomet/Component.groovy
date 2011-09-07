package gcomet

import groovyx.gpars.actor.DefaultActor

class Component extends DefaultActor {

	private ComponentClient[] clients = []	

	void act() {
		loop {
			react {message ->
				switch (message) {
					case RegisterMessage:
						clients << message.client
						break
					case UnregisterMessage:
						clients.remove(message.client)
						break
					case UpdateStateMessage:
						clients.each {it << message}
						break
				}
			}
		}
	}
	
	def register(ComponentClient client) {
		this << new RegisterMessage(client)
	}
	
	def unregister(ComponentClient client) {
		this << new UnregisterMessage(client)
	}
	
	def update(ComponentState state) {
		this << new UpdateStateMessage(state.clone())
	}
}
