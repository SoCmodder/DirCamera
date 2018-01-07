package world.mitchmiller.dircamera.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Mitch Miller on 1/7/2018.
 */

public class FileUtils {

    public final static String APP_PATH_SD_CARD = "/DirCamera/";

    public static boolean saveImageToExternalStorage(Activity activity, Bitmap image, String dirName) {
        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + APP_PATH_SD_CARD + dirName;

        try {
            File dir = new File(fullPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            OutputStream fOut;
            File file = new File(fullPath, NameUtilities.defaultFileName());
            file.createNewFile();
            fOut = new FileOutputStream(file);

            // 100 means no compression, the lower you go, the stronger the compression
            image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();

            MediaStore.Images.Media.insertImage(activity.getContentResolver(),
                    file.getAbsolutePath(),
                    file.getName(),
                    file.getName());

            return true;

        } catch (Exception e) {
            Log.e("saveToExternalStorage()", e.getMessage());
            return false;
        }
    }
}
