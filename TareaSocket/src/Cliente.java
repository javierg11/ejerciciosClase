import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
			
			men.setArchivo(nombre);
			men.setOrden(orden);
			
			oos.writeObject(men);
			oos.flush();
			
			
				File f = new File(nombre);
				fis = new FileInputStream(f);
				dos = new DataOutputStream(s.getOutputStream());
				dos.writeLong(f.length());
				dos.flush();
				
				dos.writeUTF(f.getName());
				dos.flush();
				
			
				transferirArchivo.transfer(f.length(), fis, s.getOutputStream());
			
			
			dis = new DataInputStream(s.getInputStream());
			String nombreArchivo = dis.readUTF();
			System.out.println(nombreArchivo+"adashfusdagfhjksbnaflghjlj");
			
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