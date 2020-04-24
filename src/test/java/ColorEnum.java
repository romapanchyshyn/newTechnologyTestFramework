public enum ColorEnum {

    RED("ff0000", "RED"),
    BLACK("000000", "BLACK");

private final String colorCode;
private final String colorName;

    ColorEnum(String colorCode, String colorName) {
        this.colorCode = colorCode;
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getColorName() {
        return colorName;
    }
}
