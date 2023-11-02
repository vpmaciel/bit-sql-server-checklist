package br.informacoes.seguranca;

import java.util.Collection;

public final class SegurancaFac {

	private static final SegurancaDao segurancaDao = new SegurancaImp();

	public static Collection<Seguranca> getRegistro() {

		return segurancaDao.getRegistro();
	}

	private SegurancaFac() {

	}
}
