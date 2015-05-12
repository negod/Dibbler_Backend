/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.generic.file;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.dibbler.generic.control.Response;
import se.dibbler.generic.error.GenericError;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class FileCreator {

    private static final Logger LOG = LoggerFactory.getLogger(FileCreator.class);

    public static Response<Map<PictureUrl, String>> createFilesFromBase64String(String base64, String url, Integer largeImageSize, Integer smallImageSize, DibblerFileType fileType) {
        try {
            Response<BufferedImage> originalImage = DibblerImageUtil.getBufferedImageFromBase64String(base64);
            if (originalImage.hasErrors) {
                return Response.error(originalImage.getError());
            }

            String smallPictuerName = UUID.randomUUID().toString() + fileType + smallImageSize + FileType.PNG.getIdWithDot();
            String largePictuerName = UUID.randomUUID().toString() + fileType + largeImageSize + FileType.PNG.getIdWithDot();

            String smallPictureNameFull = url + smallPictuerName;
            String largePictureNameFull = url + largePictuerName;

            Response<BufferedImage> smallImage = DibblerImageUtil.resizeImage(originalImage.getData(), smallImageSize, smallImageSize);
            if (smallImage.hasErrors) {
                return Response.error(smallImage.getError());
            }

            Response<BufferedImage> largeImage = DibblerImageUtil.resizeImage(originalImage.getData(), largeImageSize, largeImageSize);
            if (largeImage.hasErrors) {
                return Response.error(largeImage.getError());
            }

            Response<Boolean> writeSmallFile = DibblerImageUtil.writeFile(smallImage.getData(), smallPictureNameFull, FileAccess.READONLY, FileType.PNG);
            if (writeSmallFile.hasErrors) {
                return writeSmallFile.error(largeImage.getError());
            }

            Response<Boolean> writeLargeFile = DibblerImageUtil.writeFile(largeImage.getData(), largePictureNameFull, FileAccess.READONLY, FileType.PNG);
            if (writeLargeFile.hasErrors) {
                return writeLargeFile.error(largeImage.getError());
            }

            Map<PictureUrl, String> urls = new HashMap<PictureUrl, String>();
            urls.put(PictureUrl.FULL_URL_LARGE, largePictureNameFull);
            urls.put(PictureUrl.PICTURE_NAME_LARGE, largePictuerName);
            urls.put(PictureUrl.FULL_URL_SMALL, smallPictureNameFull);
            urls.put(PictureUrl.PICTURE_NAME_SMALl, smallPictuerName);

            return Response.success(urls);
        } catch (Exception e) {
            LOG.error("[ ERROR when creating files ] [ ERROR: ] {}", e.getMessage());
            return Response.error(GenericError.FILE_HANDLING);
        }
    }

}
