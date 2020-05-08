package gghost.beatbox;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class SoundViewModel extends BaseObservable {


    private BeatBox mBeatBox;
    private Sound mSound;

    public Sound getSound() {
        return mSound;
    }
    public void setSound(Sound sound) {
        mSound = sound;
        notifyPropertyChanged(BR.title);
    }

    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }
    @Bindable
    public String getTitle() {
        return mSound.getName();
    }

    public void onButtonClicked() {
        mBeatBox.playSound(mSound);
    }

}
