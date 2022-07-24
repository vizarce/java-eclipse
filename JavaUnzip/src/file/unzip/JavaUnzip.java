package file.unzip;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JavaUnzip {
	private static final int BUFFER_SIZE = 4096;
	
	public static void unzip(String zipFilePath, String destinationPath) throws IOException {
		File destDirectory = new File(destinationPath);
		if (!destDirectory.exists())
			destDirectory.mkdir();
		ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry ze = zis.getNextEntry();
		
		while (ze != null) {
			String filePath = destinationPath + File.separator + ze.getName();
			if (!ze.isDirectory()) {
				extractFile(zis, filePath);
			} else {
				File directory = new File(filePath);
				directory.mkdirs();
				//filePath = directory.getAbsolutePath() + ze.getName();
				//System.out.println(filePath);
				//extractFile(zis, filePath);
			}
			zis.closeEntry();
			ze = zis.getNextEntry();
		}
		zis.close();
	}
	
	private static void extractFile(ZipInputStream zis, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] b = new byte[BUFFER_SIZE];
		int readByte = 0;
		while ((readByte = zis.read(b)) != -1) {
			bos.write(b, 0, readByte);
		}
		bos.close();
	}

	public static void main(String[] args) throws IOException {
		String zipFilePath = "C:\\Users\\vi\\Desktop\\test\\test.zip";
		String destinationPath = "C:\\Users\\vi\\Desktop\\test\\output";
		unzip(zipFilePath, destinationPath);
		System.out.println("Zip File " + zipFilePath.substring(25) + " extracted Successfully");

	}

}
