package com.example.gadalka;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardRasklad3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardRasklad3 extends Fragment {


    View fragmentView;
    TextView textViewMean;




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
    ImageView card1;
    ImageView card2;
    ImageView card3;

    int[] arrayCardsId;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        fragmentView = inflater.inflate(R.layout.fragment_card_rasklad3, container, false);

        FrameLayout frameMean = fragmentView.findViewById(R.id.frameMean);
        textViewMean = fragmentView.findViewById(R.id.textViewMean);
        textViewMean.setMovementMethod(new ScrollingMovementMethod());

        frameMean.setAlpha(0);

        Button btn = fragmentView.findViewById(R.id.button);
        card1 = fragmentView.findViewById(R.id.card1);
        ImageView rub1 = fragmentView.findViewById(R.id.rub1);
        card2 = fragmentView.findViewById(R.id.card2);
        ImageView rub2 = fragmentView.findViewById(R.id.rub2);
        card3 = fragmentView.findViewById(R.id.card3);
        ImageView rub3 = fragmentView.findViewById(R.id.rub3);

        Button btnGetAnswer = fragmentView.findViewById(R.id.buttonGetAnswer);
        btnGetAnswer.setAlpha(0);
        btnGetAnswer.setVisibility(View.INVISIBLE);

        Button buttonBack = fragmentView.findViewById(R.id.buttonBack);
        buttonBack.setVisibility(View.INVISIBLE);


        card1.setRotationY(90);
        card2.setRotationY(90);
        card3.setRotationY(90);

        //GetTextFromSql(fragmentView);



        ImageView imageCaloda = fragmentView.findViewById(R.id.calodaTaro);
        ImageView imageCaloda2 = fragmentView.findViewById(R.id.calodaTaro2);

        imageCaloda.setZ(1);
        imageCaloda2.setZ(2);




        // Вытягивание карты

        imageCaloda2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    btnGetAnswer.setVisibility(View.VISIBLE);

                    AnimatorSet Intro = new AnimatorSet();

                    Animator animIntro = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.intro_full);
                    animIntro.setTarget(btnGetAnswer);

                    Intro.play(animIntro).after(8000);
                    Intro.start();


                    while(true) {
                        try {

                            Generator generator = new Generator(3);
                            arrayCardsId = generator.generateCard();

                            GetTextFromSql(fragmentView);

                            break;
                        } catch (Exception ex) {

                        }
                    }

                    rub1.setVisibility(View.VISIBLE);
                    rub2.setVisibility(View.VISIBLE);
                    rub3.setVisibility(View.VISIBLE);

                    imageCaloda.setZ(0);
                    imageCaloda2.setZ(0);

                    rub1.setZ(3);
                    rub2.setZ(2);
                    rub3.setZ(1);

                    card1.setZ(4);
                    card2.setZ(5);
                    card3.setZ(6);

                    AnimatorSet dd = new AnimatorSet();

                    Animator anim = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.less);
                    anim.setTarget(rub1);

                    Animator flip = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.flip);
                    flip.setTarget(rub1);

                    Animator flipBack = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.back_flip);
                    flipBack.setTarget(card1);

                    dd.play(anim).with(flip).before(flipBack);
                    dd.start();

                    AnimatorSet dd2 = new AnimatorSet();

                    Animator anim2 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.less);
                    anim2.setTarget(card1);
                    Animator anim3 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.less);
                    anim3.setTarget(card2);
                    Animator anim4 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.less);
                    anim4.setTarget(card3);

                    dd2.playTogether(anim2, anim3, anim4);
                    dd2.start();

                    AnimatorSet dd3 = new AnimatorSet();
                    Animator layTable = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.lay_table_left);
                    layTable.setTarget(card1);
                    Animator layTable1 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.lay_table_left);
                    layTable1.setTarget(rub1);
                    dd3.play(layTable).with(layTable1).after(2000);
                    dd3.start();


                    AnimatorSet animCard2 = new AnimatorSet();

                    Animator animC2 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.less);
                    animC2.setTarget(rub2);

                    Animator flipC2 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.flip);
                    flipC2.setTarget(rub2);

                    Animator flipBackC2 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.back_flip);
                    flipBackC2.setTarget(card2);

                    animCard2.play(animC2).with(flipC2).before(flipBackC2).after(3000);
                    animCard2.start();

                    AnimatorSet animCard2Less = new AnimatorSet();
                    Animator layTable2 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.lay_table_right);
                    layTable2.setTarget(card2);
                    Animator layTable21 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.lay_table_right);
                    layTable21.setTarget(rub2);
                    animCard2Less.play(layTable2).with(layTable21).after(5000);
                    animCard2Less.start();

                    AnimatorSet animCard3 = new AnimatorSet();

                    Animator animC3 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.less);
                    animC3.setTarget(rub3);

                    Animator flipC3 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.flip);
                    flipC3.setTarget(rub3);

                    Animator flipBackC3 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.back_flip);
                    flipBackC3.setTarget(card3);

                    animCard3.play(animC3).with(flipC3).before(flipBackC3).after(6000);
                    animCard3.start();

                    AnimatorSet animCard3Less = new AnimatorSet();
                    Animator layTable3 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.lay_table_center);
                    layTable3.setTarget(card3);
                    Animator layTable4 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.lay_table_left);
                    layTable4.setTarget(rub3);
                    animCard3Less.play(layTable3).with(layTable4).after(8000);
                    animCard3Less.start();
                }
                catch (Exception exc)
                {

                }



            }

        });


        // Кнопка Тасовать
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageCaloda.setZ(1);
                imageCaloda2.setZ(2);

                rub1.setVisibility(View.INVISIBLE);
                rub2.setVisibility(View.INVISIBLE);
                rub3.setVisibility(View.INVISIBLE);

                card1.setX(230);
                card1.setY(750);
                card2.setX(230);
                card2.setY(750);
                card3.setX(230);
                card3.setY(750);
                rub1.setX(230);
                rub1.setY(730);
                rub2.setX(230);
                rub2.setY(730);
                rub3.setX(230);
                rub3.setY(730);

                card1.setRotationY(90);
                card2.setRotationY(90);
                card3.setRotationY(90);

                rub1.setRotationY(0);
                rub2.setRotationY(0);
                rub3.setRotationY(0);

                rub1.setZ(3);
                rub1.setZ(2);
                rub1.setZ(1);

                // Анимация тасовки

                AnimatorSet dd  = new AnimatorSet();

                Animator tasovkaRight = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.tasovka_right);
                tasovkaRight.setTarget(imageCaloda2);

                Animator tasovkaLeft = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.tasovka_left);
                tasovkaLeft.setTarget(imageCaloda);

                dd.playTogether(tasovkaRight, tasovkaLeft);
                dd.start();

                Animator tasovkaLeftObr = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.tasovka_left_obr);
                tasovkaLeftObr.setTarget(imageCaloda2);

                Animator tasovkaRightObr = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.tasovka_right_obr);
                tasovkaRightObr.setTarget(imageCaloda);

                AnimatorSet dd2  = new AnimatorSet();
                dd2.play(tasovkaRightObr).with(tasovkaLeftObr).after(1000);
                dd2.start();

            }
        });

        // Кнопка Получить ответ
        btnGetAnswer.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                frameMean.setVisibility(View.VISIBLE);
                frameMean.setAlpha(0);
                buttonBack.setVisibility(View.VISIBLE);
                buttonBack.setAlpha(0);
                frameMean.setZ(7);
                buttonBack.setZ(8);


                AnimatorSet Intro = new AnimatorSet();

                Animator animIntro = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.intro);
                animIntro.setTarget(frameMean);
                Animator animIntro2 = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.intro_full);
                animIntro2.setTarget(buttonBack);

                Intro.play(animIntro).with(animIntro2);
                Intro.start();

                btnGetAnswer.setVisibility(View.INVISIBLE);
                btnGetAnswer.setAlpha(0);



            }
        });

        // Кнопка Вернуться
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameMean.setZ(0);

                frameMean.setVisibility(View.INVISIBLE);
                buttonBack.setVisibility(View.INVISIBLE);
                btnGetAnswer.setVisibility(View.INVISIBLE);

            }
        });

        return fragmentView;
    }


    List<Card> data;

    public void GetTextFromSql(View v) {

        data = new ArrayList<Card>();


       // AddItemToList(v, data, "Select * From Cards where id_card in (" + arrayCardsId[0] + "," + arrayCardsId[1] + "," + arrayCardsId[2]+")");
        AddItemToList(v, data, "Select * From Cards where id_card = " + arrayCardsId[0]
                + " or id_card = " + arrayCardsId[1] + " or id_card = " + arrayCardsId[2]);
        ImgCoder m = new ImgCoder(getActivity().getApplicationContext()); // Добавление конвертера для img

        card1.setImageBitmap(m.getUserImage(data.get(0).getImage()));
        card2.setImageBitmap(m.getUserImage(data.get(1).getImage()));
        card3.setImageBitmap(m.getUserImage(data.get(2).getImage()));

        String d = data.get(0).getDescription();
        String d2 = data.get(1).getDescription();
        String d3 = data.get(2).getDescription();
        textViewMean.setText(d + "\n\n" + d2 + "\n\n" + d3);



    }
    Connection connection;
    Card card;
    public void AddItemToList(View v, List<Card> list, String s) {
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(s);
                while (resultSet.next()) {
                    card = new Card
                            (
                                    Integer.parseInt(resultSet.getString("id_card")),
                                    resultSet.getString("name"),
                                    resultSet.getString("description"),
                                    resultSet.getString("image")
                            );
                    list.add(card);
                }
                connection.close();
            }
        } catch (Exception ex) {
            Log.e(ex.toString(), ex.getMessage());
        }
    }




}