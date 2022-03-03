package ex1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrame1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textOperand_1;
	private JTextField textOperand_2;
	private JLabel lblResult;

	private class InvalidParametersError extends Exception {
		String text;

		public InvalidParametersError(String text) {
			this.text = text;
		}

		public String toString() {
			return text;
		}

		private static final long serialVersionUID = 1L;
	}

	private void removeFrontalZeros() {
		textOperand_1.setText(textOperand_1.getText().replaceFirst("^0+(?!$)", ""));
		textOperand_2.setText(textOperand_2.getText().replaceFirst("^0+(?!$)", ""));
	}

	private void validInput(String text) throws InvalidParametersError {
		if (text.length() == 0)
			throw new InvalidParametersError("Unul dintre campuri este gol");

		for (char c : text.toCharArray()) {
			if (!Character.isDigit(c))
				throw new InvalidParametersError("Campurile pot contine doar cifre");
		}
	}

	private void validInputs(operand o) throws InvalidParametersError {
		validInput(textOperand_1.getText());
		validInput(textOperand_2.getText());
		removeFrontalZeros();
		if (o == operand.impartire) {
			if (textOperand_2.getText().contentEquals("0"))
				throw new InvalidParametersError("Nu se poate efectua impartire la zero");
		}
	}

	private enum operand {
		adunare, scadere, inmultire, impartire
	}

	private void performNumericOperation(operand o) {
		try {
			validInputs(o);

			int x, y, result;

			x = Integer.parseInt(textOperand_1.getText());
			y = Integer.parseInt(textOperand_2.getText());

			switch (o) {
			case adunare:
				result = x + y;
				break;
			case scadere:
				result = x - y;
				break;
			case inmultire:
				result = x * y;
				break;
			case impartire:
				result = x / y;
				break;
			default:
				result = x;
				break;
			}

			lblResult.setText(Integer.toString(result));
		} catch (InvalidParametersError error) {
			JOptionPane.showMessageDialog(new JFrame(), error.toString(), "Dialog", JOptionPane.ERROR_MESSAGE);
		}
	}

	private class AdunareButtonPress implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			performNumericOperation(operand.adunare);
		}
	}

	private class ScadereButtonPress implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			performNumericOperation(operand.scadere);
		}
	}

	private class InmultireButtonPress implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			performNumericOperation(operand.inmultire);
		}
	}

	private class ImpartireButtonPress implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			performNumericOperation(operand.impartire);
		}
	}

	private class ClearFields implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			textOperand_1.setText("");
			textOperand_2.setText("");
			lblResult.setText("");
		}

	}

	public JFrame1() {
		setResizable(false);
		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 243, 289);
		setLocation(600, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textOperand_1 = new JTextField();
		textOperand_1.setBounds(94, 26, 122, 20);
		contentPane.add(textOperand_1);
		textOperand_1.setColumns(10);

		JLabel lblOperand_1 = new JLabel("Operand 1");
		lblOperand_1.setBounds(20, 29, 64, 14);
		contentPane.add(lblOperand_1);

		JLabel lblOperand_2 = new JLabel("Operand 2");
		lblOperand_2.setBounds(20, 68, 64, 14);
		contentPane.add(lblOperand_2);

		textOperand_2 = new JTextField();
		textOperand_2.setColumns(10);
		textOperand_2.setBounds(94, 65, 122, 20);
		contentPane.add(textOperand_2);

		JButton btnAdunare = new JButton("Adunare");
		btnAdunare.addActionListener(new AdunareButtonPress());
		btnAdunare.setBounds(10, 116, 89, 23);
		contentPane.add(btnAdunare);

		JButton btnScadere = new JButton("Scadere");
		btnScadere.addActionListener(new ScadereButtonPress());
		btnScadere.setBounds(127, 116, 89, 23);
		contentPane.add(btnScadere);

		JButton Inmultire = new JButton("Inmultire");
		Inmultire.addActionListener(new InmultireButtonPress());
		Inmultire.setBounds(10, 150, 89, 23);
		contentPane.add(Inmultire);

		JButton btnImpartire = new JButton("Impartire");
		btnImpartire.addActionListener(new ImpartireButtonPress());
		btnImpartire.setBounds(127, 150, 89, 23);
		contentPane.add(btnImpartire);

		lblResult = new JLabel("");
		lblResult.setBackground(Color.WHITE);
		lblResult.setForeground(Color.BLACK);
		lblResult.setBounds(10, 196, 206, 14);
		lblResult.setOpaque(true);
		contentPane.add(lblResult);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ClearFields());
		btnClear.setBounds(68, 221, 89, 23);
		contentPane.add(btnClear);
	}
}
