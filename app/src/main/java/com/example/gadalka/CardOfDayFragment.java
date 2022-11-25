package com.example.gadalka;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

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
    ListView lstView;
    AdapterCard adapterBooks;
    EditText etSearch;

    View fragmentView;
    ImageView imageViewCard;
    ListView listViewAllCards;
    Card card;

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
        imageViewCard = fragmentView.findViewById(R.id.imageViewCard);

        GetTextFromSql(fragmentView); // Заполнение экземпляра класса Card - card



        return fragmentView;
    }

    public void GetTextFromSql(View v) {

        data = new ArrayList<Card>();
        AddItemToList(v, data, "Select * From Cards where id_card = 1");
        ImgCoder m = new ImgCoder(getActivity().getApplicationContext()); // Добавление конвертера для img
        imageViewCard.setImageBitmap(m.getUserImage(card.getImage())); // Установка значения
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