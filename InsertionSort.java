import javax.swing.*;

public class InsertionSort extends JFrame
{
    private Sorting.VisualizerPanel panel;

    public InsertionSort(int[] array)
    {
        setTitle("InsertionSort Visualizer");
        setSize(2880, 1800);
        panel = new InsertionSortPanel(array);
        add(panel);
        setVisible(true);

        new Thread(() -> panel.algorithm(array)).start();
    }

    public static void INSERTIONSORT()
    {
        int[] array = {5,-2,0,-4,7,4,-1,3,1};
        new InsertionSort(array);
    }

    public class InsertionSortPanel extends Sorting.VisualizerPanel
    {
        public InsertionSortPanel(int[] array)
        {
            super(array);
        }

        @Override
        public void algorithm(int[] array)
        {
            int temp, j=0;
            for(int i=1 ; i<array.length ; i++)
            {
                temp = array[i];
                for(j=i-1 ; j>=0 && array[j] > temp ; j--)
                {
                    array[j+1] = array[j];
                    Index1 = j;
                    Index2 = j+1;
                    panel.repaint();

                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    panel.repaint();
                }
                array[j+1] = temp;

                Index1 = -1;
                Index2 = -1;
                panel.repaint();
            }
        }
    }
}