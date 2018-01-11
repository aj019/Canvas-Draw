package in.androidmate.anujgupta.canvas_draw.helper;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anujgupta on 11/01/18.
 */

public class FileManager
{
    public static boolean saveBitmap(Bitmap bitmap)
    {
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DrawingCanvas/saved";
        File dir = new File(file_path);

        if(!dir.exists())
        {
            dir.mkdirs();
        }

        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()).concat(".png");
        File file = new File(dir, name);

        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);
             boolean saved = bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
            return saved;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Uri shareBitmap(Bitmap bitmap)
    {
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DrawingCanvas/shared";
        File dir = new File(file_path);

        if(!dir.exists())
        {
            dir.mkdirs();
        }

        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()).concat(".png");
        File file = new File(dir, name);

        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
            return Uri.fromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}