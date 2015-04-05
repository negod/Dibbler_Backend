/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.utils;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.dibbler.backend.constants.DibblerFileType;
import se.dibbler.backend.constants.FileAccess;
import se.dibbler.backend.constants.FileType;
import se.dibbler.backend.constants.PictureUrl;
import se.dibbler.backend.generics.DibblerFileUtil;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class FileCreator {

    private static final Logger LOG = LoggerFactory.getLogger(FileCreator.class);

    public static Response<Map<PictureUrl, String>> createFilesFromBase64String(String base64, String url, Integer largeImageSize, Integer smallImageSize, DibblerFileType fileType) {
        try {
            Response<BufferedImage> originalImage = DibblerFileUtil.getBufferedImageFromBase64String(base64);
            if (originalImage.hasErrors) {
                return Response.error(originalImage.getError());
            }

            String smallPictuerName = UUID.randomUUID().toString() + fileType + smallImageSize + FileType.PNG.getIdWithDot();
            String largePictuerName = UUID.randomUUID().toString() + fileType + largeImageSize + FileType.PNG.getIdWithDot();

            String smallPictureNameFull = url + smallPictuerName;
            String largePictureNameFull = url + largePictuerName;

            Response<BufferedImage> smallImage = DibblerFileUtil.resizeImage(originalImage.getData(), smallImageSize, smallImageSize);
            if (smallImage.hasErrors) {
                return Response.error(smallImage.getError());
            }

            Response<BufferedImage> largeImage = DibblerFileUtil.resizeImage(originalImage.getData(), largeImageSize, largeImageSize);
            if (largeImage.hasErrors) {
                return Response.error(largeImage.getError());
            }

            Response<Boolean> writeSmallFile = DibblerFileUtil.writeFile(smallImage.getData(), smallPictureNameFull, FileAccess.READONLY, FileType.PNG);
            if (writeSmallFile.hasErrors) {
                return writeSmallFile.error(largeImage.getError());
            }

            Response<Boolean> writeLargeFile = DibblerFileUtil.writeFile(largeImage.getData(), largePictureNameFull, FileAccess.READONLY, FileType.PNG);
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
