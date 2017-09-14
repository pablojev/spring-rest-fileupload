package pl.sdacademy.fileupload.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by pablojev on 02.08.2017.
 */
@RestController
public class SimpleController {

    private final String path = "upload/";

    @RequestMapping("/")
    public String main() {
        return "FileUploadApplication";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        String name = file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path + name)));
            stream.write(bytes);
            stream.close();
            return "SUCCESS";
        } catch (Exception e) {
            return "FAIL: " + e.getMessage();
        }
    }
}
