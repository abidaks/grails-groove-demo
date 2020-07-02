package developer.test

import grails.gorm.services.Service

@Service(PhoneNumbers)
interface PhoneNumbersService {

    PhoneNumbers get(Serializable id)

    List<PhoneNumbers> list(Map args)

    Long count()

    void delete(Serializable id)

    PhoneNumbers save(PhoneNumbers phoneNumbers)

    PhoneNumbers getByPhoneNumber(String phoneNumber)

    PhoneNumbers getAllByUserId(String userId)

}