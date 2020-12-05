
package Login;
import config.Conexion;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import vista.Paciente;

public class Registro extends javax.swing.JFrame {

          ///Para establecer la conexion a la base de datos
    Conexion cone = new Conexion();
    Connection miConexion;
    Statement miStatement;
    ResultSet rs;
    DefaultTableModel modelo;
    
    
     public void agregar(){ ///Inserta nuevos regsitros en la tabla
    
        ///Captura los datos que hay en los text field
        String nombre = txtNombre.getText();
        String aPaterno = txtPaterno.getText();
        String aMaterno = txtMaterno.getText();
        String direccion = txtDireccion.getText();
        String puesto = (String)cmbPuesto.getSelectedItem();
        String departamento = txtDepartamento.getText();
        
        ///Para mandar la fecha
        int año = fechaNacimiento.getCalendar().get(Calendar.YEAR);
        int mes = fechaNacimiento.getCalendar().get(Calendar.MONTH)+1;
        int dia = fechaNacimiento.getCalendar().get(Calendar.DAY_OF_MONTH);
        String fecha =(año+"-"+mes+"-"+dia);
           ///
        int telefono = Integer.parseInt(txtTelefono.getText());
        int celular = Integer.parseInt(txtCelular.getText());
        String email = txtEmail.getText();
     
        
        String cel = txtCelular.getText();
        String tel = txtTelefono.getText();
       
        if(nombre.equals("") || aPaterno.equals("") || aMaterno.equals("") || cel.equals("") || direccion.equals("")
              || tel.equals("") || email.equals("") || puesto.equals("") || departamento.equals("")){
            
            JOptionPane.showMessageDialog(null,"Ingresa todos los campos requeridos");
        }
        else{
            String insert = "INSERT INTO empleado(emplnombre, emplpaterno, emplmaterno, emplfechanacimiento, empltelefono,"
                    + " emplcelular, emplemail, empldireccion, emplpuesto, empldepartamento) "
                    + "VALUES ('"+nombre+"', '"+aPaterno+"', '"+aMaterno+"', '"+fecha+"', '"+telefono+"', '"+celular+"', '"+email+"', '"+direccion+"', '"+puesto+"', '"+departamento+"')";
            try{
                miConexion = cone.getConnection();
                Statement miStatement = miConexion.createStatement();
                miStatement.executeUpdate(insert);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    
    }
     
     
       public void limpiaTextField(){
        txtNombre.setText("");
        txtPaterno.setText("");
        txtMaterno.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCelular.setText("");
       
        txtDepartamento.setText("");
        txtEmail.setText("");
       
        try { ///Coloca el jdatachooser con la fecha capturada en la tabla
               java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse("0000-01-01 ");
                fechaNacimiento.setDate(date);
            }catch (ParseException ex) {
                Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    public Registro() {
        initComponents();
        setTitle("Registro");
        setLocationRelativeTo(null);
        setSize(900, 550);
       
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lab6 = new javax.swing.JLabel();
        lab8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtPaterno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMaterno = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbPuesto = new javax.swing.JComboBox<>();
        txtDepartamento = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lab9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(250, 100));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        panel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 130, 10));

        jPanel5.setBackground(new java.awt.Color(0, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lab6.setBackground(new java.awt.Color(0, 0, 204));
        lab6.setFont(new java.awt.Font("avocado", 0, 36)); // NOI18N
        lab6.setForeground(new java.awt.Color(255, 255, 255));
        lab6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab6.setText("Registrarse");
        lab6.setToolTipText("");
        lab6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lab6MouseClicked(evt);
            }
        });
        jPanel5.add(lab6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 50));

        panel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 310, 60));

        lab8.setFont(new java.awt.Font("avocado", 1, 36)); // NOI18N
        lab8.setForeground(new java.awt.Color(0, 204, 204));
        lab8.setText("DENTIL");
        panel1.add(lab8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 130, 50));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 204));
        jLabel5.setText("Nombre:");
        panel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 100, 20));
        panel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 110, -1));
        panel1.add(txtPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 110, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 204, 204));
        jLabel6.setText("Apellido Paterno:");
        panel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 190, 20));
        panel1.add(txtMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 110, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 204, 204));
        jLabel7.setText("Fecha de Nacimiento:");
        panel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 250, 20));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 204, 204));
        jLabel8.setText("Apellido Materno:");
        panel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 200, 20));

        fechaNacimiento.setDateFormatString("yyyy/MM/dd");
        panel1.add(fechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 110, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 204));
        jLabel9.setText("Telefono:");
        panel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 110, 20));
        panel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 110, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 204, 204));
        jLabel10.setText("Celular:");
        panel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 90, 20));

        txtCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularActionPerformed(evt);
            }
        });
        panel1.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 120, -1));

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        panel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 120, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        jLabel11.setText("Email:");
        panel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 80, 20));

        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        panel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 120, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 204, 204));
        jLabel12.setText("Direccion:");
        panel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, 110, 20));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 204, 204));
        jLabel13.setText("Puesto:");
        panel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 90, 20));

        cmbPuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador Global", "Secretaria", "Gerente", "Doctor", "Enfermero", "Quimico", "Almacenista" }));
        cmbPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPuestoActionPerformed(evt);
            }
        });
        panel1.add(cmbPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, -1, -1));

        txtDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartamentoActionPerformed(evt);
            }
        });
        panel1.add(txtDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 120, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 204, 204));
        jLabel14.setText("Departamento:");
        panel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 160, 20));

        jPanel1.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 760, 500));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/por2.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 0, 1080, 660));

        lab9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login/icons8_Cancel_30px.png"))); // NOI18N
        lab9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lab9MousePressed(evt);
            }
        });
        jPanel1.add(lab9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 30, -1, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lab6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lab6MouseClicked
       agregar();
       String usuario = txtEmail.getText();
       
       int id = 0;
       String query = "SELECT *FROM empleado";
       String email = "";
       
        if(txtNombre.equals("") || txtPaterno.equals("") || txtMaterno.equals("") || txtCelular.equals("") || txtDireccion.equals("")
              || txtTelefono.equals("") || txtEmail.equals("") || txtDepartamento.equals("")){
            
            JOptionPane.showMessageDialog(null,"Ingresa todos los campos requeridos");
           
        }
        else{
                try{
               miConexion = cone.getConnection();
               Statement miStatement = miConexion.createStatement();
               ResultSet RS = miStatement.executeQuery(query);
               while(RS.next()){
                   email = RS.getString("emplemail");
                   if(email.equals(txtEmail.getText())){
                      id = RS.getInt("emplid");
                   }
               }  
           }catch(Exception e){
                e.printStackTrace();
           }

          JOptionPane.showMessageDialog(null, "Registro exitoso\nNombre de usuario:   "+usuario+"\nContraseña:   "+id);
          limpiaTextField();
          setVisible(false);

        }
       
    }//GEN-LAST:event_lab6MouseClicked

    private void txtCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartamentoActionPerformed

    private void lab9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lab9MousePressed
        //        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_lab9MousePressed

    private void cmbPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPuestoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbPuesto;
    private com.toedter.calendar.JDateChooser fechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lab6;
    private javax.swing.JLabel lab8;
    private javax.swing.JLabel lab9;
    private javax.swing.JPanel panel1;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDepartamento;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaterno;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPaterno;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
