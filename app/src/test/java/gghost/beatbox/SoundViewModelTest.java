package gghost.beatbox;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SoundViewModelTest {

    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {

        mBeatBox = Mockito.mock(BeatBox.class);
        mSubject = new SoundViewModel(mBeatBox);

        mSound = new Sound("assetPath");
        mSubject.setSound(mSound);
    }

    @Test
    public void exposesSoundNameAsTitle() {
                assertThat(mSubject.getTitle(), is(mSound.getName()));
    }

    @Test
    public void callBeatBoxPlayOnButtonClicked() {
        mSubject.onButtonClicked();
        Mockito.verify(mBeatBox).playSound(mSound);
    }
}