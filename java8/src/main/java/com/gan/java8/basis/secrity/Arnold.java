package com.gan.java8.basis.secrity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Arnold's Cat Map(Arnold变换)
 *
 * @author BenZeph
 *
 */
public class Arnold {
    private File srcImageFile, desImageFile;
    private BufferedImage srcImage, desImage;
    private int[][] srcMatrix, desMatrix;
    private int N;// 图像的长度（宽度）
    private int time;// 周期

    /**
     * Arnold's Cat Map(Arnold变换)
     *
     * @param srcImageFile
     * @param desImageFile
     * @param time
     *            周期
     */
    public Arnold(File srcImageFile, File desImageFile, int time) {
        this.srcImageFile = srcImageFile;
        this.desImageFile = desImageFile;
        this.time = time;
    }

    /**
     * 读取图像
     *
     * @param imageFile
     * @return
     */
    public BufferedImage readImage(File imageFile) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 获取图像RGB矩阵
     *
     * @param image
     * @return
     */
    public int[][] getMatrixRGB(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] imageMatrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                imageMatrix[i][j] = image.getRGB(i, j);
            }
        }
        return imageMatrix;
    }

    /**
     * 写入图像
     *
     * @param
     */
    public void writeImage(File imageFile, BufferedImage image) {
        try {
            ImageIO.write(image, "jpg", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Arnold变换初始化
     *
     * @param image
     * @return
     */
    public boolean initArnold(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        if (width != height) {
            return false;
        } else {
            N = width;
            srcMatrix = getMatrixRGB(image);
            desMatrix = new int[N][N];
            desImage = new BufferedImage(width, height, srcImage.getType());
            return true;
        }
    }

    /**
     * Arnold变换
     */
    public void arnoldTransform() {
        srcImage = readImage(srcImageFile);
        if (initArnold(srcImage)) {
            for (int n = 0; n < time; n++) {
                if (n != 0) {
                    srcMatrix = desMatrix;
                    desMatrix = new int[N][N];
                }
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        desMatrix[(i + j) % N][(i + 2 * j) % N] = srcMatrix[i][j];
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    desImage.setRGB(i, j, desMatrix[i][j]);
                }
            }
        }
        writeImage(desImageFile, desImage);
    }

    public static void main(String[] args) {
        File srcImageFile = new File("D://SecretiveBmp.jpg");
        File desImageFile = new File("D://15.jpg");
        Arnold arnold = new Arnold(srcImageFile, desImageFile, 15);
        arnold.arnoldTransform();
    }
}