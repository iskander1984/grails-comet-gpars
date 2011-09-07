package gcomet

import groovyx.gpars.actor.DefaultActor

class ComponentClient extends DefaultActor {

	private states = new LinkedBlockingQueue<ComponentState>()
	
	void act() {
		loop {
			react {message ->
				states.put(message)
			}
		}
	}

	def getStates(timeout) {
		def result = new ArrayList<String>()
		if (states.size() > 0) {
			while (states.size() > 0) {
				result << states.take()
			}
		} else {
			def message = states.poll(timeout, TimeUnit.MILLISECONDS)
			if (message != null) {
				result << message
			}
		}
		return result
	}
}
