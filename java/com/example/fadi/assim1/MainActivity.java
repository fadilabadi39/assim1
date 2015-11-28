package com.example.fadi.assim1;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

    boolean turn = true; // true = X & false = O
    int turn_count = 0;
    Button[] bArray = null;
    Button a1, a2, a3, b1, b2, b3, c1, c2, c3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a1 = (Button) findViewById(R.id.A1);
        b1 = (Button) findViewById(R.id.B1);
        c1 = (Button) findViewById(R.id.C1);
        a2 = (Button) findViewById(R.id.A2);
        b2 = (Button) findViewById(R.id.B2);
        c2 = (Button) findViewById(R.id.C2);
        a3 = (Button) findViewById(R.id.A3);
        b3 = (Button) findViewById(R.id.B3);
        c3 = (Button) findViewById(R.id.C3);
        bArray = new Button[]{a1, a2, a3, b1, b2, b3, c1, c2, c3};

        for (Button b : bArray)
            b.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        buttonClicked(v);
    }

    private void buttonClicked(View v) {
        Button b = (Button) v;
        if (turn) {
// X's turn
            b.setText("X");

        } else {
// O's turn
            b.setText("O");
        }
        turn_count++;
        b.setClickable(false);
        b.setBackgroundColor(Color.LTGRAY);
        turn = !turn;

        checkForWinner();
    }

    private void checkForWinner() {


        boolean there_is_a_winner = false;

// horizontal:
        if ((a1.getText() == a2.getText() && a2.getText() == a3.getText()
                && !a1.isClickable()) || (b1.getText() == b2.getText() && b2.getText() == b3.getText()
                && !b1.isClickable()) || (c1.getText() == c2.getText() && c2.getText() == c3.getText()
                && !c1.isClickable()))

            there_is_a_winner = true;
// vertical:
        else if ((a1.getText() == b1.getText() && b1.getText() == c1.getText()
                && !a1.isClickable()) || (a2.getText() == b2.getText() && b2.getText() == c2.getText()
                && !b2.isClickable()) || (a3.getText() == b3.getText() && b3.getText() == c3.getText()
                && !c3.isClickable()))
            there_is_a_winner = true;


// diagonal:
        else if ((a1.getText() == b2.getText() && b2.getText() == c3.getText()
                && !c3.isClickable()) || (a3.getText() == b2.getText() && b2.getText() == c1.getText()
                && !b2.isClickable()))
            there_is_a_winner = true;


        if (there_is_a_winner) {
            if (!turn)
                message("X wins");
            else
                message("O wins");


        } else if (turn_count == 9 && there_is_a_winner == false)
            message("Draw!");

    }

    private void message(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
                .show();
    }
}