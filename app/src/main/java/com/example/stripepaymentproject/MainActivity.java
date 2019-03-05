package com.example.stripepaymentproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

public class MainActivity extends AppCompatActivity {

    CardInputWidget cardInputWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);

        Card card = cardInputWidget.getCard();

        if (card == null) {
            Toast.makeText(this, "Invalid Card", Toast.LENGTH_LONG).show();
        }
        else {
            Stripe stripe = new Stripe(this, "pk_test_EAdrepNNWDFd9A1BVVeUxVcu");
            stripe.createToken(card, new TokenCallback() {
                @Override
                public void onError(Exception error) {
                    Log.e("Stripe", error.getMessage());
                }

                @Override
                public void onSuccess(Token token) {
                    Log.d("Stripe", token.getId());
                }
            });
        }
    }
}
