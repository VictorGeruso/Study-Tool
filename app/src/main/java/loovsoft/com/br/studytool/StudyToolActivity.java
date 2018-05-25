package loovsoft.com.br.studytool;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import loovsoft.com.br.studytool.fragments.HomeFragment;
import loovsoft.com.br.studytool.fragments.HorarioFragment;

public class StudyToolActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.drawer_view_materia){
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, homeFragment).commit();
        } else if(id == R.id.drawer_view_horario){
            Fragment horarioFragment = new HorarioFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, horarioFragment).commit();
        } else if (id == R.id.drawer_view_atividade){
            Toast.makeText(this, "Atividade", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
