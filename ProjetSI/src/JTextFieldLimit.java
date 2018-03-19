import javax.swing. * ;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends JTextField {
	// text field modifier permettant de rentrer un nombre limite de charactère
	private static final long serialVersionUID = 1L;
	private int L;
	JTextFieldLimit(int L) {
		super();
		this.L = L;
	}

	protected Document createDefaultModel() {
		return new LimitDocument();
	}

	// regarde si chaine de characère est plus grande que la valeur limite si ou suprime le dernier charact
	private class LimitDocument extends PlainDocument {

		private static final long serialVersionUID = 1L;

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null) return;
			if (Character.isDigit(str.charAt(0)) && this.getLength() + str.length() <= L) super.insertString(offset, str, attr);
		}
	}
}