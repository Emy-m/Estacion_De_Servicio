package email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import modelo.Observer;
import modelo.Venta;

public class EMail implements Observer {

	@Override
	public void enviarMensaje(String direccionReceptor, Venta venta) {
		String textoMensaje = "Detalle de compra de combustible:\n" + venta.toString();
		String titulo = "Compra de combustible";
		String direccionEmisor = "informe_ventas@YPZW.org";

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");// it�s optional in Mailtrap
		props.put("mail.smtp.host", "smtp.mailtrap.io");
		props.put("mail.smtp.port", "2525");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("049dd5c2655c43", "c014a3c7865110");
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(direccionEmisor));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(direccionReceptor));
			message.setSubject(titulo);
			message.setText(textoMensaje);

			// Send message
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
