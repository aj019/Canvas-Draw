package in.androidmate.anujgupta.canvas_draw;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.androidmate.anujgupta.canvas_draw.adapters.GalleryAdapter;
import in.androidmate.anujgupta.canvas_draw.helper.FileManager;
import in.androidmate.anujgupta.canvas_draw.helper.PermissionManager;

public class GalleryActivity extends AppCompatActivity {

    @BindView(R.id.rvGallery)
    RecyclerView rvGallery;

    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    private static final int REQUEST_READ_STORAGE = 100;
    File file;
    RecyclerView.Adapter adapter;
    private static String TAG = "GalleryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        initViews();
        loadImagesFromDirectory();
        setRecyclerView();


    }


    private void initViews() {
        rvGallery.setLayoutManager(new GridLayoutManager(this,3));
    }

    private void loadImagesFromDirectory() {

        if (PermissionManager.checkReadStoragePermissions(this,REQUEST_READ_STORAGE))
        {
            if (!Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                Toast.makeText(this, "Error! No SDCARD Found!", Toast.LENGTH_LONG)
                        .show();
            } else {
                // Locate the image folder in your SD Card
                file = new File(Environment.getExternalStorageDirectory()
                        + File.separator + "DrawingCanvas/saved");
                // Create a new folder if no folder named SDImageTutorial exist
                file.mkdirs();
            }

            if (file.isDirectory()) {
                listFile = file.listFiles();
                // Create a String array for FilePathStrings
                FilePathStrings = new String[listFile.length];
                // Create a String array for FileNameStrings
                FileNameStrings = new String[listFile.length];

                for (int i = 0; i < listFile.length; i++) {
                    // Get the path of the image file
                    FilePathStrings[i] = listFile[i].getAbsolutePath();
                    Log.d(TAG,FilePathStrings[i]);
                    // Get the name image file
                    FileNameStrings[i] = listFile[i].getName();
                }
            }

            if(listFile.length == 0){
                Toast.makeText(this,"Gallery Empty",Toast.LENGTH_SHORT).show();
            }else{
                setRecyclerView();
            }

        }

    }

    private void setRecyclerView(){

        adapter = new GalleryAdapter(this,FileNameStrings,FilePathStrings);
        rvGallery.setAdapter(adapter);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_READ_STORAGE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Log.d(TAG,"Permission Granted");
                    loadImagesFromDirectory();
                } else
                {
                    Toast.makeText(this, "The app was not allowed to read your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }
}
