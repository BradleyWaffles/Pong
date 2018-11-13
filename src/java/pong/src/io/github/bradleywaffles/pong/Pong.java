package io.github.bradleywaffles.pong;

import processing.core.PApplet;
import java.util.Random;

public class Pong extends PApplet{
    public static void main(String[] args) {
        PApplet.main(Pong.class.getCanonicalName());
    }
    Random rand = new Random();

    int width = 25;
    int height = 100;
    int x1 = 0;
    int y1 = 200;
    int x2 = 975;
    int y2 = 200;
    int x3 = 475;
    int y3 = 225;
    int speedX = 3;
    int speedY = 3;
    int c1 = 200;
    int c2 = 50;
    int c3 = 255;

    int startSpeed = rand.nextInt(1);

    public void settings() {
        size(1000, 500, "processing.awt.PGraphicsJava2D");
    }
    public void setup() {
        if (startSpeed == 0) {
            speedY *= -1;
            speedX *= -1;
        }
    }

    int seizureCount = 0;

    public void draw() {
        background(c1,c2,c3);

        charcter1(x1,y1,width,height);
        charcter2(x2,y2,width,height);
        ball(x3,y3,25,25);

        movement();
        if (seizureCount < 100) {
            seizureCount++;
            fuckingSeizure();
        }

        if (collision() && seizureCount >= 100) {
            seizureCount = 0;
        }

        if (gameOver()) {
            stop();
            delay(2000);
            exit();
        }


    }

    public void movement() {
        x3 += speedX;
        y3 += speedY;

        if (y3 > 475) {
            speedY *= -1;
        }
        if(y3 < 0) {
            speedY *= -1;
        }
        if (y1 > 400) {
            y1 = 400;
        }
        if(y1 < 0) {
            y1 = 0;
        }
        if (y2 > 400) {
            y2 = 400;
        }
        if(y2 < 0) {
            y2 = 0;
        }

        if (x3 > 975) {
            speedX *= -1;
        }
        if(x3 < 0) {
            speedX *= -1;
        }
    }

        public boolean collision() {
            if (((x3+25) >= x2) && (((y3+24) >= (y2)) && ((y3) <= (y2+100)))) {
                speedX *= -1;
                if (speedX < 0) {
                    speedX -= 1;
                    if (speedX < -24) {
                        speedX +=1;
                    }
                }
                if (speedX > 0) {
                    speedX += 1;
                    if (speedX > 24) {
                        speedX -=1;
                    }
                }
                return true;
            }
            if ((x3 <= (x1+width)) && (((y3+24) >= (y1)) && ((y3) <= (y1+100)))) {
                speedX *= -1;
                if (speedY < 0) {
                    speedY -= 1;
                    if (speedY < -24) {
                        speedY +=1;
                    }
                }
                if (speedY > 0) {
                    speedY += 1;
                    if (speedY > 24) {
                        speedY -=1;
                    }
                }
                return true;
            }
            return false;
        }
        public void fuckingSeizure() {
            seizureCount++;
            c1 = rand.nextInt(254) + 1;
            c2 = rand.nextInt(254) + 1;
            c3 = rand.nextInt(254) + 1;
        }

        public boolean gameOver() {
            if ((x3+26) >= 1000 || x3 <= 0){
                return true;
            }
            return false;
        }

    public void charcter1(int x, int y, int width, int height) {
        fill(255,0,0);
        rect(x,y,width,height);
    }
    public void charcter2(int x,int y,int width, int height) {
        fill(0,0,255);
        rect(x,y,width,height);
    }
    public void ball(int x, int y, int width, int height) {
        fill(0,255,0);
        rect(x,y,width,height);
    }


    public void keyPressed() {
        if (keyPressed) {
            if (keyCode == UP) {
                y2-=25;
            }
            if (keyCode == DOWN) {
                y2+=25;
            }
            if (key == 'w') {
                y1-=25;
            }
            if (key == 's') {
                y1+=25;
            }
        }
    }

}
