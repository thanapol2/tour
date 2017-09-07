package com.base;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class icon {
	private static String rootPath;
	static {
		URL url = Tools.class.getResource("/");
		try {
//			File file = new File(url.toURI());
//			JOptionPane.showMessageDialog(null,file.getCanonicalPath());
//			rootPath = file.getCanonicalPath();
//			rootPath = rootPath.replace("\\classes", "icon") + "\\";
			Path currentRelativePath = Paths.get("");
			rootPath = currentRelativePath.toAbsolutePath().toString()+"\\icon\\";
			System.out.println("Current relative path is: " + rootPath);

		}
		catch (Exception ex) {
			
		}
	}
	public static String getPath(){
		return rootPath;
	}
	public static String getPicPath(String aName){
		return rootPath+aName;
	}
}
