package com.iptv.aaa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button bthoriz=(Button) findViewById(R.id.AnimationHorizontal);
        Button btverti=(Button) findViewById(R.id.AnimationVertical);
        LinearLayout target=(LinearLayout) findViewById(R.id.target);

        bthoriz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.LayoutParams params = target.getLayoutParams();
                params.height=(int)(40*getResources().getDisplayMetrics().density);
                params.width=0;
                target.setLayoutParams(params);
                Animation animation=new slideHorizontal(target,0,(int)(screenWidth(MainActivity.this)));
                animation.setInterpolator(new AccelerateInterpolator());
                animation.setDuration(800);
                target.setAnimation(animation);
                target.startAnimation(animation);
            }
        });
        btverti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.LayoutParams params = target.getLayoutParams();
                params.height=0;
                params.width=ViewGroup.LayoutParams.MATCH_PARENT;
                target.setLayoutParams(params);
                Animation animation=new slideVertical(target,0,(int)(40*getResources().getDisplayMetrics().density));
                animation.setInterpolator(new AccelerateInterpolator());
                animation.setDuration(800);
                target.setAnimation(animation);
                target.startAnimation(animation);
            }
        });

    }



    public static  class slideVertical extends Animation{
        int mFromHeight;
        int mToHeight;
        View mView;

        public slideVertical(View view, int FromHeight,int ToHeight){
            this.mFromHeight=FromHeight;
            this.mToHeight=ToHeight;
            this.mView=view;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {


            int newheight;
            if(mView.getHeight()!= mToHeight){
                newheight=(int)(mFromHeight+((mToHeight-mFromHeight)*interpolatedTime));
                mView.getLayoutParams().height=newheight;
                mView.requestLayout();
            }

        }





        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }
        @Override
        public boolean willChangeBounds() {
            return super.willChangeBounds();
        }

    }



    public static  class slideHorizontal extends Animation{
        int mFromwidth;
        int mTowidth;
        View mView;

        public slideHorizontal(View view, int Fromwidth,int Towidth){
            this.mTowidth=Fromwidth;
            this.mTowidth=Towidth;
            this.mView=view;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {


            int newheight;
            if(mView.getWidth()!= mTowidth){
                newheight=(int)(mFromwidth+((mTowidth-mFromwidth)*interpolatedTime));
                mView.getLayoutParams().width=newheight;
                mView.requestLayout();
            }

        }





        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }
        @Override
        public boolean willChangeBounds() {
            return super.willChangeBounds();
        }

    }


    public  static int screenWidth(Activity activity){

        DisplayMetrics displaymetrics=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }


}