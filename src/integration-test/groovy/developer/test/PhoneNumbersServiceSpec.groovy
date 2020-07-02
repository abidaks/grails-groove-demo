package developer.test

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PhoneNumbersServiceSpec extends Specification {

    PhoneNumbersService phoneNumbersService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PhoneNumbers(...).save(flush: true, failOnError: true)
        //new PhoneNumbers(...).save(flush: true, failOnError: true)
        //PhoneNumbers phoneNumbers = new PhoneNumbers(...).save(flush: true, failOnError: true)
        //new PhoneNumbers(...).save(flush: true, failOnError: true)
        //new PhoneNumbers(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //phoneNumbers.id
    }

    void "test get"() {
        setupData()

        expect:
        phoneNumbersService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PhoneNumbers> phoneNumbersList = phoneNumbersService.list(max: 2, offset: 2)

        then:
        phoneNumbersList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        phoneNumbersService.count() == 5
    }

    void "test delete"() {
        Long phoneNumbersId = setupData()

        expect:
        phoneNumbersService.count() == 5

        when:
        phoneNumbersService.delete(phoneNumbersId)
        sessionFactory.currentSession.flush()

        then:
        phoneNumbersService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PhoneNumbers phoneNumbers = new PhoneNumbers()
        phoneNumbersService.save(phoneNumbers)

        then:
        phoneNumbers.id != null
    }
}
