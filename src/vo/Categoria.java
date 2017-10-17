package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class Categoria {

	@JsonProperty(value="idCategoria")
	private Long idCategoria;

	@JsonProperty(value="nombre")
	private String nombre;

	public Categoria(@JsonProperty(value="idCategoria")Long idCategoria,
					 @JsonProperty(value="nombre")String nombre) 
	{
		this.idCategoria = idCategoria;
		this.nombre = nombre;
	}

	public Long getId() {
		return idCategoria;
	}

	public void setId(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
}
