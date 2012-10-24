package com.moneyorganizer.organizer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MoneyOrganizer extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_organizer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_money_organizer, menu);
        return true;
    }
}
