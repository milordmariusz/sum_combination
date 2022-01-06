import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    static int number = 0;
    static String combination = "";
    static StringBuilder sb = new StringBuilder(); //easter egg

    static void findCombinationsFinal(int arr[], int index, int num, int reduce_num){
        if (reduce_num < 0){
            return;
        }

        if (reduce_num == 0){
            for (int i = 0; i < index; i++){
                sb.append(arr[i]);
                sb.append(" ");
            }
            sb.append('\n');
            number += 1;
            return;
        }

        int prev = 0;

        if (index == 0){
            prev = 1;
        }
        else{
            prev = arr[index - 1];
        }

        for (int j = prev; j <= num; j++){
            arr[index] = j;
            findCombinationsFinal(arr, index + 1, num, reduce_num - j);
        }
    }

    static void findCombinations(int n){
        int[] arr = new int [n];
        findCombinationsFinal(arr, 0, n, n);
    }

    public static void main (String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        File file = new File("data.txt");
        Scanner scanfile = new Scanner(file);


        int n = 0;
        if (!scanfile.hasNextLine()){
            sb.replace(0,sb.capacity()," "+ "\nBrak Danych");
        }
        while (scanfile.hasNextLine()) {
            try {
                n = scanfile.nextInt();
                sb.append("n: " + n);
                sb.append('\n' + "-------------------------" + '\n');
                findCombinations(n);
                sb.append("-------------------------" + '\n');
                sb.append("Number of combination: " + number);
                sb.append('\n' + "-------------------------" + '\n');
                sb.append(" " + '\n' + '\n' + " " + '\n');
                } catch (Exception e) {
                    sb.replace(0,sb.capacity()," "+ "\nWystąpił błąd, sprawdź czy w pliku data.txt\n znajdują się wyłącznie liczby naturalne!");
                    break;
                }
        }
        JPanel panel = new JPanel(new BorderLayout());
        ArrayList<String> myList = new ArrayList<>(10);
        Scanner scan2 = new Scanner(sb.toString());
        while(scan2.hasNextLine()){
            myList.add(scan2.nextLine());
        }
        final JList<String> list = new JList(myList.toArray());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)list.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        list.setBackground(Color.cyan);
        panel.add(scrollPane);
        JFrame frame = new JFrame("Finding Combinations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
