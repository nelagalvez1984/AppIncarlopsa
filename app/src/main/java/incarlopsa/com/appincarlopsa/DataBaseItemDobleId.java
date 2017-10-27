package incarlopsa.com.appincarlopsa;

public abstract class DataBaseItemDobleId {

    protected Integer id1;
    protected Integer id2;

    public Integer getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public Integer getId2() {
        return id2;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataBaseItemDobleId that = (DataBaseItemDobleId) o;

        if (!id1.equals(that.id1)) return false;
        return id2.equals(that.id2);

    }

    @Override
    public int hashCode() {
        int result = id1.hashCode();
        result = 31 * result + id2.hashCode();
        return result;
    }
}
