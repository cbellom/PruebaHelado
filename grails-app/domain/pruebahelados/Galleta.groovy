package pruebahelados

class Galleta extends Inventario{
	String tipo
	String tama�o	
    static constraints = {
		tipo(blank:false)
		tama�o(blank:false)
		
    }
}
