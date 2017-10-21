package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class EquivalenciaIngredientes 
{
	@JsonProperty(value="idIngrediente1")
	private Long idIngrediente1;
	
	@JsonProperty(value="idIngrediente2")
	private Long idIngrediente2;

	public EquivalenciaIngredientes(@JsonProperty(value="idIngrediente1")Long idIngrediente1, 
			@JsonProperty(value="idIngrediente1")Long idIngrediente2) {
		this.idIngrediente1=idIngrediente1;
		this.idIngrediente2=idIngrediente2;
	}

	public Long getIdIngrediente1() {
		return idIngrediente1;
	}

	public void setIdIngrediente1(Long idIngrediente1) {
		this.idIngrediente1 = idIngrediente1;
	}

	public Long getIdIngrediente2() {
		return idIngrediente2;
	}

	public void setIdIngrediente2(Long idIngrediente2) {
		this.idIngrediente2 = idIngrediente2;
	}

	
	
	
}
