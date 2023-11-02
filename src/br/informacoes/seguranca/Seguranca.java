package br.informacoes.seguranca;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Seguranca implements Serializable {

	private String codigo;
	private String categoria;
	private String verificado;
	private String avaliacao;
	private String descricaoDoProblema;
	private String detalhamentoDaVerificacao;
	private String sugestaoDeCorrecao;
	private String resultadosDaValidacao;
	private String urlDeReferencia;

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Seguranca)) {
			return false;
		}

		if (this.verificado.equals(((Seguranca) object).getVerificado())) {
			return true;
		}

		return false;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getVerificado() {
		return verificado;
	}

	public void setVerificado(String verificado) {
		this.verificado = verificado;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getDescricaoDoProblema() {
		return descricaoDoProblema;
	}

	public void setDescricaoDoProblema(String descricaoDoProblema) {
		this.descricaoDoProblema = descricaoDoProblema;
	}

	public String getDetalhamentoDaVerificacao() {
		return detalhamentoDaVerificacao;
	}

	public void setDetalhamentoDaVerificacao(String detalhamentoDaVerificacao) {
		this.detalhamentoDaVerificacao = detalhamentoDaVerificacao;
	}

	public String getSugestaoDeCorrecao() {
		return sugestaoDeCorrecao;
	}

	public void setSugestaoDeCorrecao(String sugestaoDeCorrecao) {
		this.sugestaoDeCorrecao = sugestaoDeCorrecao;
	}

	public String getResultadosDaValidacao() {
		return resultadosDaValidacao;
	}

	public void setResultadosDaValidacao(String resultadosDaValidacao) {
		this.resultadosDaValidacao = resultadosDaValidacao;
	}

	public String getUrlDeReferencia() {
		return urlDeReferencia;
	}

	public void setUrlDeReferencia(String urlDeReferencia) {
		this.urlDeReferencia = urlDeReferencia;
	}

	@Override
	public String toString() {
		return this.verificado;
	}
}
