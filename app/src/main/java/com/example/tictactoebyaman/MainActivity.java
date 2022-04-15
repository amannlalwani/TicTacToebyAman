package com.example.tictactoebyaman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {







   boolean isWinner=false;
    int imageClicked=-1;

    int player=1;
    int [][]winningStates={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int gameState[]={-1,-1,-1,-1,-1,-1,-1,-1,-1};

    public void load(View view){
        if(imageClicked==-1 && isWinner==false) {

            ImageView k = (ImageView) view;
            int tag = Integer.parseInt(k.getTag().toString());
            imageClicked=gameState[tag];
            if (player == 1) {
                k.setImageResource(R.drawable.cross);
                Toast.makeText(this, tag + " box made cross", Toast.LENGTH_SHORT).show();
                gameState[tag] = 1;
                player = 0;
            } else {
                k.setImageResource(R.drawable.circle);
                gameState[tag] = 0;
                Toast.makeText(this, tag + " box made circle", Toast.LENGTH_SHORT).show();
                player = 1;
            }
            for (int i = 0; i < winningStates.length; i++) {
                if ((gameState[winningStates[i][0]] == gameState[winningStates[i][1]]) && (gameState[winningStates[i][1]] == gameState[winningStates[i][2]]) && (gameState[winningStates[i][0]] > -1)) {
//                    Toast.makeText(this, "Player" + (player == 0 ? 1 : 0) + "is winner", Toast.LENGTH_SHORT).show();
                    new  AlertDialog.Builder(this)
                            .setIcon(R.drawable.ic_action_name)
                            .setTitle("Game OVER!")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    reset(view);
                                }
                            }).setMessage((player == 0 ? 1 : 0) + "is winner").show();
                    isWinner=true;
                }
            }
        }}


    public void reset(View view){
        GridLayout gridLayout=findViewById(R.id.grid);
        int totalClicked=gridLayout.getChildCount();
        for (int l = 0; l < totalClicked; l++) {
              ImageView k =(ImageView) gridLayout.getChildAt(l);
              k.setImageDrawable(null);
        }
        isWinner=false;
        imageClicked=-1;

        for (int j = 0; j < gameState.length; j++) {
            gameState[j]=-1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}