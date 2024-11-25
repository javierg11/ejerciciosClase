import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.InputStream;

public class Cliente {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		DataOutputStream dos = null;
		DataInputStream dis = null;
		Socket s = null;
		ObjectOutputStream oos = null;
		Mensaje men = null;
		Scanner lectura = new Scanner(System.in);

		System.out.print("Pon lo que quiere hacer (up, down, delete): ");
		String orden = lectura.next();

		System.out.print("Pon el nombre del archivo: ");
		String nombre = lectura.next();

		try {
			s = new Socket("127.0.0.1", 2311);
			oos = new ObjectOutputStream(s.getOutputStream());

			men = new Mensaje();

			//nombre = "C:\\Users\\rafag\\Desktop\\Fotos_random\\Cid-Aurora - copia.png";
			nombre = "Cid.png";
			men.setArchivo(nombre);
			men.setOrden(orden);

			oos.writeObject(men);
			oos.flush();

			dis = new DataInputStream(s.getInputStream());
			String nombreArchivo = dis.readUTF();

			if (nombreArchivo.equals("up")) {

				File f = new File(nombre);
				fis = new FileInputStream(f);
				dos = new DataOutputStream(s.getOutputStream());

				dos.writeLong(f.length());
				dos.flush();
				dos.writeUTF(f.getName());
				dos.flush();
				transferirArchivo.transfer(f.length(), fis, s.getOutputStream());

			}
			else if (nombreArchivo.equals("down")){
				File f = new File(nombre);
				fos = new FileOutputStream(f);
				
				dos = new DataOutputStream(s.getOutputStream());
				
				dos.writeLong(f.length());
				dos.flush();
				dos.writeUTF(f.getName());
				dos.flush();
				
				
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}