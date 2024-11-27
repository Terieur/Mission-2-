package mission;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTable;

public class listeProduit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					listeProduit frame = new listeProduit();
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
	public listeProduit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 54);
		panel.setBackground(new Color(233, 177, 177));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("←");
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton.setBackground(new Color(233, 177, 177));
		btnNewButton.setBounds(0, 0, 50, 23);
		panel.add(btnNewButton);
		
		JTextPane txtpnListeDesCommande = new JTextPane();
		txtpnListeDesCommande.setText("Liste des produits");
		txtpnListeDesCommande.setFont(new Font("Arial", Font.PLAIN, 12));
		txtpnListeDesCommande.setBackground(new Color(233, 177, 177));
		txtpnListeDesCommande.setBounds(155, 13, 116, 20);
		panel.add(txtpnListeDesCommande);
		
		table = new JTable();
		table.setBounds(0, 54, 435, 101);
		contentPane.add(table);
	}
}
