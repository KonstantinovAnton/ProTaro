package com.example.gadalka;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardOfDayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardOfDayFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Connection connection;
    Spinner spinner;
    List<Card> data;


    View fragmentView;
    ImageView imageViewRubashka;
    ImageView imageViewCard;
    Card card;
    int idCard;
    FrameLayout frameMean;
    TextView textViewMean;

    public CardOfDayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CardOfDayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardOfDayFragment newInstance(String param1, String param2) {
        CardOfDayFragment fragment = new CardOfDayFragment();
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

        fragmentView = inflater.inflate(R.layout.fragment_card_of_day, container, false);
        imageViewRubashka = fragmentView.findViewById(R.id.rubashkaOfDayCard);
        imageViewCard = fragmentView.findViewById(R.id.DayCard);
        imageViewCard.setRotationY(90);

        frameMean = fragmentView.findViewById(R.id.frameCardOdFay);
        textViewMean = fragmentView.findViewById(R.id.textViewCardOfDay);
        textViewMean.setMovementMethod(new ScrollingMovementMethod());

        frameMean.setAlpha(0);
        frameMean.setVisibility(View.INVISIBLE);

        TextView textViewNameCardDay = fragmentView.findViewById(R.id.textViewNameCardDay);
        textViewNameCardDay.setAlpha(0);

        Button buttonOk = fragmentView.findViewById(R.id.buttonOk);
        buttonOk.setAlpha(0);
        buttonOk.setVisibility(View.INVISIBLE);

        while(true) {
            try {
                Generator generator = new Generator(1);
                idCard = generator.generateDayCard();
                GetTextFromSql(fragmentView);
                textViewNameCardDay.setText(data.get(0).getNameCard());


                break;
            } catch (Exception ex) {

            }
        }

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                frameMean.setVisibility(View.INVISIBLE);
                frameMean.setAlpha(0);

                buttonOk.setVisibility(View.INVISIBLE);
                buttonOk.setAlpha(0);

            }
        });

        imageViewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOk.setVisibility(View.VISIBLE);

                frameMean.setVisibility(View.VISIBLE);

                AnimatorSet dd1 = new AnimatorSet();

                Animator intro = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.intro);
                intro.setTarget(frameMean);

                Animator introButton = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.intro_full);
                introButton.setTarget(buttonOk);

                dd1.play(intro).with(introButton);
                dd1.start();
            }
        });

        // Перевернуть карту
        imageViewRubashka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonOk.setVisibility(View.VISIBLE);

                frameMean.setVisibility(View.VISIBLE);

                AnimatorSet dd = new AnimatorSet();

                Animator flip = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.flip);
                flip.setTarget(imageViewRubashka);

                Animator flipBack = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.back_flip);
                flipBack.setTarget(imageViewCard);

                Animator introName = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.intro_full);
                introName.setTarget(textViewNameCardDay);



               // dd.play(flip).before(flipBack).with(introName);
                dd.play(flipBack).after(flip).before(introName);
                dd.start();

                AnimatorSet dd1 = new AnimatorSet();

                Animator intro = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.intro);
                intro.setTarget(frameMean);

                Animator introButton = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), R.animator.intro_full);
                introButton.setTarget(buttonOk);

                dd1.play(intro).with(introButton).after(3000);
                dd1.start();

            }
        });


        return fragmentView;
    }


    public void GetTextFromSql(View v) {

        data = new ArrayList<Card>();
        AddItemToList(v, data, "Select * From Cards where id_card = " + idCard);
        ImgCoder m = new ImgCoder(getActivity().getApplicationContext()); // Добавление конвертера для img
        imageViewCard.setImageBitmap(m.getUserImage(card.getImage())); // Установка значения
        textViewMean.setText(data.get(0).getDescription());

    }

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