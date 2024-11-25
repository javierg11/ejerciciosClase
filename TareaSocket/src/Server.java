import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

public class Server {

	public static void main(String[] args) {
		Socket s = null;
		ServerSocket ss = null;
		String respuesta = "";
		ObjectInputStream ois = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;

		try {
			ss = new ServerSocket(2311);

			s = ss.accept();
			ois = new ObjectInputStream(s.getInputStream());
			Mensaje men = (Mensaje) ois.readObject();

			men.setOrden(men.getOrden().toLowerCase().trim());

			switch (men.getOrden()) {
			case "up":
				respuesta = "up";
				Thread thTransferir = new Thread(new HiloTransferencia(s));
				thTransferir.start();

				break;
			case "down":
				
				
				respuesta = "down";
				Thread thDescargar = new Thread(new hiloDescargar(s));
				thDescargar.start();
				
				break;
			case "delete":
				respuesta = "delete";
				
				
				Thread thBorrar = new Thread(new HiloBorrar(s, men.getArchivo()));
				thBorrar.start();
				break;
			default:
				respuesta = "Por favor pon una orden válida";
				break;
			}

			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(respuesta);
			dos.flush();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}