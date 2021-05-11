package test;

import org.junit.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ImageTest {
    @Test
  public void test(){
//       fail("Not yet implemented");
//
        try {
//            BufferedImage  image = ImageIO.read(new File("C:\\Users\\yujie\\Pictures\\1.jpg"));
//            assertNotNull(image);
            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
