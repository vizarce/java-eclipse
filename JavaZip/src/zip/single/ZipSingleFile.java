package zip.single;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipSingleFile {
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String sourceFilePath = "C:\\Users\\vi\\Desktop\\test\\test.csv";
		String targetFilePath = "C:\\Users\\vi\\Desktop\\test\\zipFile.zip";
		zipSingleFile(sourceFilePath, targetFilePath);
		System.out.println("File " + sourceFilePath.substring(25) + " was Successfully Zipped");

	}
	
	public static void zipSingleFile(String filePath, String targetFilePath) throws IOException, FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(targetFilePath);
		ZipOutputStream zos = new ZipOutputStream(fos);
		File fileToZip = new File(filePath);
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry ze = new ZipEntry(fileToZip.getName());
		zos.putNextEntry(ze);
		byte[] b = new byte[BUFFER_SIZE];
		int len;
		while ((len = fis.read(b)) >= 0) {
			zos.write(b, 0, len);
		}
		zos.close();
		fis.close();
		fos.close();
	}

}
