package rpg.api.weapons

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class WeaponController {

    WeaponService weaponService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond weaponService.list(params), model:[weaponCount: weaponService.count()]
    }

    def show(Long id) {
        respond weaponService.get(id)
    }

    @Transactional
    def save(Weapon weapon) {
        if (weapon == null) {
            render status: NOT_FOUND
            return
        }
        if (weapon.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond weapon.errors
            return
        }

        try {
            weaponService.save(weapon)
        } catch (ValidationException e) {
            respond weapon.errors
            return
        }

        respond weapon, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Weapon weapon) {
        if (weapon == null) {
            render status: NOT_FOUND
            return
        }
        if (weapon.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond weapon.errors
            return
        }

        try {
            weaponService.save(weapon)
        } catch (ValidationException e) {
            respond weapon.errors
            return
        }

        respond weapon, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        weaponService.delete(id)

        render status: NO_CONTENT
    }
}
