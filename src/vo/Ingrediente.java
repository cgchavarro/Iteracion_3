package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class Ingrediente 
{

	@JsonProperty(value="idIngrediente")
	private Long idIngrediente;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="descripcionEsp")
	private String descripcionEsp;
	
	@JsonProperty(value="descripcionIng")
	private String descripcionIng;
	
		
	
	
	public Ingrediente(@JsonProperty(value="idIngrediente")Long idIngrediente, 
					   @JsonProperty(value="nombre")String nombre, 
					   @JsonProperty(value="descripcionEsp")String descripcionEsp, 
					   @JsonProperty(value="descripcionIng")String descripcionIng) {
		this.idIngrediente = idIngrediente;
		this.nombre = nombre;
		this.descripcionEsp = descripcionEsp;
		this.descripcionIng = descripcionIng;
	}

	public Long getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(Long idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcionEsp() {
		return descripcionEsp;
	}

	public void setDescripcionEsp(String descripcionEsp) {
		this.descripcionEsp = descripcionEsp;
	}

	public String getDescripcionIng() {
		return descripcionIng;
	}

	public void setDescripcionIng(String descripcionIng) {
		this.descripcionIng = descripcionIng;
	}
	
	

}
