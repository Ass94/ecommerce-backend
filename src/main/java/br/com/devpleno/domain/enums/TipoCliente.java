package br.com.devpleno.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa física"), PESSOAJURIDICA(2, "Pessoa jurídica");

	private int codigo;
	private String descricao;

	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		if(codigo == null) return null;
		for(TipoCliente t: TipoCliente.values()) {
			if(codigo.equals(t.getCodigo())) {
				return t;
			}
		}
		throw new IllegalArgumentException("Código inválido " + codigo);
	}

}
