package com.example.windows10.myapplication;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.example.windows10.myapplication.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    public ViewHolder(View itemView){
        super(itemView);

        mView=itemView;
        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        //item Long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view,getAdapterPosition());
                return true;
            }
        });

    }
    public void setDetails(Context context,String title,String image,String description) {
        TextView mTitle = mView.findViewById(R.id.rTitle);
        TextView mDetail = mView.findViewById(R.id.rDescription);
        ImageView mImage = mView.findViewById(R.id.rImageView);
        mTitle.setText(title);
        mDetail.setText(description);
        Picasso.get().load(image).into(mImage);

    }
    private ViewHolder.ClickListener mClickListener;
        public interface ClickListener{
            void onItemClick(View view,int position);
            void onItemLongClick(View view,int position);
        }
        public void setOnClickListener(ViewHolder.ClickListener clickListener){
            mClickListener= clickListener;
        }


    }


