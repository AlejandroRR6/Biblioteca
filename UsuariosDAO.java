import java.io.*;
import java.util.*;

public class UsuariosDAO 
{
    private static final String ARCHIVO = "usuarios.txt";

    public static void guardarUsuarios(List<Usuario>usuarios)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO)))
        {
            for (Usuario u : usuarios)
            {
                writer.println(u.getId() + "," + u.getNombre() + "," + u.getTelefono() + "," + u.getDireccion() + "," + u.getEmail());

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static List<Usuario> cargarUsuarios()
    {
        List<Usuario> usuarios = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO)))
        {
            String linea;
            while ((linea = reader.readLine()) != null)
            {
                String[] partes = linea.split(",");

                if (partes.length == 5)
                {
                    Usuario u = new Usuario(Integer.parseInt(partes[0]), partes[1], partes[2], partes[3], partes[4]);
                    usuarios.add(u);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static void agregarUsuario(Usuario nuevoUsuario)
    {
        List<Usuario> usuarios = cargarUsuarios();
        usuarios.add(nuevoUsuario);
        guardarUsuarios(usuarios);
    }

    public static void eliminarUsuario(int id)
    {
        List<Usuario> usuarios = cargarUsuarios();
        usuarios.removeIf(u -> u.getId() == id);
        guardarUsuarios(usuarios);
    }

    public static void actualizarUsuario(Usuario usuarioActualizado)
    {
        List<Usuario> usuarios = cargarUsuarios();
        
        for(int i=0; i < usuarios.size(); i++)
        {
            if(usuarios.get(i).getId() == usuarioActualizado.getId())
            {
                usuarios.set(i, usuarioActualizado);
                break;
            }
        }
        guardarUsuarios(usuarios);
    }

    public static Usuario buscarUsuario(int id)
    {
        List<Usuario> usuarios = cargarUsuarios();
        for(Usuario u : usuarios)
        {
            if(u.getId() == id)
            {
                return u;
            }
        }
        return null;
    }
}
