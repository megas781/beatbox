package gghost.beatbox;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected static final String ONLY_FRAGMENT_TAG = "ONLY_FRAGMENT_TAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);


//        String asdf = "zhopa";
//
//        if (getSupportFragmentManager().getFragments().size() > 0) {
//            asdf = getSupportFragmentManager().getFragments().get(0).getTag();
//        }

        if (getSupportFragmentManager().findFragmentByTag(ONLY_FRAGMENT_TAG) == null) {

            Fragment f = this.createFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, f, ONLY_FRAGMENT_TAG)
                    .commit();
        }

    }

    protected Fragment createFragment() {
        return new Fragment();
    }
}
