class BootStrap {

    def init = { servletContext ->
		servletContext['channels'] = [:];
    }
    def destroy = {
    }
}
