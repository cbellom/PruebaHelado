package pruebahelados

class Galleta extends Inventario{
	String tipo
	String tamaño	
    static constraints = {
		tipo(blank:false)
		tamaño(blank:false)
		
    }
}
