
package com.ipn.mx.tt.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Address;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;

public class Email {
    private final String correo;
    private String asunto;
    private String cuerpo;
    private final String nombre;
    private final String apellidos;
    private final String user;
    private final String password;

    public Email(String nombre, String apellidos, String user, String correo, String password){
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.user=user;
        this.correo=correo;
        this.password=password;
        password = "";
        setMessage();
    }
    
    public void send() throws AddressException, MessagingException{
        String remitente = "sdst.2018.a030@gmail.com";
        String clave = "2018-A030";
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, 
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(remitente,clave);
                    }
                });
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject(asunto);
            message.setText(cuerpo);
            
            Transport.send(message);
        }catch(MessagingException e){
            throw new RuntimeException(e);
        }
        
    }

    private void setMessage() {
        asunto = "Recuperación de Contraseña - SDST";
        cuerpo = "¡Hola " + nombre + " " + apellidos + "!\n";
        cuerpo += "\nHas recibido este correo porque hiciste una solicitud de recuperación de contraseña para tu cuenta.\n";
        cuerpo += "\nTus datos de acceso son los siguientes: \n";
        cuerpo += "\t- Usuario: " + user + "\n";
        cuerpo += "\t- Contraseña: " + password + "\n";
        cuerpo += "\nPor tu seguridad, te recomendamos actualizar tu contraseña y eliminar este correo.\n";
        cuerpo += "\nSi no realizaste esta solicitud, no se requiere realizar ninguna otra acción.\n";
        cuerpo += "\nSaludos, Equipo SDST.";
    }
}
