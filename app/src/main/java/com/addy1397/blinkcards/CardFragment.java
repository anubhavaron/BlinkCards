package com.addy1397.blinkcards;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yalantis.guillotine.animation.GuillotineAnimation;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {

    private static final long RIPPLE_DURATION = 250;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private ImageView image_blinkCard;
    private TextView textView_data;
    public Toolbar mtoolbar;
    private FrameLayout frameLayout_root;
    private TextView textView_toolbarTitle;
    private View CardFragment_Index;
    int mToolbarHeight, mAnimDuration = 600/* milliseconds */;
    private View imageView_Search;

    ValueAnimator mVaActionBar;



    public CardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        mtoolbar = (Toolbar) view.findViewById(R.id.toolbar_cardFragment);

        ((AppCompatActivity)getActivity()).setSupportActionBar(mtoolbar);

        //textView_toolbarTitle = (TextView) view.findViewById(R.id.toolbar_textView_Title);

        //Typeface typeface = Typeface.createFromAsset( getActivity().getAssets() , "fonts/greatvibesregular.ttf");

        //textView_toolbarTitle.setTypeface(typeface);

        mtoolbar = (Toolbar) view.findViewById(R.id.toolbar_cardFragment);
        frameLayout_root = (FrameLayout) view.findViewById(R.id.FrameLayout_CardFragment);
        CardFragment_Index = (View) view.findViewById(R.id.imageView_toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(mtoolbar);

        mtoolbar.setTitle(null);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);

        //setHasOptionsMenu(true);

        View guillotineMenu = LayoutInflater.from(getContext()).inflate(R.layout.fragmentcard_index, null);

        frameLayout_root.addView(guillotineMenu);

        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.imageView_Index_toolbar), CardFragment_Index)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(mtoolbar)
                .setClosedOnStart(true)
                .build();

        hideActionBar();

        textView_data = (TextView) view.findViewById(R.id.textView_TEXT);

        textView_data.setMovementMethod(new ScrollingMovementMethod());

        image_blinkCard = (ImageView) view.findViewById(R.id.image_blinkCard);

        Picasso.with(getContext())
                .load(R.drawable.background_1)
                .fit()
                .into(image_blinkCard);

        /*image_blinkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = ((AppCompatActivity)getActivity()).getSupportActionBar().isShowing();
                if(result) {
                    hideActionBar();
                }
                else{
                    showActionBar();
                }
            }
        });*/

        imageView_Search = (View) view.findViewById(R.id.imageView_toolbar_search);

        imageView_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.viewPager_main, new SearchFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        setHasOptionsMenu(true);

        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                       // Log.i(Constants.APP_TAG, "onFling has been called!");
                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
                        try {
                            if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                                Toast.makeText(getContext(), "UP SWIPE", Toast.LENGTH_SHORT).show();

                                /*Fragment frg = null;
                                frg = getActivity().getSupportFragmentManager().findFragmentById();
                                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                ft.detach(frg);
                                ft.attach(frg);
                                ft.commit();*/

                                //Log.i(Constants.APP_TAG, "Right to Left");
                            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                                Toast.makeText(getContext(), "DOWN SWIPE", Toast.LENGTH_SHORT).show();
                                //Log.i(Constants.APP_TAG, "Left to Right");
                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });


        return view;
    }

    private void showActionBar() {

        if (mVaActionBar != null && mVaActionBar.isRunning()) {
            // we are already animating a transition - block here
            return;
        }

        // restore `Toolbar's` height
        mVaActionBar = ValueAnimator.ofInt(0 , mToolbarHeight);
        mVaActionBar.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // update LayoutParams
                (mtoolbar.getLayoutParams()).height
                        = (Integer)animation.getAnimatedValue();
                mtoolbar.requestLayout();
            }
        });

        mVaActionBar.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

                if (((AppCompatActivity)getActivity()).getSupportActionBar() != null) { // sanity check
                    ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                }
            }
        });

        mVaActionBar.setDuration(mAnimDuration);
        mVaActionBar.start();
    }

    private void hideActionBar() {

        if (mToolbarHeight == 0) {
            mToolbarHeight = mtoolbar.getHeight();
        }

        if (mVaActionBar != null && mVaActionBar.isRunning()) {
            // we are already animating a transition - block here
            return;
        }

        // animate `Toolbar's` height to zero.
        mVaActionBar = ValueAnimator.ofInt(mToolbarHeight , 0);
        mVaActionBar.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // update LayoutParams
                /*(AppBarLayout.LayoutParams)*/
                (mtoolbar.getLayoutParams()).height
                        = (Integer)animation.getAnimatedValue();
                mtoolbar.requestLayout();
            }
        });

        mVaActionBar.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                if (((AppCompatActivity)getActivity()).getSupportActionBar() != null) { // sanity check
                    ((AppCompatActivity)getActivity()).getSupportActionBar() .hide();
                }
            }
        });

        mVaActionBar.setDuration(mAnimDuration);
        mVaActionBar.start();
    }

}
