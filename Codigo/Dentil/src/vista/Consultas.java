
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

public class Consultas extends javax.swing.JFrame {

    
       ///Para establecer la conexion a la base de datos
    Conexion cone = new Conexion();
    Connection miConexion;
    Statement miStatement;
    ResultSet rs;
    DefaultTableModel modelo;
    

    public Consultas() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Consultas");
        listar();
    }
    
    void buscaIdPaciente(){
    
        String sql = "SELECT * FROM cita";
        int buscar = Integer.parseInt(txtFolioCita.getText());
        String nombrePaciente = "";
        int idPaciente = 0;
        boolean b = false;
        int folio = 0;
          try{
            miConexion = cone.getConnection();
            miStatement = miConexion.createStatement();
            rs = miStatement.executeQuery(sql);   
            while(rs.next()){
                 folio = rs.getInt("citaid");
                if(buscar == folio){
                    nombrePaciente =  rs.getString("citanombrepaciente");
                    idPaciente = rs.getInt("paciid"); 
                    b = true;
                }
            }
            if(b){   
                txtNombrePaciente.setText(nombrePaciente);
                txtIdPaciente.setText(String.valueOf(idPaciente));
            }
            else{
                JOptionPane.showMessageDialog(null, "No se ha encontrado nigun paciente con ese Folio.");
                txtIdPaciente.setText(""); 
                txtFolioCita.setText("");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
       
        
    
    
    }
    void listar(){ /// Carga los elementos de la base de datos (de la tabla pacientes)
        String sql = "SELECT * FROM consulta";
        try{
            miConexion = cone.getConnection();
            miStatement = miConexion.createStatement();
            rs = miStatement.executeQuery(sql);
            Object []consultas = new Object[10];
            modelo = (DefaultTableModel)tablaConsultas.getModel();
            while(rs.next()){
                consultas[0] = rs.getString("consid");
                consultas[1] = rs.getString("consconsultorio");
                consultas[2] = rs.getString("emplid");
                consultas[3] = rs.getString("consnombreempleado");
                consultas[4] = rs.getString("paciid");
                consultas[5] = rs.getString("consnombrepaciente");
                consultas[6] = rs.getString("conspadecimiento");
                consultas[7] = rs.getString("consmedicamento");
                consultas[8] = rs.getString("consfecha");
                consultas[9] = rs.getString("conshora");
              
                modelo.addRow(consultas);
            }
            tablaConsultas.setModel(modelo);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     
    public void agregar(){ ///Inserta nuevos regsitros en la tabla
    
        ///Captura los datos que hay en los text field
        String consultorio = txtConsultorio.getText();
        String nombreEmpleado = txtNombreEmpleado.getText();
        String idEmpleado = txtIdEmpleado.getText();
        String idPaciente = txtIdEmpleado.getText();
        String nombrePaciente = txtNombrePaciente.getText();
        String padecimiento = txtPadecimiento.getText();
        String medicamento = txtHora.getText();
      
        ///Para mandar la fecha
        int año = fechaConsultas.getCalendar().get(Calendar.YEAR);
        int mes = fechaConsultas.getCalendar().get(Calendar.MONTH)+1;
        int dia = fechaConsultas.getCalendar().get(Calendar.DAY_OF_MONTH);
        String fecha =(año+"-"+mes+"-"+dia);
        int folioCita = Integer.parseInt(txtFolioCita.getText());
        String hora = txtHora.getText();
        

        if(consultorio.equals("") || nombreEmpleado.equals("") || idEmpleado.equals("") || idPaciente.equals("") || nombrePaciente.equals("")
              || padecimiento.equals("") || medicamento.equals("") || hora.equals("")){
            
            JOptionPane.showMessageDialog(null,"Ingresa todos los campos requeridos");
            limpiar();
        }
        else{
            String insert = "INSERT INTO consulta(consconsultorio, emplid, consnombreempleado, paciid, consnombrepaciente, conspadecimiento, consmedicamento, consfecha, conshora) "
                    + "VALUES ('"+consultorio+"', '"+idEmpleado+"', '"+nombreEmpleado+"', '"+idPaciente+"', '"+nombrePaciente+"', '"+padecimiento+"', '"+medicamento+"', '"+fecha+"', '"+hora+"')";
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
        txtConsultorio.setText("");
        txtNombreEmpleado.setText("");
        txtNombrePaciente.setText("");
        txtHora.setText("");
        txtPadecimiento.setText("");
        txtFolioCita.setText("");
        txtIdEmpleado.setText("");
        txtIdPaciente.setText("");
        txtHora.setText("");
        txtMedicamento.setText("");
        
        try { ///Coloca el jdatachooser con la fecha capturada en la tabla
               java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01 ");
                fechaConsultas.setDate(date);
            }catch (ParseException ex) {
                Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        
    
    
    public void eliminar(){
          ///Captura los datos que hay en los text field
        int ID = Integer.parseInt(txtFolioCita.getText());
        String sql = "DELETE FROM consulta WHERE consid = "+ID;
           
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
        String consultorio = txtConsultorio.getText();
        String nombreEmpleado = txtNombreEmpleado.getText();
        int idEmpleado = Integer.parseInt(txtIdEmpleado.getText());
        int idPaciente = Integer.parseInt(txtIdPaciente.getText());
        String nombrePaciente = txtNombrePaciente.getText();
        String padecimiento = txtPadecimiento.getText();
        String medicamento = txtMedicamento.getText();
        String hora = txtHora.getText();
        int folioCita = Integer.parseInt(txtFolioCita.getText());
        ///Para mandar la fecha
        int año = fechaConsultas.getCalendar().get(Calendar.YEAR);
        int mes = fechaConsultas.getCalendar().get(Calendar.MONTH)+1;
        int dia = fechaConsultas.getCalendar().get(Calendar.DAY_OF_MONTH);
        String fecha =(año+"-"+mes+"-"+dia);
        int ID = Integer.parseInt(txtFolioCita.getText());

        String sql = "UPDATE consulta set consconsultorio = '"+consultorio+"', emplid = '"+idEmpleado+"', consnombreempleado = '"+nombreEmpleado+"',"
        + "paciid = '"+idPaciente+"', consnombrepaciente = '"+nombrePaciente+"', conspadecimiento = '"+padecimiento+"', consmedicamento = '"+medicamento+"', conshora = '"+hora+"' WHERE consid = "+ID;
           
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtConsultorio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombreEmpleado = new javax.swing.JTextField();
        txtNombrePaciente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPadecimiento = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaConsultas = new javax.swing.JTable();
        btnRegistrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        fechaConsultas = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txtFolioCita = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIdEmpleado = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtIdPaciente = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtHora = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMedicamento = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("avocado", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("C o n s u l t a s");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(319, 319, 319))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel2.setText("Consultorio:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel3.setText("Nombre Empleado:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel4.setText("Nombre Paciente:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel6.setText("Padecimientos:");

        tablaConsultas.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tablaConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio Cita", "Consultorio", "id Empleado", "Nombre Empl.", "id Paciente", "Nombre Pac.", "Padecimientos", "Medicamento", "Fecha", "Hora"
            }
        ));
        tablaConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaConsultasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaConsultas);

        btnRegistrar.setBackground(new java.awt.Color(0, 153, 153));
        btnRegistrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(0, 153, 153));
        btnActualizar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(0, 153, 153));
        btnEliminar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel7.setText("Fecha:");

        fechaConsultas.setDateFormatString("yyyy-MM-dd");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel8.setText("Folio de Cita:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel9.setText("ID empleado:");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel10.setText("ID Paciente:");

        btnBuscar.setBackground(new java.awt.Color(0, 153, 153));
        btnBuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel5.setText("Hora:");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel11.setText("Medicamento:");

        jLabel12.setText("hh:mm:ss");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdPaciente)
                            .addComponent(txtNombreEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(txtConsultorio)
                            .addComponent(txtIdEmpleado, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNombrePaciente, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtFolioCita, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fechaConsultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPadecimiento)
                            .addComponent(txtHora)
                            .addComponent(txtMedicamento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtConsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtFolioCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtPadecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(fechaConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addComponent(txtMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
       agregar();
       limpiaTextField();
       listar();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tablaConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaConsultasMouseClicked
           ///Obtiene los valores de los campos de la fila que seleccionemos dando un click sobre la tabla
        int fila = tablaConsultas.getSelectedRow();
       if(fila == -1){
           JOptionPane.showMessageDialog(null, "Usuario no seleccionado");
       }
       else{
                ///Obteneoms lo que se selecciona en la tabla
            String folioCita = (String)tablaConsultas.getValueAt(fila, 0);
            String consultorio = (String)tablaConsultas.getValueAt(fila, 1);
            String idEmpleado = (String)tablaConsultas.getValueAt(fila, 2);
            String nombreEmpleado = (String)tablaConsultas.getValueAt(fila, 3);
            String idPaciente = (String)tablaConsultas.getValueAt(fila, 4);                
            String nombrePaciente = (String)tablaConsultas.getValueAt(fila, 5);
            String padecimiento = (String)tablaConsultas.getValueAt(fila, 6);
            String medicamento = (String)tablaConsultas.getValueAt(fila, 7);
            String hora = (String)tablaConsultas.getValueAt(fila, 9);
            
                
                ///colocamos en los text fiel lo que obtuvimos de la tabla
            txtConsultorio.setText(consultorio);
            txtNombreEmpleado.setText(nombreEmpleado);
            txtNombrePaciente.setText(nombrePaciente);
            txtHora.setText(hora);
            txtPadecimiento.setText(padecimiento);
            txtFolioCita.setText(folioCita);
            txtIdEmpleado.setText(idEmpleado);
            txtIdPaciente.setText(idPaciente);
            txtHora.setText(hora);
            txtMedicamento.setText(medicamento);
                
             String FN = (String)tablaConsultas.getValueAt(fila, 8);
            try { ///Coloca el jdatachooser con la fecha capturada en la tabla
               java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(FN);
                fechaConsultas.setDate(date);
            }catch (ParseException ex) {
                Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }//GEN-LAST:event_tablaConsultasMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        modificar();
        listar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
        listar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       buscaIdPaciente();
    }//GEN-LAST:event_btnBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private com.toedter.calendar.JDateChooser fechaConsultas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tablaConsultas;
    private javax.swing.JTextField txtConsultorio;
    private javax.swing.JTextField txtFolioCita;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtIdPaciente;
    private javax.swing.JTextField txtMedicamento;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtNombrePaciente;
    private javax.swing.JTextField txtPadecimiento;
    // End of variables declaration//GEN-END:variables
}
