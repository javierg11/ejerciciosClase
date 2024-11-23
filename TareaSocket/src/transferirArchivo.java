
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
				"C:\\Users\\rafag\\Desktop\\Fotos_random\\asta.jpg";
		File file = new File(fileRoute);
		if (file.exists()) {
			try {
				fis = new FileInputStream(file);
				File fileOutput = new File ("ArchivoRecibido.png");
				System.out.println("Archivo guardado en: "+fileOutput.getAbsolutePath());
				if (fileOutput.exists())
					fileOutput.delete();
				fos = new FileOutputStream(fileOutput);
				Socket s = new Socket("127.0.0.1",2311);
				
				
				
				
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
	
	public  static void transfer(long tamaArchivo, InputStream fis, OutputStream os) 
			throws FileNotFoundException, IOException {
		
		byte [] array = new byte[2048];
		int cantBytes=0;
		
		//int total=0;
		
		//System.out.println(tamaArchivo);
		
		cantBytes= fis.read(array, 0 , array.length);
		while(cantBytes!=-1) {
			os.write(array, 0, cantBytes);
			if (cantBytes<tamaArchivo)
				cantBytes= fis.read(array, 0 , array.length);
			
				
			
				
			//total+=cantBytes;
			
			//System.out.println("% de transferencia: "+(total*100/tamaArchivo)+"%");
			//System.out.println("Lo que lleva del archivo es: "+total);
		}
		fis.close();
		os.close();
		
		//System.out.println(total);

		
	}

}
