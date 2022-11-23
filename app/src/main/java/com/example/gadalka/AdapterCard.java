package com.example.gadalka;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterCard extends BaseAdapter {

    Context mContext;
    List<Card> cardsList;

    public AdapterCard(Context mContext, List<Card> cardsList) {
        this.mContext = mContext;
        this.cardsList = cardsList;
    }

    @Override
    public int getCount() {
        return cardsList.size();
    }

    @Override
    public Object getItem(int i) {
        return cardsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return cardsList.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_card, null);

        ImageView Img = v.findViewById(R.id.imageViewCard);
        Card card = cardsList.get(i);

        TextView textViewName = v.findViewById(R.id.textViewName);
        textViewName.setText(card.getNameCard());

        ImgCoder m = new ImgCoder(mContext);
        Img.setImageBitmap(m.getUserImage(card.getImage()));

      /*  v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AddData.class);
                intent.putExtra(Card.class.getSimpleName(), book);
                mContext.startActivity(intent);
            }
        });
            */
        return v;
    }

}
