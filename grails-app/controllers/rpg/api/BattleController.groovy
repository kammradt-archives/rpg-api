package rpg.api


import grails.rest.*
import grails.converters.*
import org.apache.commons.lang3.ObjectUtils

import static org.springframework.http.HttpStatus.BAD_REQUEST

class BattleController {
	static responseFormats = ['json', 'xml']
    static allowedMethods = [calculate: "GET", fight: "POST"]

    BattleService battleService


    def calculate(Long id) {
        render battleService.calculate(id) as JSON
    }

    def fight() {
        if (!request.JSON.ids)
            render status: BAD_REQUEST, message: 'Send valid ID(s)'
        render battleService.fight(request.JSON.ids) as JSON
    }

}
