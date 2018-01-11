package in.androidmate.anujgupta.canvas_draw.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import in.androidmate.anujgupta.canvas_draw.GalleryActivity;
import in.androidmate.anujgupta.canvas_draw.R;

/**
 * Created by anujgupta on 11/01/18.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.Gallery> {

    Context context;
    String[] fileNameStrings;
    String[] filePathStrings;
    public GalleryAdapter(Context context, String[] fileNameStrings, String[] filePathStrings) {

        this.context = context;
        this.fileNameStrings = fileNameStrings;
        this.filePathStrings = filePathStrings;
    }

    @Override
    public Gallery onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_gallery,parent,false);
        Gallery g = new Gallery(v);
        return g;
    }

    @Override
    public void onBindViewHolder(Gallery holder, int position) {

    }

    @Override
    public int getItemCount() {
        return fileNameStrings.length;
    }

    public class Gallery extends RecyclerView.ViewHolder{

        ImageView ivLoadImage;

        public Gallery(View itemView) {
            super(itemView);

            ivLoadImage = itemView.findViewById(R.id.ivLoadImage);
        }
    }
}
