package be.yfrickx.app.day2;

import java.util.Arrays;

public class CubeSet {
    private int amountBlue = 0;
    private int amountGreen = 0;
    private int amountRed = 0;


    // Example set
    // 5 blue, 4 red, 13 green
    public CubeSet(String stringSet) {
        String cleaned = stringSet.trim();
        String[] colors = cleaned.split(",");
        Arrays.stream(colors).forEach(color -> {
            String cleanedColor = color.trim();
            int amount = Integer.parseInt(cleanedColor.split(" ")[0]);
            String colorName = cleanedColor.split(" ")[1];
            setColorAmount(amount, colorName);
        });
    }

    private void setColorAmount(int amount, String name) {
        switch (name) {
            case "blue":
                amountBlue = amount;
                break;
            case "green":
                amountGreen = amount;
                break;
            case "red":
                amountRed = amount;
                break;
        }
    }

    public boolean isPossible(int red, int green, int blue) {
        return (amountBlue <= blue && amountRed <= red && amountGreen <= green);
    }

    public int getAmountBlue() {
        return amountBlue;
    }

    public int getAmountGreen() {
        return amountGreen;
    }

    public int getAmountRed() {
        return amountRed;
    }
}
