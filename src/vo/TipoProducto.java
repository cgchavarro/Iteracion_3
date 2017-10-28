package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class TipoProducto 
{	
	@JsonProperty(value="idTipo")
	private Long idTipo;
	
	@JsonProperty(value="idProducto")
	private Long idProducto;

	public TipoProducto(@JsonProperty(value="idProducto")Long idProducto, 
						@JsonProperty(value="idTipo")Long idTipo) {
		this.idProducto = idProducto;
		this.idTipo = idTipo;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}
	
	public String toParametros()
	{
		return 	Long.class.getName() + ":" + idTipo + "," + 
				String.class.getName()+ ":" + idProducto;
	}
}
