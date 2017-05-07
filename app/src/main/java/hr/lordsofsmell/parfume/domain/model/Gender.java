package hr.lordsofsmell.parfume.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thekarlo95 on 5/6/17.
 */

public enum Gender {

    @SerializedName("M")
    MALE,
    @SerializedName("F")
    FEMALE;

    private static final String GENDER_MALE = "M";
    private static final String GENDER_FEMALE = "F";

    public static Gender fromString(String str) {
        switch (str) {
            case GENDER_MALE:
                return MALE;
            case GENDER_FEMALE:
                return FEMALE;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case MALE:
                return GENDER_MALE;
            case FEMALE:
                return GENDER_FEMALE;
            default:
                return null;
        }
    }
}
