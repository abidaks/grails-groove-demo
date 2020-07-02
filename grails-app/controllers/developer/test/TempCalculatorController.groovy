package developer.test

import grails.http.client.*
import grails.async.*

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import org.xml.sax.InputSource

class TempCalculatorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
    }

    def convert() {
    	String type = params.property
    	String value = params.val

    	//println(type)
    	//println(value)

    	if(type == "Celsius" || type == "Fahrenheit"){
    		if(!value.isNumber()){
	    		flash.errorMsg = "Value is not a valid number"
	    		//respond tempCalculator.errors, view:'index'
	    		redirect  (controller: "tempCalculator" , action:"index")
	            return
	    	}
    		
    	}else{
    		println(type)
    		if (type == null) {
	            notFound()
	            return
	        }else{
	    		flash.errorMsg = "Type accepted values (Celsius, Fahrenheit)"
	    		//respond tempCalculator.errors, view:'index'
	    		redirect  (controller: "tempCalculator" , action:"index")
	            return
	        }
    	}

    	String tempURL = "https://www.q88.com/WS/Q88WSInternal.asmx/ConvertTemperature?property="+type+"&val="+value

    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		def get = new URL(tempURL).openConnection()
		def getRC = get.getResponseCode()
		//println(getRC)
		if(getRC.equals(200)) {
			InputSource isource = new InputSource(new StringReader(get.getInputStream().getText()));
			Document document = builder.parse(isource)
			String celsius = document.getElementsByTagName("Celsius").item(0).getTextContent()
			String fahrenheit = document.getElementsByTagName("Fahrenheit").item(0).getTextContent()
			//println(celsius)
			//println(fahrenheit)

			flash.message = "Celsius value: " + celsius + ", Fahrenheit value: " + fahrenheit
			redirect  (controller: "tempCalculator" , action:"index")
			return
		}

    }

    def api() {

    }

    protected void notFound() {
        flash.message = "Not found"
		redirect  (controller: "tempCalculator" , action:"index")
    }

}
