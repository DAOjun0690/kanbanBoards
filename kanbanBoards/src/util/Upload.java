package util;

import model.Account;

import javax.servlet.http.Part;
import java.io.File;
import java.util.Date;

public class Upload {
    public String uploadFile(final Account account, final Part file, final String type, final String path) throws Exception {
        String fileName = (new Date()).getTime() + "_" + type + "_" + account.getIdNumber() + ".jpg";
        File dir = new File(path + File.separator + account.getIdNumber());
        if(!dir.exists()){
            dir.mkdirs();
        }
        String pathSaving = dir.getPath() + File.separator + fileName;
        System.out.println(pathSaving);
        file.write(pathSaving);
        return fileName;
    }
}
