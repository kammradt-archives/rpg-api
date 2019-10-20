package rpg.api.equipaments

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class GlovesController {

    GlovesService glovesService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond glovesService.list(params), model:[glovesCount: glovesService.count()]
    }

    def show(Long id) {
        respond glovesService.get(id)
    }

    @Transactional
    def save(Gloves gloves) {
        if (gloves == null) {
            render status: NOT_FOUND
            return
        }
        if (gloves.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond gloves.errors
            return
        }

        try {
            glovesService.save(gloves)
        } catch (ValidationException e) {
            respond gloves.errors
            return
        }

        respond gloves, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Gloves gloves) {
        if (gloves == null) {
            render status: NOT_FOUND
            return
        }
        if (gloves.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond gloves.errors
            return
        }

        try {
            glovesService.save(gloves)
        } catch (ValidationException e) {
            respond gloves.errors
            return
        }

        respond gloves, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        glovesService.delete(id)

        render status: NO_CONTENT
    }
}
