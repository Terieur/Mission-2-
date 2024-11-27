package mission;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class FormulaireCommande extends JFrame {
	//Attributes 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtQuantitSouhait;
	private JTable tableCommande;
	private JTable tableProduit;
    private DefaultTableModel modelProduit;
    private DefaultTableModel modelCommande;

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
		
		
		
		txtQuantitSouhait = new JTextField();
		txtQuantitSouhait.setToolTipText("Rentrer les quantité souhaité pour le produit");
		txtQuantitSouhait.setBounds(341, 121, 151, 20);
		contentPane.add(txtQuantitSouhait);
		txtQuantitSouhait.setColumns(2);
		
		
		
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setToolTipText("Retourner au menu précedent");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRetour.setBounds(0, 0, 102, 23);
		contentPane.add(btnRetour);
		
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBackground(Color.WHITE);
		btnAjouter.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAjouter.setToolTipText("Ajouter le produit sélectionné dans la commande");
		btnAjouter.setBounds(341, 152, 151, 23);
		contentPane.add(btnAjouter);
		
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(Color.WHITE);
		btnSupprimer.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSupprimer.setToolTipText("Supprimer le produit sélectionné de la commande");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSupprimer.setBounds(341, 262, 151, 23);
		contentPane.add(btnSupprimer);
		
		
		JButton btnCommande = new JButton("Envoyer la commande");
		btnCommande.setBackground(Color.WHITE);
		btnCommande.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCommande.setToolTipText("Envoyer la commande à l'entrepôt");
		btnCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCommande.setBounds(160, 387, 187, 23);
		contentPane.add(btnCommande);
		
		
		
		JLabel Jlb_Formulaire = new JLabel("Formulaire de commande");
		Jlb_Formulaire.setBounds(112, 11, 353, 31);
		Jlb_Formulaire.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		contentPane.add(Jlb_Formulaire);
		
		
		JLabel Jlb_Ajouter = new JLabel("Ajouter le produit sélectionnée:");
		Jlb_Ajouter.setBounds(331, 71, 187, 14);
		contentPane.add(Jlb_Ajouter);
		
		
		JLabel Jlb_Supprimer = new JLabel("Supprimer le produit sélectionnée:");
		Jlb_Supprimer.setBounds(331, 237, 211, 14);
		contentPane.add(Jlb_Supprimer);
		
		
		JLabel Jlb_Entrepot = new JLabel("Produits en Entrepôt :\r\n");
		Jlb_Entrepot.setFont(new Font("Tahoma", Font.BOLD, 14));
		Jlb_Entrepot.setBounds(41, 53, 176, 14);
		contentPane.add(Jlb_Entrepot);
		
		
		JLabel Jlb_ProduitAjouter = new JLabel("Sélectionner un produit a ajouter :\r\n\r\n");
		Jlb_ProduitAjouter.setFont(new Font("Tahoma", Font.ITALIC, 11));
		Jlb_ProduitAjouter.setBounds(41, 71, 192, 14);
		contentPane.add(Jlb_ProduitAjouter);
		
		
		JLabel Jlb_ResumerCommande = new JLabel("Résumer de la commande :\r\n");
		Jlb_ResumerCommande.setFont(new Font("Tahoma", Font.BOLD, 14));
		Jlb_ResumerCommande.setBounds(41, 214, 279, 14);
		contentPane.add(Jlb_ResumerCommande);
		
		
		JLabel Jlb_ProduitSupprimer = new JLabel("Sélectionner un produit a supprimer:\r\n\r\n");
		Jlb_ProduitSupprimer.setFont(new Font("Tahoma", Font.ITALIC, 11));
		Jlb_ProduitSupprimer.setBounds(41, 237, 192, 14);
		contentPane.add(Jlb_ProduitSupprimer);
		
		
        JLabel Jlb_Quantite = new JLabel("Quantité souhaité :");
        Jlb_Quantite.setBounds(341, 108, 151, 14);
        contentPane.add(Jlb_Quantite);
	

        
		tableProduit = new JTable();
		tableProduit.setToolTipText("Liste de tous les produits disponible ");
		tableProduit.setBounds(42, 87, 278, 103);
	    tableProduit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    tableProduit.setFont(new Font("Arial", Font.PLAIN, 11));
	    
        
        
		modelProduit = new DefaultTableModel();
	    modelProduit.addColumn("Nom Produits");
	    modelProduit.addColumn("Quantité en Entrepôt");
	    
	    tableProduit.setModel(modelProduit);
        
        loadTableData();
        
		tableCommande = new JTable();
		tableCommande.setToolTipText("Liste de tous les produit commandé et leur quantité ");
		tableCommande.setBounds(42, 251, 278, 103);
		tableCommande.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tableCommande.setFont(new Font("Arial", Font.PLAIN, 11));
        contentPane.add(tableCommande);
        
        
      	modelCommande = new DefaultTableModel();
	    contentPane.add(tableProduit);

        

    
    
        JScrollPane JscrollP_Produit = new JScrollPane(tableProduit);
        JscrollP_Produit.setBounds(42, 87, 278, 103);
        contentPane.add(JscrollP_Produit);
        
        JScrollPane JscrollP_Commande = new JScrollPane(tableCommande);
        JscrollP_Commande.setBounds(42, 251, 278, 103);
        contentPane.add(JscrollP_Commande);
        }



	private void loadTableData() {
        Connection conn = connexionBaseDeDonnees.getConnection(); 
        if (conn != null) {
            try {
                
                String query = "SELECT designationProduit, stockEntrepot FROM produit;";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                // Clear existing rows
                modelProduit.setRowCount(0);

                
                while (rs.next()) {
                    String NomProduit = rs.getString("designationProduit");
                    int QuantiteEntrepot = rs.getInt("stockEntrepot");


                    // Ajouter les données au modèle de table
                    modelProduit.addRow(new Object[]{
                    		NomProduit, 
                    		QuantiteEntrepot, 
                    });
                }
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Erreur : Connexion à la base de données non établie.");
        }
    }
}
