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
class PantsController {

    PantsService pantsService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pantsService.list(params), model:[pantsCount: pantsService.count()]
    }

    def show(Long id) {
        respond pantsService.get(id)
    }

    @Transactional
    def save(Pants pants) {
        if (pants == null) {
            render status: NOT_FOUND
            return
        }
        if (pants.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pants.errors
            return
        }

        try {
            pantsService.save(pants)
        } catch (ValidationException e) {
            respond pants.errors
            return
        }

        respond pants, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Pants pants) {
        if (pants == null) {
            render status: NOT_FOUND
            return
        }
        if (pants.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pants.errors
            return
        }

        try {
            pantsService.save(pants)
        } catch (ValidationException e) {
            respond pants.errors
            return
        }

        respond pants, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        pantsService.delete(id)

        render status: NO_CONTENT
    }
}
