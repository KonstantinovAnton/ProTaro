package com.example.gadalka;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllCardsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllCardsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllCardsFragment() {
        // Required empty public constructor
    }

    Connection connection;
    Spinner spinner;
    List<Card> data;
    ListView lstView;
    AdapterCard adapterBooks;
    EditText etSearch;

    View fragmentView;
    ListView listViewAllCards;
    String[] name = new String[]{"India", "Somali", "Russia"};

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllCardsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllCardsFragment newInstance(String param1, String param2) {
        AllCardsFragment fragment = new AllCardsFragment();
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






            //lstView.findViewById(R.id.listViewAllCards);
            //GetTextFromSql(lstView);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentView = inflater.inflate(R.layout.fragment_all_cards, container, false);
        listViewAllCards = fragmentView.findViewById(R.id.listViewAllCards);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, name);
        //listViewAllCards.setAdapter(adapter);


        GetTextFromSql(fragmentView);

        return fragmentView;
    }

    public void GetTextFromSql(View v) {

        //Generator generator = new Generator(3);
        //String strCatdsId =  generator.generateCard();

        data = new ArrayList<Card>();
        AddItemToList(v, data, "Select * From Cards");
        SetAdapter(data);
    }


    public void AddItemToList(View v, List<Card> list, String s) {
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(s);
                while (resultSet.next()) {
                    Card tempBook = new Card
                            (
                                    Integer.parseInt(resultSet.getString("id_card")),
                                    resultSet.getString("name"),
                                    resultSet.getString("description"),
                                    resultSet.getString("image")
                            );
                    list.add(tempBook);
                }
                connection.close();
            }
        } catch (Exception ex) {
            Log.e(ex.toString(), ex.getMessage());
        }
    }

    public void SetAdapter(List<Card> list){


        //adapterBooks = new AdapterCard(getActivity().getApplicationContext() ,list);
       // adapterBooks.notifyDataSetInvalidated();
        //lstView.setAdapter(adapterBooks);

        AdapterCard adapter = new AdapterCard(getActivity().getApplicationContext(), list);
        listViewAllCards.setAdapter(adapter);
    }



}