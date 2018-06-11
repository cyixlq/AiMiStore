package top.cyixlq.cy.ui.recycler;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int blue();

    public abstract int green();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
