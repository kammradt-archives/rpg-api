package rpg.api.equipaments

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ArmorServiceSpec extends Specification {

    ArmorService armorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Armor(...).save(flush: true, failOnError: true)
        //new Armor(...).save(flush: true, failOnError: true)
        //Armor armor = new Armor(...).save(flush: true, failOnError: true)
        //new Armor(...).save(flush: true, failOnError: true)
        //new Armor(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //armor.id
    }

    void "test get"() {
        setupData()

        expect:
        armorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Armor> armorList = armorService.list(max: 2, offset: 2)

        then:
        armorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        armorService.count() == 5
    }

    void "test delete"() {
        Long armorId = setupData()

        expect:
        armorService.count() == 5

        when:
        armorService.delete(armorId)
        sessionFactory.currentSession.flush()

        then:
        armorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Armor armor = new Armor()
        armorService.save(armor)

        then:
        armor.id != null
    }
}
