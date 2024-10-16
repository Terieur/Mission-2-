package mission2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AffecterCommande extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AffecterCommande frame = new AffecterCommande();
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
	public AffecterCommande() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 367);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel fond = new JPanel();
		fond.setBounds(0, 0, 526, 77);
		contentPane.add(fond);
		fond.setLayout(null);
		
		JLabel Titre = new JLabel("Affecter une commande à préparer");
		Titre.setFont(new Font("Arial", Font.BOLD, 12));
		Titre.setBounds(20, 32, 203, 14);
		fond.add(Titre);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setForeground(new Color(0, 0, 0));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRetour.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRetour.setBackground(Color.WHITE);
		btnRetour.setBounds(0, 0, 89, 23);
		fond.add(btnRetour);
		
		JLabel Titre_1 = new JLabel("1 - Sélectionner une commande à préparer");
		Titre_1.setFont(new Font("Arial", Font.ITALIC, 11));
		Titre_1.setBounds(22, 88, 223, 14);
		contentPane.add(Titre_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		comboBox.setBounds(343, 113, 90, 22);
		contentPane.add(comboBox);
		
		JLabel Titre_1_1 = new JLabel("2 - Sélectionner votre matricule");
		Titre_1_1.setFont(new Font("Arial", Font.ITALIC, 11));
		Titre_1_1.setBounds(343, 88, 161, 14);
		contentPane.add(Titre_1_1);
		
		JLabel Titre_1_1_1 = new JLabel("3 - Confirmer votre affectation");
		Titre_1_1_1.setFont(new Font("Arial", Font.ITALIC, 11));
		Titre_1_1_1.setBounds(343, 157, 161, 14);
		contentPane.add(Titre_1_1_1);
		
		JButton btnNewButton = new JButton("Affecter");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 11));
		btnNewButton.setBounds(343, 182, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
