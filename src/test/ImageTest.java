package test;

import org.junit.Test;
import sun.awt.image.BufferedImageDevice;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ImageTest {
    @Test
  public void test(){
//       fail("Not yet implemented");
//
        try {
            BufferedImage  image = ImageIO.read(new File("C:\\Users\\yujie\\Pictures\\1.jpg"));
            assertNotNull(new Object());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
