package com.bloggazete.arda.bloggazete.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bloggazete.arda.bloggazete.Model.RSSObject;
import com.bloggazete.arda.bloggazete.R;
import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by Arda on 4/23/2018.
 */

class FeedViewHolder extends RecyclerView.ViewHolder
{

    public TextView txtTitle,txtPubDate,txtContent,txtAuthor,txtViewCount;

    public ImageView imgImageView;

    public Button btnShare;

    CardView parentLayout;


    public FeedViewHolder(View itemView) {
        super(itemView);

        txtTitle=(TextView)itemView.findViewById(R.id.txtTitle);
        txtPubDate=(TextView)itemView.findViewById(R.id.txtPubDate);
        txtAuthor=(TextView)itemView.findViewById(R.id.txtAuthor);
        txtContent=(TextView)itemView.findViewById(R.id.txtContent);
        imgImageView=(ImageView)itemView.findViewById(R.id.imageView);
        parentLayout = itemView.findViewById(R.id.parent_layout);
        btnShare=itemView.findViewById(R.id.btnShare);
        txtViewCount=(TextView)itemView.findViewById(R.id.txtViewCount);
    }
}
public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>{
    int[] itemSizeArray = new int[10];
    public RSSObject rssObject;
    public final Context mContext;
    public LayoutInflater inflater;



    public FeedAdapter(RSSObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        inflater =LayoutInflater.from(mContext);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row,parent,false);

        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FeedViewHolder holder, final int position) {
        holder.txtTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.txtPubDate.setText(rssObject.getItems().get(position).getPubDate());
        holder.txtAuthor.setText(rssObject.getItems().get(position).getAuthor());
        holder.txtContent.setText(rssObject.getItems().get(position).getContent());
        holder.txtViewCount.setText( "Viewed: 0");
       Glide.with(mContext)
               .asBitmap()
               .load(rssObject.getItems().get(position).getThumbnail())
               .into(holder.imgImageView);

        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    itemSizeArray[position]++;
                holder.txtViewCount.setText( "Viewed: "+itemSizeArray[position]);

               /* Uri webpage = Uri.parse("http://www.android.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);


                PackageManager packageManager = mContext.getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(webIntent,
                        PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentSafe = activities.size() > 0;
                if (isIntentSafe) {
                    getApplicationContext().startActivity(webIntent);
                }
                else{
                    holder.txtViewCount.setText( "FAILED "+itemSizeArray[position]);
                }
// Start an activity if it's safe



                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(rssObject.getItems().get(position).getLink()));
                mContext.startActivity(intent);*/
            }
        });
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("link", rssObject.getItems().get(position).getLink());
                clipboard.setPrimaryClip(clip);



            }
        });
    }


    @Override
    public int getItemCount() {
        return rssObject.items.size();
    }
}
