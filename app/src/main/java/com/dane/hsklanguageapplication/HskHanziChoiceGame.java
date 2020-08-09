package com.dane.hsklanguageapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;

public class HskHanziChoiceGame extends AppCompatActivity{
    private TextView textView;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;
    private Button btnNext;
    private TextView txtTranslation;
    private ImageButton btnFlipPinyin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsk_hanzi_choice_game);

        Intent intent = getIntent();
        String hsk = intent.getStringExtra("hsk");

        Integer score = new Integer(0);
        createGame(score, true, hsk);
    }


    /**
     *
     * @param hsk File that needs to be returned
     * @return returns the file contents as a string
     */
    public String readJSONFromAsset(String hsk) {
        String json = null;
        try {
            InputStream is = getAssets().open(hsk);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     *
     * @param hsk Pass the HSK file that needs to be opened e.g. hsk 1 or hsk 2
     * @return Returns a JSONArray of 4 selected characters (at random), the objects hold the Hanzi, Pinyin and the translations for the characters
     * @throws JSONException
     */
    public JSONArray selectHanZi(String hsk) throws JSONException {
        JSONArray jsA = new JSONArray(readJSONFromAsset(hsk));
        JSONArray selectedHanZi = new JSONArray();

        for (int i = 0; i < 4; i++){
            JSONObject tmp = new JSONObject();

            double randomHanZi = Math.random() * ((jsA.length() - 1)) + 0;
            tmp.put("hanzi",jsA.getJSONObject((int) (randomHanZi)).optString("hanzi"));
            tmp.put("pinyin", jsA.getJSONObject((int) (randomHanZi)).optString("pinyin"));
            tmp.put( "translations", jsA.getJSONObject((int) (randomHanZi)).optString("translations"));
            selectedHanZi.put(i, tmp);
            jsA.remove((int) randomHanZi);
        }
        return selectedHanZi ;
    }

    /**
     *
     * @param score This is the continuing score of the game
     * @param hanzi This is a toggle between displaying the hanzi at the top or as the four buttons
     * @param hsk This is the hsk file that you want to open e.g. hsk 1 or hsk 2
     */
    public void createGame(final int score, boolean hanzi, String hsk){
        final Integer[] countingScore = {score};
        final TextView textScore = (TextView) findViewById(R.id.txtScore);
        txtTranslation = findViewById(R.id.txtTranslation);
        btnFlipPinyin = findViewById(R.id.btnFlipPinyin);
        textScore.setText("Score: " + score);
        textView = (TextView) findViewById(R.id.txt1);
        final Button[] allButtons;
        answerBtn1 = (Button) findViewById(R.id.btnAnswer1);
        answerBtn2 = (Button) findViewById(R.id.btnAnswer2);
        answerBtn3 = (Button) findViewById(R.id.btnAnswer3);
        answerBtn4 = (Button) findViewById(R.id.btnAnswer4);
        allButtons = new Button[]{answerBtn1, answerBtn2, answerBtn3, answerBtn4};
        answerBtn1.setBackgroundColor(Color.parseColor("#ffffff"));
        answerBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
        answerBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
        answerBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
        btnNext = findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        allButtons[0].setEnabled(true);
        allButtons[1].setEnabled(true);
        allButtons[2].setEnabled(true);
        allButtons[3].setEnabled(true);
        txtTranslation.setText("");
        btnNext.setVisibility(textView.INVISIBLE);
        btnNext.setBackgroundColor(Color.parseColor("#ffffff"));
        final Boolean[] setHanzi = {hanzi};


        try {
            final JSONArray selectedCharacters = selectHanZi(hsk);
            //Display the selected hanzi
            //We have already randomized everything so we can simply set the answers and fake answers like so
            final String answerPinyin = selectedCharacters.getJSONObject((int) (0)).optString("pinyin");
            final String answerHanzi = selectedCharacters.getJSONObject((int) (0)).optString("hanzi");
            if(setHanzi[0] == true){
                textView.setText(selectedCharacters.getJSONObject((int) (0)).optString("hanzi"));
            } else {
                textView.setText(selectedCharacters.getJSONObject((int) (0)).optString("pinyin"));
            }

            final String translation = selectedCharacters.getJSONObject((int) (0)).optString("translations").replaceAll("\\[|]|\"","").replace(",", ", ");
            Collections.shuffle(Arrays.asList(allButtons));

            for(int i = 0; i < 4; i++){
                if(setHanzi[0] == true){
                    allButtons[i].setText(selectedCharacters.getJSONObject((int) (i)).optString("pinyin"));
                } else {
                    allButtons[i].setText(selectedCharacters.getJSONObject((int) (i)).optString("hanzi"));
                }

            }

            btnFlipPinyin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                        flipIt(allButtons[0]);
                        flipIt(allButtons[1]);
                        flipIt(allButtons[2]);
                        flipIt(allButtons[3]);

                        if (setHanzi[0] == true){
                            setHanzi[0] = false;
                        } else  {
                            setHanzi[0] = true;
                        }

                        if (setHanzi[0] == true){
                            textView.setText(selectedCharacters.getJSONObject((int) (0)).optString("hanzi"));
                            for(int i = 0; i < 4; i++){
                                allButtons[i].setText(selectedCharacters.getJSONObject((int) (i)).optString("pinyin"));
                            }

                        } else {

                            textView.setText(selectedCharacters.getJSONObject((int) (0)).optString("pinyin"));
                            for(int i = 0; i < 4; i++){
                                allButtons[i].setText(selectedCharacters.getJSONObject((int) (i)).optString("hanzi"));
                            }
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


            allButtons[0].setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if (allButtons[0].getText() == answerPinyin || allButtons[0].getText() == answerHanzi){
                        countingScore[0] += 1;
                        allButtons[0].setBackgroundColor(Color.parseColor("green"));
                        textScore.setText("Score: " + countingScore[0]);
                        btnNext.setEnabled(true);
                        allButtons[0].setEnabled(false);
                        allButtons[1].setEnabled(false);
                        allButtons[2].setEnabled(false);
                        allButtons[3].setEnabled(false);
                        txtTranslation.setText(translation);
                        btnNext.setVisibility(textView.VISIBLE);
                    }
                    else {
                        allButtons[0].setText("Try again!");
                        allButtons[0].setBackgroundColor(Color.parseColor("red"));
                        countingScore[0] =0;
                        textScore.setText("Score: " + 0);
                    }
                }
            });

            allButtons[1].setOnClickListener(new View.OnClickListener(){
                @RequiresApi(api = Build.VERSION_CODES.O)
                public void onClick(View v){
                    if (allButtons[1].getText() == answerPinyin || allButtons[1].getText() == answerHanzi){
                        countingScore[0] += 1;
                        allButtons[1].setBackgroundColor(Color.parseColor("green"));
                        textScore.setText("Score: " + countingScore[0]);
                        btnNext.setEnabled(true);
                        allButtons[0].setEnabled(false);
                        allButtons[1].setEnabled(false);
                        allButtons[2].setEnabled(false);
                        allButtons[3].setEnabled(false);
                        txtTranslation.setText(translation);
                        btnNext.setVisibility(textView.VISIBLE);
                    }
                    else {
                        allButtons[1].setText("Try again!");
                        allButtons[1].setBackgroundColor(Color.parseColor("red"));
                        countingScore[0] =0;
                        textScore.setText("Score: " + 0);
                    }
                }
            });

            allButtons[2].setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if (allButtons[2].getText() == answerPinyin || allButtons[2].getText() == answerHanzi){
                        countingScore[0] += 1;
                        allButtons[2].setBackgroundColor(Color.parseColor("green"));
                        textScore.setText("Score: " + countingScore[0]);
                        btnNext.setEnabled(true);
                        allButtons[0].setEnabled(false);
                        allButtons[1].setEnabled(false);
                        allButtons[2].setEnabled(false);
                        allButtons[3].setEnabled(false);
                        txtTranslation.setText(translation);
                        btnNext.setVisibility(textView.VISIBLE);
                    }
                    else {
                        allButtons[2].setText("Try again!");
                        allButtons[2].setBackgroundColor(Color.parseColor("red"));
                        countingScore[0] =0;
                        textScore.setText("Score: " + 0);
                    }
                }
            });

            allButtons[3].setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if (allButtons[3].getText() == answerPinyin || allButtons[3].getText() == answerHanzi){
                        countingScore[0] += 1;
                        allButtons[3].setBackgroundColor(Color.parseColor("green"));
                        textScore.setText("Score: " + countingScore[0]);
                        btnNext.setEnabled(true);
                        allButtons[0].setEnabled(false);
                        allButtons[1].setEnabled(false);
                        allButtons[2].setEnabled(false);
                        allButtons[3].setEnabled(false);
                        txtTranslation.setText(translation);
                        btnNext.setVisibility(textView.VISIBLE);
                    }
                    else {
                        allButtons[3].setText("Try again!");
                        allButtons[3].setBackgroundColor(Color.parseColor("red"));
                        countingScore[0] =0;
                        textScore.setText("Score: " + 0);
                    }
                }
            });

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = getIntent();
                    String hsk = intent.getStringExtra("hsk");
                    createGame(countingScore[0], setHanzi[0], hsk);
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * This will flip a view.
     * @param viewToFlip What do you want to flip?
     */
    private void flipIt(final View viewToFlip) {
        ObjectAnimator flip = ObjectAnimator.ofFloat(viewToFlip, "rotationX", 0f, 360f);
        flip.setDuration(600);
        flip.start();
    }
}