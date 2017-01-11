package com.example.rouge.anem.Main;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import com.example.rouge.anem.R;

/**
 * Created by laureduchemin on 04/01/2017.
 */

public class ShopActivity extends MainActivity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Button addPoly = (Button) findViewById(R.id.AddPoly);
        addPoly.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                setContentView(R.layout.activity_addpoly);
            }
        });

        Button addDecap = (Button) findViewById(R.id.AddDecap);
        addDecap.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                setContentView(R.layout.activity_decap);
            }
        });

        Button addStylo = (Button) findViewById(R.id.AddStylo);
        addStylo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                setContentView(R.layout.activity_stylo);
            }
        });

        Button addTShirt = (Button) findViewById(R.id.AddTshirt);
        addTShirt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                setContentView(R.layout.activity_tshirt);
            }
        });

        Button addecocup = (Button) findViewById(R.id.AddEcoCup);
        addecocup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                setContentView(R.layout.activity_ecocup);
            }
        });

        Button addregle = (Button) findViewById(R.id.AddRegle);
        addregle.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                setContentView(R.layout.activity_regle);
            }
        });

        Button addsticker = (Button) findViewById(R.id.AddSticker);
        addsticker.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                setContentView(R.layout.activity_sticker);
            }
        });
    }

}
