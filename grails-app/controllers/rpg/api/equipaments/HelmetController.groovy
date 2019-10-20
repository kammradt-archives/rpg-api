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
class HelmetController {

    HelmetService helmetService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond helmetService.list(params), model:[helmetCount: helmetService.count()]
    }

    def show(Long id) {
        respond helmetService.get(id)
    }

    @Transactional
    def save(Helmet helmet) {
        if (helmet == null) {
            render status: NOT_FOUND
            return
        }
        if (helmet.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond helmet.errors
            return
        }

        try {
            helmetService.save(helmet)
        } catch (ValidationException e) {
            respond helmet.errors
            return
        }

        respond helmet, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Helmet helmet) {
        if (helmet == null) {
            render status: NOT_FOUND
            return
        }
        if (helmet.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond helmet.errors
            return
        }

        try {
            helmetService.save(helmet)
        } catch (ValidationException e) {
            respond helmet.errors
            return
        }

        respond helmet, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        helmetService.delete(id)

        render status: NO_CONTENT
    }
}
