package zip.multiple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipMultipleFiles {
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String sourceFile01Path = "C:\\Users\\vi\\Desktop\\test\\test.csv";
		String sourceFile02Path = "C:\\Users\\vi\\Desktop\\test\\Hello Agneta.docx";
		String sourceFile03Path = "C:\\Users\\vi\\Desktop\\test\\employees.json";
		String targetFilePath = "C:\\Users\\vi\\Desktop\\test\\zipMultipleFiles01.zip";
		zipMultipleFiles(targetFilePath, sourceFile01Path, sourceFile02Path, sourceFile03Path);
		System.out.println("Files " + sourceFile01Path.substring(25) + ", " + sourceFile02Path.substring(25) + " and " + sourceFile03Path.substring(25) + " were Successfully Zipped");

	}
	
	public static void zipMultipleFiles(String targetFilePath, String ...files) throws FileNotFoundException, IOException {
		List<String> srcFiles = Arrays.asList(files);
		FileOutputStream fos = new FileOutputStream(targetFilePath);
		ZipOutputStream zos = new ZipOutputStream(fos);
		for (String srcFile : srcFiles) {
			File fileToZip = new File(srcFile);
			FileInputStream fis = new FileInputStream(fileToZip);
			ZipEntry ze = new ZipEntry(fileToZip.getName());
			zos.putNextEntry(ze);
			byte[] b = new byte[BUFFER_SIZE];
			int len;
			while ((len = fis.read(b)) >= 0) {
				zos.write(b, 0, len);
			}
			fis.close();
		}
		zos.close();
		fos.close();
	}

}
