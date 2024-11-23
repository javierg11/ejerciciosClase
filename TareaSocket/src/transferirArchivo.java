
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class transferirArchivo {
	
	public static void main(String[] args) {
		FileInputStream fis=null;
		FileOutputStream fos=null;
		String fileRoute = //(args.length>0) ? args[0] : 
				"/home/alumno/Imágenes/hasfola.gif";
		File file = new File(fileRoute);
		if (file.exists()) {
			try {
				fis = new FileInputStream(file);
				File fileOutput = new File ("ArchivoRecibido.gif");
				System.out.println("Archivo guardado en: "+fileOutput.getAbsolutePath());
				if (fileOutput.exists())
					fileOutput.delete();
				fos = new FileOutputStream(fileOutput);
				Socket s = new Socket("127.0.0.1",8542);
				
				
				
				
				long tamaArchivo=file.length();
				transfer(tamaArchivo, fis, s.getOutputStream());
				
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if (fis !=null) {
						try {
							fis.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
				if (fos !=null) {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			}
		}		
		else
			System.err.println("no esxiste: "+fileRoute);
	}
	
	public static void transfer(long tamaArchivo, InputStream is, OutputStream os) 
	        throws IOException {
	    byte[] buffer = new byte[8192]; // Buffer más grande para mejor rendimiento
	    int bytesRead;
	    long totalBytesRead = 0;

	    while (totalBytesRead < tamaArchivo) {
	        bytesRead = is.read(buffer, 0, (int) Math.min(buffer.length, tamaArchivo - totalBytesRead));
	        if (bytesRead == -1) {
	            break; // Fin del stream antes de lo esperado
	        }
	        os.write(buffer, 0, bytesRead);
	        totalBytesRead += bytesRead;
	    }

	    os.flush();
	    
	    if (totalBytesRead < tamaArchivo) {
	        System.out.println("Advertencia: Se leyeron menos bytes de los esperados");
	    }
	}


}
