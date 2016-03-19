package cc.aisc.ybk.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * Created by sjf on 15-11-14.
 */
public abstract class ImageUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);

    public static final MediaTracker tracker = new MediaTracker(new Component() {
        private static final long serialVersionUID = 1234162663955668507L;
    });

    public static void cut(String srcPath, String savePath, int x, int y, int width, int height) {
        String houZhui = srcPath.substring(srcPath.lastIndexOf(".") + 1);// 后缀

        FileInputStream is = null;
        ImageInputStream iis = null;
        try {
            // 读取图片文件
            is = new FileInputStream(srcPath);

			/*
			 * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .（例如 "jpeg" 或 "tiff"）等 。
			 */
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = it.next();
            // 获取图片流
            iis = ImageIO.createImageInputStream(is);

			/*
			 * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
			 */
            reader.setInput(iis, true);

			/*
			 * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java CommentImage I/O 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回
			 * ImageReadParam 的实例。
			 */
            ImageReadParam param = reader.getDefaultReadParam();

			/*
			 * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
			 */
            Rectangle rect = new Rectangle(x, y, width, height);

            // 提供一个 BufferedImage，将其用作解码像素数据的目标。
            param.setSourceRegion(rect);

			/*
			 * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的 BufferedImage 返回。
			 */
            BufferedImage bi = reader.read(0, param);

            // 保存新图片
            ImageIO.write(bi, houZhui, new File(savePath)); // "jpg"
        } catch (Exception e) {
            throw new RuntimeException("CommentImage cut exception!");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (iis != null) {
                try {
                    iis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * @param  resizedPath
     *            原图像
     * @param originalPath
     *            压缩后的图像
     * @param width
     *            图像宽
     * @param format
     *            图片格式 jpg, jpeg, png, gif(非动画)
     * @throws IOException
     */
    public static void resize(String originalPath, String resizedPath, int width, String format)  {
        File originalFile = new File(originalPath);
        File resizedFile = new File(resizedPath);
        if (format != null && "gif".equals(format.toLowerCase())) {
            resize(originalFile, resizedFile, width, 1);
            return;
        }
        FileInputStream fis = null;
        ByteArrayOutputStream byteStream = null;
        byte[] in = null;
        try {
            fis = new FileInputStream(originalFile);
            byteStream = new ByteArrayOutputStream();
            int readLength = -1;
            int bufferSize = 1024;
            byte bytes[] = new byte[bufferSize];
            while ((readLength = fis.read(bytes, 0, bufferSize)) != -1) {
                byteStream.write(bytes, 0, readLength);
            }
            in = byteStream.toByteArray();

            Image inputImage = Toolkit.getDefaultToolkit().createImage(in);
            waitForImage(inputImage);
            int imageWidth = inputImage.getWidth(null);
            if (imageWidth < 1)
                throw new IllegalArgumentException("image width " + imageWidth + " is out of range");
            int imageHeight = inputImage.getHeight(null);
            if (imageHeight < 1)
                throw new IllegalArgumentException("image height " + imageHeight + " is out of range");

            // Create output image.
            int height = -1;
            double scaleW = (double) imageWidth / (double) width;
            double scaleY = (double) imageHeight / (double) height;
            if (scaleW >= 0 && scaleY >= 0) {
                if (scaleW > scaleY) {
                    height = -1;
                } else {
                    width = -1;
                }
            }
            Image outputImage = inputImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            checkImage(outputImage);
            encode(new FileOutputStream(resizedFile), outputImage, format);
        } catch (Exception e) {
            throw new RuntimeException("resize异常");
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                byteStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** Checks the given image for valid width and height. */
    private static void checkImage(Image image) {
        waitForImage(image);
        int imageWidth = image.getWidth(null);
        if (imageWidth < 1)
            throw new IllegalArgumentException("image width " + imageWidth + " is out of range");
        int imageHeight = image.getHeight(null);
        if (imageHeight < 1)
            throw new IllegalArgumentException("image height " + imageHeight + " is out of range");
    }

    /**
     * Waits for given image to load. Use before querying image height/width/colors.
     */
    private static void waitForImage(Image image) {
        try {
            tracker.addImage(image, 0);
            tracker.waitForID(0);
            tracker.removeImage(image, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Encodes the given image at the given quality to the output stream. */
    private static void encode(OutputStream outputStream, Image outputImage, String format) throws IOException {
        int outputWidth = outputImage.getWidth(null);
        if (outputWidth < 1)
            throw new IllegalArgumentException("output image width " + outputWidth + " is out of range");
        int outputHeight = outputImage.getHeight(null);
        if (outputHeight < 1)
            throw new IllegalArgumentException("output image height " + outputHeight + " is out of range");

        // Get a buffered image from the image.
        BufferedImage bi = new BufferedImage(outputWidth, outputHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D biContext = bi.createGraphics();
        biContext.drawImage(outputImage, 0, 0, null);
        ImageIO.write(bi, format, outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 缩放gif图片
     *
     * @param originalFile
     *            原图片
     * @param resizedFile
     *            缩放后的图片
     * @param newWidth
     *            宽度
     * @param quality
     *            缩放比例 (等比例)
     * @throws IOException
     */
    private static void resize(File originalFile, File resizedFile, int newWidth, float quality) {
        if (quality < 0 || quality > 1) {
            throw new IllegalArgumentException("Quality has to be between 0 and 1");
        }
        FileOutputStream out = null;
        try {
            ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());

            Image i = ii.getImage();
            Image resizedImage = null;
            int iWidth = i.getWidth(null);
            int iHeight = i.getHeight(null);
            if (iWidth > iHeight) {
                resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight) / iWidth, Image.SCALE_SMOOTH);
            } else {
                resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight, newWidth, Image.SCALE_SMOOTH);
            }
            // This code ensures that all the pixels in the image are loaded.
            Image temp = new ImageIcon(resizedImage).getImage();
            // Create the buffered image.
            BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // Copy image to buffered image.
            Graphics g = bufferedImage.createGraphics();
            // Clear background and paint the image.
            g.setColor(Color.white);
            g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
            g.drawImage(temp, 0, 0, null);
            g.dispose();
            // Soften.
            float softenFactor = 0.05f;
            float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
            Kernel kernel = new Kernel(3, 3, softenArray);
            ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
            bufferedImage = cOp.filter(bufferedImage, null);
            // Write the jpeg to a file.
            out = new FileOutputStream(resizedFile);
            // Encodes image as a JPEG data stream
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
            param.setQuality(quality, true);
            encoder.setJPEGEncodeParam(param);
            encoder.encode(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把图片印刷到图片上
     *
     * @param img
     *            -- 水印文件
     * @param targetImg
     *            -- 目标文件
     * @param x
     * @param y
     */
    public final static void imgWatermark(String img, String targetImg, int x, int y) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            // 水印文件
            File _filebiao = new File(img);
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.drawImage(src_biao, wideth - wideth_biao - x, height - height_biao - y, wideth_biao, height_biao, null);
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印文字水印图片，右下角计算坐标
     * @param pressText --文字
     * @param targetImg -- 目标图片
     * @param fontName -- 字体名
     * @param fontStyle -- 字体样式
     * @param color -- 字体颜色
     * @param fontSize -- 字体大小
     * @param x -- 偏移量
     * @param y
     */
    public static void txtWatermark(String pressText, String targetImg,
                                 String fontName, int fontStyle, int color, int fontSize, int x, int y) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
            g.setColor(Color.RED);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.drawString(pressText, wideth - fontSize - x, height - fontSize/2 - y);
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
