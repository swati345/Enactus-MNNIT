package com.example.admin1.enactusmnnit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

public class sample extends AppCompatActivity {
    ProgressDialog mProgressDialog;
    ProgressBar progressbar;
    float scale=1f;
    ImageView imagedisplay=(ImageView)findViewById(R.id.image);
    ScaleGestureDetector scaleGDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent g = getIntent();
        final String url = g.getExtras().getString("picselect");
        mProgressDialog = new ProgressDialog(sample.this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setIndeterminate(false);
        progressbar=(ProgressBar)findViewById(R.id.progressbar);
        progressbar.setVisibility(View.VISIBLE);


         Picasso.with(this)
                .load(url)
                 .priority(Picasso.Priority.HIGH)
                 .resize(500,0)
                .into(imagedisplay, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        if (progressbar != null) {
                            progressbar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
            mProgressDialog.dismiss();
        scaleGDetector=new ScaleGestureDetector(this, new ScaleListener());



    }
    @Override

    public boolean onTouchEvent(MotionEvent ev) {

        scaleGDetector.onTouchEvent(ev);

        return true;

    }
    private class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener{

        public boolean onScaleBegin(ScaleGestureDetector sgd){



            return true;



        }

        public void onScaleEnd(ScaleGestureDetector sgd){



        }

        public boolean onScale(ScaleGestureDetector sgd){

            // Multiply scale factor

            scale*= sgd.getScaleFactor();

            // Scale or zoom the imageview

            imagedisplay.setScaleX(scale);

            imagedisplay.setScaleY(scale);

            Log.i("Main",String.valueOf(scale));

            return true;

        }

    }

}
