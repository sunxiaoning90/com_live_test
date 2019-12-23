package com.live.test.javaweb.core.addchristmashat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.opencv.core.Core;
import org.opencv.core.Mat;

public class Test {
public static void main(String[] args) {
	//Exception in thread "main" java.lang.UnsatisfiedLinkError: org.opencv.core.Mat.n_Mat(III)J
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	
	//Exception in thread "main" java.lang.UnsatisfiedLinkError: no opencv_java320 in java.library.path
	//安装opencv
	String from = "/opt/hat/from/a.PNG";
	String to = "/opt/hat/to/hat-a.PNG";
	
	File file = new File(from );
    try {
        InputStream is = new FileInputStream(file);
        BufferedImage bi = ImageIO.read(is);
        BufferedImageToMat bf = new BufferedImageToMat(bi);
        Mat input = bf.getMat();

        face_detect fc = new face_detect(input);
        Mat result = null;
        result = fc.returnMat();
        MatToBufferedImage mt = new MatToBufferedImage(result);

        BufferedImage out = mt.GetBuffer();

 ImageIO.write(out, "png", new File(to));
    JOptionPane.showMessageDialog(null,"图片已生成并保存到D:");
//   System.exit(0);
//   System.out.print(bi);
    } catch (FileNotFoundException e1) {
        e1.printStackTrace();
    } catch (IOException e1) {
        e1.printStackTrace();
    }
}
}
