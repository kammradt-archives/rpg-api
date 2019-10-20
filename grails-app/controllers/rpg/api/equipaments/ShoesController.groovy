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
class ShoesController {

    ShoesService shoesService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond shoesService.list(params), model:[shoesCount: shoesService.count()]
    }

    def show(Long id) {
        respond shoesService.get(id)
    }

    @Transactional
    def save(Shoes shoes) {
        if (shoes == null) {
            render status: NOT_FOUND
            return
        }
        if (shoes.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond shoes.errors
            return
        }

        try {
            shoesService.save(shoes)
        } catch (ValidationException e) {
            respond shoes.errors
            return
        }

        respond shoes, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Shoes shoes) {
        if (shoes == null) {
            render status: NOT_FOUND
            return
        }
        if (shoes.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond shoes.errors
            return
        }

        try {
            shoesService.save(shoes)
        } catch (ValidationException e) {
            respond shoes.errors
            return
        }

        respond shoes, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        shoesService.delete(id)

        render status: NO_CONTENT
    }
}
