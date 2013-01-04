package compartirmesadetren

enum EstadoPeticion {
	
	A_LA_ESPERA("Esperando gestion"), BAJO_GESTION("Gestionandose"), CERRADA("Finalizada"), CANCELADA("Cancelada") 

	String name
	
	EstadoPeticion(String name) {
		this.name = name	
	}
	
}
