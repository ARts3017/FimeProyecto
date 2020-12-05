
package vista;
import config.Conexion;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Medicamentos extends javax.swing.JFrame {

          ///Para establecer la conexion a la base de datos
    Conexion cone = new Conexion();
    Connection miConexion;
    Statement miStatement;
    ResultSet rs;
    DefaultTableModel modelo;
    
    
    public Medicamentos() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Medicamentos");
        listar();
    }
    
    
    void listar(){ /// Carga los elementos de la base de datos (de la tabla pacientes)
        String sql = "SELECT * FROM medicamento";
        try{
            miConexion = cone.getConnection();
            miStatement = miConexion.createStatement();
            rs = miStatement.executeQuery(sql);
            Object []medicamento = new Object[7];
            modelo = (DefaultTableModel)tablaMedicamentos.getModel();
            while(rs.next()){
                medicamento[0] = rs.getString("mediid");
                medicamento[1] = rs.getString("medidescripcion");
                medicamento[2] = rs.getString("medicantidad");
                medicamento[3] = rs.getString("medicosto");
                medicamento[4] = rs.getString("mediventa");
                medicamento[5] = rs.getString("paciid");
                medicamento[6] = rs.getString("medinombrepaciente");
                
                //medicamento[6] = rs.getString("medinombre"); ///Falta agregar
                modelo.addRow(medicamento);
            }
            tablaMedicamentos.setModel(modelo);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
     public void agregar(){ ///Inserta nuevos regsitros en la tabla
    
        ///Captura los datos que hay en los text field
        String nombrePaciente = txtNombrePaciente.getText();
        String descripcion = txtDescripcionMedicamento.getText();
        String idMedicamento = txtIdMedicamento.getText();
        String idPaciente = txtIdPaciente.getText();
        int cantidad = Integer.parseInt(txtCantidad.getText());
        float costo = Float.parseFloat(txtCosto.getText());
        float venta = Float.parseFloat(txtVenta.getText());
        

     
        
        if(nombrePaciente.equals("") || txtIdPaciente.equals("") || txtCantidad.equals("") || txtCosto.equals("") || txtVenta.equals("")
           || descripcion.equals("")){
            
            JOptionPane.showMessageDialog(null,"Ingresa todos los campos requeridos");
            limpiar();
        }
        else{
            String insert = "INSERT INTO medicamento(medidescripcion, medicantidad, medicosto, mediventa, paciid, medinombrepaciente) "
                    + "VALUES ('"+descripcion+"', '"+cantidad+"', '"+costo+"', '"+venta+"', '"+idPaciente+"', '"+nombrePaciente+"')"; ///Nombre falta
            try{ 
                miConexion = cone.getConnection();
                Statement miStatement = miConexion.createStatement();
                miStatement.executeUpdate(insert);
                JOptionPane.showMessageDialog(null, "El registro se ha agregado correctamente");
                limpiar();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    
    }
    ///Limpia la tabla pacientes
    public void limpiar(){
        for(int i = 0; i<modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i = i-1;
        }
        
    }
    
    
     public void limpiaTextField(){
        txtDescripcionMedicamento.setText("");
        txtCantidad.setText("");
        txtCosto.setText("");
        txtVenta.setText("");
        txtNombrePaciente.setText("");
        txtIdPaciente.setText("");
        txtIdMedicamento.setText("");
     
    }
    
    public void eliminar(){
          ///Captura los datos que hay en los text field
        int ID = Integer.parseInt(txtIdMedicamento.getText());
        String sql = "DELETE FROM medicamento WHERE mediid = "+ID;
           
            try{ 
                miConexion = cone.getConnection();
                Statement miStatement = miConexion.createStatement();
                miStatement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Eliminado correctamemte");
                limpiaTextField();
                limpiar();
            }catch(Exception e){
                
                  e.printStackTrace();
            } 
     }
    
    public void modificar(){ ///Modifica cualquier registro de la tabla
   ///Captura los datos que hay en los text field
                ///Captura los datos que hay en los text field
        String nombrePaciente = txtNombrePaciente.getText();
        String descripcion = txtDescripcionMedicamento.getText();
        String idMedicamento = txtIdMedicamento.getText();
        String idPaciente = txtIdPaciente.getText();
        int cantidad = Integer.parseInt(txtCantidad.getText());
        float costo = Float.parseFloat(txtCosto.getText());
        float venta = Float.parseFloat(txtVenta.getText());

        String sql = "UPDATE medicamento set medidescripcion = '"+descripcion+"', medicantidad = '"+cantidad+"', medicosto = '"+costo+"',"
        + "mediventa = '"+venta+"', paciid = '"+idPaciente+"', medinombrepaciente = '"+nombrePaciente+"' WHERE mediid = "+idMedicamento;
           
            try{ 
                miConexion = cone.getConnection();
                Statement miStatement = miConexion.createStatement();
                miStatement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Actualizado correctamemte");
                limpiaTextField();
                limpiar();
            }catch(Exception e){
                
                  e.printStackTrace();
            } 
           

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombrePaciente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtCosto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnActulizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtIdMedicamento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtVenta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtIdPaciente = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMedicamentos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtDescripcionMedicamento = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(900, 700));

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("avocado", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("M e d i c a m e n t o s");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("Nombre Paciente:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Cantidad:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setText("Costo:");

        btnRegistrar.setBackground(new java.awt.Color(0, 204, 204));
        btnRegistrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnActulizar.setBackground(new java.awt.Color(0, 204, 204));
        btnActulizar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnActulizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActulizar.setText("Actualizar");
        btnActulizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActulizarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(0, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setText("ID Medicamento:");

        txtIdMedicamento.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel7.setText("Venta:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel8.setText("ID Paciente:");

        btnBuscar.setBackground(new java.awt.Color(0, 153, 153));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombrePaciente)
                    .addComponent(txtCosto)
                    .addComponent(txtCantidad)
                    .addComponent(txtIdMedicamento)
                    .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnActulizar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar)
                            .addComponent(btnActulizar)
                            .addComponent(btnEliminar)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtIdMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tablaMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id medicamento", "Descr. Medic", "Cantidad", "Costo", "Venta", "id paciente", "Nombre Pac."
            }
        ));
        tablaMedicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMedicamentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaMedicamentos);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        jLabel9.setFont(new java.awt.Font("avocado", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("L i s t a  d e  M e d i c a m e nt o s");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(156, 156, 156))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setText("Descripcion del medicamento:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtDescripcionMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcionMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
       agregar();
       limpiaTextField();
       listar();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnActulizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActulizarActionPerformed
        modificar();
        listar();
    }//GEN-LAST:event_btnActulizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       eliminar();
        listar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tablaMedicamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMedicamentosMouseClicked
        ///Obtiene los valores de los campos de la fila que seleccionemos dando un click sobre la tabla
        int fila = tablaMedicamentos.getSelectedRow();
       if(fila == -1){
           JOptionPane.showMessageDialog(null, "Usuario no seleccionado");
       }
        else{
         
                ///Obteneoms lo que se selecciona en la tabla
            String idMedicamento = (String)tablaMedicamentos.getValueAt(fila, 0);
            String descripcion = (String)tablaMedicamentos.getValueAt(fila, 1);
            String cantidad = (String)tablaMedicamentos.getValueAt(fila, 2);
            String costo = (String)tablaMedicamentos.getValueAt(fila, 3);
            String venta = (String)tablaMedicamentos.getValueAt(fila, 4);
            String idPaciente = (String)tablaMedicamentos.getValueAt(fila, 5); 
            String nombrePaciente = (String)tablaMedicamentos.getValueAt(fila, 6); 
      
                
                ///colocamos en los text fiel lo que obtuvimos de la tabla
               txtDescripcionMedicamento.setText(descripcion);
                txtCantidad.setText(cantidad);
                txtCosto.setText(costo);
                txtVenta.setText(venta);
               // txtNombrePaciente.setText(nombrePaciente);
                txtIdPaciente.setText(idPaciente);
                txtIdMedicamento.setText(idMedicamento);
                txtNombrePaciente.setText(nombrePaciente);
                
            
        }
        
    }//GEN-LAST:event_tablaMedicamentosMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscaIdPaciente();
    }//GEN-LAST:event_btnBuscarActionPerformed

     void buscaIdPaciente(){
    
        String sql = "SELECT * FROM paciente";
        int buscar = Integer.parseInt(txtIdPaciente.getText());
        String nombrePaciente = "";
        int idPaciente = 0;
        int folio = 0;
        
        String paterno = "";
        String materno = "";
        boolean b = false;
          try{
            miConexion = cone.getConnection();
            miStatement = miConexion.createStatement();
            rs = miStatement.executeQuery(sql);   
            while(rs.next()){
                 idPaciente = rs.getInt("paciid"); 
                if(buscar == idPaciente){
                    nombrePaciente =  rs.getString("pacinombre");
                    paterno =  rs.getString("pacipaterno");
                    materno =  rs.getString("pacimaterno");
                    b = true;
                }
                
            }
            if(b){   
                txtNombrePaciente.setText(nombrePaciente+" "+materno+" "+paterno);
                txtIdPaciente.setText(String.valueOf(buscar));
            }
            else{
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningun paciente con ese id");
                txtIdPaciente.setText("");
                txtNombrePaciente.setText("");
         
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
          

    }
    
    
    
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
            java.util.logging.Logger.getLogger(Medicamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Medicamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Medicamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Medicamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Medicamentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActulizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaMedicamentos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtDescripcionMedicamento;
    private javax.swing.JTextField txtIdMedicamento;
    private javax.swing.JTextField txtIdPaciente;
    private javax.swing.JTextField txtNombrePaciente;
    private javax.swing.JTextField txtVenta;
    // End of variables declaration//GEN-END:variables
}
