/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jhery
 */
public class Usuario {
    private String username;
    private String password;
    private String rol;

    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }

    public void setRol(String rol) { this.rol = rol; }
    
    //Verifica si la contraseña coincide con la almacenada
    public boolean verificarPassword(String password) { return this.password.equals(password); }

    @Override
    public String toString() {
        return "Usuario" + 
                "usuario=" + username + 
                ", password=" + password + 
                ", rol=" + rol;
    }
}
