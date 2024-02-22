package school.hei.sary.service.event;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ImageProcessingService {

    public byte[] processToBlackAndWhite(byte[] inputImage, String key) {
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(inputImage));
            if (image == null) {
                System.out.println("Impossible de lire l'image. Assurez-vous qu'il s'agit d'une image valide");
                return new byte[0];
            }

            BufferedImage convertedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g = convertedImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(convertedImage, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            System.out.println("Impossible de lire l'image. Assurez-vous qu'il s'agit d'une image valide");
            return new byte[0];
        }
    }
}


