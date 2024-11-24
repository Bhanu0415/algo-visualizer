import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

class QuickSortVisualizer extends JPanel {
    private int[] array;
    private int swapIndex1 = -1;
    private int swapIndex2 = -1;

    public QuickSortVisualizer(int[] array) {
        this.array = array;
        setPreferredSize(new Dimension(600, 400));
    }

    public void highlightSwap(int index1, int index2) {
        swapIndex1 = index1;
        swapIndex2 = index2;
        repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int barWidth = getWidth() / array.length;
        for (int i = 0; i < array.length; i++) {
            if (i == swapIndex1 || i == swapIndex2) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLUE);
            }
            int height = array[i] * 10 + 200;
            g.fillRect(i * barWidth, getHeight() - height, barWidth - 2, height);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial",Font.BOLD,24));
            g.drawString(String.valueOf(array[i]), i*barWidth+(barWidth-2)/2,getHeight() - height +(height)/2);
        }
    }

    public void updateArray(int[] newArray) {
        this.array = Arrays.copyOf(newArray, newArray.length);
        repaint();
    }
}

class QuickSort {
    private QuickSortVisualizer visualizer;

    public QuickSort(int[] array, QuickSortVisualizer visualizer) {
        this.visualizer = visualizer;
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        visualizer.highlightSwap(i, j);
        visualizer.updateArray(array);
    }
}

public class QuickSortVisualize {
    public static void QUICKSORT() {
        int[] array = {5, -2, 0, -4, 7, 4,-1,3,-3,2};

        QuickSortVisualizer visualizer = new QuickSortVisualizer(array);
        JFrame frame = new JFrame("QuickSort Visualization");
        frame.add(visualizer);
        frame.pack();
        frame.setVisible(true);

        new Thread(() -> new QuickSort(array, visualizer)).start();
    }
}
