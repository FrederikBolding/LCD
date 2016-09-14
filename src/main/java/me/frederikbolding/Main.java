package me.frederikbolding;

public class Main {

    static final int BUS_NO = 1;
    static final int BUS_ADDRESS = 0x20;

    public static void main(String[] args) {
        try {
            System.out.println("Setting up LCD with BUSNO " + BUS_NO + " and ADDRESS " + BUS_ADDRESS);
            final LCD lcd = new LCD(BUS_NO, BUS_ADDRESS);
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    lcd.shutdown();
                }
            });
            lcd.setLedColor(LCD.LED_OFF);
            lcd.setBacklight(true);
            lcd.write(0, "Running Java!");
            lcd.write(1, "Woop woop!");
            while (true) {
                for (Button button : Button.values()) {
                    if (lcd.getButtonPressed(button)) {
                        lcd.clear();
                        lcd.write(button.toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String byteToString(byte b) {
        byte[] masks = {-128, 64, 32, 16, 8, 4, 2, 1};
        StringBuilder builder = new StringBuilder();
        for (byte m : masks) {
            if ((b & m) == m) {
                builder.append('1');
            } else {
                builder.append('0');
            }
        }
        return builder.toString();
    }
}
