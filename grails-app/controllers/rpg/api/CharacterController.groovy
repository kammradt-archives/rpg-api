package rpg.api

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class CharacterController {

    CharacterService characterService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        JSON.use('deep') {
            render characterService.list(params) as JSON
        }
    }

    def show(Long id) {
        JSON.use('deep') {
            render characterService.get(id) as JSON
        }
    }

    @Transactional
    def save(Character character) {
        if (character == null) {
            render status: NOT_FOUND
            return
        }
        if (character.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond character.errors
            return
        }

        try {
            characterService.save(character)
        } catch (ValidationException e) {
            respond character.errors
            return
        }

        respond character, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Character character) {
        if (character == null) {
            render status: NOT_FOUND
            return
        }
        if (character.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond character.errors
            return
        }

        try {
            characterService.save(character)
        } catch (ValidationException e) {
            respond character.errors
            return
        }

        respond character, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        characterService.delete(id)

        render status: NO_CONTENT
    }

    def vocation() {
        def vocations = [vocations: Vocation.values()*.name()] as JSON
        render vocations
    }
}
