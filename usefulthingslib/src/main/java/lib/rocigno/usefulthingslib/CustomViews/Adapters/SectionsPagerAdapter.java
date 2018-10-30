package lib.rocigno.usefulthingslib.CustomViews.Adapters;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PageAdapter para os ViewPager com a função de linkar com o BottomNavigationView
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public void listFragments(Fragment... fragments){
        this.fragments.addAll(Arrays.asList(fragments));
    }

    public void addFragment(Fragment fragment){
        this.fragments.add(fragment);
        notifyDataSetChanged();
    }

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem (int position){
        return fragments.get(position);
    }

    @Override
    public int getCount(){
        return fragments.size();
    }

    //Este "execute" serve para barrar o evento do bottomNavigationView que acontece ao usar a função
    //setSelectedItem, pois sem isso ele executa duas vezes o código de selecionar a pagina, deixado a animação travada
    boolean execute = true;
    public void setLinkWithBottomNavigation(final ViewPager viewPager, final BottomNavigationView bottomNavigationView){

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                execute = false;
                bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(i).getItemId());
                execute = true;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(!execute){ return true;}
                for (int i = 0; i < bottomNavigationView.getMaxItemCount(); i++) {
                    if(bottomNavigationView.getMenu().getItem(i).getItemId() == menuItem.getItemId()){
                        viewPager.setCurrentItem(i);
                        return true;
                    }
                }
                return false;
            }
        });
    }

}
