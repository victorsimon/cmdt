package compartirmesadetren;

import java.util.List;

public class PeticionesTren {
	
	def int pasajeros
	def Tren tren
	
	public PeticionesTren (Tren tren) {
		List<Peticion> peticiones = Peticion.findAllBySalidaAndTrayecto(tren.salida, tren.trayecto)
		pasajeros = peticiones.size()
		this.tren = tren
	}
	
	def Tren getTren() {
		return tren
	}
	
	/**
	 * Dependiendo de los pasajeros con solicitud, faltaran m�s o menos
	 * por apuntarse para poder iniciar las gestines de mesa
	 * 
	 * @return int numero de parajeros que faltan
	 */
	def int getCuantosFaltan() {
		if (pasajeros == 2)
			return 1
		if (pasajeros == 1)
			return 2
		if (pasajeros == 0)
			return 3
		return 0
	}
	
	/**
	 * Comprueba si el tren requiere gestion (compra de mesas)
	 * 
	 * @return true en caso de que deban comprarse mesas
	 */
    def boolean isGestionable() {
		return pasajeros > 2
    }
	
	/**
	 * Comprueba si el tren requiere gestion (compra de mesas)
	 * 
	 * @return true en caso de que deban comprarse mesas
	 */
    def boolean isViajaSeguro() {
		return pasajeros > 2
    }
	
	/**
	 * Comprueba si con uno mas se gestionan las mesas
	 * 
	 * @return true en caso de que con uno mas se
	 * gestionen mesas
	 */
    def boolean isMeApuntoYViajoSeguro() {
		return pasajeros > 2
    }

	/**
	 * Comprueba si debe mostrarse dicho tren como oferta para
	 * el pasajero
	 * 
	 * @return true en caso de ser ofertable para el pasajero
	 */
	def boolean isOferta() {
		return pasajeros == 1 || pasajeros == 2
	}
	
	/**
	 * Comprueba si debe marcarse dicho tren como ocasion para
	 * el pasajero
	 * 
	 * @return true en caso de ser una ocasion para el pasajero
	 */
	def boolean isOportunidad() {
		return pasajeros == 2
	}
	
	/**
	 * Calcula las mesas que son necesarias para cubrir a los
	 * pasajeros de un tren concreto
	 * 
	 * @return numero de mesas necesarias
	 */
	def int getMesasNecesarias() {
		//Comprobamos que haya alguna peticion para dicho tren
		if (pasajeros == 0)
			return 0;
		
		/* Comprobamos que la lista precalculada es lo 
		 * suficientemente amplia
		 */
		while (pasajeros > mesas4.size()) {
			recalcularListaMesas() //La hacemos crecer
		}
		 
		def mesas = mesas4[pasajeros - 1] + mesas3[pasajeros - 1]
		return mesas
	}

	/**
	 * Calcula para las mesas de ese tren, en cuantas deberian
	 * sentarse cuatro pasajeros
	 * 
	 * @return measa ocupadas por cuatro pasajeros
	 */
	def int getMesasCon4() {
		//Comprobamos que haya alguna peticion para dicho tren
		if (pasajeros == 0)
			return 0;
			
		/* Comprobamos que la lista precalculada es lo 
		 * suficientemente amplia
		 */
		while (pasajeros > mesas4.size()) {
			recalcularListaMesas() //La hacemos crecer
		}
		 

		return mesas4[pasajeros - 1]
	}
	
	/**
	 * Calcula para las mesas de ese tren, en cuantas deberian
	 * sentarse tres pasajeros
	 * 
	 * @return mesas ocupadas por tres pasajeros
	 */
	def int getMesasCon3() {
		//Comprobamos que haya alguna peticion para dicho tren
		if (pasajeros == 0)
			return 0;
			
		/* Comprobamos que la lista precalculada es lo 
		 * suficientemente amplia
		 */
		while (pasajeros > mesas4.size()) {
			recalcularListaMesas() //La hacemos crecer
		}
		 
		return mesas3[pasajeros - 1]
	}
	
	/**
	 * Calcula los valores de las mesas cuando la lista se queda corta
	 * Amplia en uno los pasajeros calculados
	 * 
	 * NOTA: No se espera tal cosas ya que las mesas son limitadas (8 aprox)
	 * 
	 * @return void
	 */
	private recalcularListaMesas() {
		def pos = mesas4.size() + 1
		def int co = pos / 4
		def int re = pos % 4
		def m4 = (co + 1) - (4 - re)
		def m3 = 4 - re
		def asiento = 0
		if (re == 1) {
			m4 = co
			m3 = 0
			asiento = 1
		} 
		mesas4 << m4
		mesas3 << m3
		asientos << asiento
	}
	
	def List oportunidades = [2]
	def List mesas4   = [0,0,0,1,1,0,1,2,2,1,2,3,3,2,3,4,0,3,4,5,5,4,5,6,6,5,6,7,7,6,7,8,8,7,8,9,9,8,9,10]
	def List mesas3   = [0,0,1,0,0,2,1,0,0,2,1,0,0,2,1,0,0,2,1,0,0,2,1,0,0,2,1,0,0,2,1,0,0,2,1,0,0,2,1,0]
	def List asientos = [0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0]
}
