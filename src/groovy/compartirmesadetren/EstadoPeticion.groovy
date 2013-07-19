package compartirmesadetren

enum EstadoPeticion {
	
	IGNORAR("Ignorada", "La peticion sera ignorada."), 
	A_LA_ESPERA("Esperando gestion", "La peticion se ha recibido pero aun no hay el numero necesario de usuarios apuntados."), 
	BAJO_GESTION("Gestionandose", "Tenemos los usuarios, pero estamos pendientes de los billetes."), 
	VIGILAR("Bajo vigilancia", "No hay billetes y se ha creado una alarma por si aparecen disponibles."),
	RESERVAR("Pendiente de reservar", "Debemos reservar estas peticiones cuanto antes."), 
	COBRAR("Pendiente de cobro", "Hay que enviarle la solicitud de pago al usuario."), 
	COMPRAR("Para comprar", "La peticion ha sido pagada por el usuario y esta pendiente de comprar del billete reservado."),
	ENVIAR("Enviar los billetes", "Tenemos los billetes pero aun no los hemos enviado."), 
	CERRADA("Finalizada", "Peticion finalizada. El usuario ha viajado."), 
	CANCELAR_FIANZA("Cancelar la fianza", "Ha cumplido el plazo de 48 horas y esta pendiente la cancelacion de la fianza."),
	CANCELADA("Cancelada", "Ha cumplido el plazo de 48h y la fianza esta anulada."), 
	COBRAR_FIANZA("Cobrar la fianza", "El usuario a solicitado la anulacion y se debe cobrar la fianza."), 
	ANULADA("Anulada", "Se ha anulado y cobrado la fianza.");

	String name, help
	
	EstadoPeticion(name, help) {
		this.name = name	
		this.help = help
	}
	
}
