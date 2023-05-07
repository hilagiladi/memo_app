import java.io.Serializable;
import java.util.Objects;

public class Date implements Serializable {
    private int day, month, year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date date) {
            if (this == obj)
                return true;
            if (getClass() != obj.getClass())
                return false;
            return day == date.day && month == date.month && year == date.year;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
