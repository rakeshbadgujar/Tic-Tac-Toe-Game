package com.example.colorxo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class TicTacToe extends AppCompatActivity implements View.OnClickListener {

    Button b[][];
    char turn ;
    View.OnClickListener clickListener;
    GridLayout gd;
    int count;
    TextView tv;
    int color[];
    static int c;
    int row =4,col=4;
    PopupWindow popUpWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);

        turn=getIntent().getCharExtra("c",'X');
        color=getIntent().getIntArrayExtra("p");
        c=0;
        gd=(GridLayout)findViewById(R.id.activity_main);

        gd.setRowCount(row);
        gd.setColumnCount(col);


        count=0;
        b=new Button[row][col];
        tv= (TextView) findViewById(R.id.tv3);

        for (int i=0;i<row;i++)
        {
            for (int j=0;j<col;j++)
            {
                b[i][j]=new Button(this);
                b[i][j].setText("");
                b[i][j].setTextSize(20f);
                gd.addView(b[i][j]);
                b[i][j].setOnClickListener(this);
            }
        }



        Button b1=(Button)findViewById(R.id.reset);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            reset();
            }
        });

    }

    @Override
    public void onClick(View v) {

        Button b=(Button)v;

        if(turn == 'X')
        {
            b.setText("X");
            turn='O';
            b.setBackgroundColor(color[c]);
            c++;
            if(c==2)
                c=0;
            b.setClickable(false);
        }
        else
        {
            b.setText("O");
            turn='X';
            b.setBackgroundColor(color[c]);
            c++;
            if(c==2)
                c=0;
            b.setClickable(false);
        }
        count++;
        if(count>4)
        {
            checkRow();
            checDiagnol();
            checkColumn();
        }
    }

    public void checkRow()
    {
        for(int j=0;j<row;j++)
        {
            //if(b[j][0].getText()!="" && b[j][0].getText()==b[j][1].getText() && b[j][1].getText()==b[j][2].getText())
                if(b[j][0].getText()!="" && b[j][0].getText()==b[j][1].getText() && b[j][1].getText()==b[j][2].getText() && b[j][2].getText()==b[j][3].getText())
            {
                won();
            }
        }
    }
    public void checkColumn()
    {

        for(int j=0;j<col;j++)
        {
            //if(b[0][j].getText()!="" && b[0][j].getText()==b[1][j].getText() && b[1][j].getText()==b[2][j].getText())
            if(b[0][j].getText()!="" && b[0][j].getText()==b[1][j].getText() && b[1][j].getText()==b[2][j].getText()&& b[2][j].getText()==b[3][j].getText())
            {
                won();
            }
        }
    }
    public void checDiagnol()
    {
        //if( b[0][0].getText()==b[1][1].getText()&& b[1][1].getText()==b[2][2].getText())
        if( b[0][0].getText()!="" && b[0][0].getText()==b[1][1].getText()&& b[1][1].getText()==b[2][2].getText()&& b[2][2].getText()==b[3][3].getText())
        {
            won();
        }

       // if( b[0][2].getText()==b[1][1].getText() && b[1][1].getText()==b[2][0].getText())
         if( b[0][3].getText()!="" && b[0][3].getText()==b[1][2].getText() && b[1][2].getText()==b[2][1].getText() &&  b[2][1].getText()==b[3][0].getText())
        {
            won();
        }
    }

    public void won()
    {
        if(turn == 'X')
        {
            tv.setText("\n  'O' Won");
        }
        else
        {
            tv.setText("\n  'X'  Won");
        }

        for (int i=0;i<row;i++)
        {
            for (int j=0;j<col;j++)
            {
                b[i][j].setClickable(false);
            }
        }
    }

    public void reset()
    {
        count=0;

        for (int i=0;i<row;i++) {
            for (int j = 0; j < col; j++) {

                b[i][j].setText("");
                b[i][j].setClickable(true);
                b[i][j].setBackgroundResource(android.R.drawable.btn_default);
                tv.setText("");
                turn='O';
                c=0;

            }
        }
    }
}


