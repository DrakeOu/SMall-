package com.xjtuse.mall.utils;

import com.xjtuse.mall.bean.system.Storage;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author 失了秩
 * @date 2020/3/14 15:11
 * @description
 */
public class FileUploadUtil {

    public static Storage init(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = UUIDUtils.getUUID();
        transfer(file, uuid);
        Storage storage = new Storage();
        storage.setName(file.getOriginalFilename());
        storage.setDeleted(false);
        storage.setUrl("http://localhost:6001/admin/storage/fetch/" + uuid + fileType );
        storage.setSize((int) file.getSize());
        storage.setType(file.getContentType());
        storage.setKey(uuid);
        return storage;
    }

    public static void transfer(MultipartFile file, String uuid) {
        try {
            String originalFilename = file.getOriginalFilename();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            File path =
                    new File("small/src/main/resources/static/" + uuid + fileType );
            if (!path.getParentFile().exists()) {
                path.getParentFile().mkdirs();
            }

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path));
            bufferedOutputStream.write(file.getBytes());
            bufferedOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
