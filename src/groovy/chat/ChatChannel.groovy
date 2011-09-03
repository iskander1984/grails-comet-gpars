import groovyx.gpars.actor.DefaultActor

class ChatChannel extends DefaultActor {    
    def clients = []
    
    void act() {        
        loop {
            react {message ->
                println 'Got message: ' + message
                switch (message) {
                    case SubscribeMessage:
                        clients << message.client                        
                        break
                    case UnsubscribeMessage:
                        clients.remove(message.client)
                        break
                    default: clients.each {it << message}
                }   
                println 'Subscribers: ' + clients                     
            }
        }
    }
}