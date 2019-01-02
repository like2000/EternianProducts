package ch.li.k.eternianproducts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ch.li.k.eternianproducts.ui.countdown.CountdownFragment;

public class CountdownActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CountdownFragment.newInstance())
                    .commitNow();
        }
    }
}
