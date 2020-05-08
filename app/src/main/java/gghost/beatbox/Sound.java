package gghost.beatbox;

import java.util.Arrays;

public class Sound {

    private String mAssetPath;
    private String mName;



    private Integer mSoundId;

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        //Достаем имя файла
        String filename = components[components.length - 1];
        //Убираем расширение
        mName = filename.replace(".wav", "");
    }

    public String getAssetPath() {
        return mAssetPath;
    }
    public String getName() {
        return mName;
    }
    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
