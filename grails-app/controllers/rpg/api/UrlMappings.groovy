package rpg.api

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?(.$format)?" {
        }

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
