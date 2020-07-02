package developer.test

class FileUpload {

    String fileName
    String fileData
    Date updatedOn

    static constraints = {
    	fileName size: 5..40, blank: false
    	fileData type: 'text', blank: false
        updatedOn nullable: true
    }
    
    String toString() {
        fileName
    }
}
