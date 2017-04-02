package com.example.colorxo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class MainActivity extends AppCompatActivity {

    char c;
    int p[];
    static int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c=' ';
        p=new int[2];
    }

    public void onClick(View v)
    {
        Boolean b=((RadioButton)v).isChecked();
        switch (v.getId())
        {
            case R.id.o :
            {
                if(b);
                c='O';
            }
            break;
            case R.id.x :
            {
                if(b)
                c='X';
            }
        }
    }

    public void selectColor(View view)
    {
        final Button temp=(Button)view;
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .initialColor(0xacacac)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(10)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                       // toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {

                    temp.setBackgroundColor(selectedColor);
                        p[count]=selectedColor;
                        count++;
                        if(count>=2)
                            count=0;
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    public void submit(View view)
    {
        Intent i=new Intent(getApplicationContext(),TicTacToe.class);
        i.putExtra("c",c);
        i.putExtra("p",p);
        startActivity(i);
    }
}
