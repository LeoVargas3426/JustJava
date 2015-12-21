package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    private int quantity;
    private boolean moca;
    private boolean chocolate;
    private int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity = 0;
        price = 5;
        moca = false;
        chocolate = false;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox moc = (CheckBox) findViewById(R.id.mocacheck);
        CheckBox cho = (CheckBox) findViewById(R.id.chococheck);
        moca = moc.isChecked();
        chocolate = cho.isChecked();
        EditText name = (EditText) findViewById(R.id.editText);
        String nombre = name.getText().toString();
        int adding = 0;

        if (moca) {
            adding += 1 * quantity;
        }

        if (chocolate) {
            adding += 2 * quantity;
        }

        String priceMessage = "Nombre: " + nombre + "\n" + "Tazas de cafe: " + quantity + "\n" + "Mocca($1): " + moca + "\n" + "Chocolate($2): " + chocolate + "\n" + "Total: $" + (quantity * price + adding) + "\n" + "Gracias";


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, "jl.vargas3426@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffe order");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * this method increase the cuantity of the caffe cups
     *
     * @param view
     */
    public void increase(View view) {
        if (quantity + 1 <= 100) {
            quantity++;
        }
        display(quantity);
        displayPrice(quantity * 5);
    }

    /**
     * this method decrease the cuantity of the caffe cups
     *
     * @param view
     */

    public void decrease(View view) {
        if (quantity - 1 >= 0) {
            quantity--;
        }
        display(quantity);
        displayPrice(quantity * 5);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price);
        priceTextView.setText(message);
    }
}