package com.example.gadalka;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardRasklad3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardRasklad3 extends Fragment {


    View fragmentView;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CardRasklad3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CardRasklad3.
     */
    // TODO: Rename and change types and number of parameters
    public static CardRasklad3 newInstance(String param1, String param2) {
        CardRasklad3 fragment = new CardRasklad3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentView = inflater.inflate(R.layout.fragment_card_rasklad3, container, false);

        Button btn = fragmentView.findViewById(R.id.button);
        ImageView im = fragmentView.findViewById(R.id.cc);

        ImageView im1 = fragmentView.findViewById(R.id.cc1);
        //im.animate().rotationYBy(180);

        im1.setRotationY(90);

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    btn.setBackgroundColor(Color.RED);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    btn.setBackgroundColor(Color.BLUE);



                AnimatorSet dd  = new AnimatorSet();

                Animator anim = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.flip);
                anim.setTarget(im);

               Animator anim1 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.back_flip);
                anim1.setTarget(im1);

                Animator anim2 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.less);
                anim2.setTarget(im1);



                dd.playSequentially(anim, anim1, anim2);

                dd.start();

            }
        });

        return fragmentView;
    }

    public void startAnimation(ImageView imageView)
    {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity().getApplicationContext(),
                R.animator.flip);
        set.setTarget(imageView);
        //set.start();

    }

}