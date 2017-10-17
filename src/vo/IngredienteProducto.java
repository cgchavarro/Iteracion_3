package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class IngredienteProducto {
	
	@JsonProperty(value="idIngrediente")
	private Long idIngrediente;
	
	@JsonProperty(value="idProducto")
	private Long idProducto;

	public IngredienteProducto(@JsonProperty(value="idIngrediente")Long idIngrediente,
							   @JsonProperty(value="idProducto") Long idProducto) {
		this.idIngrediente = idIngrediente;
		this.idProducto = idProducto;
	}

	public Long getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(Long idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}	

}
