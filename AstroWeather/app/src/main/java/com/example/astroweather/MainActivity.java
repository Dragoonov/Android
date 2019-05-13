package com.example.astroweather;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    TextView time;
    private TextView data;
    private Button fab;
    FragmentMoon moon = new FragmentMoon();
    FragmentSun sun = new FragmentSun();
    ViewPager pager;
    LinearLayout linearLayout;
    PagerAdapter pagerAdapter;
    Thread t1 = null;
    Thread t2 = null;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (tabletSize) {
            linearLayout = findViewById(R.id.linearLayout2);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.content_secondary,sun,"sun");
            fragmentTransaction.add(R.id.content_primary,moon,"moon");
            fragmentTransaction.commit();

        }
        else
        {
            pager = (ViewPager) findViewById(R.id.container);
            pagerAdapter = new ListPagerAdapter(getSupportFragmentManager());
            pager.setAdapter(pagerAdapter);
        }

        fab = findViewById(R.id.button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddItemDialog(MainActivity.this);
            }
        });
        time = findViewById(R.id.time);
        time.setText(DataProcessor.simpleDateFormat.format(System.currentTimeMillis()));
        data = findViewById(R.id.data);
        data.setText("Szerokość: " + DataProcessor.widthGeo + ", długość:" + DataProcessor.heightGeo);

            t1 = new Thread() {
                @Override
                public void run() {
                    try {
                        while (!isInterrupted()) {
                            Thread.sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    time.setText(DataProcessor.simpleDateFormat.format(System.currentTimeMillis()));
                                    DataProcessor.refresh();
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t1.start();



            t2 = new Thread() {
                @Override
                public void run() {
                    try {
                        while (!isInterrupted()) {
                            Thread.sleep(DataProcessor.timeRefresh * 1000 * 60);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    DataProcessor.refresh();
                                    sun.update();
                                    moon.update();
                                    System.out.println("Update time refresh");
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t2.start();

        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        t1.interrupt();
        t2.interrupt();
    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }


    private void showAddItemDialog(Context c) {
        LinearLayout layout = new LinearLayout(c);
        layout.setOrientation(LinearLayout.VERTICAL);
        final TextView widthInfo = new TextView(c);
        widthInfo.setText("Szerokość geograficzna");
        final EditText width = new EditText(c);
        width.setHint("Szerokość geograficzna");
        width.setText(Double.toString(DataProcessor.widthGeo));
        layout.addView(widthInfo);
        layout.addView(width);
        final TextView heightInfo = new TextView(c);
        heightInfo.setText("Długość geograficzna");
        final EditText heigth = new EditText(c);
        heigth.setHint("Długość geograficzna");
        heigth.setText(Double.toString(DataProcessor.heightGeo));
        layout.addView(heightInfo);
        layout.addView(heigth);
        final EditText refreshRate = new EditText(c);
        final TextView refreshInfo = new TextView(c);
        refreshInfo.setText("Czas odświeżania (minuty)");
        refreshRate.setHint("Czas odświeżania (minuty)");
        refreshRate.setText(Integer.toString(DataProcessor.timeRefresh));
        layout.addView(refreshInfo);
        layout.addView(refreshRate);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setMessage("Ustawienia")
                .setView(layout)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            if(Integer.valueOf(refreshRate.getText().toString())<=0)
                                throw new NumberFormatException("Zly czas odswiezania");
                            DataProcessor.update(Float.valueOf(width.getText().toString()), Float.valueOf(heigth.getText().toString()), Integer.valueOf(refreshRate.getText().toString()));
                            sun.update();
                            moon.update();
                            data.setText("Szerokość: " + DataProcessor.widthGeo + ", długość:" + DataProcessor.heightGeo);
                            t2.interrupt();
                            t2 = new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        while (!isInterrupted()) {
                                            Thread.sleep(DataProcessor.timeRefresh * 1000 * 60);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    DataProcessor.refresh();
                                                    sun.update();
                                                    moon.update();
                                                    System.out.println("Update time refresh");
                                                }
                                            });
                                        }
                                    } catch (InterruptedException e) {
                                    }
                                }
                            };
                            t2.start();
                        }
                        catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"Wpisz poprawne dane",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    public class ListPagerAdapter extends PagerAdapter {
        FragmentManager fragmentManager;
        Fragment[] fragments;

        ListPagerAdapter(FragmentManager fm){
            fragmentManager = fm;
            fragments = new Fragment[2];
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position,@NonNull Object object) {
            assert(0 <= position && position < fragments.length);
            FragmentTransaction trans = fragmentManager.beginTransaction();
            trans.remove(fragments[position]);
            trans.commit();
            fragments[position] = null;
        }

        @Override
        public Fragment instantiateItem(@NonNull ViewGroup container, int position){
            Fragment fragment = getItem(position);
            FragmentTransaction trans = fragmentManager.beginTransaction();
            trans.add(container.getId(),fragment,"fragment:"+position);
            trans.commit();
            return fragment;
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view,@NonNull Object fragment) {
            return ((Fragment) fragment).getView() == view;
        }

        Fragment getItem(int position){
            assert(0 <= position && position < fragments.length);
            if(fragments[position] == null){
                if(position==0)
                    fragments[position] = (sun = new FragmentSun()); //make your fragment here
                else
                    fragments[position] = (moon = new FragmentMoon());
            }
            return fragments[position];
        }
    }

}
