package mission2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Import for dialog
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class AffecterCommande extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private JComboBox<String> matriculeComboBox; 
    private int selectedCommandId = -1; 

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

    public AffecterCommande() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 530, 367);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        
        JPanel fond = new JPanel();
        fond.setBounds(0, 0, 526, 77);
        contentPane.add(fond);
        fond.setLayout(null);

       
        JLabel Titre = new JLabel("Affecter une commande à préparer");
        Titre.setFont(new Font("Arial", Font.BOLD, 14));
        Titre.setBounds(20, 32, 300, 20);
        fond.add(Titre);

        
        JButton btnRetour = new JButton("Retour");
        btnRetour.setForeground(Color.BLACK);
        btnRetour.setFont(new Font("Arial", Font.PLAIN, 11));
        btnRetour.setBackground(Color.WHITE);
        btnRetour.setBounds(0, 0, 89, 23);
        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action à définir pour le bouton Retour
            }
        });
        fond.add(btnRetour);

       
        JLabel Titre_1 = new JLabel("1 - Sélectionner une commande à préparer");
        Titre_1.setFont(new Font("Arial", Font.ITALIC, 12));
        Titre_1.setBounds(22, 88, 250, 20);
        contentPane.add(Titre_1);

        JLabel Titre_1_1 = new JLabel("2 - Sélectionner votre matricule");
        Titre_1_1.setFont(new Font("Arial", Font.ITALIC, 12));
        Titre_1_1.setBounds(343, 88, 250, 20);
        contentPane.add(Titre_1_1);

        JLabel Titre_1_1_1 = new JLabel("3 - Confirmer votre affectation");
        Titre_1_1_1.setFont(new Font("Arial", Font.ITALIC, 12));
        Titre_1_1_1.setBounds(343, 157, 250, 20);
        contentPane.add(Titre_1_1_1);

        
        matriculeComboBox = new JComboBox<>();
        matriculeComboBox.setForeground(Color.BLACK);
        matriculeComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
        matriculeComboBox.setBounds(343, 113, 90, 22); 
        contentPane.add(matriculeComboBox);

   
        JButton btnAffecter = new JButton("Affecter");
        btnAffecter.setBackground(Color.WHITE);
        btnAffecter.setFont(new Font("Arial", Font.PLAIN, 11));
        btnAffecter.setBounds(343, 182, 89, 23);
        btnAffecter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                affectCommandToPreparator(); 
            }
        });
        contentPane.add(btnAffecter);

        
        table = new JTable();
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Date Commande");
        table.setModel(model);
        table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.setBounds(32, 117, 278, 103);
        contentPane.add(table);

        
        loadTableData();
        loadMatricules(); 

        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    selectedCommandId = (int) model.getValueAt(row, 0); 
                }
            }
        });
    }

   
    private void loadTableData() {
        Connection conn = connexionBaseDeDonnees.getConnection(); 
        if (conn != null) {
            try {
                
                String query = "SELECT id, dateCommande FROM commande WHERE preparateur_id IS NULL";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                // Clear existing rows
                model.setRowCount(0);

                
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String dateCommande = rs.getString("dateCommande");
                    model.addRow(new Object[]{id, dateCommande});
                }
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Erreur : Connexion à la base de données non établie.");
        }
    }

    
    private void loadMatricules() {
        Connection conn = connexionBaseDeDonnees.getConnection(); 
        if (conn != null) {
            try {
                String query = "SELECT matricule FROM preparateur"; 
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

               
                matriculeComboBox.removeAllItems();

                
                while (rs.next()) {
                    String matricule = rs.getString("matricule");
                    matriculeComboBox.addItem(matricule); 
                }
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Erreur : Connexion à la base de données non établie.");
        }
    }

   
    private void affectCommandToPreparator() {
        if (selectedCommandId == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une commande à préparer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String selectedMatricule = (String) matriculeComboBox.getSelectedItem();
        if (selectedMatricule == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un matricule.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        int preparateurId = getPreparatorId(selectedMatricule);
        if (preparateurId == -1) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération de l'ID du préparateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        Connection conn = connexionBaseDeDonnees.getConnection();
        if (conn != null) {
            try {
                String query = "UPDATE commande SET preparateur_id = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, preparateurId);
                stmt.setInt(2, selectedCommandId);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    
                    removeCommandFromTable(selectedCommandId); 

                    JOptionPane.showMessageDialog(this, "Commande affectée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    selectedCommandId = -1; // Reset selection
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'affectation de la commande.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Erreur : Connexion à la base de données non établie.");
        }
    }

    
    private void removeCommandFromTable(int commandId) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(commandId)) { 
                model.removeRow(i); 
                break; 
            }
        }
    }

    
    private int getPreparatorId(String matricule) {
        int preparateurId = -1; 
        Connection conn = connexionBaseDeDonnees.getConnection(); 
        if (conn != null) {
            try {
                
                String query = "SELECT id FROM preparateur WHERE matricule = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, matricule); 
                ResultSet rs = stmt.executeQuery();

                
                if (rs.next()) {
                    preparateurId = rs.getInt("id"); 
                }
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Erreur : Connexion à la base de données non établie.");
        }
        return preparateurId; 
    }
}
