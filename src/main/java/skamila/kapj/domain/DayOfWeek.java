package skamila.kapj.domain;

public enum DayOfWeek {

    MONDAY("dayOfWeek.mon"),
    TUESDAY("dayOfWeek.tue"),
    WEDNESDAY("dayOfWeek.wed"),
    THURSDAY("dayOfWeek.thu"),
    FRIDAY("dayOfWeek.fri"),
    SATURDAY("dayOfWeek.sat"),
    SUNDAY("dayOfWeek.sun");

    public final String translation;

    DayOfWeek(String translation) {
        this.translation = translation;
    }

}
