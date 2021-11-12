package com.example.gpsdemo.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.gpsdemo.R;
import com.example.gpsdemo.TruckListActivity;
import com.example.gpsdemo.utils.SliderItemObject;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private final List<SliderItemObject> mSliderItems = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayout ll_truck = root.findViewById(R.id.ll_truck);
        mSliderItems.clear();
        SliderView sliderView = root.findViewById(R.id.imageSlider);
        SliderItemObject sl1 = new SliderItemObject(R.drawable.one, "");
        SliderItemObject sl2 = new SliderItemObject(R.drawable.two, "");
        SliderItemObject sl3 = new SliderItemObject(R.drawable.three, "स्वच्छ भारत, स्वस्थ भारत");
        mSliderItems.add(sl1);
        mSliderItems.add(sl2);
        mSliderItems.add(sl3);
        sliderView.setSliderAdapter(new SliderAdapterExample(getActivity()));
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
        TextView text_news = root.findViewById(R.id.text_news);
        text_news.setSelected(true);
        ll_truck.setOnClickListener(v -> {
            Intent intent1 = new Intent(getActivity(), TruckListActivity.class);
            startActivity(intent1);
        });
        return root;
    }

    public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

        private final Context context;

        public SliderAdapterExample(Context context) {
            this.context = context;
        }


        @Override
        public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
            return new SliderAdapterVH(inflate);
        }

        @Override
        public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

            SliderItemObject sliderItem = mSliderItems.get(position);

            viewHolder.textViewDescription.setText(sliderItem.getText());
            viewHolder.textViewDescription.setTextSize(16);
            viewHolder.textViewDescription.setTextColor(Color.WHITE);
            Glide.with(viewHolder.itemView)
                    .load(sliderItem.getImage())
                    .fitCenter()
                    .into(viewHolder.imageViewBackground);

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getCount() {
            //slider view count could be dynamic size
            return mSliderItems.size();
        }

        class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

            View itemView;
            ImageView imageViewBackground;
            ImageView imageGifContainer;
            TextView textViewDescription;

            public SliderAdapterVH(View itemView) {
                super(itemView);
                imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
                imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
                textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
                this.itemView = itemView;
            }
        }

    }
}
