import javax.swing.*;

public class SelectionSort extends JFrame
{
    private Sorting.VisualizerPanel panel;

    public SelectionSort(int[] array)
    {
        setTitle("SelectionSort Visualizer");
        setSize(2880, 1800);
        panel = new SelectionSortPanel(array);
        add(panel);
        setVisible(true);

        new Thread(() -> panel.algorithm(array)).start();
    }

    public static void SELECTIONSORT()
    {
        int[] array = {5,-2,0,-4,7,4,-1,3,1};
        new SelectionSort(array);
    }

    public class SelectionSortPanel extends Sorting.VisualizerPanel
    {
        public SelectionSortPanel(int[] array)
        {
            super(array);
        }

        @Override
        public void algorithm(int[] array)
        {
            for(int i = 0; i < array.length - 1; i++)
            {
                int minIndex = i;
                for (int j = i + 1; j < array.length; j++)
                {
                    Index1 = minIndex;
                    Index2 = j;
                    panel.repaint();

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    if(array[j] < array[minIndex])
                    {
                        minIndex = j;
                    }
                }

                if(minIndex != i)
                {
                    int temp = array[minIndex];
                    array[minIndex] = array[i];
                    array[i] = temp;

                    Index1 = i;
                    Index2 = minIndex;
                    panel.repaint();

                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            Index1 = -1;
            Index2 = -1;
            panel.repaint();
        }
    }
}