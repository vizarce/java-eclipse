package unzip.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFiles {
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		String zipFilePath = "C:\\Users\\vi\\Desktop\\test\\test.zip";
		String destDir = "C:\\Users\\vi\\Desktop\\test\\output";
		unzip(zipFilePath, destDir);

	}
	
	private static void unzip(String zipFilePath, String destDir) {
		File dir = new File(destDir);
		if (!dir.exists()) dir.mkdir();
		byte[] buffer = new byte[BUFFER_SIZE];
		try {
			FileInputStream fis = new FileInputStream(zipFilePath);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);
				System.out.println("Unzipping to: " + newFile.getAbsolutePath());
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
