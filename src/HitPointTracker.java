public class HitPointTracker {
    private int hitPointMax = 0;

    public HitPointTracker(int hitPointMax) {
        this.hitPointMax = hitPointMax;
    }

    public void setHitPointMax(int hitPointMax) {
        this.hitPointMax = hitPointMax;
    }

    public int getHitPointMax() {
        return hitPointMax;
    }

    public void subtractHitPoint(int hitPoint) {
        this.hitPointMax -= hitPoint;
    }

    public void addHitPoint(int hitPoint) {
        this.hitPointMax += hitPoint;
    }
}
