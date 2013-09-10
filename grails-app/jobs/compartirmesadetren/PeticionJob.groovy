package compartirmesadetren

class PeticionJob {

    static triggers = {
      simple repeatInterval: 60000l // execute job once in 60 seconds
    }

    def notificacionesService

    def execute() {
        //Acciones para las peticiones A_LA_ESPERA
        def pendientes = Peticion.findAllByEstado(EstadoPeticion.A_LA_ESPERA)
        pendientes.each { peticion ->
            if (peticion.estado == EstadoPeticion.A_LA_ESPERA) { //Puede haber cambiado el estado de la petición
    	        //Estoy dentro del plazo de 48 horas?
    	        if (!caducada(peticion)) {
        	        //Tengo los usuarios suficientes? más de 2      
                    def reservas = Peticion.findAllBySalidaAndTrayecto(peticion.salida, peticion.trayecto)
                    def plazas = 0
                    reservas.each { reserva -> 
                        plazas += reserva.plazas
                    }
                    if (plazas > 2) {
                        log.debug "Suficientes usuarios para $peticion.salida $peticion.trayecto"
                        reservas.each { reserva ->
                            establecerEstado(reserva, EstadoPeticion.BAJO_GESTION)
                        }
                        notificacionesService.reservasSuficientes(peticion)
                    }
                }
            }  	
        }

        //Acciones para las peticiones BAJO_GESTION
        def bajoGestion = Peticion.findAllByEstado(EstadoPeticion.BAJO_GESTION)
        bajoGestion.each { peticion ->
            if (peticion.estado == EstadoPeticion.BAJO_GESTION) { //Puede haber cambiado el estado de la petición
                //Estoy dentro del plazo de 48 horas?
                if (!caducada(peticion)) {
                    //Hay billetes disponibles? 
                    def tren = Tren.findAllBySalidaAndTrayecto(peticion.salida, peticion.trayecto)
                    if (tren) {
                        log.debug "$tren ${tren.noValido.class} ${tren.noValido} "
                        def noValido = tren.noValido
                        if (tren.noValido instanceof List) //El extreño caso de la conversion a List de un boolean
                            noValido = tren.noValido[0]
                        log.debug "${noValido.class} ${noValido} "
                        if (noValido) {
                            log.debug "No hay billetes ${peticion.salida.format('dd/MM/yyyy HH:mm')}"
                            vigilar(peticion)
                            establecerEstado(peticion, EstadoPeticion.VIGILAR)
                        } else {
                            log.debug "Notificamos la orden de reserva"
                            establecerEstado(peticion, EstadoPeticion.RESERVAR)
                            notificacionesService.reservarMesa(peticion)
                        }
                    } else {
                        log.debug "No hay datos para el tren ${peticion.salida.format('dd/MM/yyyy HH:mm.s')} ${peticion.trayecto}"
                    }
                }
            }
        }

        //Acciones para las peticiones VIGILAR
        def vigiladas = Peticion.findAllByEstado(EstadoPeticion.VIGILAR)
        vigiladas.each { peticion ->
            if (peticion.estado == EstadoPeticion.VIGILAR) { //Puede haber cambiado el estado de la petición
                //Estoy dentro del plazo de 48 horas?
                if (caducada(peticion)) {
                    dejarVigilancia(peticion)
                } else {
                    //Hay billetes disponibles? 
                    def tren = Tren.findBySalidaAndTrayecto(peticion.salida, peticion.trayecto)
                    if (tren) {
                        def noValido = tren.noValido
                        if (tren.noValido instanceof List) //El extreño caso de la conversion a List de un boolean
                            noValido = tren.noValido[0]
                        if (!noValido) {
                            log.debug "Notificamos la orden de reserva"
                            establecerEstado(peticion, EstadoPeticion.RESERVAR)
                            notificacionesService.reservarMesa(peticion)
                        }
                    } else {
                        log.debug "No hay datos para el tren"
                    }
                }
            }
        }
    }

    private establecerEstado(peticion, estado) {
        peticion.estado = estado
        peticion.save(flush: true)
    }

    private Boolean caducada(peticion) {
        def cancelacion = peticion.salida - 2
        def cad = false
        if (cancelacion.before(new Date())) {
            log.debug "Cancelando peticion $peticion"
            establecerEstado(peticion, EstadoPeticion.CANCELAR_FIANZA)
            notificacionesService.cancelarReserva(peticion)
            cad = true
        }  
        cad
    }

    private vigilar(peticion) {
        def salida = peticion.salida.clone()
        salida.clearTime()
        def hora = peticion.salida.format("HH.mm")
        def objetivos = TrenObjetivo.findAllBySalidaAndTrayecto(salida, peticion.trayecto)
        def activo = false
        objetivos.each { objetivo ->
            if (hora == objetivo.hora && objetivo.activo)
                activo = true
        }
        if (!activo) {
            log.debug "Creamos vigilancia para $salida y hora $hora"
            def nuevoObjetivo = new TrenObjetivo(
                salida: salida, trayecto: peticion.trayecto, hora: hora)
            nuevoObjetivo.save(flush: true)
        } else {
            log.debug "Este tren ya esta bajo vigilancia"
        }
    }

    private dejarVigilancia(peticion) {
        def salida = peticion.salida.clone()
        salida.clearTime()
        def hora = peticion.salida.format("HH.mm")
        def objetivos = TrenObjetivo.findAllBySalidaAndTrayecto(salida, peticion.trayecto)
        objetivos.each { objetivo ->
            if (hora == objetivo.hora) {
                log.debug "Creamos vigilancia para $salida y hora $hora"
                objetivo.activo = false
                objetivo.save(flush: true)
            }
        }
    }
}
