
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



public class Citas extends javax.swing.JFrame {

          ///Para establecer la conexion a la base de datos
    Conexion cone = new Conexion();
    Connection miConexion;
    Statement miStatement;
    ResultSet rs;
    DefaultTableModel modelo;

    public Citas() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Pacientes");
        listar();
    }

    
    void listar(){ /// Carga los elementos de la base de datos (de la tabla pacientes)
        String sql = "SELECT * FROM cita";
        try{
            miConexion = cone.getConnection();
            miStatement = miConexion.createStatement();
            rs = miStatement.executeQuery(sql);
            Object []cita = new Object[10];
            modelo = (DefaultTableModel)tablaCitas.getModel();
            while(rs.next()){
                cita[0] = rs.getString("citaid");
                cita[1] = rs.getString("citatipocita");
                cita[2] = rs.getString("citafecha");
                cita[3] = rs.getString("citahora");
                cita[4] = rs.getString("paciid");
                cita[5] = rs.getString("citanombrepaciente");
                cita[6] = rs.getString("citaiddoctor");
                cita[7] = rs.getString("citanombredoctor");
                cita[8] = rs.getString("citaconsultorio");
                cita[9] = rs.getString("citaconfirmacion");
            
          
                modelo.addRow(cita);
            }
            tablaCitas.setModel(modelo);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
     public void agregar(){ ///Inserta nuevos regsitros en la tabla
    
        ///Captura los datos que hay en los text field
        String nombrePaciente = txtNombrePaciente.getText();
        String nombreDoctor = txtNombreDoctor.getText();
        int idPaciente = Integer.parseInt(txtIdPaciente.getText());
        int idDoctor = Integer.parseInt(txtIdDoctor.getText());
        String folioCita = txtFolioCita.getText();
        String tipoCita = txtTipoCita.getText();
        String consultorio = txtConsultorio.getText();
        String confirmacion = txtConfirmacion.getText();
        String hora = txtHora.getText();
        ///Para mandar la fecha
        int año = fechaHora.getCalendar().get(Calendar.YEAR);
        int mes = fechaHora.getCalendar().get(Calendar.MONTH)+1;
        int dia = fechaHora.getCalendar().get(Calendar.DAY_OF_MONTH);
        
        String fecha =(año+"-"+mes+"-"+dia);
        ///

        if(nombrePaciente.equals("") || nombreDoctor.equals("") || txtIdPaciente.equals("") || txtIdDoctor.equals("")
              || tipoCita.equals("") || consultorio.equals("") || confirmacion.equals("") || hora.equals("")){
            
            JOptionPane.showMessageDialog(null,"Ingresa todos los campos requeridos");
            limpiar();
        }
        else{
            String insert = "INSERT INTO cita(citatipocita, citafecha, citahora, paciid, citanombrepaciente, citaiddoctor, citanombredoctor, citaconsultorio, citaconfirmacion) "
                    + "VALUES ('"+tipoCita+"', '"+fecha+"', '"+hora+"', '"+idPaciente+"', '"+nombrePaciente+"', '"+idDoctor+"', '"+nombreDoctor+"', '"+consultorio+"', '"+confirmacion+"')";
           
            //String queryAux = "INSERT INTO paciente(paciid, pacinombre) "
                   // + "VALUES ('"+idPaciente+"', '"+nombrePaciente+"')";
            
            try{
                miConexion = cone.getConnection();
                Statement miStatement = miConexion.createStatement();
                miStatement.executeUpdate(insert);
               // miStatement.executeUpdate(queryAux);
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
        txtIdDoctor.setText("");
        txtNombrePaciente.setText("");
        txtNombreDoctor.setText("");
        txtTipoCita.setText("");
        txtConfirmacion.setText("");
        txtConsultorio.setText("");
        txtFolioCita.setText("");
        txtHora.setText("");
        try { ///Coloca el jdatachooser con la fecha capturada en la tabla
               java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01 ");
                fechaHora.setDate(date);
            }catch (ParseException ex) {
                Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
            }
      
    }
    
    public void eliminar(){
          ///Captura los datos que hay en los text field
        int ID = Integer.parseInt(txtFolioCita.getText());
        int idPaciente = Integer.parseInt(txtIdPaciente.getText());
        String sql = "DELETE FROM cita WHERE citaid = "+ID;
        //String deletePaciente = "DELETE FROM paciente WHERE paciid = "+idPaciente;
        ///Para borrar en cascada
           
            try{ 
                miConexion = cone.getConnection();
                Statement miStatement = miConexion.createStatement();
                miStatement.executeUpdate(sql);
                //miStatement.executeUpdate(deletePaciente);
                JOptionPane.showMessageDialog(null, "Eliminado correctamemte");
                limpiaTextField();
                limpiar();
            }catch(Exception e){
                
                  e.printStackTrace();
            } 
     }
    
     public void modificar(){ ///Modifica cualquier registro de la tabla
   
        ///Captura los datos que hay en los text field
        String nombrePaciente = txtNombrePaciente.getText();
        String nombreDoctor = txtNombreDoctor.getText();
        String idPaciente = txtIdPaciente.getText();
        String idDoctor = txtIdDoctor.getText();
        String folioCita = txtFolioCita.getText();
        String tipoCita = txtTipoCita.getText();
        String consultorio = txtConsultorio.getText();
        String confirmacion = txtConfirmacion.getText();
        String hora = txtHora.getText();
        
        ///Para mandar la fecha
        int año = fechaHora.getCalendar().get(Calendar.YEAR);
        int mes = fechaHora.getCalendar().get(Calendar.MONTH)+1;
        int dia = fechaHora.getCalendar().get(Calendar.DAY_OF_MONTH);
        
        String fecha =(año+"-"+mes+"-"+dia);
        ///
        int ID = Integer.parseInt(txtFolioCita.getText());

        String sql = "UPDATE cita set citatipocita = '"+tipoCita+"', citafecha = '"+fecha+"', citahora = '"+hora+"', paciid = '"+idPaciente+"',"
        + "citanombrepaciente = '"+nombrePaciente+"', citaiddoctor = '"+idDoctor+"', citanombredoctor = '"+nombreDoctor+"', citaconsultorio = '"+consultorio+"', citaconfirmacion = '"+confirmacion+"' WHERE citaid = "+ID;
           
        
        String queryAux = "UPDATE paciente set paciid = '"+idPaciente+"', pacinombre = '"+nombrePaciente+"' WHERE paciid = "+idPaciente;
           
        
            try{ 
                miConexion = cone.getConnection();
                Statement miStatement = miConexion.createStatement();
                miStatement.executeUpdate(sql);
                miStatement.executeUpdate(queryAux);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCitas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFolioCita = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTipoCita = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombrePaciente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreDoctor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtConsultorio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtConfirmacion = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnRegistrar1 = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        fechaHora = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtIdPaciente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtIdDoctor = new javax.swing.JTextField();
        btnBuscarPaciente = new javax.swing.JButton();
        btnBuscarDoctor = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio", "Tipo cita", "Fecha", "Hora", "idPaciente", "Nombre Pac.", "idDoctor", "Nombre Doc.", "Consultorio", "C. Confirmada"
            }
        ));
        tablaCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCitasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCitas);

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel9.setFont(new java.awt.Font("avocado", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("C i t a s");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(441, 441, 441)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setText("Folio Cita:");

        txtFolioCita.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("Tipo Cita:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Fecha:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setText("Nombre Paciente:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setText("Nombre Doctor:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel7.setText("Consultorio:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel8.setText("Confirmar Cita:");

        btnEliminar.setBackground(new java.awt.Color(0, 153, 153));
        btnEliminar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRegistrar1.setBackground(new java.awt.Color(0, 153, 153));
        btnRegistrar1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnRegistrar1.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar1.setText("Registrar");
        btnRegistrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrar1ActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(0, 153, 153));
        btnActualizar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        fechaHora.setDateFormatString("yyyy/MM/dd");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setText("ID Paciente:");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel11.setText("ID Doctor:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel12.setText("Hora:");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel13.setText("hh:mm:ss ");

        btnBuscarPaciente.setBackground(new java.awt.Color(0, 153, 153));
        btnBuscarPaciente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnBuscarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarPaciente.setText("Buscar");
        btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPacienteActionPerformed(evt);
            }
        });

        btnBuscarDoctor.setBackground(new java.awt.Color(0, 153, 153));
        btnBuscarDoctor.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnBuscarDoctor.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarDoctor.setText("Buscar");
        btnBuscarDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDoctorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(14, 14, 14)
                        .addComponent(txtFolioCita, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarDoctor))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(35, 35, 35)
                                .addComponent(txtHora))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTipoCita)
                                    .addComponent(fechaHora, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombrePaciente)
                    .addComponent(txtNombreDoctor)
                    .addComponent(txtConsultorio)
                    .addComponent(txtConfirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarPaciente))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegistrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(262, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarPaciente))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnRegistrar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombreDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtConsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtConfirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFolioCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarDoctor))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTipoCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("avocado", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("L i s t a d o  de  C i t a s");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(406, 406, 406)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCitasMouseClicked
          ///Obtiene los valores de los campos de la fila que seleccionemos dando un click sobre la tabla
        int fila = tablaCitas.getSelectedRow();
       if(fila == -1){
           JOptionPane.showMessageDialog(null, "Usuario no seleccionado");
       }
       else{
         
                ///Obteneoms lo que se selecciona en la tabla
            String folioCita = (String)tablaCitas.getValueAt(fila, 0);
            String tipoCita = (String)tablaCitas.getValueAt(fila, 1);
            String hora = (String)tablaCitas.getValueAt(fila, 3);
            String idPaciente = (String)tablaCitas.getValueAt(fila, 4);                
            String nombrePaciente = (String)tablaCitas.getValueAt(fila, 5);
            String idDoctor = (String)tablaCitas.getValueAt(fila, 6);
            String nombreDoctor = (String)tablaCitas.getValueAt(fila, 7);
            String consultorio = (String)tablaCitas.getValueAt(fila, 8);
            String confirmacion = (String)tablaCitas.getValueAt(fila, 9);
           
                
                ///colocamos en los text fiel lo que obtuvimos de la tabla
             txtIdPaciente.setText(idPaciente);
             txtIdDoctor.setText(idDoctor);
             txtHora.setText(hora);
             txtNombrePaciente.setText(nombrePaciente);
             txtNombreDoctor.setText(nombreDoctor);
             txtTipoCita.setText(tipoCita);
             txtConfirmacion.setText(confirmacion);
             txtConsultorio.setText(consultorio);
             txtFolioCita.setText(folioCita);
            
            String FN = (String)tablaCitas.getValueAt(fila, 2);
            try { ///Coloca el jdatachooser con la fecha capturada en la tabla
               java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(FN);
                fechaHora.setDate(date);
            }catch (ParseException ex) {
                Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
        
    }//GEN-LAST:event_tablaCitasMouseClicked

    private void btnRegistrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrar1ActionPerformed
       agregar();
       limpiaTextField();
       listar();
    }//GEN-LAST:event_btnRegistrar1ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
        listar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
       modificar();
        listar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed
        buscaIdPaciente();
    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnBuscarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDoctorActionPerformed
        buscaIdDoctor();
    }//GEN-LAST:event_btnBuscarDoctorActionPerformed

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
                txtFolioCita.setText("");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
          

    }
    
    void buscaIdDoctor(){
    
        String sql = "SELECT * FROM empleado";
        int buscar = Integer.parseInt(txtIdDoctor.getText());
        String nombreDoctor = "";
        int idPaciente = 0;
        String puesto = "";
        String paterno = "";
        String materno = "";
        boolean b = false;
          try{
            miConexion = cone.getConnection();
            miStatement = miConexion.createStatement();
            rs = miStatement.executeQuery(sql);   
            while(rs.next()){
                puesto = rs.getString("emplpuesto");          
                idPaciente = rs.getInt("emplid"); 
                if(buscar == idPaciente && puesto.equals("Doctor")){
                      nombreDoctor =  rs.getString("emplnombre");
                      materno = rs.getString("emplmaterno");
                      paterno = rs.getString("emplmaterno");
                      b = true;
                }
                
            }
            if(b){   
                txtNombreDoctor.setText(nombreDoctor+" "+materno+" "+paterno);
                txtIdDoctor.setText(String.valueOf(buscar));
            }
            else{
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningun Doctor con ese id.");
                txtIdDoctor.setText("");
                txtNombreDoctor.setText("");
             
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
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Citas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscarDoctor;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar1;
    private com.toedter.calendar.JDateChooser fechaHora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCitas;
    private javax.swing.JTextField txtConfirmacion;
    private javax.swing.JTextField txtConsultorio;
    private javax.swing.JTextField txtFolioCita;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtIdDoctor;
    private javax.swing.JTextField txtIdPaciente;
    private javax.swing.JTextField txtNombreDoctor;
    private javax.swing.JTextField txtNombrePaciente;
    private javax.swing.JTextField txtTipoCita;
    // End of variables declaration//GEN-END:variables
}
