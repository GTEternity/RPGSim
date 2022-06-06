import java.io.Serializable;
import java.util.Objects;

public class Quest implements Serializable {
    private String request;
    private int needed;
    private int done;
    private int coinReward;
    private int expReward;
    private Enemy subject;
    boolean completed;
    public Quest(String quest, int amtNeeded, int amtDone, int coins, int exp, Enemy kill) {
        request = quest;
        needed = amtNeeded;
        done = amtDone;
        completed = (done >= needed);
        coinReward = coins;
        expReward = exp;
        subject = kill;
    }

    public boolean isDone() {
        completed = (done >= needed);
        return completed;
    }
    public String getQuest() {
        return request;
    }
    public int getNeeded() {
        return needed;
    }
    public int getDone() {
        return done;
    }
    public int getCoinReward() {
        return coinReward;
    }
    public int getExpReward() {
        return expReward;
    }
    public Enemy getSubject() {
        return subject;
    }

    public void setDone(int amtDone) {
        done = amtDone;
    }
    public void setNeeded(int amtNeeded) {
        needed = amtNeeded;
    }
    public void setRequest(String newRequest) {
        request = newRequest;
    }
    public void setCoinReward(int coinReward) {
        this.coinReward = coinReward;
    }
    public void setExpReward(int expReward) {
        this.expReward = expReward;
    }
    public void setSubject(Enemy subject) {
        this.subject = subject;
    }

    public boolean checkSubject(Enemy e) {
        if (e.equals(subject))
            done++;
        return e.equals(subject);
    }
    public String toString() {
        return request + " " + done + "/" + needed + ", " + coinReward + " coin(s) and " + expReward + " experience reward.";
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return needed == quest.needed && done == quest.done && coinReward == quest.coinReward && expReward == quest.expReward && Objects.equals(request, quest.request) && Objects.equals(subject, quest.subject);
    }
}