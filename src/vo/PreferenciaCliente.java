package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class PreferenciaCliente 
{
	@JsonProperty(value="idCliente")
	private Long idCliente;

	@JsonProperty(value="preferencia")
	private String preferencia;

	public PreferenciaCliente(@JsonProperty(value="idCliente")Long idCliente,
					 @JsonProperty(value="preferencia")String preferencia) 
	{
		this.idCliente = idCliente;
		this.preferencia = preferencia;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(String preferencia) {
		this.preferencia = preferencia;
	}

		
}
