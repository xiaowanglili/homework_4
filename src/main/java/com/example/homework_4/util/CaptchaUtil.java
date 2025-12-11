
package com.example.homework_4.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;

public class CaptchaUtil {
    private static final String CHARACTERS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int IMG_WIDTH = 100;
    private static final int IMG_HEIGHT = 30;

    public static String generateText() {
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            captcha.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return captcha.toString();
    }

    public static BufferedImage generateImage(String text) {
        BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = img.createGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);

        drawNoise(graphics);
        drawText(graphics, text);

        graphics.dispose();
        return img;
    }

    private static void drawNoise(Graphics2D g) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
        for (int i = 0; i < 5; i++) {
            g.setColor(new Color(RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255)));
            g.drawLine(RANDOM.nextInt(IMG_WIDTH), RANDOM.nextInt(IMG_HEIGHT),
                    RANDOM.nextInt(IMG_WIDTH), RANDOM.nextInt(IMG_HEIGHT));
        }
    }

    private static void drawText(Graphics2D g, String text) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        for (int i = 0; i < text.length(); i++) {
            g.setColor(new Color(RANDOM.nextInt(100), RANDOM.nextInt(100), RANDOM.nextInt(100)));
            g.drawString(String.valueOf(text.charAt(i)), 20 * i + 15, 22);
        }
    }
}
