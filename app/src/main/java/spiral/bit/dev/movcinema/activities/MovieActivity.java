package spiral.bit.dev.movcinema.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

import spiral.bit.dev.movcinema.R;
import spiral.bit.dev.movcinema.adapters.TabAdapter;

public class MovieActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.main_tab);
        tabLayout.post(() -> tabLayout.setupWithViewPager(viewPager));
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.main_tabs_pager);
        viewPager.setAdapter(tabAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings_icon) {
            startActivity(new Intent(MovieActivity.this, SettingsActivity.class));
            return true;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}