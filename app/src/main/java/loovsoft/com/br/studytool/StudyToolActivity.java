package loovsoft.com.br.studytool;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import loovsoft.com.br.studytool.fragments.HomeFragment;

public class StudyToolActivity extends AppCompatActivity {

    private String nomeEstudante;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private View hView;
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_tool);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.nomeEstudante = this.mSecurityPreferences.getStoreString("ESTUDANTE");
        
        //header view
        navigationView = findViewById(R.id.nav_view);

        updateHeader();
        
        //Drawer
        drawerLayout = findViewById(R.id.drawer_layout);

        //Toolbar
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorTextToolbar));

        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        HomeFragment homeFragment = new HomeFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.content_frame, homeFragment).commit();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_menu);

    }

    private void updateHeader() {

        hView = navigationView.getHeaderView(0);

        TextView username = hView.findViewById(R.id.nav_header_username);

        username.setText(this.nomeEstudante);

    }

    //Tollbar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:

                drawerLayout.openDrawer(Gravity.START);
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
