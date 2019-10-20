package rpg.api.equipaments

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class HelmetServiceSpec extends Specification {

    HelmetService helmetService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Helmet(...).save(flush: true, failOnError: true)
        //new Helmet(...).save(flush: true, failOnError: true)
        //Helmet helmet = new Helmet(...).save(flush: true, failOnError: true)
        //new Helmet(...).save(flush: true, failOnError: true)
        //new Helmet(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //helmet.id
    }

    void "test get"() {
        setupData()

        expect:
        helmetService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Helmet> helmetList = helmetService.list(max: 2, offset: 2)

        then:
        helmetList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        helmetService.count() == 5
    }

    void "test delete"() {
        Long helmetId = setupData()

        expect:
        helmetService.count() == 5

        when:
        helmetService.delete(helmetId)
        sessionFactory.currentSession.flush()

        then:
        helmetService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Helmet helmet = new Helmet()
        helmetService.save(helmet)

        then:
        helmet.id != null
    }
}
