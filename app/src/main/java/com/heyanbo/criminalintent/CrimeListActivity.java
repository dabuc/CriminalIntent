package com.heyanbo.criminalintent;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

/**
 * Created by kaite on 2015/3/8.
 */
public class CrimeListActivity extends SingleFragmentActivity implements CrimeListFragment.Callbacks,CrimeFragment.CallBacks {
    @Override
    protected Fragment createFragment() {
        Log.d("CrimeListActivity", "CrimeListActivity is called");
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detailFragmentContainer)==null){
            Intent i=new Intent(this,CrimePagerActivity.class);
            i.putExtra(CrimeFragment.EXTRA_CRIME_ID,crime.getId());
            startActivity(i);
        }else {
            FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
            Fragment oldDetail = fm.findFragmentById(R.id.detailFragmentContainer);
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());
            if (oldDetail!=null){
                ft.remove(oldDetail);
            }
            ft.add(R.id.detailFragmentContainer,newDetail);
            ft.commit();
        }
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
        FragmentManager fm=getSupportFragmentManager();
        CrimeListFragment listFragment = (CrimeListFragment) fm.findFragmentById(R.id.fragmentContainer);
        listFragment.updateUI();
    }
}
