package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class Rotonda {

	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="nombre")
	private String nombre;

	public Rotonda(@JsonProperty(value="id")Long id,
				   @JsonProperty(value="nombre")String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
