
package com.ipn.mx.tt.util;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.BodyPart;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class EmailAdjunto {
    private final String correo;
    private final String nombre;
    private final String ruta;
    private String text;

    public EmailAdjunto(String nombre, String correo, String ruta){
        this.correo=correo;
        this.nombre=nombre;
        this.ruta=ruta;
        
        setBodyText();
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
        
        BodyPart texto = new MimeBodyPart();
        texto.setText(text);
        
        BodyPart adjunto = new MimeBodyPart();
        adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
        adjunto.setFileName("Reporte.pdf");
        
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(texto);
        mp.addBodyPart(adjunto);
        
        MimeMessage msg = new MimeMessage(session);
        
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
        msg.setSubject("Reporte");
        msg.setContent(mp);
        
        Transport.send(msg);
    }

    private void setBodyText() {
        text = "Hola " + nombre +",\n";
        text += "\nA continuación anexamos el reporte con los resultados de tu consulta.\n";
        text += "\nSaludos.";
    }

}
