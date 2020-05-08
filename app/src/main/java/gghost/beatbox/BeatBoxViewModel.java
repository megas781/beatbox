package gghost.beatbox;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class BeatBoxViewModel extends BaseObservable {

    private Context mContext;
    private BeatBox mBeatBox;

    public BeatBoxViewModel(Context context, BeatBox beatBox) {
        mContext = context;
        mBeatBox = beatBox;
    }

    @Bindable
    public String getPlayback() {
        return mContext.getResources().getString(R.string.playback_speed, (mBeatBox.getPlaybackSpeed() * 100));
    }

    /**
     * @param playbackProgress – int значение от 0 до 100, которое нужно кнвертировать в 50...200
     */
    public void setPlayback(int playbackProgress) {
        //exp - в диапазоне от [-1; 1]
        float exp = ((float) playbackProgress - 50) / 50;
        //В диапазоне от 1/2 до 2
        float progressScale = (float) Math.pow(2, exp);
        mBeatBox.setPlaybackSpeed(progressScale);
        notifyPropertyChanged(BR.playback);
    }

}
