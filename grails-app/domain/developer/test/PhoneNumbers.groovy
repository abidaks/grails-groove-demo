package developer.test

class PhoneNumbers {

    String userId
    String phoneNumber
    Boolean activated

    static constraints = {
    	userId size: 1..40, blank: false
    	phoneNumber size: 5..20, blank: false
        activated deafult: false, blank: false
    }
    
    String toString() {
        userId
    }
}
