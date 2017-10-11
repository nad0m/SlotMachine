package com.example.bigmac.slotmachine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import static com.example.bigmac.slotmachine.Slots.getBet;
import static java.lang.String.valueOf;


public class MainActivity extends AppCompatActivity {

    public int bet = 1;
    public int winnings = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing UI objects
        final Button spinReel = (Button) findViewById(R.id.pullButton);
        final Button addition = (Button) findViewById(R.id.buttonAdd);
        final Button subtraction = (Button) findViewById(R.id.buttonSubtract);
        final Button quit = (Button) findViewById(R.id.exitButton);
        final Bitmap bar = BitmapFactory.decodeResource(getResources(),R.drawable.bar);
        final Bitmap cherry = BitmapFactory.decodeResource(getResources(),R.drawable.cherry);
        final Bitmap seven = BitmapFactory.decodeResource(getResources(),R.drawable.seven);
        final Bitmap blank = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        final Bitmap logo1 = BitmapFactory.decodeResource(getResources(),R.drawable.logo1);
        final Bitmap logo2 = BitmapFactory.decodeResource(getResources(),R.drawable.logo2);
        final Bitmap logo3 = BitmapFactory.decodeResource(getResources(),R.drawable.logo3);
        final ImageView image1 = (ImageView)findViewById(R.id.slot1);
        final ImageView image2 = (ImageView)findViewById(R.id.slot2);
        final ImageView image3 = (ImageView)findViewById(R.id.slot3);
        final TextView betBox = (TextView)findViewById(R.id.amount);
        final TextView console = (TextView)findViewById(R.id.console);

        betBox.setText(valueOf(bet));

// ------- Quit Button ---------
        quit.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                console.setText("Your total winnings: " + winnings + ". Thanks for playing!");
                reset();
                bet = 1;
                winnings = 0;
                betBox.setText(valueOf(bet));
            }

            public void reset()
            {
                image1.setImageBitmap(blank);
                image2.setImageBitmap(blank);
                image3.setImageBitmap(blank);
            }
        });
// ------- Add bet Button ---------
        addition.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                bet += 1;
                betBox.setText(valueOf(bet));
            }
        });

// ------- Minus bet Button ---------
        subtraction.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                bet -= 1;
                betBox.setText(valueOf(bet));
            }
        });

// ------- Pull Button ---------
        spinReel.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (getBet(bet))
                {
                    reset();

                    final Slots slots = new Slots();
                    final TripleString ts = slots.pull();
                    final String[] results = slots.loadArray();
                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            displayImage1(results[0]);
                        }
                    }, 1000);


                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            displayImage2(results[1]);
                        }
                    }, 2000);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            displayImage3(results[2]);
                            winnings += bet*slots.getPayMultiplier(ts);

                            console.setText("You win " + bet*slots.getPayMultiplier(ts) + ". Try again?");

                            restoreButton();

                        }
                    }, 3000);
                }

                else{
                    console.setText("Please enter valid bet amount. (1 - 99)");
                }
            }

            public void reset()
            {
                console.setText("Reel is spinning...");
                image1.setImageBitmap(logo1);
                image2.setImageBitmap(logo2);
                image3.setImageBitmap(logo3);

                spinReel.setEnabled(false);
                addition.setEnabled(false);
                subtraction.setEnabled(false);
                quit.setEnabled(false);

            }
            public void restoreButton()
            {
                spinReel.setEnabled(true);
                addition.setEnabled(true);
                subtraction.setEnabled(true);
                quit.setEnabled(true);
            }
            private void displayImage1(String result)
            {

                switch(result)
                {
                    case "cherries":
                        image1.setImageBitmap(cherry);
                        break;
                    case "BAR":
                        image1.setImageBitmap(bar);
                        break;
                    case "7":
                        image1.setImageBitmap(seven);
                        break;
                    case "(space)":
                        image1.setImageBitmap(blank);
                }
            }


            private void displayImage2(String result)
            {
                switch(result)
                {
                    case "cherries":
                        image2.setImageBitmap(cherry);
                        break;
                    case "BAR":
                        image2.setImageBitmap(bar);
                        break;
                    case "7":
                        image2.setImageBitmap(seven);
                        break;
                    case "(space)":
                        image2.setImageBitmap(blank);
                }
            }
            private void displayImage3(String result)
            {
                switch(result)
                {
                    case "cherries":
                        image3.setImageBitmap(cherry);
                        break;
                    case "BAR":
                        image3.setImageBitmap(bar);
                        break;
                    case "7":
                        image3.setImageBitmap(seven);
                        break;
                    case "(space)":
                        image3.setImageBitmap(blank);
                }
            }

        });


    }


}
