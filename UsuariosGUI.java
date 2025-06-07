import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class UsuariosGUI extends JFrame {
    private JTextField idField, nombreField, telefonoField, direccionField, emailField;
    private JTextArea resultadoArea;

    public UsuariosGUI() {
        setTitle("Gestión de Usuarios");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel camposPanel = new JPanel(new GridLayout(5, 2));

        camposPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        camposPanel.add(idField);

        camposPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        camposPanel.add(nombreField);

        camposPanel.add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        camposPanel.add(telefonoField);

        camposPanel.add(new JLabel("Dirección:"));
        direccionField = new JTextField();
        camposPanel.add(direccionField);

        camposPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        camposPanel.add(emailField);

        add(camposPanel, BorderLayout.NORTH);

        
        JPanel botonesPanel = new JPanel(new GridLayout(1, 5));

        JButton agregarBtn = new JButton("Agregar");
        agregarBtn.addActionListener(e -> agregarUsuario());
        botonesPanel.add(agregarBtn);

        JButton actualizarBtn = new JButton("Actualizar");
        actualizarBtn.addActionListener(e -> actualizarUsuario());
        botonesPanel.add(actualizarBtn);

        JButton eliminarBtn = new JButton("Eliminar");
        eliminarBtn.addActionListener(e -> eliminarUsuario());
        botonesPanel.add(eliminarBtn);

        JButton buscarBtn = new JButton("Buscar");
        buscarBtn.addActionListener(e -> buscarUsuario());
        botonesPanel.add(buscarBtn);

        JButton mostrarBtn = new JButton("Mostrar Todos");
        mostrarBtn.addActionListener(e -> mostrarUsuarios());
        botonesPanel.add(mostrarBtn);

        add(botonesPanel, BorderLayout.CENTER);

        
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void agregarUsuario() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nombre = nombreField.getText();
            String telefono = telefonoField.getText();
            String direccion = direccionField.getText();
            String email = emailField.getText();

            Usuario u = new Usuario(id, nombre, telefono, direccion, email);
            UsuariosDAO.agregarUsuario(u);

            resultadoArea.setText("✅ Usuario agregado correctamente.\n");
            limpiarCampos();
        } catch (Exception e) {
            resultadoArea.setText("❌ Error al agregar usuario. Verifica los campos.\n");
        }
    }

    private void actualizarUsuario() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nombre = nombreField.getText();
            String telefono = telefonoField.getText();
            String direccion = direccionField.getText();
            String email = emailField.getText();

            Usuario u = new Usuario(id, nombre, telefono, direccion, email);
            UsuariosDAO.actualizarUsuario(u);

            resultadoArea.setText("✅ Usuario actualizado correctamente.\n");
            limpiarCampos();
        } catch (Exception e) {
            resultadoArea.setText("❌ Error al actualizar usuario.\n");
        }
    }

    private void eliminarUsuario() {
        try {
            int id = Integer.parseInt(idField.getText());
            UsuariosDAO.eliminarUsuario(id);
            resultadoArea.setText("✅ Usuario eliminado correctamente.\n");
            limpiarCampos();
        } catch (Exception e) {
            resultadoArea.setText("❌ Error al eliminar usuario.\n");
        }
    }

    private void buscarUsuario() {
        try {
            int id = Integer.parseInt(idField.getText());
            Usuario u = UsuariosDAO.buscarUsuario(id);
            if (u != null) {
                resultadoArea.setText("🔍 Usuario encontrado:\n" +
                    "ID: " + u.getId() + "\n" +
                    "Nombre: " + u.getNombre() + "\n" +
                    "Teléfono: " + u.getTelefono() + "\n" +
                    "Dirección: " + u.getDireccion() + "\n" +
                    "Email: " + u.getEmail());
            } else {
                resultadoArea.setText("❌ Usuario no encontrado.\n");
            }
        } catch (Exception e) {
            resultadoArea.setText("❌ Error al buscar usuario.\n");
        }
    }

    private void mostrarUsuarios() {
        List<Usuario> usuarios = UsuariosDAO.cargarUsuarios();
        resultadoArea.setText("📋 Lista de usuarios:\n");
        for (Usuario u : usuarios) {
            resultadoArea.append("ID: " + u.getId() + " - " +
                                 "Nombre: " + u.getNombre() + " - " +
                                 "Tel: " + u.getTelefono() + " - " +
                                 "Dir: " + u.getDireccion() + " - " +
                                 "Email: " + u.getEmail() + "\n");
        }
    }

    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        telefonoField.setText("");
        direccionField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        new UsuariosGUI();
    }
}
