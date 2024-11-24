import javax.swing.*;
import java.awt.*;

public interface Sorting
{
    class VisualizerPanel extends JPanel
    {
        private int[] array;
        int Index1 = -1;
        int Index2 = -1;

        public VisualizerPanel(int[] array)
        {
            this.array = array;
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            int width = 100;
            int height = 50;
            int x = 50;
            int y = 150;

            for (int i = 0; i < array.length; i++)
            {
                if(i == Index1 || i == Index2)
                {
                    g.setColor(Color.RED);
                }
                else
                {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(x + i * width, y, width, height);
                g.setColor(Color.BLACK);
                g.drawRect(x + i * width, y, width, height);

                g.setFont(new Font("Arial", Font.BOLD, 24));
                FontMetrics metrics = g.getFontMetrics();
                int textX = x + i * width + (width - metrics.stringWidth(String.valueOf(array[i]))) / 2;
                int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
                g.drawString(String.valueOf(array[i]), textX, textY);
            }
        }

        public void algorithm(int[] array)
        {

        }
    }
}
