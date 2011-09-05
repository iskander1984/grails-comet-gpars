class BootStrap {

    def init = { servletContext ->
		servletContext['channels'] = new HashMap();
    }
    def destroy = {
    }
}
