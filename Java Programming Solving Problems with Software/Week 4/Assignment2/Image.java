
/**
 * Write a description of Image here.
 * 
 * @author ahmedfci93
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class Image {
    public void saveConvertedImg(ImageResource img,String newName)
    {
        img.setFileName(newName);
        img.save();
    }
    public ImageResource makeGray(ImageResource inImg)
    {
        ImageResource outImg=new ImageResource(inImg.getWidth(),inImg.getHeight());
        for(Pixel p:outImg.pixels())
        {
            Pixel inP=inImg.getPixel(p.getX(), p.getY());
            int avg = (inP.getBlue()+inP.getGreen()+inP.getRed())/3;
            p.setBlue(avg);
            p.setGreen(avg);
            p.setRed(avg);
        }
        return outImg;
    }
    public void selectAndConvert()
    {
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles())
        {
            ImageResource ir=new ImageResource(f);
            ir.draw();
            ImageResource gray=makeGray(ir);
            String newName="Gray- "+f.getName();
            saveConvertedImg(gray,newName);
            gray.draw();
        }
    }
    public void testGray()
    {
        ImageResource ir=new ImageResource();
        ir.draw();
        ImageResource gray=makeGray(ir);
        gray.draw();
    }
}
