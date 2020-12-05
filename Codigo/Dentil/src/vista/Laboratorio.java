
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

public class Laboratorio extends javax.swing.JFrame {

            ///Para establecer la conexion a la base de datos
    Conexion cone = new Conexion();
    Connection miConexion;
    Statement miStatement;
    ResultSet rs;
    DefaultTableModel modelo;

    public Laboratorio() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Laboratorio");
        listar();
    }
    
     void listar(){ /// Carga los elementos de la base de datos (de la tabla pacientes)
        String sql = "SELECT * FROM laboratorio";
        try{
            miConexion = cone.getConnection();
            miStatement = miConexion.createStatement();
            rs = miStatement.executeQuery(sql);
            Object []laboratorio = new Object[9];
            modelo = (DefaultTableModel)tablaLaboratorio.getModel();
            while(rs.next()){
                laboratorio[0] = rs.getString("laboid");
                laboratorio[1] = rs.getString("paciid");
                laboratorio[2] = rs.getString("labonombrepaciente");
                laboratorio[3] = rs.getString("emplid");
                laboratorio[4] = rs.getString("labonombreempleado");
                laboratorio[5] = rs.getString("labofecha");
                laboratorio[6] = rs.getString("labocosto");
                laboratorio[7] = rs.getString("labodescripcion");
                laboratorio[8] = rs.getString("laboresultados");

                modelo.addRow(laboratorio);
            }
            tablaLaboratorio.setModel(modelo);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     
     
     
       public void agregar(){ ///Inserta nuevos regsitros en la tabla
    
        ///Captura los datos que hay en los text field
        String nombrePaciente = txtNombrePaciente.getText();
        String nombreEmpleado = txtNombreEmpleado.getText();
        String idPaciente = txtIdPaciente.getText();
        String idEmpleado = txtIdEmpleado.getText();
    
        String costo = txtCosto.getText();
        String descripcion = txtDescripcion.getText();
        String resultados = txtResultados.getText();
     
        ///Para mandar la fecha
       int año = fechaLaboratorio.getCalendar().get(Calendar.YEAR);
        int mes = fechaLaboratorio.getCalendar().get(Calendar.MONTH)+1;
        int dia = fechaLaboratorio.getCalendar().get(Calendar.DAY_OF_MONTH);
        int hora = fechaLaboratorio.getCalendar().get(Calendar.HOUR);
        int minutos = fechaLaboratorio.getCalendar().get(Calendar.MINUTE);
        int segundos = fechaLaboratorio.getCalendar().get(Calendar.SECOND);
        String fecha =(año+"-"+mes+"-"+dia+" "+hora+":"+minutos+":"+segundos);
        ///

        if(nombrePaciente.equals("") || nombreEmpleado.equals("") || idPaciente.equals("") || idEmpleado.equals("")
              || descripcion.equals("") || resultados.equals("") || costo.equals("")){
            
            JOptionPane.showMessageDialog(null,"Ingresa todos los campos requeridos");
            limpiar();
        }
        else{
            String insert = "INSERT INTO laboratorio(paciid, labonombrepaciente, emplid, labonombreempleado, labofecha, labocosto, labodescripcion, laboresultados) "
                    + "VALUES ('"+idPaciente+"', '"+nombrePaciente+"', '"+idEmpleado+"', '"+nombreEmpleado+"', '"+fecha+"', '"+costo+"', '"+descripcion+"', '"+resultados+"')";
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
        txtIdPaciente.setText("");
        txtIdEmpleado.setText("");
        txtNombrePaciente.setText("");
        txtNombreEmpleado.setText("");
        txtIdAnalisis.setText("");
        txtDescripcion.setText("");
        txtResultados.setText("");
        txtCosto.setText("");
 
    }
    
    
     public void eliminar(){
          ///Captura los datos que hay en los text field
        int ID = Integer.parseInt(txtIdAnalisis.getText());
        String sql = "DELETE FROM laboratorio WHERE laboid = "+ID;
           
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
        String nombreEmpleado = txtNombreEmpleado.getText();
        String idPaciente = txtIdPaciente.getText();
        String idEmpleado = txtIdEmpleado.getText();
        String idAnalisisLab = txtIdAnalisis.getText();
        String costo = txtCosto.getText();
        String descripcion = txtDescripcion.getText();
        String resultados = txtResultados.getText();
         
        ///Para mandar la fecha
        int año = fechaLaboratorio.getCalendar().get(Calendar.YEAR);
        int mes = fechaLaboratorio.getCalendar().get(Calendar.MONTH)+1;
        int dia = fechaLaboratorio.getCalendar().get(Calendar.DAY_OF_MONTH);
        int hora = fechaLaboratorio.getCalendar().get(Calendar.HOUR);
        int minutos = fechaLaboratorio.getCalendar().get(Calendar.MINUTE);
        int segundos = fechaLaboratorio.getCalendar().get(Calendar.SECOND);
        String fecha =(año+"-"+mes+"-"+dia+" "+hora+":"+minutos+":"+segundos);
        ///
        ///
        int ID = Integer.parseInt(txtIdAnalisis.getText());

        String sql = "UPDATE laboratorio set paciid = '"+idPaciente+"', labonombrepaciente = '"+nombrePaciente+"', emplid = '"+idEmpleado+"',"
        + "labonombreempleado = '"+nombreEmpleado+"', labofecha = '"+fecha+"', labocosto = '"+costo+"', labodescripcion = '"+descripcion+"', laboresultados = '"+resultados+"' WHERE laboid = "+ID;
           
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombrePaciente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombreEmpleado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtIdAnalisis = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIdPaciente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtResultados = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        fechaLaboratorio = new com.toedter.calendar.JDateChooser();
        txtIdEmpleado = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLaboratorio = new javax.swing.JTable();
        btnRegistrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("avocado", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("L a b o r a t o r i o");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(341, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(329, 329, 329))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("Nombre Paciente:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Nombre Empleado:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setText("Costo:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel7.setText("Descripcion:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel8.setText("ID Analisis:");

        txtIdAnalisis.setEnabled(false);
        txtIdAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdAnalisisActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel9.setText("ID Paciente:");

        txtIdPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdPacienteActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel11.setText("Resultados:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel12.setText("Fecha/Hora:");

        fechaLaboratorio.setDateFormatString("yyyy/MM/dd HH:mm:ss");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel10.setText("ID Empleado:");

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtResultados, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(txtIdEmpleado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreEmpleado))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCosto)
                            .addComponent(fechaLaboratorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtIdAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtIdAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(fechaLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        tablaLaboratorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id Analisis", "id paciente", "Nombre Pac.", "id Empl.", "Nombre Empl.", "Fecha/Hora", "Costo", "Descripcion A.", "Resultados"
            }
        ));
        tablaLaboratorio.setToolTipText("");
        tablaLaboratorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaLaboratorioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaLaboratorio);

        btnRegistrar.setBackground(new java.awt.Color(0, 153, 153));
        btnRegistrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(0, 153, 153));
        btnEliminar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(0, 153, 153));
        btnActualizar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar)
                            .addComponent(btnEliminar)
                            .addComponent(btnActualizar))
                        .addGap(130, 130, 130))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        modificar();
        listar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtIdAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdAnalisisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdAnalisisActionPerformed

    private void txtIdPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdPacienteActionPerformed

    private void tablaLaboratorioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaLaboratorioMouseClicked
             ///Obtiene los valores de los campos de la fila que seleccionemos dando un click sobre la tabla
        int fila = tablaLaboratorio.getSelectedRow();
       if(fila == -1){
           JOptionPane.showMessageDialog(null, "Usuario no seleccionado");
       }
       else{
         
                ///Obteneoms lo que se selecciona en la tabla
            String idAnalisis = (String)tablaLaboratorio.getValueAt(fila, 0);
            String idPaciente = (String)tablaLaboratorio.getValueAt(fila, 1);
            String nombrePaciente = (String)tablaLaboratorio.getValueAt(fila, 2);                
            String idEmpleado = (String)tablaLaboratorio.getValueAt(fila, 3);
             String nombreEmpleado = (String)tablaLaboratorio.getValueAt(fila, 4);
            String costo = (String)tablaLaboratorio.getValueAt(fila, 6);
             String descripcion = (String)tablaLaboratorio.getValueAt(fila, 7);
              String resultados = (String)tablaLaboratorio.getValueAt(fila, 8);
                
                    ///colocamos en los text fiel lo que obtuvimos de la tabla
          txtIdPaciente.setText(idPaciente);
            txtIdEmpleado.setText(idEmpleado);
            txtNombrePaciente.setText(nombrePaciente);
            txtNombreEmpleado.setText(nombreEmpleado);
            txtIdAnalisis.setText(idAnalisis);
            txtDescripcion.setText(descripcion);
            txtResultados.setText(resultados);
            txtCosto.setText(costo);
             String FN = (String)tablaLaboratorio.getValueAt(fila, 5);
       
            try { ///Coloca el jdatachooser con la fecha capturada en la tabla
               java.util.Date date = new SimpleDateFormat("HH:mm:ss").parse(FN);
                
                fechaLaboratorio.setDateFormatString(FN);
            }catch (ParseException ex) {
                Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
        
    }//GEN-LAST:event_tablaLaboratorioMouseClicked

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
       agregar();
       limpiaTextField();
       listar();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       eliminar();
        listar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscaIdPaciente();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       buscaIdEmpleado();
    }//GEN-LAST:event_jButton2ActionPerformed

    
    void buscaIdEmpleado(){
        String sql = "SELECT * FROM empleado";
        int buscar = Integer.parseInt(txtIdEmpleado.getText());
        String nombreEmpleado = "";
        int idEmpleado = 0;
        String paterno = "";
        String materno = "";
        boolean b = false;
          try{
            miConexion = cone.getConnection();
            miStatement = miConexion.createStatement();
            rs = miStatement.executeQuery(sql);   
            while(rs.next()){
                idEmpleado = rs.getInt("emplid");    
                if(buscar == idEmpleado){
                    nombreEmpleado =  rs.getString("emplnombre");
                    paterno = rs.getString("emplpaterno");
                    materno = rs.getString("emplmaterno");
                    b = true;
                }
                
            }
            if(b){   
                txtNombreEmpleado.setText(nombreEmpleado+" "+materno+" "+paterno);
                txtIdEmpleado.setText(String.valueOf(buscar));
            }
            else{
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningun Empleado con ese id");
                txtIdEmpleado.setText("");
                txtNombreEmpleado.setText("");  
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
     }
    
    
    
    
    
    
    void buscaIdPaciente(){
    
        String sql = "SELECT * FROM paciente";
        int buscar = Integer.parseInt(txtIdPaciente.getText());
        String nombrePaciente = "";
        int idPaciente = 0;
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
            java.util.logging.Logger.getLogger(Laboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laboratorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private com.toedter.calendar.JDateChooser fechaLaboratorio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaLaboratorio;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtIdAnalisis;
    private javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtIdPaciente;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtNombrePaciente;
    private javax.swing.JTextField txtResultados;
    // End of variables declaration//GEN-END:variables
}
