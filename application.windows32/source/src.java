import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class src extends PApplet {

PImage hoop;
PImage wallpaper;
PFont scorefont;
int missed;
int score;
ball[] balls = new ball[10];

public void setup() {
  
  score = 0;
  missed = 0;
  hoop = loadImage("../imgs/hoop.png");
  hoop.resize(250, 250);
  wallpaper = loadImage("../imgs/wallpaper.jpg");
  imageMode(CENTER);
  //scorefont = loadFont("super-webcomic-bros.bold.ttf");
  for (int i = 0; i < balls.length; i++) {
    balls[i] = new ball();
  }
}

public void draw() {
  background(wallpaper);
  //textFont(scorefont);
  fill(255, 176, 30);
  textSize(20);
  text("Score: " + score, width - 100, 30);
  text("Misses: " + missed, width - 100, 50);
  image(hoop, mouseX, height - 150);
  if (missed < 5) {
    for (ball basketball : balls) {
      basketball.fall();
      if (dist(basketball.x, basketball.y, mouseX, 425) <= 50) {
        if (basketball.keepScoring) {
          score++;
          basketball.keepScoring = false;
        }
        basketball.visible = false;
      } else {
        if (basketball.visible) {
          basketball.show();
        }
      }
      if (basketball.y >= 530) {
        if (!basketball.alreadyMissed && basketball.visible) {
          missed++;
          basketball.alreadyMissed = true;
        }
      }
  
    }
  } else {
    textSize(50);
    textAlign(CENTER);
    text("Game Over... Reload to play again", width/2, height/2 - 30);
  }
  
}


class ball {
  PImage ballimg = loadImage("../imgs/ball.png");
  PImage hoopimg = loadImage("../imgs/hoop.png");

  float x=random(width);
  float y=random(-3000, -100);
  float speed=random(1, 5);
  
  boolean visible = true;
  boolean keepScoring = true;
  boolean alreadyMissed = false;
  
  public void fall() {
    y+=speed;
  }
  
  public void show() {
    ballimg.resize(75, 75);
    image(ballimg, x, y);
  }
  
}
  public void settings() {  size (1000, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "src" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
