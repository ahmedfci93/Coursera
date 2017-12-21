import edu.duke.*;
import java.io.File;
public class PerimeterRunner {
    public File getFileWithLargestPerimeter()
    {
        DirectoryResource dr = new DirectoryResource();
        double ans=0.0;
        File ret=null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double max=getPerimeter(s);
            if(ans<max)
            {
                ret=f;
                ans=max;
            }
        }
        return ret;
    }
    public double getLargestPerimeterMultipleFiles()
    {
        DirectoryResource dr = new DirectoryResource();
        double ans=0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            ans=Math.max(ans,getPerimeter(s));
        }
        return ans;
    }
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    public double getAverageLength(Shape s)
    {
        double ans=0.0;
        ans=getPerimeter(s)/getNumPoints(s);
        return ans;
    }
    public double getLargestSide(Shape s)
    {
        double ans=0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            ans=Math.max(ans,currDist);
        }
        return ans;
    }
        public double getLargestX(Shape s)
    {
        double ans=0.0;
        for (Point currPt : s.getPoints()) {
            ans=Math.max(ans,currPt.getX());
        }
        return ans;
    }
    public int getNumPoints(Shape s)
    {
        int ans=0;
        for (Point currPt : s.getPoints())
        {
            ans++;
        }
        return ans;
    }
    public void testPerimeterMultipleFiles()
    {
        System.out.println("LargestPerimeterMultipleFiles = "+ getLargestPerimeterMultipleFiles());
    }
    public void testFileWithLargestPerimeter()
    {
        System.out.println("FileNameWithLargestPerimeter = " + getFileWithLargestPerimeter().getName());
    }
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        int points= getNumPoints(s);
        double length = getPerimeter(s);
        double average = getAverageLength(s);
        double largestSide=getLargestSide(s);
        double largestX=getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("NumPoints = " + points);
        System.out.println("AverageLength = " + average);
        System.out.println("LargestSide = " + largestSide);
        System.out.println("LargestX = " + largestX);
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
    }
}
