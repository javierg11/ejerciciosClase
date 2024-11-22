import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class hiloDescargar implements Runnable{
	private Socket s;

	public hiloDescargar(Socket s) {
		this.s = s;
	}
	@Override
	public void run() {
		DataInputStream dis = null;
		DataOutputStream dos=null;
		try {
			
			dis = new DataInputStream(s.getInputStream());

			String nombreArchivo = "server/"+dis.readUTF();

			File f = new File(nombreArchivo);
			dos = new DataOutputStream(s.getOutputStream());
			
			
			
	}catch (IOException e) {

		e.printStackTrace();
	}finally {
		if (dis != null) {
			try {
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (dos != null) {
			try {
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
		
}
	
