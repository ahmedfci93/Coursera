
/**
 * Write a description of BatchInversions here.
 * 
 * @author ahmedfci92 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
public class BatchInversions {
    public void saveConvertedImg(ImageResource img,String newName)
    {
        img.setFileName(newName);
        img.save();
    }
    public ImageResource makeInversion(ImageResource img)
    {
        ImageResource invImg=new ImageResource(img.getWidth(),img.getHeight());
        for(Pixel p: invImg.pixels())
        {
            Pixel imgP= img.getPixel(p.getX(),p.getY());
            p.setBlue(255-imgP.getBlue());
            p.setRed(255-imgP.getRed());
            p.setGreen(255-imgP.getGreen());
        }
        return invImg;
    }
    public void selectAndConvert()
    {
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles())
        {
            ImageResource ir=new ImageResource(f);
            ir.draw();
            ImageResource inverted=makeInversion(ir);
            String newName="Inverted-"+f.getName();
            System.out.println(newName);
            saveConvertedImg(inverted,newName);
            inverted.draw();
        }
    }
    public void testInversion()
    {
        ImageResource ir=new ImageResource();
        ir.draw();
        ImageResource gray=makeInversion(ir);
        gray.draw();
    }
}
