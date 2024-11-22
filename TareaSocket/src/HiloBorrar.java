import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloBorrar implements Runnable{
	
	private Socket s;
	private String archivo;

	public HiloBorrar(Socket s, String archivo) {
		this.s = s;
		this.archivo=archivo;
	}
	@Override
	public void run() {
		ObjectInputStream ois = null;	
		try {
			
//			ois = new ObjectInputStream(s.getInputStream());
//
//			Mensaje resp = (Mensaje) ois.readObject();
//			String nombreArchivo = resp.getArchivo();
//			System.out.println(nombreArchivo);

			File f = new File(archivo);
			
			if (f.exists()) {
				f.delete();
			}
			else {
				System.out.println("no existe");
				System.out.println(f.getAbsolutePath());
			}
			
	}catch (Exception e) {

		e.printStackTrace();
	} finally {
		if (ois != null) {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
}
