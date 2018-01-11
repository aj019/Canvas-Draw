package in.androidmate.anujgupta.canvas_draw;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.File;

import butterknife.BindView;
import in.androidmate.anujgupta.canvas_draw.adapters.GalleryAdapter;

public class GalleryActivity extends AppCompatActivity {

    @BindView(R.id.rvGallery)
    RecyclerView rvGallery;

    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    File file;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        initViews();
        loadImagesFromDirectory();
        setRecyclerView();


    }


    private void initViews() {
        rvGallery.setLayoutManager(new GridLayoutManager(this,2));
    }

    private void loadImagesFromDirectory() {

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
                // Get the name image file
                FileNameStrings[i] = listFile[i].getName();
            }
        }

    }

    private void setRecyclerView(){

        adapter = new GalleryAdapter(this,FileNameStrings,FilePathStrings);

    }

}
