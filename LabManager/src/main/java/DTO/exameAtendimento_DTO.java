package DTO;

import java.util.ArrayList;
import java.util.List;

public class exameAtendimento_DTO {

	private ArrayList lista_idPedido = new ArrayList();
	private String codConvenio;
	private ArrayList lista_idExame = new ArrayList();
	private String dataCoelta;
	private String entrega_material;
	private Double preco;
	
	private String seq;
	private String codigo;
	private String descExame;
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescExame() {
		return descExame;
	}
	public void setDescExame(String descExame) {
		this.descExame = descExame;
	}
	
	
	
	
	
	

}
