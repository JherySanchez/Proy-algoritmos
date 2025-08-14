/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jhery
 */
public class GestorUsuarios {
    private final Map<String, Usuario> usuarios;// Para almacenar usuarios

    public GestorUsuarios() {
        this.usuarios = new HashMap<>();
        
        //Se pude cambiar a futuro
        usuarios.put("admin", new Usuario("admin","admin123","Administrador")); 
        usuarios.put("bibliotecario", new Usuario("bibliotecario","biblio456","Bibliotecario"));
        usuarios.put("cliente", new Usuario("cliente123","cliente123","Cliente"));
    }
    
    public boolean agregarUsuario(String username, String password, String rol) {
        if (usuarios.containsKey(username)) {
            return false; // El usuario ya existe
        }
        usuarios.put(username, new Usuario(username, password, rol));
        return true;
    }
    
    public Usuario validarCredenciales(String username, String password) {
        Usuario usuario = usuarios.get(username);
        if (usuario != null && usuario.verificarPassword(password)) {
            return usuario; // Check
        }
        return null; // Error
    }
}
