package tour.tourListDetail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import com.base.Tools;
import com.base.ftpConnection;

public class listDetailFTP {
	
	public BufferedImage downloadImage(String fileName) {
		FTPClient ftp  = null;
		BufferedImage image = null;
		try {
			ftp = ftpConnection.INSTANCE.getFTP();
			InputStream inputSteam = ftp.retrieveFileStream(fileName);
			image = ImageIO.read(inputSteam);
			ftpConnection.INSTANCE.closeConnection(ftp);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Can't download Image. Please Check FTP server."
					,"Error", JOptionPane.ERROR_MESSAGE);
		}finally{
			if(ftp != null)ftpConnection.INSTANCE.closeConnection(ftp);
		}

		return image;
	}
	
	public boolean uploadImage(File file,String picName) {
		boolean isUpload = false;
//		String picName = null;
		FTPClient ftp = null;
		try {
			ftp = ftpConnection.INSTANCE.getFTP();
			InputStream inputStream = new FileInputStream(file);
//			picName = Tools.genPicName(file.getName());
			isUpload = ftp.storeFile(picName, inputStream);
            inputStream.close();
//			Change Pic Name for new file
//            this.picName = temp;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Can't upload file and Insert Data."
					+ "Please check FTP server"
					,"Error", JOptionPane.ERROR_MESSAGE);
		} finally{
			if(ftp != null)ftpConnection.INSTANCE.closeConnection(ftp);
		}

		return isUpload;
	}
	
	
}
