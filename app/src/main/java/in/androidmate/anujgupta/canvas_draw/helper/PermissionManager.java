package in.androidmate.anujgupta.canvas_draw.helper;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by anujgupta on 11/01/18.
 */

public class PermissionManager
{

    public static boolean checkWriteStoragePermissions(Activity activity,int REQUEST_WRITE_STORAGE)
    {
        boolean hasPermission = (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
        return hasPermission;
    }

    public static boolean checkReadStoragePermissions(Activity activity,int REQUEST_WRITE_STORAGE)
    {
        boolean hasPermission = (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
        return hasPermission;
    }
}
