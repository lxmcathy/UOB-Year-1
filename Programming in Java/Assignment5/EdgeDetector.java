package com.bham.pij.assignments.edgedetector;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EdgeDetector {
	

    private final Color borderColor = new Color(0, 0, 0, 1.0);
	


    public static void main(String[] args) {
        EdgeDetector edgeDetector = new EdgeDetector();
        String file = "rubik.png";

        Image originalImage = edgeDetector.readImage(file);
        Image filteredImage = edgeDetector.filterImage(originalImage);

    }

    public Image filterImage(Image image) {
    	float[][] filter = createFilter();
        Color[][] pixelDataExtended = getPixelDataExtended(image);
        Color[][] filteredColors = applyFilter(pixelDataExtended, filter);
        Color[][] greyScaledColors = applyGreyscale(filteredColors);

        String fileName = "filtered_image.png";
        saveImage(greyScaledColors, fileName);
        return readImage(fileName);
    }


    public float[][] createFilter() {
        float[][]  f = {
                {-1, -1, -1},
                {-1, 8, -1},
                {-1, -1, -1}
        };
		return f;
    }

    public Color[][] getPixelDataExtended(Image image) {
    	
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();

        Color[][] colors = new Color[width + 2][height + 2];
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                if (0 == i || width + 1 == i || j == 0 || height + 1 == j) {
                    colors[i][j] = borderColor;
                } else {
                    colors[i][j] = pixelReader.getColor(i - 1, j - 1);
                }
            }
        }

        return colors;
    }



    public Color[][] applyGreyscale(Color[][] pixels) {
        int width = pixels.length;
        int height = pixels[0].length;

        Color[][] greyColors = new Color[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = pixels[i][j];
                double avg = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

                greyColors[i][j] = new Color(avg, avg, avg, 1.0);
            }
        }

        return greyColors;
    }

    public Color[][] applyFilter(Color[][] pixels, float[][] filter) {
        int width = pixels.length - 2;
        int height = pixels[0].length - 2;

        Color[][] filteredColors = new Color[width][height];
        for (int i = 1; i <= width; i++) {
            for (int j = 1; j <= height; j++) {
                filteredColors[i - 1][j - 1] = applyFilter(pixels, filter, i, j);
            }
        }

        return filteredColors;
    }

    private Color applyFilter(Color[][] pixels, float[][] filter, int row, int col) {
        double r = 0, g = 0, b = 0;
        for (int i = 0; i < filter.length; i++) {
            for (int j = 0; j < filter.length; j++) {
                Color color = pixels[row - 1 + i][col - 1 + j];
                r += color.getRed() * filter[i][j];
                g += color.getGreen() * filter[i][j];
                b += color.getBlue() * filter[i][j];
            }
        }

        r = Math.max(0, r);
        r = Math.min(1, r);

        g = Math.max(0, g);
        g = Math.min(1, g);

        b = Math.max(0, b);
        b = Math.min(1, b);

        return new Color(r, g, b, 1.0);
    }


    private void saveImage(Color[][] pixels, String filename) {
        WritableImage wimg = new WritableImage(pixels.length, pixels.length);
        PixelWriter pw = wimg.getPixelWriter();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels.length; j++) {
                pw.setColor(i, j, pixels[i][j]);
            }
        }
        BufferedImage bImage = SwingFXUtils.fromFXImage(wimg, null);
        try {
            ImageIO.write(bImage, "png", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Image readImage(String fileName) {
        try {
            return new Image(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



}
