import javax.swing.*;

public class BubbleSort extends JFrame
{
    private Sorting.VisualizerPanel panel;

    public BubbleSort(int[] array)
    {
        setTitle("BubbleSort Visualizer");
        setSize(2880, 1800);
        panel = new BubbleSortPanel(array);
        add(panel);
        setVisible(true);

        new Thread(() -> panel.algorithm(array)).start();
    }

    public static void BUBBLESORT()
    {
        int[] array = {5,-2,0,-4,7,4,-1,3,1};
        new BubbleSort(array);
    }

    public class BubbleSortPanel extends Sorting.VisualizerPanel
    {
        public BubbleSortPanel(int[] array)
        {
            super(array);
        }

        @Override
        public void algorithm(int[] array)
        {
            boolean swap;
            for(int i=0 ; i<array.length-1 ; i++)
            {
                swap = false;
                for(int j=0 ; j<array.length-i-1 ; j++)
                {
                    if(array[j] > array[j+1])
                    {
                        Index1 = j;
                        Index2 = j+1;
                        panel.repaint();

                        int temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                        swap = true;

                        try
                        {
                            Thread.sleep(1000);
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        panel.repaint();
                    }
                }
                if(!swap)
                {
                    break;
                }
            }

            Index1 = -1;
            Index2 = -1;
            panel.repaint();
        }
    }
}