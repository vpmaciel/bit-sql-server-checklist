package br.arquitetura.util;

import java.lang.reflect.Field;
import java.util.List;

public class Reflexao {

	private final Field[] campos;
	private Class<?> classe;
	private String[] colunas;
	private Object[][] dados;
	private List<?> list;
	private final Object object;

	public Reflexao(Object object, List<?> list) {
		this.object = object;
		this.classe = object.getClass();
		this.campos = classe.getDeclaredFields();
		this.list = list;
	}

	public String[] getColunas() {

		this.colunas = new String[campos.length];
		int indice = 0;

		for (Field campo : campos) {
			try {
				colunas[indice++] = campo.getName();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return colunas;
	}

	public Object[][] getDados() {

		dados = new Object[list.size()][colunas.length];
		int linha = 0, coluna = 0;
		for (@SuppressWarnings("unused")
		Object object : list) {
			for (Field campo : campos) {
				try {
					// String nomeAtributo = campo.getName();
					campo.setAccessible(true); // Necessário por conta do encapsulamento das variáveis (private)
					// Object valorAtributo = campo.get(this.object);
					dados[linha][coluna] = campo.get(this.object);
				} catch (Exception e) {
					e.printStackTrace();
				}
				coluna++;
			}
			linha++;
			coluna = 0;
		}
		return dados;
	}
}
