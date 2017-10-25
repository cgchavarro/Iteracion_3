package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class OrdenRestauranteEquivalencias {


	@JsonProperty(value="ordenRestaurante")
	private OrdenRestaurante ordenRestaurante;
	

	@JsonProperty(value="equivalencias")
	private EquivalenciaProductos[] equivalencias;


	public OrdenRestaurante getOrdenRestaurante() {
		return ordenRestaurante;
	}


	public void setOrdenRestaurante(OrdenRestaurante ordenRestaurante) {
		this.ordenRestaurante = ordenRestaurante;
	}


	public EquivalenciaProductos[] getEquivalencias() {
		return equivalencias;
	}


	public void setEquivalencias(EquivalenciaProductos[] equivalencias) {
		this.equivalencias = equivalencias;
	}
	
	
}
