package br.dev.botecodigital.microblog.shared;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import br.dev.botecodigital.microblog.shared.exceptions.ResourceNotFoundException;

@Service
public class FileStoreService {

	@Value("${store.pathBase}")
	private String pathBase;

	@Value("${store.urlBase}")
	private String urlBase;

	public String writeFileBase64(String imageBase64, File path) {
		byte[] decodedBytes = Base64.getDecoder().decode(imageBase64);
		try {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(decodedBytes));
			BufferedImage jpgImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
			jpgImg.getGraphics().drawImage(img, 0, 0, null);
			ImageIO.write(jpgImg, "jpeg", new File(pathBase, path.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return urlBase.concat(path.getPath().replace("\\", "/"));
	}
	
	public InputStreamResource getImageJpgFile(String path) {
		try {
			InputStream in = new FileInputStream(pathBase + path);
			return new InputStreamResource(in);
		} catch (FileNotFoundException e) {
			throw new ResourceNotFoundException("A imagem não pode ser encontrada");
		}
		
	}
}
