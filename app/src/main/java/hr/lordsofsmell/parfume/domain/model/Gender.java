package hr.lordsofsmell.parfume.domain.model;

import com.google.gson.annotations.SerializedName;

public enum Gender {

    @SerializedName("Masculine")
    MALE,
    @SerializedName("Feminine")
    FEMALE,
    @SerializedName("Shared / Unisex")
    UNISEX;

    private static final String GENDER_MALE = "M";
    private static final String GENDER_FEMALE = "F";
    private static final String GENDER_UNISEX = "U";

    private static final String GENDER_FULL_MALE = "male";
    private static final String GENDER_FULL_FEMALE = "female";
    private static final String GENDER_FULL_UNISEX = "unisex";

    public static Gender fromString(String str) {
        switch (str) {
            case GENDER_MALE:
                return MALE;
            case GENDER_FEMALE:
                return FEMALE;
            case GENDER_UNISEX:
                return UNISEX;
            default:
                return null;
        }
    }

    public String toFullGenderName() {
        switch (this) {
            case MALE:
                return GENDER_FULL_MALE;
            case FEMALE:
                return GENDER_FULL_FEMALE;
            case UNISEX:
                return GENDER_FULL_UNISEX;
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
            case UNISEX:
                return GENDER_UNISEX;
            default:
                return null;
        }
    }
}
