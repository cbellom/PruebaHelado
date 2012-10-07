package pruebahelados

class Inventario {
	String nombre
	int cantidad
	int referencia
	double precio
    static constraints = {
		nombre(blank:false)
		cantidad(blank:false)
		referencia(blank:false)
		precio(blank:false)
		
    }
}
