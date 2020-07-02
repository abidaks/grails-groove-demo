package developer.test

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

import grails.rest.RestfulController

class PhoneNumbersController  extends RestfulController {

    PhoneNumbersController() {
        super(PhoneNumbers)
    }

    PhoneNumbersService phoneNumbersService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['json']

    def all() {
        respond phoneNumbersService.list(params)
    }

    def show(Long id) {
        respond phoneNumbersService.get(id)
    }

    def activate(String id) {
        print(params)
        print(id)
        PhoneNumbers phoneNumber =  PhoneNumbers.findByPhoneNumber(id)
        if(phoneNumber){
            if(phoneNumber.activated == false){
                phoneNumber.activated = true;
                phoneNumbersService.save(phoneNumber)
                respond phoneNumber
                return
            }
            response.setContentType("application/json")
            render '[{"message": "already activated"}]'
        }
            
        response.setContentType("application/json")
        render '[{"message": "not found"}]'
    }

    def getUserNumbers(String id) {
        respond PhoneNumbers.findAllByUserId(id)
    }

    def save(PhoneNumbers phoneNumbers) {
        if (phoneNumbers == null) {
            notFound()
            return
        }

        try {
            phoneNumbersService.save(phoneNumbers)
        } catch (ValidationException e) {
            respond phoneNumbers.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'phoneNumbers.label', default: 'PhoneNumbers'), phoneNumbers.id])
                redirect phoneNumbers
            }
            '*' { respond phoneNumbers, [status: CREATED] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'phoneNumbers.label', default: 'PhoneNumbers'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
