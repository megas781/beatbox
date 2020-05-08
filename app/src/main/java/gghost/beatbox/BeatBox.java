package gghost.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс BeatBox – это всся логика воспроизведения звуков.
 * BeatBox осуществляет следующие задачи:
 * - извлечение списка звуков с помощью AssetManager'а
 * - создание и хранение объектов Sound созданных благодаря ранее извлеченному списку
 * - создание и хранение объекта класса SoundPool, чтобы воспроизводить звуки
 * - загрузка звуков в SoundPool с помощью mAssets.openFd (не mAssets.open) из путей объектов Sound (sound.getAssetPath)
 */
public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    public float getPlaybackSpeed() {
        System.out.println("playback: " + mPlaybackSpeed);
        return mPlaybackSpeed;
    }
    public void setPlaybackSpeed(float playbackSpeed) {
        mPlaybackSpeed = playbackSpeed;
//        mSoundPool.set
    }

    private float mPlaybackSpeed = 1.0f;

    private AssetManager mAssets;
    private ArrayList<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public ArrayList<Sound> getSounds() {
        return mSounds;
    }

    public BeatBox(AssetManager assetManager) {
        mAssets = assetManager;
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        mSoundPool = new SoundPool.Builder()
                .setMaxStreams(MAX_SOUNDS)
                .setAudioAttributes(attributes)
                .build();
        loadSounds();

    }

    public void loadSounds() {
        //Создаем хранилище для будущих имен файлов звуков
        String[] soundNames = new String[0];
        try {
            //Достаем имена звуков
            soundNames = mAssets.list(SOUNDS_FOLDER);
//            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException e) {
//            Log.e(TAG, "Couldn't list assets: ", e);
        }

        //По именям файлов инициализируем звуки по путям к активам
        for (String filename: soundNames) {
            try {
                //Создаем путь, по которому можно будет обращаться к активу
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                //Инициализируем объект Sound по пути к активу
                Sound sound = new Sound(assetPath);

                //Добавляем объект Sound в список звуков BeatBox'a
                this.mSounds.add(sound);
                //Добавляем объект sound в mSoundPool
                this.loadSoundToPool(sound);

            } catch (IOException e) {
                Log.e(TAG, "Sound " + filename + " wasn't loaded successfully", e);
            }
        }
    }

    /**
     * Загрузка одного звука в mSoundPool
     * @param sound объект, описывающий звук, его путь к asset'у
     * @throws IOException
     */
    private void loadSoundToPool(Sound sound) throws IOException {
        //Открываем файловый дескриптор актива
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        //Загружаем файловый дескриптор в mSoundPool и сохраняем его Id
        int soundId = mSoundPool.load(afd, 1);
        //Указываем объекту sound, какой у него id в mSoundPool'е
        sound.setSoundId(soundId);
    }

    public void playSound(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        mSoundPool.play(soundId, 1, 1,0,0, mPlaybackSpeed);

    }
    public void releaseSounds() {
        mSoundPool.release();
    }

}
