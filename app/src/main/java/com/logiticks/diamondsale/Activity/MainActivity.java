package com.logiticks.diamondsale.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.logiticks.diamondsale.Fragment.FragmentCustomer;
import com.logiticks.diamondsale.Fragment.FragmentDiamonds;
import com.logiticks.diamondsale.Fragment.FragmentInvoices;
import com.logiticks.diamondsale.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    private Handler mHandler;
    private DrawerLayout drawer;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment != null && fragment.getTag().equals("fragDiamonds")) {
                    Intent intent = new Intent(MainActivity.this, CreateDiamondActivity.class);
                    startActivityForResult(intent, 0);
                }else if(fragment != null && fragment.getTag().equals("fragCustomer")) {
                    Intent intent = new Intent(MainActivity.this, CreateCustomerActivity.class);
                    startActivityForResult(intent, 1);
                }else {
                    Intent intent = new Intent(MainActivity.this, CreateInvoiceActivity.class);
                    startActivityForResult(intent, 2);
                }
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            loadHomeFragment(FragmentDiamonds.newInstance(),"fragDiamonds");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


        String tag = "";
        int id = item.getItemId();

        if (id == R.id.nav_diamonds) {
            fragment = FragmentDiamonds.newInstance();
            tag = "fragDiamonds";
            // Handle the camera action
        } else if (id == R.id.nav_customer) {
            fragment = FragmentCustomer.newInstance();
            tag = "fragCustomer";

        } else if (id == R.id.nav_order) {
            fragment = FragmentInvoices.newInstance();
            tag = "fragInvoice";
        }

        loadHomeFragment(fragment,tag);
        return true;
    }

    private void loadHomeFragment(final Fragment mFragment, final String tag) {

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                fragment = mFragment;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, tag);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        mHandler.post(mPendingRunnable);

        // show or hide the fab button
        //toggleFab();

        //Closing drawer on item click
        //drawer.closeDrawers();
        drawer.closeDrawer(GravityCompat.START);


        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            loadHomeFragment(FragmentDiamonds.newInstance(),"fragDiamonds");
        }else {
            loadHomeFragment(FragmentCustomer.newInstance(),"fragCustomer");
        }

    }
}
