package fatslayers.fatslayerrpg;

/**
 * Created by drako on 7/31/2017.
 */

public class QuestGame {

    public enum DifficultyLevel {Easy, Harder, Expert}

    private DifficultyLevel mDifficultyLevel;

    public QuestGame() {
        mDifficultyLevel = DifficultyLevel.Easy;
    }

    public DifficultyLevel getDifficultyLevel() {
        return mDifficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        mDifficultyLevel = difficultyLevel;
    }
}
