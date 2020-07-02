package developer.test

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import java.io.File

class FileUploadController {

    FileUploadService fileUploadService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond fileUploadService.list(params), model:[fileUploadCount: fileUploadService.count()]
    }

    def show(Long id) {
        respond fileUploadService.get(id)
    }

    def create() {
        respond new FileUpload(params)
    }

    def save(FileUpload fileUpload) {
        //println(params.myFile.getInputStream().read())

        BufferedReader reader = new BufferedReader(new InputStreamReader(params.myFile.getInputStream()))
        StringBuilder sb = new StringBuilder()
        while(reader.ready()) {
            String line;
            Integer counter = 0;
            while ((line = reader.readLine()) != null) {
                counter = counter + 1;
                String[] uData = line.split(",")
                if(uData.length < 3 || !uData[0].isNumber() || uData[0].length() > 10 || !uData[1].isNumber() || !(Integer.parseInt(uData[1]) > 0) || !uData[2].matches(".*[a-z].*")){
                    flash.errorMsg = "There is an error in your file on line " + counter
                    respond fileUpload.errors, view:'create'
                    return
                }else{
                    sb.append(line).append("\n");
                }
            }
        }
        //Here validation is done.
        //String builder contains each record 
        //We can enter each record seperately or as one entry to database
        //I entered as one entry to database.

        fileUpload.fileName = params.myFile.getOriginalFilename()
        fileUpload.fileData = sb.toString()
        fileUpload.updatedOn = new Date()


        //exit
        if (fileUpload == null) {
            notFound()
            return
        }

        try {
            fileUploadService.save(fileUpload)
        } catch (ValidationException e) {
            respond fileUpload.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fileUpload.label', default: 'FileUpload'), fileUpload.id])
                redirect fileUpload
            }
            '*' { respond fileUpload, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond fileUploadService.get(id)
    }

    def update(FileUpload fileUpload) {
        if (fileUpload == null) {
            notFound()
            return
        }

        try {
            fileUploadService.save(fileUpload)
        } catch (ValidationException e) {
            respond fileUpload.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fileUpload.label', default: 'FileUpload'), fileUpload.id])
                redirect fileUpload
            }
            '*'{ respond fileUpload, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        fileUploadService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fileUpload.label', default: 'FileUpload'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fileUpload.label', default: 'FileUpload'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
