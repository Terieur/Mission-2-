package mission2;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FormulaireCommande extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtQuantitSouhait;
	private JTable table_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormulaireCommande frame = new FormulaireCommande();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormulaireCommande() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.setBounds(0, 0, 65, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Formulaire de commande");
		lblNewLabel.setBounds(112, 11, 353, 31);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Ajouter");
		btnNewButton_1.setBounds(368, 127, 151, 23);
		contentPane.add(btnNewButton_1);
		
		txtQuantitSouhait = new JTextField();
		txtQuantitSouhait.setText("Quantité souhaité");
		txtQuantitSouhait.setToolTipText("");
		txtQuantitSouhait.setBounds(368, 96, 151, 20);
		contentPane.add(txtQuantitSouhait);
		txtQuantitSouhait.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ajouter le produit sélectionnée:");
		lblNewLabel_1.setBounds(368, 71, 151, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Supprimer le produit sélectionnée:");
		lblNewLabel_1_1.setBounds(368, 237, 163, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton_1_1 = new JButton("Supprimer");
		btnNewButton_1_1.setBounds(368, 262, 151, 23);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Envoyer la commande");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1_1.setBounds(203, 385, 151, 23);
		contentPane.add(btnNewButton_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Produits en Entrepôt :\r\n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(41, 53, 176, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sélectionner un produit a ajouter :\r\n\r\n");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(41, 71, 192, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("Résumer de la commande :\r\n");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(41, 214, 279, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Sélectionner un produit a supprimer:\r\n\r\n");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3_1.setBounds(41, 237, 192, 14);
		contentPane.add(lblNewLabel_3_1);
		
		table_1 = new JTable();
		table_1.setBounds(42, 87, 278, 103);
		contentPane.add(table_1);
		
		table = new JTable();
		table.setBounds(42, 251, 278, 103);
		contentPane.add(table);
	}
}
