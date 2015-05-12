/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.generic.file;

import se.dibbler.generic.error.GenericError;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.dibbler.generic.control.Response;
import static se.dibbler.generic.file.FileAccess.EXECUTEONLY;
import static se.dibbler.generic.file.FileAccess.READONLY;
import static se.dibbler.generic.file.FileAccess.READ_AND_EXECUTE;
import static se.dibbler.generic.file.FileAccess.READ_AND_WRITE;
import static se.dibbler.generic.file.FileAccess.WRITEONLY;
import static se.dibbler.generic.file.FileAccess.WRITE_AND_EXECUTE;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class DibblerImageUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DibblerImageUtil.class);

    public static Response<BufferedImage> resizeImage(BufferedImage originalImage, int height, int width) {
        try {
            Response<Integer> fileType = getImageType(originalImage);
            if (fileType.hasErrors) {
                return Response.error(fileType.getError());
            }
            return resize(originalImage, fileType.getData(), height, width);
        } catch (Exception e) {
            LOG.error("[ ERROR when rezising image ] [ MESSAGE : {}]", e.getMessage());
            return Response.error(GenericError.FILE_HANDLING);
        }
    }

    private static Response<BufferedImage> resize(BufferedImage originalImage, int type, int height, int width) {
        try {
            BufferedImage resizedImage = new BufferedImage(width, height, type);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, width, height, null);
            g.dispose();
            return Response.success(resizedImage);
        } catch (Exception e) {
            LOG.error("[ ERROR when rezising file ] [ MESSAGE : {}]", e.getMessage());
            return Response.error(GenericError.FILE_HANDLING);
        }
    }

    private static Response<BufferedImage> resizeWithHint(BufferedImage originalImage, int type, int height, int width) {
        try {
            BufferedImage resizedImage = new BufferedImage(width, height, type);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, width, height, null);
            g.dispose();
            g.setComposite(AlphaComposite.Src);

            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            return Response.success(resizedImage);
        } catch (Exception e) {
            LOG.error("[ ERROR when resizing file ] [ MESSAGE : {}]", e.getMessage());
            return Response.error(GenericError.FILE_HANDLING);
        }
    }

    private static Response<Integer> getImageType(BufferedImage image) {
        try {
            Integer type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
            return Response.success(type);
        } catch (Exception e) {
            LOG.error("[ ERROR when getting image type ] [ MESSAGE : {}]", e.getMessage());
            return Response.error(GenericError.FILE_HANDLING);
        }
    }

    public static Response<Boolean> createFile(String base64Picture, DibblerFileType fileType, String url) {
        try {
            String singlePictureName = UUID.randomUUID().toString() + fileType.name() + ".png";
            String pictureNameFull = url + singlePictureName;

            Response<BufferedImage> image = getBufferedImageFromBase64String(base64Picture);
            if (image.hasErrors) {
                return Response.error(image.getError());
            }

            Response<Boolean> writeFile = writeFile(image.getData(), pictureNameFull, FileAccess.READONLY, FileType.PNG);
            if (writeFile.hasErrors) {
                return Response.error(writeFile.getError());
            }

            return Response.success(Boolean.TRUE);
        } catch (Exception e) {
            LOG.error("[ ERROR when creating file ] [ MESSAGE : {}]", e.getMessage());
            return Response.error(GenericError.FILE_HANDLING);
        }
    }

    public static Response<Boolean> writeFile(BufferedImage image, String fileName, FileAccess access, FileType type) {
        try {
            File imgOutFile = new File(fileName);
            setFileAccess(imgOutFile, access);
            ImageIO.write(image, type.getId(), imgOutFile);
            return Response.success(Boolean.TRUE);
        } catch (Exception e) {
            LOG.error("[ ERROR when writing file to disk ] [ MESSAGE : {}]", e.getMessage());
            return Response.error(GenericError.FILE_HANDLING);
        }
    }

    public static Response<BufferedImage> getBufferedImageFromBase64String(String base64Picture) {
        try {
            Response<byte[]> decodedImage = getByteFromBase64String(base64Picture);
            if (decodedImage.hasErrors) {
                return Response.error(decodedImage.getError());
            }
            BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(decodedImage.getData()));
            return Response.success(bufImg);
        } catch (Exception e) {
            LOG.error("[ ERROR when converting base64String to Buffered Image ] [ MESSAGE : {}]", e.getMessage());
            return Response.error(GenericError.FILE_HANDLING);
        }
    }

    public static Response<byte[]> getByteFromBase64String(String base64String) {
        try {
            byte[] data = Base64.decodeBase64(base64String);
            return Response.success(data);
        } catch (Exception e) {
            LOG.error("[ ERROR getting byte from Base64 String ] [ MESSAGE : {}]", e.getMessage());
            return Response.error(GenericError.FILE_HANDLING);
        }
    }

    private static void setFileAccess(File imgOutFile, FileAccess access) {
        switch (access) {
            case READONLY:
                imgOutFile.setReadable(true);
                imgOutFile.setWritable(false);
                imgOutFile.setExecutable(false);
                break;
            case EXECUTEONLY:
                imgOutFile.setReadable(false);
                imgOutFile.setWritable(false);
                imgOutFile.setExecutable(true);
                break;
            case WRITEONLY:
                imgOutFile.setReadable(false);
                imgOutFile.setWritable(true);
                imgOutFile.setExecutable(false);
                break;
            case READ_AND_EXECUTE:
                imgOutFile.setReadable(true);
                imgOutFile.setWritable(false);
                imgOutFile.setExecutable(true);
                break;
            case WRITE_AND_EXECUTE:
                imgOutFile.setReadable(false);
                imgOutFile.setWritable(true);
                imgOutFile.setExecutable(true);
                break;
            case READ_AND_WRITE:
                imgOutFile.setReadable(true);
                imgOutFile.setWritable(true);
                imgOutFile.setExecutable(false);
                break;
            default:
                LOG.error("[ Wrong file access type or not implemented in method ]");
        }
    }

}
