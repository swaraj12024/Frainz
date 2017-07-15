package com.example.swaraj.frainz;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    Adapter adapter;
    RelativeLayout relative;
    TextView connect,thanks,soontext,mainanimation;
    Button join,sign;
    ImageView image;
    CardView card;
    int[] layouts ={R.layout.firstscreen,R.layout.secondscreen,R.layout.thirdscreen,R.layout.fourthscreen};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager=(ViewPager) findViewById(R.id.viewer);
        adapter=new Adapter();
        join=(Button)findViewById(R.id.jointext);
        sign=(Button)findViewById(R.id.signtext);

        viewPager.setAdapter(adapter);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(viewlistner);

    }

    ViewPager.OnPageChangeListener viewlistner= new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



        }

        @Override
        public void onPageSelected(int position) {
            thanks = (TextView) findViewById(R.id.thanks);
            soontext=(TextView)findViewById(R.id.soontext);
            image=(ImageView)findViewById(R.id.image);
            mainanimation=(TextView)findViewById(R.id.anima);

            if(position==0)
            {
                join.setTextColor(Color.WHITE);
                sign.setTextColor(Color.parseColor("#0077B5"));
            }
            if(position==1)
            {
                join.setTextColor(Color.parseColor("#87cefa"));
                sign.setTextColor(Color.parseColor("#87cefa"));
                sign.setBackgroundColor(Color.TRANSPARENT);
                connect=(TextView)findViewById(R.id.connectbutton);
                relative=(RelativeLayout)findViewById(R.id.relative);
                Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.connecttext);
                final Animation secondanimation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.connectedtext);

                mainanimation.setAnimation(animation);
                mainanimation.startAnimation(animation);

                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainanimation.startAnimation(secondanimation);
                        connect.setText("\u2713 CONNECTED");
                    }
                }, 500);
            }
            if(position==2)
            {
                final Animation imagefirstanimation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.zoomout);
                final Animation imagesecondanimation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.zoomin);
                final Animation thanksfirstanimation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.zoomout);
                final Animation thankssecondanimation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.zoomin);
                final Animation soonfirstanimation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.zoomout);
                final Animation soonsecondanimation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.zoomin);
                image.setVisibility(View.VISIBLE);
                image.setAnimation(imagefirstanimation);
                image.startAnimation(imagefirstanimation);
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image.startAnimation(imagesecondanimation);
                    }
                }, 500);
                handler.removeMessages(500);

                thanksfirstanimation.setStartOffset(1000);
                thanks.setVisibility(View.VISIBLE);
                thanks.startAnimation(thanksfirstanimation);
                Handler handler1=new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        thanks.startAnimation(thankssecondanimation);
                    }
                }, 1500);
                handler1.removeMessages(1500);


                soonfirstanimation.setStartOffset(2000);
                soontext.setVisibility(View.VISIBLE);
                soontext.startAnimation(soonfirstanimation);
                Handler handler2=new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        soontext.startAnimation(soonsecondanimation);
                    }
                }, 2500);
                handler2.removeMessages(2500);
            }
            if(position==3)
            {   card=(CardView)findViewById(R.id.car);
                card.setVisibility(View.VISIBLE);
            }
        }


        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    class Adapter extends PagerAdapter {

        LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(layouts[position],container,false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           View view=(View)object;
            container.removeView(view);
        }

    }
}

