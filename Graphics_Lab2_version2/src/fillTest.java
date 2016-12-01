//
//  fillTest.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  This file should not be modified by students.
//

import java.awt.*;
import java.awt.event.*;

public class fillTest implements KeyListener {
    
    private simpleCanvas T;
    private Rasterizer R;

    fillTest()
    {
        
        T = new simpleCanvas( 901, 601 );
        R = new Rasterizer( 601 );

        T.setColor( 0.0f, 0.0f, 0.0f );
        T.clear();
        T.setColor( 1.0f, 0.0f, 0.0f );

        int x[] = new int[10];
        int y[] = new int[10];

        
        x[0] = 150; y[0] = 90;
        x[1] = 140; y[1] = 65;
        x[2] = 110; y[2] = 65;
        x[3] = 140; y[3] = 40;
        x[4] = 110; y[4] = 10;
        x[5] = 150; y[5] = 25;
        x[6] = 190; y[6] = 10;
        x[7] = 160; y[7] = 40;
        x[8] = 190; y[8] = 65;
        x[9] = 160; y[9] = 65;
        R.drawPolygon(10, x, y, T);
//        
//        // ########### TEAPOT START ###########
//        // BASE
//        x[0] = 760;  y[0] = 40;
//        x[1] = 600;  y[1] = 40;
//        x[2] = 620;  y[2] = 60;
//        x[3] = 740;  y[3] = 60;
//        R.drawPolygon( 4, x, y, T );
//    
//        // RIGHT BOTTOM TRIANGLE
//        x[0] = 800;  y[0] = 120;
//        x[1] = 740;  y[1] = 60;
//        x[2] = 620;  y[2] = 60;
//        T.setColor( 0.90f, 0.0f, 0.0f );
//        R.drawPolygon( 3, x, y, T );
//    
//        // SPOUT
//        x[0] = 620;  y[0] = 60;
//        x[1] = 560;  y[1] = 100;
//        x[2] = 500;  y[2] = 180;
//        T.setColor( 0.80f, 0.0f, 0.0f );
//        R.drawPolygon( 3, x, y, T );
//    
//        x[0] = 620;  y[0] = 60;
//        x[1] = 500;  y[1] = 180;
//        x[2] = 460;  y[2] = 200;
//        x[3] = 520;  y[3] = 200;
//        x[4] = 580;  y[4] = 160;
//        T.setColor( 0.7f, 0.0f, 0.0f );
//        R.drawPolygon( 5, x, y, T );
//    
//        x[0] = 620;  y[0] = 60;
//        x[1] = 580;  y[1] = 160;
//        x[2] = 620;  y[2] = 240;
//        x[3] = 740;  y[3] = 240;
//        x[4] = 800;  y[4] = 120;
//        T.setColor( 0.6f, 0.0f, 0.0f );
//        R.drawPolygon( 5, x, y, T );
//    
//        x[0] = 800;  y[0] = 120;
//        x[1] = 840;  y[1] = 160;
//        x[2] = 855;  y[2] = 200;
//        x[3] = 720;  y[3] = 220;
//        x[4] = 720;  y[4] = 200;
//        x[5] = 830;  y[5] = 190;
//        x[6] = 825;  y[6] = 165;
//        x[7] = 780;  y[7] = 120;
//        T.setColor( 0.5f, 0.0f, 0.0f );
//        R.drawPolygon( 8, x, y, T );
//    
//        x[0] = 690;  y[0] = 240;
//        x[1] = 710;  y[1] = 260;
//        x[2] = 650;  y[2] = 260;
//        x[3] = 670;  y[3] = 240;
//        T.setColor( 0.4f, 0.0f, 0.0f );
//        R.drawPolygon( 4, x, y, T );
//    
//        // ######## TRIANGLE #######
//        x[0] = 460;  y[0] = 220;
//        x[1] = 490;  y[1] = 280;
//        x[2] = 420;  y[2] = 280;
//        T.setColor( 0.0f, 1.0f, 0.0f );
//        R.drawPolygon( 3, x, y, T );
//    
//        // ########## QUAD ##########
//        x[0] = 380;  y[0] = 280;
//        x[1] = 320;  y[1] = 320;
//        x[2] = 360;  y[2] = 380;
//        x[3] = 420;  y[3] = 340;
//        T.setColor( 0.0f, 0.8f, 0.8f );
//        R.drawPolygon( 4, x, y, T );
//    
//        // ############ STAR #############
//        // RIGHT SIDE
//        x[0] = 230;  y[0] = 389;
//        x[1] = 260;  y[1] = 369;
//        x[2] = 254;  y[2] = 402;
//        x[3] = 278;  y[3] = 425;
//        x[4] = 245;  y[4] = 430;
//        x[5] = 230;  y[5] = 460;
//        x[6] = 230;  y[6] = 410;
//        T.setColor( 0.8f, 0.8f, 0.0f );
//        R.drawPolygon( 7, x, y, T );
//    
//        // LEFT SIDE
//        x[0] = 230;  y[0] = 460;
//        x[1] = 216;  y[1] = 430;
//        x[2] = 183;  y[2] = 425;
//        x[3] = 207;  y[3] = 402;
//        x[4] = 201;  y[4] = 369;
//        x[5] = 230;  y[5] = 389;
//        x[6] = 230;  y[6] = 410;
//        T.setColor( 0.7f, 0.7f, 0.0f );
//        R.drawPolygon( 7, x, y, T );
//    
//        // ########## BORDERS ###############
//        // SQUARE BOTTOM LEFT
//        x[0] = 0;    y[0] = 0;
//        x[1] = 0;    y[1] = 20;
//        x[2] = 20;   y[2] = 20;
//        x[3] = 20;   y[3] = 0;
//        T.setColor( 0.0f, 0.0f, 1.0f );
//        R.drawPolygon( 4, x, y, T );
//    
//        x[0] = 0;    y[0] = 10;
//        x[1] = 10;   y[1] = 10;
//        x[2] = 10;   y[2] = 580;
//        x[3] = 0;    y[3] = 580;
//        T.setColor( 0.0f, 0.1f, 0.9f );
//        R.drawPolygon( 4, x, y, T );
//    
//        x[0] = 0;    y[0] = 580;
//        x[1] = 0;    y[1] = 600;
//        x[2] = 20;   y[2] = 600;
//        x[3] = 20;   y[3] = 580;
//        T.setColor( 0.0f, 0.2f, 0.8f );
//        R.drawPolygon( 4, x, y, T );
//    
//        //  TRIANGLE TOP:TOP
//        x[0] = 10;   y[0] = 590;
//        x[1] = 10;   y[1] = 600;
//        x[2] = 880;  y[2] = 600;
//        T.setColor( 0.0f, 0.3f, 0.7f );
//        R.drawPolygon( 3, x, y, T );
//    
//        //  TRIANGLE TOP:BOTTOM
//        x[0] = 10;   y[0] = 590;
//        x[1] = 880;  y[1] = 590;
//        x[2] = 880;  y[2] = 600;
//        T.setColor( 0.0f, 0.4f, 0.6f );
//        R.drawPolygon( 3, x, y, T );
//    
//        // SQUARE TOP RIGHT
//        x[0] = 900;  y[0] = 600;
//        x[1] = 900;  y[1] = 580;
//        x[2] = 880;  y[2] = 580;
//        x[3] = 880;  y[3] = 600;
//        T.setColor( 0.1f, 0.4f, 0.5f );
//        R.drawPolygon( 4, x, y, T );
//    
//        //  TRIANGLE RIGHT:RIGHT
//        x[0] = 890;  y[0] = 580;
//        x[1] = 900;  y[1] = 580;
//        x[2] = 890;  y[2] = 20;
//        T.setColor( 0.2f, 0.4f, 0.4f );
//        R.drawPolygon( 3, x, y, T );
//    
//        //  TRIANGLE RIGHT:LEFT
//        x[0] = 900;  y[0] = 580;
//        x[1] = 900;  y[1] = 20;
//        x[2] = 890;  y[2] = 20;
//        T.setColor( 0.3f, 0.4f, 0.3f );
//        R.drawPolygon( 3, x, y, T );
//    
//        // SQUARE BOTTOM RIGHT
//        x[0] = 900;  y[0] = 0;
//        x[1] = 900;  y[1] = 20;
//        x[2] = 880;  y[2] = 20;
//        x[3] = 880;  y[3] = 0;
//        T.setColor( 0.4f, 0.4f, 0.2f );
//        R.drawPolygon( 4, x, y, T );
//    
//        // QUAD BOTTOM
//        x[0] = 20;   y[0] = 0;
//        x[1] = 20;   y[1] = 10;
//        x[2] = 880;  y[2] = 10;
//        x[3] = 880;  y[3] = 0;
//        T.setColor( 0.4f, 0.5f, 0.1f );
//        R.drawPolygon( 4, x, y, T );
//
    }

    // Because we are a KeyListener
    public void keyTyped(KeyEvent e)
    {
        // What key did we type?
        char key = e.getKeyChar();

        if( (key == 'Q') || (key == 'q') ) System.exit(0); // quit

    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    static public void main(String[] args)
    {
        fillTest F = new fillTest();
	F.T.addKeyListener( F );

        Frame f = new Frame( "Poly Fill Test" );
        f.add("Center", F.T);
        f.pack();
        f.setResizable (false);
        f.setVisible(true);
        
        f.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        
    }

}
