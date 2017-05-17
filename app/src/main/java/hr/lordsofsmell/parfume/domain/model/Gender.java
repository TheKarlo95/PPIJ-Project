package hr.lordsofsmell.parfume.domain.model;

import com.google.gson.annotations.SerializedName;

public enum Gender {

    @SerializedName("Masculine")
    MALE,
    @SerializedName("Feminine")
    FEMALE,
    @SerializedName("Shared / Unisex")
    UNISEX;

    private static final String GENDER_MALE = "Masculine";
    private static final String GENDER_FEMALE = "Feminine";
    private static final String GENDER_UNISEX = "Shared / Unisex";

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
