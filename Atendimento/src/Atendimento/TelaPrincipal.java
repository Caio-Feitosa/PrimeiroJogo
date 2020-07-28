package Atendimento;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Scrollbar;
import java.awt.Canvas;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.JMenuBar;

public class TelaPrincipal {
	Fila fila_Lazaro = new Fila();
	Fila fila_Elisvania = new Fila();
	private ButtonGroup bgPrecedencia = new ButtonGroup();
	private ButtonGroup bgDoutor = new ButtonGroup();
	public String eAtual = new String();
	public String lAtual = new String();
	private JTextField textFieldNomePaciente;
	private JFrame frmAoSocial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("CALIBRI", Font.PLAIN, 18)));
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmAoSocial.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmAoSocial = new JFrame();
		frmAoSocial.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmAoSocial.getContentPane().setBackground(Color.WHITE);
		frmAoSocial.setTitle("A\u00E7\u00E3o Social");
		frmAoSocial.setResizable(false);
		frmAoSocial.setBounds(100, 100, 822, 512);
		frmAoSocial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAoSocial.getContentPane().setLayout(null);
		
		JButton btnButtonMostrar1 = new JButton("Mostrar fila");
		btnButtonMostrar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (!fila_Lazaro.isEmpty())
						JOptionPane.showMessageDialog(null, fila_Lazaro.toString(), "", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Não há pacientes nesta fila!", "  Atenção",
								JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnButtonMostrar1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnButtonMostrar1.setBounds(622, 338, 125, 25);
		frmAoSocial.getContentPane().add(btnButtonMostrar1);
		
		JButton ButtonTamanhoFila = new JButton("Fila geral");
		ButtonTamanhoFila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Fila do doutor Lázaro:  " + fila_Lazaro.sizeGeral() + 
								"\nFila da doutora Elisvania:  " + fila_Elisvania.sizeGeral() + "\nQuantidade Final:  "
								+ (fila_Lazaro.sizeGeral() + fila_Elisvania.sizeGeral()), "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		ButtonTamanhoFila.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonTamanhoFila.setBounds(527, 419, 160, 25);
		frmAoSocial.getContentPane().add(ButtonTamanhoFila);
		
		JLabel lblpAtual1 = new JLabel("");
		lblpAtual1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblpAtual1.setBackground(Color.WHITE);
		lblpAtual1.setBounds(527, 263, 255, 23);
		frmAoSocial.getContentPane().add(lblpAtual1);
		
		JButton ButtonChamar1 = new JButton("Chamar");
		ButtonChamar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fila_Lazaro.isEmpty()) {
					lAtual = fila_Lazaro.atenderCliente();
					JOptionPane.showMessageDialog(null, "Próximo paciente: " + lAtual, "", JOptionPane.INFORMATION_MESSAGE);
					lblpAtual1.setText(lAtual);
				}
				else
					JOptionPane.showMessageDialog(null, "Não há pacientes nesta fila!", "  Atenção",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		ButtonChamar1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonChamar1.setBounds(427, 338, 125, 25);
		frmAoSocial.getContentPane().add(ButtonChamar1);
		
		JLabel lblCadastroDePaciente = new JLabel("Cadastro de Paciente");
		lblCadastroDePaciente.setBackground(Color.WHITE);
		lblCadastroDePaciente.setFont(new Font("Arial", Font.BOLD, 23));
		lblCadastroDePaciente.setBounds(32, 31, 268, 25);
		frmAoSocial.getContentPane().add(lblCadastroDePaciente);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBackground(Color.WHITE);
		lblNome.setFont(new Font("Arial", Font.BOLD, 19));
		lblNome.setBounds(32, 74, 68, 25);
		frmAoSocial.getContentPane().add(lblNome);
		
		textFieldNomePaciente = new JTextField();
		textFieldNomePaciente.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldNomePaciente.setBounds(32, 107, 339, 30);
		frmAoSocial.getContentPane().add(textFieldNomePaciente);
		textFieldNomePaciente.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 54, 373, 2);
		frmAoSocial.getContentPane().add(separator);
		
		JLabel lblPrecedncia = new JLabel("Preced\u00EAncia");
		lblPrecedncia.setBackground(Color.WHITE);
		lblPrecedncia.setFont(new Font("Arial", Font.BOLD, 19));
		lblPrecedncia.setBounds(32, 157, 125, 25);
		frmAoSocial.getContentPane().add(lblPrecedncia);
		
		JRadioButton rdbtnDrLzaro = new JRadioButton("Dr. L\u00E1zaro");
		rdbtnDrLzaro.setBackground(Color.WHITE);
		rdbtnDrLzaro.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnDrLzaro.setBounds(32, 286, 109, 23);
		frmAoSocial.getContentPane().add(rdbtnDrLzaro);
		
		JRadioButton rdbtnDraElisvania = new JRadioButton("Dra. Elisvania");
		rdbtnDraElisvania.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnDraElisvania.setBackground(Color.WHITE);
		rdbtnDraElisvania.setBounds(231, 286, 140, 23);
		frmAoSocial.getContentPane().add(rdbtnDraElisvania);
		
		JRadioButton rdbtnPrioridade = new JRadioButton("Prioridade");
		rdbtnPrioridade.setBackground(Color.WHITE);
		rdbtnPrioridade.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnPrioridade.setBounds(32, 196, 109, 23);
		frmAoSocial.getContentPane().add(rdbtnPrioridade);
		
		JRadioButton rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setBackground(Color.WHITE);
		rdbtnNormal.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnNormal.setBounds(231, 196, 109, 23);
		frmAoSocial.getContentPane().add(rdbtnNormal);
		
		bgPrecedencia.add(rdbtnPrioridade);
		bgPrecedencia.add(rdbtnNormal);
		
		bgDoutor.add(rdbtnDrLzaro);
		bgDoutor.add(rdbtnDraElisvania);
		
		JLabel lblPacienteDe = new JLabel("Paciente de:");
		lblPacienteDe.setFont(new Font("Arial", Font.BOLD, 19));
		lblPacienteDe.setBackground(Color.WHITE);
		lblPacienteDe.setBounds(32, 246, 125, 25);
		frmAoSocial.getContentPane().add(lblPacienteDe);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(405, 40, 12, 421);
		frmAoSocial.getContentPane().add(separator_1);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int entrou = 0;
				
				if (textFieldNomePaciente.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Como o paciente se chama?","  Atenção", JOptionPane.ERROR_MESSAGE);
					entrou = 1;
				} else {
					if (!rdbtnPrioridade.isSelected() && !rdbtnNormal.isSelected()) {
						JOptionPane.showMessageDialog(null, "Qual é a prioridade do paciente?","  Atenção",
								JOptionPane.ERROR_MESSAGE);
						entrou = 1;
					} else {
						if (!rdbtnDrLzaro.isSelected() && !rdbtnDraElisvania.isSelected()) {
							JOptionPane.showMessageDialog(null, "Quem atenderá o paciente?","  Atenção",
									JOptionPane.ERROR_MESSAGE);
							entrou = 1;
						}
					}
				}
				if (entrou == 0) {
					if (rdbtnDrLzaro.isSelected()) {
						if (rdbtnPrioridade.isSelected())
							fila_Lazaro.inserirPrioridade(textFieldNomePaciente.getText());
						else
							fila_Lazaro.inserirNormal(textFieldNomePaciente.getText());
						
					} else {
						if (rdbtnPrioridade.isSelected())
							fila_Elisvania.inserirPrioridade(textFieldNomePaciente.getText());
						else
							fila_Elisvania.inserirNormal(textFieldNomePaciente.getText());
						
					}
					
					JOptionPane.showMessageDialog(null, "Paciente inserido com sucesso!");
					textFieldNomePaciente.setText("");
					bgPrecedencia.clearSelection();
					bgDoutor.clearSelection();
				}
			}
		});
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnConfirmar.setBounds(140, 366, 109, 30);
		frmAoSocial.getContentPane().add(btnConfirmar);
		
		JLabel lblPrximoPacientePara = new JLabel("Doutor L\u00E1zaro");
		lblPrximoPacientePara.setBackground(Color.WHITE);
		lblPrximoPacientePara.setFont(new Font("Arial", Font.BOLD, 20));
		lblPrximoPacientePara.setBounds(638, 229, 140, 23);
		frmAoSocial.getContentPane().add(lblPrximoPacientePara);
		
		JLabel lblPrximoPacientePara_1 = new JLabel("Doutora Elisvania");
		lblPrximoPacientePara_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblPrximoPacientePara_1.setBackground(Color.WHITE);
		lblPrximoPacientePara_1.setBounds(610, 49, 172, 25);
		frmAoSocial.getContentPane().add(lblPrximoPacientePara_1);
		
		JLabel lblpAtual2 = new JLabel("");
		lblpAtual2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblpAtual2.setBackground(Color.WHITE);
		lblpAtual2.setBounds(527, 80, 255, 25);
		frmAoSocial.getContentPane().add(lblpAtual2);
		
		JButton btnChamar2 = new JButton("Chamar");
		btnChamar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fila_Elisvania.isEmpty()) {
					eAtual = fila_Elisvania.atenderCliente();
					JOptionPane.showMessageDialog(null, "Próximo paciente: " + eAtual, "", JOptionPane.INFORMATION_MESSAGE);
					lblpAtual2.setText(eAtual);
				}
				else
					JOptionPane.showMessageDialog(null, "Não há pacientes nesta fila!", "  Atenção",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		btnChamar2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChamar2.setBounds(427, 157, 125, 25);
		frmAoSocial.getContentPane().add(btnChamar2);
		
		JLabel lblAtendendo2 = new JLabel("Atendendo: ");
		lblAtendendo2.setBackground(Color.WHITE);
		lblAtendendo2.setFont(new Font("Arial", Font.BOLD, 19));
		lblAtendendo2.setBounds(415, 80, 125, 21);
		frmAoSocial.getContentPane().add(lblAtendendo2);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(405, 72, 377, 2);
		frmAoSocial.getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(405, 252, 373, 2);
		frmAoSocial.getContentPane().add(separator_3);
		
		JLabel lblAtendendo1 = new JLabel("Atendendo:");
		lblAtendendo1.setFont(new Font("Arial", Font.BOLD, 19));
		lblAtendendo1.setBackground(Color.WHITE);
		lblAtendendo1.setBounds(415, 263, 111, 21);
		frmAoSocial.getContentPane().add(lblAtendendo1);
		
		JButton btnButtonMostrar2 = new JButton("Mostrar fila");
		btnButtonMostrar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fila_Elisvania.isEmpty())
					JOptionPane.showMessageDialog(null, fila_Elisvania.toString(), "", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Não há pacientes nesta fila!", "  Atenção",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		btnButtonMostrar2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnButtonMostrar2.setBounds(622, 157, 125, 25);
		frmAoSocial.getContentPane().add(btnButtonMostrar2);
		
	}
}