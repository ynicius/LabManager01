package views.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class limitaFormato extends PlainDocument {

	public enum TipoEntrada {
		NUMEROINTEIRO, NUMERODECIMAL, NOME, EMAIL, DATA, MAIUSCULO, CODIGO, NOMENUMEROS, NADA, NUMERETRACO;
	};

	private int qtdCaracteres;
	private TipoEntrada tpEntrada;
	private boolean stringUp;

	public limitaFormato(int qtdCaracteres, TipoEntrada tpEntrada, boolean stringUp) {
		this.qtdCaracteres = qtdCaracteres;
		this.tpEntrada = tpEntrada;
		this.stringUp = stringUp;
	}

	@Override
	public void insertString(int i, String string, AttributeSet as) throws BadLocationException {
		if (string == null || getLength() == qtdCaracteres) {
			return;
		}
		int totalCarac = getLength() + string.length();
		// filtro de caracteres
		String regex = "";
		switch (tpEntrada) {
		case NUMEROINTEIRO:
			regex = "[^0-9]";
			break;
		case NUMERODECIMAL:
			regex = "[^0-9,.]";
			break;
		case NOME:
			regex = "[^\\p{IsLatin} ]";
			break;
		case EMAIL:
			regex = "[^\\p{IsLatin}@.\\-_][^0-9]";
			break;
		case DATA:
			regex = "[^0-9/]";
			break;
		case CODIGO:
			regex = "[^0-9\\p{IsLatin}]";
			// .replaceAll("[^\\p{ASCII}]", "");		
			break;
		case NOMENUMEROS:
			regex = "[^0-9\\p{IsLatin} -]";
			// .replaceAll("[^\\p{ASCII}]", "");		
			break;
		case NADA:
			regex = "[]";
			// .replaceAll("[^\\p{ASCII}]", "");		
			break;
		case NUMERETRACO:
			regex = "[^0-9.-]";
			// .replaceAll("[^\\p{ASCII}]", "");		
			break;

		}
		// fazendo a substituição
		string = string.replaceAll(regex, "");

		if (totalCarac <= qtdCaracteres) {
			if (stringUp == true) {
				super.insertString(i, string.toUpperCase(), as);
			} else {
				super.insertString(i, string, as); // To change body of generated methods, choose Tools | Templates.
			}

		} else {
			String nova = string.substring(0, qtdCaracteres);
			super.insertString(i, nova, as);
		}
	}

}
