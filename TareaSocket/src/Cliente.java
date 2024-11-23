import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Cliente {
	public static void main(String[] args) {
		FileInputStream fis = null;
		DataOutputStream dos = null;
		DataInputStream dis=null;
		Socket s = null;
		ObjectOutputStream oos=null;
		Mensaje men=null;
		Scanner lectura = new Scanner (System.in);
		
		System.out.print("Pon lo que quiere hacer (up, down, delete): ");
		String orden = lectura.next();
		
		System.out.print("Pon el nombre del archivo: ");
		String nombre = lectura.next();
		
		try {
			s= new Socket("127.0.0.1", 8542);
			oos = new ObjectOutputStream(s.getOutputStream());
			
			men = new Mensaje();
			
			nombre="C:\\Users\\rafag\\Desktop\\Fotos_random\\Alpha.png";
			men.setArchivo(nombre);
			men.setOrden(orden);
			
			oos.writeObject(men);
			oos.flush();
			
			

			
			
			dis = new DataInputStream(s.getInputStream());
			String nombreArchivo = dis.readUTF();
			
			
			if (nombreArchivo.equals("up")) {
				
				
			
			
			System.out.println("dsaiugfdsiua");
			Thread thDescargar = new Thread(new HiloTransferencia(s));
			thDescargar.start();
			
			
			
			
		
			
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (s!=null) {
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (dos!=null) {
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (oos!=null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (dis!=null) {
				try {
					dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}