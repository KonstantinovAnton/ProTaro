package com.example.gadalka;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //http://translit-online.ru/image-base64-converter.html

    Connection connection;
    Spinner spinner;
    List<Card> data;
    ListView lstView;
    AdapterCard adapterBooks;
    EditText etSearch;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        NavController navController = Navigation.findNavController(this, R.id.NavHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);

    }
/*
    public void GetTextFromSql(View v) {

        Generator generator = new Generator(3);
        String strCatdsId =  generator.generateCard();

        data = new ArrayList<Card>();
        AddItemToList(v, data, "Select * From Cards Where id_card in (" + strCatdsId + ") ");
        lstView = findViewById(R.id.listViewCards);
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
        adapterBooks = new AdapterCard(MainActivity.this,list);
        adapterBooks.notifyDataSetInvalidated();
        lstView.setAdapter(adapterBooks);


    }


 */
}

