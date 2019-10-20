package rpg.api.equipaments

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PantsServiceSpec extends Specification {

    PantsService pantsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Pants(...).save(flush: true, failOnError: true)
        //new Pants(...).save(flush: true, failOnError: true)
        //Pants pants = new Pants(...).save(flush: true, failOnError: true)
        //new Pants(...).save(flush: true, failOnError: true)
        //new Pants(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //pants.id
    }

    void "test get"() {
        setupData()

        expect:
        pantsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Pants> pantsList = pantsService.list(max: 2, offset: 2)

        then:
        pantsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        pantsService.count() == 5
    }

    void "test delete"() {
        Long pantsId = setupData()

        expect:
        pantsService.count() == 5

        when:
        pantsService.delete(pantsId)
        sessionFactory.currentSession.flush()

        then:
        pantsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Pants pants = new Pants()
        pantsService.save(pants)

        then:
        pants.id != null
    }
}
