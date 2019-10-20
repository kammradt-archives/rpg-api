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
class ArmorController {

    ArmorService armorService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond armorService.list(params), model:[armorCount: armorService.count()]
    }

    def show(Long id) {
        respond armorService.get(id)
    }

    @Transactional
    def save(Armor armor) {
        if (armor == null) {
            render status: NOT_FOUND
            return
        }
        if (armor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond armor.errors
            return
        }

        try {
            armorService.save(armor)
        } catch (ValidationException e) {
            respond armor.errors
            return
        }

        respond armor, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Armor armor) {
        if (armor == null) {
            render status: NOT_FOUND
            return
        }
        if (armor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond armor.errors
            return
        }

        try {
            armorService.save(armor)
        } catch (ValidationException e) {
            respond armor.errors
            return
        }

        respond armor, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        armorService.delete(id)

        render status: NO_CONTENT
    }
}
