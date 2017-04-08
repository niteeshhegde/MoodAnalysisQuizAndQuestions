package abc.moodanalysis;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle extras = getIntent().getExtras();
        final  String  a=extras.getString("gender");
        final String b=extras.getString("age");
        final int c=extras.getInt("total");
        TextView xx=(TextView)findViewById(R.id.textView12);




        //Toast.makeText(getBaseContext(),""+a+" "+b+" "+c, Toast.LENGTH_SHORT).show();

        int x= Integer.parseInt(b);
        if(x<18){
            if(a.equals("MALE")){
                if(c<16){
                    xx.setText("YOUR SCORE IS "+ c+". ITS A NORMAL SCORE FOR A BOY OF AGE "+b+".");

                }else{
                    xx.setText("YOUR SCORE IS "+ c+". ITS ABNORMAL SCORE FOR A BOY OF AGE "+b+". CONSULT A DOCTOR.");
                }
            }else{

                if(c<15){
                    xx.setText("YOUR SCORE IS "+ c+". ITS A NORMAL SCORE FOR A GIRL OF AGE "+b+".");

                }else{
                    xx.setText("YOUR SCORE IS "+ c+". ITS ABNORMAL SCORE FOR A GIRL OF AGE "+b+". CONSULT A DOCTOR.");
                }
            }

        }else if(x<60){
            if(a.equals("MALE")){
                if(c<13){
                    xx.setText("YOUR SCORE IS "+ c+". ITS A NORMAL SCORE FOR A MAN OF AGE "+b+".");

                }else{
                    xx.setText("YOUR SCORE IS "+ c+". ITS ABNORMAL SCORE FOR A MAN OF AGE "+b+". CONSULT A DOCTOR.");
                }
            }else{

                if(c<12){
                    xx.setText("YOUR SCORE IS "+ c+". ITS A NORMAL SCORE FOR A WOMAN OF AGE "+b+".");

                }else{
                    xx.setText("YOUR SCORE IS "+ c+". ITS ABNORMAL SCORE FOR A WOMAN OF AGE "+b+". CONSULT A DOCTOR.");
                }
            }





        }else{

            if(a.equals("MALE")){
                if(c<17){
                    xx.setText("YOUR SCORE IS "+ c+". ITS A NORMAL SCORE FOR A MAN OF AGE "+b+".");

                }else{
                    xx.setText("YOUR SCORE IS "+ c+". ITS ABNORMAL SCORE FOR A MAN OF AGE "+b+". CONSULT A DOCTOR.");
                }
            }else{

                if(c<16){
                    xx.setText("YOUR SCORE IS "+ c+". ITS A NORMAL SCORE FOR A WOMAN OF AGE "+b+".");

                }else{
                    xx.setText("YOUR SCORE IS "+ c+". ITS ABNORMAL SCORE FOR A WOMAN OF AGE "+b+". CONSULT A DOCTOR.");
                }
            }

        }


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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
