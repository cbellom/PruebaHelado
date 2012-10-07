package pruebahelados

class Vendedor extends Persona{
	Double salario
	String descripcion
	
    static constraints = {
		salario(blank:false)
    }
}
