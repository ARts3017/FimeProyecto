
package Login;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import config.Conexion;
import vista.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
public class Login extends javax.swing.JFrame {

    
           ///Para establecer la conexion a la base de datos
    Conexion cone = new Conexion();
    Connection miConexion;
    Statement miStatement;
    ResultSet rs;
    DefaultTableModel modelo;
    
    
    
    
    public Login() {
        initComponents();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setSize(900, 550);
      
        setTitle("Dentil");
        
     
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel5 = new javax.swing.JPanel();
        lab6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lab9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lab2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(250, 100));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        panel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 180, 10));

        jPasswordField1.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField1.setBorder(null);
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });
        panel1.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 250, 30));

        jPanel5.setBackground(new java.awt.Color(0, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lab6.setBackground(new java.awt.Color(0, 255, 255));
        lab6.setFont(new java.awt.Font("avocado", 0, 36)); // NOI18N
        lab6.setForeground(new java.awt.Color(255, 255, 255));
        lab6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab6.setText("Iniciar Sesion");
        lab6.setToolTipText("");
        lab6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lab6MouseClicked(evt);
            }
        });
        jPanel5.add(lab6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 270, 70));

        panel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 270, 80));

        jSeparator2.setForeground(new java.awt.Color(0, 153, 153));
        panel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 230, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 204));
        jLabel5.setText("Usuario:");
        panel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));
        panel1.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 150, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 204));
        jLabel2.setText("Contraseña:");
        panel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, -1));

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        panel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 150, 30));

        lab9.setFont(new java.awt.Font("avocado", 1, 36)); // NOI18N
        lab9.setForeground(new java.awt.Color(0, 153, 153));
        lab9.setText("DENTIL");
        panel1.add(lab9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 130, 50));

        jPanel3.setBackground(new java.awt.Color(102, 153, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lab2.setFont(new java.awt.Font("avocado", 0, 36)); // NOI18N
        lab2.setForeground(new java.awt.Color(255, 255, 255));
        lab2.setText("  Registrarse");
        lab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lab2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lab2MousePressed(evt);
            }
        });
        jPanel3.add(lab2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 230, 40));

        panel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 260, 60));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/484717.jpg"))); // NOI18N
        panel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 140, 200));

        jPanel1.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 310, 490));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/por2.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 660));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lab2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lab2MousePressed

    }//GEN-LAST:event_lab2MousePressed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyTyped
       
// TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1KeyTyped

    private void lab6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lab6MouseClicked
        String user = txtUsuario.getText();
        int pass = Integer.parseInt(txtContraseña.getText());
       
        String email = "";
        String puesto= "";
        String query = "SELECT * FROM empleado";
        boolean band = false;
        
          try{
            miConexion = cone.getConnection();
            Statement miStatement = miConexion.createStatement();
            ResultSet RS = miStatement.executeQuery(query);
            while(RS.next()){
                email = RS.getString("emplemail");
                 int id  = Integer.parseInt(RS.getString("emplid"));
                if(email.equals(user) && pass == id){
                   band = true;
                   puesto = RS.getString("emplpuesto");
                   if(puesto.equals("Administrador Global")){
                       AdministradorGlobal A = new AdministradorGlobal();
                        A.setVisible(true);
                        A.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); ///Oculta la aplicacion
                        A.setLocationRelativeTo(null);   
                   } 
                   if(puesto.equals("Secretaria")){
                       ModuloSecretaria A = new ModuloSecretaria();
                        A.setVisible(true);
                        A.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); ///Oculta la aplicacion
                        A.setLocationRelativeTo(null);   
                   } 
                    if(puesto.equals("Gerente")){
                       ModuloGerentes A = new ModuloGerentes();
                        A.setVisible(true);
                        A.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); ///Oculta la aplicacion
                        A.setLocationRelativeTo(null);   
                   } 
                   if(puesto.equals("Doctor")){
                       ModuloDoctores A = new ModuloDoctores();
                        A.setVisible(true);
                        A.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); ///Oculta la aplicacion
                        A.setLocationRelativeTo(null);   
                   } 
                    if(puesto.equals("Enfermero")){
                       moduloEnfermeros A = new moduloEnfermeros();
                        A.setVisible(true);
                        A.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); ///Oculta la aplicacion
                        A.setLocationRelativeTo(null);   
                   }
                   if(puesto.equals("Quimico")){
                       moduloQuimicos A = new moduloQuimicos();
                        A.setVisible(true);
                        A.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); ///Oculta la aplicacion
                        A.setLocationRelativeTo(null);   
                   }
                    if(puesto.equals("Almacenista")){
                        moduloInventario A = new moduloInventario();
                        A.setVisible(true);
                        A.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); ///Oculta la aplicacion
                        A.setLocationRelativeTo(null);   
                   }
                }
            }  
        }catch(Exception e){
             e.printStackTrace();
        }
        if(band == false)
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos, vuelve a intentar");
        txtUsuario.setText("");
        txtContraseña.setText("");
        
        
        
        
        
    }//GEN-LAST:event_lab6MouseClicked

    private void lab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lab2MouseClicked
        Registro R = new Registro();
        R.setVisible(true);
        R.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); ///Oculta la aplicacion
        R.setLocationRelativeTo(null);
    }//GEN-LAST:event_lab2MouseClicked

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lab2;
    private javax.swing.JLabel lab6;
    private javax.swing.JLabel lab9;
    private javax.swing.JPanel panel1;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
