package pruebahelados

class Persona {
	
	String nombre
	int edad
	
    static constraints = {
		edad(size:15..100,blank:false)
		nombre(blank:false)
    }
}
