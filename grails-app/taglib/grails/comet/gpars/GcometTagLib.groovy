package grails.comet.gpars

class GcometTagLib {
	static namespace = "gcomet"
	
	def includes = { attrs, body ->
		out << "<script type=\"text/javascript\" src=\"/grails-comet-gpars/js/gcomet.js\"></script>" 
	}
	
	def component = { attrs, body ->
		out << "<span id=${attrs.id} type='gcomet' updateHandler='${attrs.updateHandler}'>" + body + "</span>"
	}

}
