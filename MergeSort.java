import java.awt.*;
import javax.swing.*;

public class MergeSort extends JFrame {
    private int[] array = {8, 7, 6, 5, 4, 3, 2, 1};
    private int[] array1 = {8, 7, 6, 5, 4, 3, 2, 1},array2 = {7,8,5,6,3,4,1,2},array3 = {5,6,7,8,1,2,3,4},array4 = {1,2,3,4,5,6,7,8};
    private int dividelevels = 1;
    private int conquerlevels = array.length;
    private int showlines = 0;
    private Timer timer1,timer,swap;

    public MergeSort() {
        setTitle("Array Representation");
        setSize(2880, 1800);

        timer = new Timer(2000, e -> {

            if (dividelevels < array1.length) {
                repaint();
                dividelevels = 2* dividelevels;
            } else if (dividelevels == array1.length && conquerlevels >=1) {
                repaint();
                conquerlevels = conquerlevels /2;
            } else{
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
        timer1 = new Timer(1000,e->
        {
            if(showlines == 0) {
                repaint();
                showlines++;
            }
            else if(showlines < 14)
            {
                showlines++;
                repaint();
            }
            else{
                ((Timer) e.getSource()).stop();
            }
        });
        timer1.start();
    }

    public void paint(Graphics g) {
        super.paint(g);

        int arr_x = getWidth()/2 - 160;// Starting x-coordinate for the first element
        int temp_x = 0;
        int arr_y = 50; // y-coordinate for the rectangles
        int temp_y = 1;
        int width = 40; // Width of each rectangle
        int height = 30; // Height of each rectangle
        int padding = 40; // Space between rectangles
        int j = 1;
        int p = 0;
        // Draw each element in the array
        for(j = 1; j <= dividelevels; j = j*2) {
            temp_x = (int)(arr_x - ((Math.pow(2,p)-1) * 20));
            p++;
            if(j == 1) {
                //temp_x = arr_x;
                temp_y = arr_y;
            }
            else {
                temp_y = temp_y + 50 + height;
            }
            for (int i = 0; i < array.length; i++) {
                g.setColor(Color.lightGray);
                g.fillRect(temp_x, temp_y, width, height); // Draw the rectangle
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(array1[i]), temp_x + 15, temp_y + 20); // Draw the number
                temp_x += width; // Update x-coordinate for the next element
                if((i+1)%(array.length/j) == 0) {
                    temp_x += padding;
                }
            }
        }
        int wid = getWidth()/2;
        if(showlines >=1)
        {
            g.drawLine(wid,80,wid - 100,130);
            g.drawLine(wid,80,wid + 100,130);
            if (showlines >= 3) {
                g.drawLine(wid-100, 160, wid - 180, 210);
                g.drawLine(wid-100, 160, wid - 60, 210);
                g.drawLine(wid+100, 160, wid + 180, 210);
                g.drawLine(wid+100, 160, wid + 60, 210);
                if(showlines >=5)
                {
                    g.drawLine(wid - 180, 240, wid - 280, 290);
                    g.drawLine(wid - 180, 240, wid - 200, 290);
                    g.drawLine(wid - 60, 240, wid - 120, 290);
                    g.drawLine(wid - 60, 240, wid - 40, 290);
                    g.drawLine(wid + 180, 240, wid + 280, 290);
                    g.drawLine(wid + 180, 240, wid + 200, 290);
                    g.drawLine(wid + 60, 240, wid + 120, 290);
                    g.drawLine(wid + 60, 240, wid + 40, 290);
                    if(showlines >=9)
                    {
                        g.drawLine(wid - 280, 400, wid - 180, 450);
                        g.drawLine(wid - 200, 400, wid - 180, 450);
                        g.drawLine(wid - 120, 400, wid - 60, 450);
                        g.drawLine(wid - 40, 400, wid - 60, 450);
                        g.drawLine(wid + 280, 400, wid + 180, 450);
                        g.drawLine(wid + 200, 400, wid + 180, 450);
                        g.drawLine(wid + 120, 400, wid + 60, 450);
                        g.drawLine(wid + 40, 400, wid + 60, 450);
                        if(showlines >= 11)
                        {
                            g.drawLine(wid-180, 480, wid - 100, 530);
                            g.drawLine(wid-60, 480, wid - 100, 530);
                            g.drawLine(wid+180, 480, wid + 100, 530);
                            g.drawLine(wid+60, 480, wid + 100, 530);
                            if(showlines >= 13)
                            {
                                g.drawLine(wid - 100,560,wid,610);
                                g.drawLine(wid+ 100,560,wid,610);
                            }
                        }
                    }
                }
            }
        }

        if(dividelevels == array.length) {
            j = dividelevels;
            int ind = 0;
            for(j = array.length; j > conquerlevels; j=j/2)
            {
                if(ind == 0)
                {
                    for(int in =0;in < array.length;in++)
                    {
                        array[in] = array1[in];
                    }
                    ind++;
                } else if (ind == 1) {
                    for(int in =0;in < array.length;in++)
                    {
                        array[in] = array2[in];
                    }
                    ind++;
                }  else if (ind == 2) {
                    for(int in =0;in < array.length;in++)
                    {
                        array[in] = array3[in];
                    }
                    ind++;
                }
                else if(ind == 3)
                {
                    for(int in =0;in < array.length;in++)
                    {
                        array[in] = array4[in];
                    }
                    ind++;
                }
                //temp_x = arr_x;
                p--;
                temp_x = (int)(arr_x - ((Math.pow(2,p)-1) * 20));
                temp_y = temp_y + 50 + height;
                for (int i = 0; i < array.length; i++) {
                    g.setColor(Color.lightGray);
                    g.fillRect(temp_x, temp_y, width, height); // Draw the rectangle
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(array[i]), temp_x + 15, temp_y + 20);// Draw the number

                    temp_x += width; // Update x-coordinate for the next element
                    if ((i + 1) % (array.length / j) == 0) {
                        temp_x += padding;
                    }
                }
            }
        }
    }

    public static void MERGESORT() {
        MergeSort frame = new MergeSort();
        frame.setVisible(true);
    }
}