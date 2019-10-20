package rpg.api.weapons

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class WeaponServiceSpec extends Specification {

    WeaponService weaponService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Weapon(...).save(flush: true, failOnError: true)
        //new Weapon(...).save(flush: true, failOnError: true)
        //Weapon weapon = new Weapon(...).save(flush: true, failOnError: true)
        //new Weapon(...).save(flush: true, failOnError: true)
        //new Weapon(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //weapon.id
    }

    void "test get"() {
        setupData()

        expect:
        weaponService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Weapon> weaponList = weaponService.list(max: 2, offset: 2)

        then:
        weaponList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        weaponService.count() == 5
    }

    void "test delete"() {
        Long weaponId = setupData()

        expect:
        weaponService.count() == 5

        when:
        weaponService.delete(weaponId)
        sessionFactory.currentSession.flush()

        then:
        weaponService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Weapon weapon = new Weapon()
        weaponService.save(weapon)

        then:
        weapon.id != null
    }
}
