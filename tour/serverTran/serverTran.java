package tour.serverTran;

import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.awt.image.*;

import javax.imageio.*; 
import javax.swing.*; 

public class serverTran extends Thread {
    public static final int PORT = 4000;
    public static final String path = "TourData\\image";
    public static final int BUFFER_SIZE = 100;
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static final Date date = new Date();
    @Override
    public void run() {
        try {
        	File theDir = new File(path);
        	if (!theDir.exists()) {
       	        theDir.mkdirs();
       	    }
        	ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = new Socket();  
            while (true) {
            	System.out.println("["+dateFormat.format(date)+"] "+
            			"Server Status : Online");
            	socket = serverSocket.accept();
            	System.out.println("["+dateFormat.format(date)+"] "+
            			"Server Status : Start save image");
                saveFile(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void saveFile(Socket socket) throws Exception {
        InputStream in = socket.getInputStream();
        DataInputStream dis = new DataInputStream(in);
        String fileName = dis.readUTF();
        System.out.println(fileName);
        int len = dis.readInt();
        System.out.println("Image Size: " + len/1024 + "KB");
        
        byte[] data = new byte[len];
        dis.readFully(data);
        dis.close();
        in.close();

        InputStream ian = new ByteArrayInputStream(data);
        BufferedImage bImage = ImageIO.read(ian);
        ImageIO.write(bImage, "jpg",new File(path+"\\"+fileName));
        System.out.println("["+dateFormat.format(date)+"] "+
    			"Server Status : Finish save image");
    }
 
    public static void throwException(String message) throws Exception {
        throw new Exception(message);
    }
 
    public static void main(String[] args) {
        new serverTran().start();
    }
}  