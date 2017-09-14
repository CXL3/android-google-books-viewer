package com.pocorusso.holiducodingtask;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * The main activity that host the main fragment.
 * When we want to add a menu or a drawers we can add it here
 * rather than polluting the fragment with drawers/menu code.
 * Also when we need to handle screens with different sizes
 * or layout, we will load a different fragment/fragments from here.
 */
public class MainActivity extends AppCompatActivity {

    //TODO Load different layout fragment depending on screen size here.
    protected Fragment createFragment() {
        return new VolumeListFragment();
    }

    @LayoutRes
    protected int getLayoutResId(){
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();

        }
    }
}
