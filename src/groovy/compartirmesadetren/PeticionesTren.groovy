package compartirmesadetren;

import java.util.List;

public class PeticionesTren {
	
	def int pasajeros
	def Tren tren
	
	public PeticionesTren (Tren tren) {
		List<Peticion> peticiones = Peticion.findAllBySalida(tren.salida)
		pasajeros = peticiones.size()
		this.tren = tren
	}
	
	def Tren getTren() {
		return tren
	}
	
	/**
	 * Dependiendo de los pasajeros con solicitud, faltaran más o menos
	 * por apuntarse para poder iniciar las gestines de mesa
	 * 
	 * @return int numero de parajeros que faltan
	 */
	def int getCuantosFaltan() {
		if (pasajeros == 5 || pasajeros == 2)
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
		return pasajeros > 2 && pasajeros != 5
    }
	
	/**
	 * Comprueba si con uno mas se gestionan las mesas
	 * 
	 * @return true en caso de que con uno mas se
	 * gestionen mesas
	 */
    def boolean isMeApuntoYViajoSeguro() {
		return pasajeros > 2 && pasajeros != 4
    }

	/**
	 * Comprueba si debe marcarse dicho tren como ocasion para
	 * el pasajero
	 * 
	 * @return true en caso de ser una ocasion para el pasajero
	 */
	def boolean isOportunidad() {
		return oportunidades.contains(pasajeros) || pasajeros > 5
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
		mesas4 << m4
		mesas3 << m3
	}
	
	def List oportunidades = [2, 5]
	def List mesas4 = [0,0,0,1,1,0,1,2,0,1,2,3,1,2,3,4,2,3,4,5,3,4,5,6,4,5,6,7,5,6,7,8,6,7,8,9,7,8,9,10]
	def List mesas3 = [0,0,1,0,0,2,1,0,3,2,1,0,3,2,1,0,3,2,1,0,3,2,1,0,3,2,1,0,3,2,1,0,3,2,1,0,3,2,1,0]
}
