package com.example.project1_kfanta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Random;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;


public class playgameActivity extends AppCompatActivity {


    Random random = new Random();

    private ImageView blocks[][] = new ImageView[4][4];


    public boolean checkWinner(){
        /*Algorithms
        *
        * Win = either 3 reds or 3green images in a raw/diagonal/column
        *
        * direction right, up, right-up = {i,j+1},{i-1,j},{i-1,j+1} = {0,1},{-1,0},{1,1}
        *
        * for any given direction, start from a lowest and far left box and check the next column
        *       If the next color is yellow skip
        *       Else if it is the same color, keep checking
        *       else(differnt color), start checking for that color
        *
        *
        * */
        /*
        * Start from 3,0
        * Check the next color, if it is yellow you go straign to the next
        *
        * */

        //row
        for(int j = 3; j >= 0; j--){
            for(int i = 0; i < 3; i++){//started at 3,0
                if(blocks[j][i+1].getTag().equals(R.drawable.yellowstar)){
                    break;//(3,1) = yellow
                }
                //not yellow,
                else if(blocks[j][i].getTag().equals(blocks[j][i+1].getTag())){ //samecolor?
                    if(i + 2 < 4 && blocks[j][i+1].getTag().equals(blocks[j][i+2].getTag())) {
                        //someone won
                        if(blocks[j][i+1].getTag().equals(R.drawable.redstar)){
                            result(1);
                            return true;
                        }else{
                            result(2);
                            return true;
                        }
                    }
                }
            }
        }
        //column
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (!blocks[j][i].getTag().equals(R.drawable.yellowstar) &&
                        blocks[j][i].getTag().equals(blocks[j + 1][i].getTag()) &&
                        blocks[j][i].getTag().equals(blocks[j + 2][i].getTag())) {
                    //someone won
                    if (blocks[j][i].getTag().equals(R.drawable.redstar)) {
                        result(1);
                    } else {
                        result(2);
                    }
                    return true;
                }
            }
        }

        //diagonal
        for (int i = 0; i < blocks.length - 2; i++) {
            for (int j = 0; j < blocks[i].length - 2; j++) {
                if (!blocks[i][j].getTag().equals(R.drawable.yellowstar) &&
                        blocks[i][j].getTag().equals(blocks[i + 1][j + 1].getTag()) &&
                        blocks[i][j].getTag().equals(blocks[i + 2][j + 2].getTag())) {
                    if (blocks[i][j].getTag().equals(R.drawable.redstar)) {
                        result(1);
                    } else {
                        result(2);
                    }
                    return true;
                }
            }
        }
        for (int i = 2; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length - 2; j++) {
                if (!blocks[i][j].getTag().equals(R.drawable.yellowstar) &&
                        blocks[i][j].getTag().equals(blocks[i - 1][j + 1].getTag()) &&
                        blocks[i][j].getTag().equals(blocks[i - 2][j + 2].getTag())) {
                    if (blocks[i][j].getTag().equals(R.drawable.redstar)) {
                        result(1);
                    } else {
                        result(2);
                    }
                    return true;
                }
            }
        }


        return false;
    }

    private void resetGame() {
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                blocks[i][j].setImageResource(R.drawable.yellowstar);
                blocks[i][j].setTag(R.drawable.yellowstar);
            }
        }
    }

    private void result(int i) {
        String[] whoWon = {"No One", "Player","Computer"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(whoWon[i] + " Won!")
                .setMessage("Do you want to play again?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resetGame();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(playgameActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private boolean moves(){
        boolean anyMoves = false;
        for(int i = 0; i < blocks.length && !anyMoves; i++){
            for(int j = 0; j < blocks[i].length; j++){
                if(blocks[i][j].getTag().equals(R.drawable.yellowstar)){
                    anyMoves = true;
                    break;
                }
            }
        }
        return anyMoves;
    }
    private void computerMove(){

        boolean choiceMade = false;

        while(!choiceMade){
            int i;
            i = random.nextInt(4);
            for(int j = 3; j >=0; j--){
                if(blocks[j][i].getTag().equals(R.drawable.yellowstar)){
                    blocks[j][i].setImageResource(R.drawable.greenstar);
                    blocks[j][i].setTag(R.drawable.greenstar);
                    choiceMade = true;
                    if(checkWinner()) return;
                    if(!moves()) result(0);
                    break;
                }
            }
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame);

        blocks = new ImageView[][]{
                {findViewById(R.id.Block00), findViewById(R.id.Block01), findViewById(R.id.Block02), findViewById(R.id.Block03)},
                {findViewById(R.id.Block10), findViewById(R.id.Block11), findViewById(R.id.Block12), findViewById(R.id.Block13)},
                {findViewById(R.id.Block20), findViewById(R.id.Block21), findViewById(R.id.Block22), findViewById(R.id.Block23)},
                {findViewById(R.id.Block30), findViewById(R.id.Block31), findViewById(R.id.Block32), findViewById(R.id.Block33)}
        };

        //Sets the Tag of every block to
        for(int i = 0; i < blocks.length; i++) {
            for(int j = 0; j < blocks[i].length; j++) {
                blocks[i][j].setTag(R.drawable.yellowstar);
            }
        }


        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                blocks[i][j].setTag(R.drawable.yellowstar);
            }
        }


        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!blocks[0][0].getTag().equals(R.drawable.yellowstar)) {
                    if(!moves()) result(0);
                    return;
                }
                for(int i = 3; i >= 0; i--){
                    if (blocks[i][0].getTag().equals(R.drawable.yellowstar)) {
                        blocks[i][0].setImageResource(R.drawable.redstar);
                        blocks[i][0].setTag(R.drawable.redstar);
                        if(checkWinner()) return;
                        break;
                    }
                }
                computerMove();
            }
        });


        //Button 2 ####################
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!blocks[0][1].getTag().equals(R.drawable.yellowstar)) {
                    if(!moves()) result(0);
                    return;
                }
                for(int i = 3; i >= 0; i--){
                    if (blocks[i][1].getTag().equals(R.drawable.yellowstar)) {
                        blocks[i][1].setImageResource(R.drawable.redstar);
                        blocks[i][1].setTag(R.drawable.redstar);
                        if(checkWinner()) return;
                        break;
                    }
                }
                computerMove();
            }
        });

        //Button3 #################
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!blocks[0][2].getTag().equals(R.drawable.yellowstar)) {
                    if(!moves()) result(0);
                    return;
                }
                for(int i = 3; i >= 0; i--){
                    if (blocks[i][2].getTag().equals(R.drawable.yellowstar)) {
                        blocks[i][2].setImageResource(R.drawable.redstar);
                        blocks[i][2].setTag(R.drawable.redstar);
                        if(checkWinner()) return;
                        break;
                    }
                }
                computerMove();
            }
        });

        //button4&&&&&&&&

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!blocks[0][3].getTag().equals(R.drawable.yellowstar)) {
                    if(!moves()) result(0);
                    return;
                }
                for(int i = 3; i >= 0; i--){
                    if (blocks[i][3].getTag().equals(R.drawable.yellowstar)) {
                        blocks[i][3].setImageResource(R.drawable.redstar);
                        blocks[i][3].setTag(R.drawable.redstar);
                        if(checkWinner()) return;
                        break;
                    }
                }
                computerMove();
            }
        });
    }
}

