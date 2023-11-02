package br.arquitetura;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Internet {

	public class LinkSistema {

		public LinkSistema(String endereco) {
			enviar(endereco);
		}
	}

	public void enviar(String endereco) {
		Desktop desktop = null;
		try {
			URI uri = new URI(endereco);
			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
			}
			if (desktop != null) {
				desktop.browse(uri);
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (URISyntaxException uriSyntaxException) {
			uriSyntaxException.printStackTrace();
		}
	}

	public class Link extends MouseAdapter {

		private final String endereco;

		public Link(String endereco) {
			this.endereco = endereco;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			Desktop desktop = null;
			try {
				URI uri = new URI(this.endereco);
				if (Desktop.isDesktopSupported()) {
					desktop = Desktop.getDesktop();
				}
				if (desktop != null) {
					desktop.browse(uri);
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			} catch (URISyntaxException uriSyntaxException) {
				uriSyntaxException.printStackTrace();
			}
		}
	}

	public class MailSistema extends MouseAdapter {

		private final String endereco;

		public MailSistema(String endereco) {
			this.endereco = endereco;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			Desktop desktop = null;
			try {
				URI uri = new URI("mailto:" + this.endereco);
				if (Desktop.isDesktopSupported()) {
					desktop = Desktop.getDesktop();
				}
				if (desktop != null) {
					desktop.mail(uri);
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			} catch (URISyntaxException uriSyntaxException) {
				uriSyntaxException.printStackTrace();
			}
		}
	}

}
