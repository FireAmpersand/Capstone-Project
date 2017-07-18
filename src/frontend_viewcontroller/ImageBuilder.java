/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend_viewcontroller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Logger.Logger;

/**
 *
 * @author Zach
 */
public class ImageBuilder {

    /**
     * Builds A Image, Then Returns It
     * @param fileLoc The File Location
     * @param name The Name Of The Image
     * @return Returns The Image
     */
    public static Image buildImage(String fileLoc, String name) {
        File file = new File(fileLoc);
        try {
            return ImageIO.read(file);
        } catch (IOException ex) {
            Logger.log(ImageBuilder.class.getName(), "FAILED TO FIND " + name.toUpperCase() + " IMAGE");
            return null;
        }
    }

    /**
     * Builds A Sized Image, Then Returns The Image
     * @param fileLoc The File Location
     * @param width The Width Of The Image
     * @param height The Height Of The Image
     * @param name The Name Of The Image
     * @return Returns The Sized Image
     */
    public static Image buildSizedImage(String fileLoc, int width, int height, String name) {
        File file = new File(fileLoc);
        BufferedImage buffImag;
        try {
            buffImag = ImageIO.read(file);
            return buffImag.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        } catch (IOException ex) {
            Logger.log(ImageBuilder.class.getName(), "FAILED TO FIND " + name.toUpperCase() + " IMAGE");
            return null;
        }
    }

}
