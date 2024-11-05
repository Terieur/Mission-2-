package mission2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import javax.swing.table.TableCellRenderer;

public class listeCommande extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table_1;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    listeCommande frame = new listeCommande();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public listeCommande() {
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
        txtpnListeDesCommande.setText("Liste des commandes à préparer");
        txtpnListeDesCommande.setFont(new Font("Arial", Font.PLAIN, 12));
        txtpnListeDesCommande.setBackground(new Color(233, 177, 177));
        txtpnListeDesCommande.setBounds(120, 11, 191, 20);
        panel.add(txtpnListeDesCommande);

        // Initialiser le modèle
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Numero de commande", "Date commande", "Livraison commande", "Prix commande"
            }
        );

        // Créer la table et appliquer le modèle
        table_1 = new JTable(model);
        table_1.setBounds(1, 54, 434, 97);
        
        // Optionnel : personnaliser l'apparence des en-têtes de colonne
        table_1.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table_1.getTableHeader().setBackground(new Color(233, 177, 177));
        table_1.getTableHeader().setForeground(Color.BLACK);

        contentPane.add(table_1);

        // Appel de la méthode pour charger les données de la base de données
        chargerDonnees();
    }

    private void chargerDonnees() {
        try {
            // Connexion à la base de données
            Connection conn = ConnexionBaseDeDonnees.getConnection();
            if (conn != null) {
                Statement stmt = conn.createStatement();

                // Requête pour récupérer les données
                String query = """
                    SELECT 
                        c.id AS numero_commande, 
                        c.dateCommande AS date_commande, 
                        b.dateLivraison AS livraison_commande, 
                        SUM(dp.qteCmd * p.prixPdt) AS prix_commande
                    FROM 
                        CmdApproDepot c
                    LEFT JOIN 
                        BonDeLivraison b ON c.id = b.id
                    LEFT JOIN 
                        DetailsProduit dp ON c.id = dp.idCommande
                    LEFT JOIN 
                        Produit p ON dp.idProduit = p.id
                    GROUP BY 
                        c.id, c.dateCommande, b.dateLivraison
                """;

                ResultSet rs = stmt.executeQuery(query);

                // Parcourir les résultats et les ajouter au modèle de table
                while (rs.next()) {
                    int numeroCommande = rs.getInt("numero_commande");
                    Date dateCommande = rs.getDate("date_commande");
                    Date livraisonCommande = rs.getDate("livraison_commande");
                    double prixCommande = rs.getDouble("prix_commande");

                    // Ajouter les données au modèle de table
                    model.addRow(new Object[]{
                        numeroCommande, 
                        dateCommande, 
                        livraisonCommande, 
                        prixCommande
                    });
                }

                // Fermer la connexion et le statement
                rs.close();
                stmt.close();
                conn.close();
            } else {
                System.out.println("Connexion à la base de données échouée.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


