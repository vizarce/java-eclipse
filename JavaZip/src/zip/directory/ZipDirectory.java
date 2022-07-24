package zip.directory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDirectory {
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String sourceDir = "C:\\Users\\vi\\Desktop\\test\\Directory";
		String targetFilePath = "C:\\Users\\vi\\Desktop\\test\\zipDirectoryWithSubDirectories.zip";
		zipDirectory(sourceDir, targetFilePath);
		System.out.println("The Directory " + sourceDir.substring(25) + " was Successfully Zipped");

	}
	
	public static void zipDirectory(String sourceDir, String targetFilePath) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(targetFilePath);
		ZipOutputStream zos = new ZipOutputStream(fos);
		File fileToZip = new File(sourceDir);
		zipFile(fileToZip, fileToZip.getName(), zos);
		zos.close();
		fos.close();
	}
	
	private static void zipFile(File fileToZip, String fileName, ZipOutputStream zos) throws IOException {
		if (fileToZip.isHidden())
			return;
		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zos.putNextEntry(new ZipEntry(fileName));
				zos.closeEntry();
			} else {
				zos.putNextEntry(new ZipEntry(fileName + "/"));
				zos.closeEntry();
			}
			File[] children = fileToZip.listFiles();
			for (File childFile: children) {
				zipFile(childFile, fileName + "/" + childFile.getName(), zos);
			}
			return;
		}
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry ze = new ZipEntry(fileName);
		zos.putNextEntry(ze);
		byte[] b = new byte[BUFFER_SIZE];
		int len;
		while ((len = fis.read(b)) >= 0) {
			zos.write(b, 0, len);
		}
		fis.close();
	}

}
