package com.bloggazete.arda.bloggazete.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
   String arrayText = String.valueOf(itemSizeArray);
    public RSSObject rssObject;
    public Context mContext;
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
    public void onBindViewHolder(FeedViewHolder holder, final int position) {
        holder.txtTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.txtPubDate.setText(rssObject.getItems().get(position).getPubDate());
        holder.txtAuthor.setText(rssObject.getItems().get(position).getAuthor());
        holder.txtContent.setText(rssObject.getItems().get(position).getContent());

        holder.txtViewCount.setText(arrayText);
       Glide.with(mContext)
               .asBitmap()
               .load(rssObject.getItems().get(position).getThumbnail())
               .into(holder.imgImageView);

        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    itemSizeArray[position]++;

                    //Internet acmaya calistim fakat calismadi
                /*Intent intent = new Intent(Intent.ACTION_VIEW,
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
