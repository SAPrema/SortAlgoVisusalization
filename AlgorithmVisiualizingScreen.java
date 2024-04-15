import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AlgorithmVisiualizingScreen implements ActionListener {
        public static final int WIDTH = 1280;
        public static final int HEIGHT = 720;
        public static final int win_WIDTH = 1000;
        public Color themeColor  = new Color(110,217,161);
        public int arraySize = 20;
        JFrame f;
        JPanel panel, btnPanel;
        JLabel statusText, textboxText, comparisonText, speedText;
        JButton startBtn, generateArrayBtn, bottomBtn;
        SortArray sortarray;
        JComboBox<String> jComboBox;
        JTextField jTextField;
        JLabel speedSlider;
        JPanel legendPanel;
        JLabel legendTitle, legendSwap, legendComparison;


    String[] algorithms = {
                "Bubble sort",
                "Insertion sort",
                "Selection sort",
                "Merge sort",
                "Quick sort",
        };

        public String mainFont = "Times New Roman";

        public AlgorithmVisiualizingScreen(){
            f = new JFrame("Sorting Visualization");
            sortarray = new SortArray(arraySize);

            panel = new JPanel();
            panel.setBounds(0,0, win_WIDTH+10, HEIGHT);
            panel.setBackground(Color.lightGray);
            panel.setLayout(new GridLayout(1,100));
            panel.setVisible(true);

            btnPanel = new JPanel();
            btnPanel.setBounds(win_WIDTH+10,0, WIDTH - 10 - win_WIDTH, HEIGHT);
            btnPanel.setBackground(sortarray.BGColor.darker());
            btnPanel.setLayout(null);
            btnPanel.setVisible(true);


            f.setSize(WIDTH, HEIGHT);
            f.setLayout(null);
            f.setLocationRelativeTo(null);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);
            textboxText = new JLabel("<html>Enter array size<br>(5-300):</html>");
            textboxText.setBounds(40, 30, 180, 60);
            textboxText.setFont(new Font(mainFont, Font.PLAIN, 20));
            textboxText.setForeground(Color.white);

            jTextField = new JTextField(Integer.toString(arraySize));
            jTextField.setBorder(BorderFactory.createLineBorder(Color.white));
            jTextField.setBounds(40, 100, 180, 50);
            jTextField.setBackground(new Color(102,102,102));
            jTextField.setForeground(Color.white);
            jTextField.setFont(new Font(mainFont, Font.BOLD, 20));
            jTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        int a = Integer.parseInt(jTextField.getText());

                        if (Integer.parseInt(jTextField.getText())>=5 && Integer.parseInt(jTextField.getText())<=300){
                            arraySize = Integer.parseInt(jTextField.getText());
                            System.out.println(arraySize);
                            startBtn.setEnabled(true);
                            statusText.setText("Press Generate");
                            comparisonText.setText("");
                        }else{
                            throw new NumberFormatException("Invalid number");
                        }
                    }catch (NumberFormatException ne){
                        System.out.println(ne);
                        startBtn.setEnabled(false);
                        statusText.setText("Invalid size format");
                        comparisonText.setText("");
                    }
                }
            });

            generateArrayBtn = new JButton("Generate");
            generateArrayBtn.setBounds(40, 170, 180,50);
            generateArrayBtn.addActionListener(this);
            generateArrayBtn.setBackground(themeColor);
            generateArrayBtn.setFont(new Font(mainFont, Font.BOLD, 18));
            generateArrayBtn.setForeground(Color.white);
            generateArrayBtn.setFocusable(false);
            generateArrayBtn.setBorder(null);
            generateArrayBtn.setVisible(true);
            generateArrayBtn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (generateArrayBtn.isEnabled())
                        generateArrayBtn.setBackground(themeColor.darker());
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    generateArrayBtn.setBackground(themeColor);
                }
            });

            startBtn = new JButton("Start");
            startBtn.setBounds(40, 240, 180,50);
            startBtn.addActionListener(this);
            startBtn.setBackground(themeColor);
            startBtn.setFont(new Font(mainFont, Font.BOLD, 18));
            startBtn.setForeground(Color.white);
            startBtn.setFocusable(false);
            startBtn.setBorder(null);
            startBtn.setVisible(true);
            startBtn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (startBtn.isEnabled())
                        startBtn.setBackground(themeColor.darker());
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    startBtn.setBackground(themeColor);
                }
            });

            jComboBox = new JComboBox<String>(algorithms);
            jComboBox.setBounds(40, 305, 180, 50);
            jComboBox.setFont(new Font(mainFont, Font.BOLD, 15));
            jComboBox.setBackground(themeColor);
            jComboBox.setForeground(Color.white);
            jComboBox.setRenderer(new MyListCellRenderer(themeColor));
            jComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    speedText.setText( "Speed: " + sortarray.animSpeed + " ms");
                }
            });
            jComboBox.setSelectedIndex(0);
            jComboBox.setVisible(true);
            speedSlider = new JLabel ();
            speedSlider.setBounds(40, 350, 180, 150);
            speedSlider.setFont(new Font(mainFont, Font.PLAIN, 13));
            speedSlider.setForeground(Color.white);

            speedText = new JLabel( "Speed: " + sortarray.animSpeed + " ms");
            speedText.setBounds(40, 480, 180, 50);
            speedText.setFont(new Font(mainFont, Font.PLAIN, 18));
            speedText.setForeground(Color.white);

            statusText = new JLabel("Unsorted");
            statusText.setBounds(40, 520, 180, 50);
            statusText.setFont(new Font(mainFont, Font.BOLD, 20));
            statusText.setForeground(Color.white);
            comparisonText = new JLabel("");
            comparisonText.setBounds(40, 550, 180, 50);
            comparisonText.setFont(new Font(mainFont, Font.PLAIN, 18));
            comparisonText.setForeground(Color.white);
            bottomBtn = new JButton("Back");
            bottomBtn.setBounds(40, 620, 180,50);
            bottomBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new SortingAlgorithms();
                    System.out.println("Came here");
                }
            });
            bottomBtn.setBackground(Color.darkGray.darker());
            bottomBtn.setFont(new Font(mainFont, Font.BOLD, 18));
            bottomBtn.setForeground(Color.white);
            bottomBtn.setFocusable(false);
            bottomBtn.setBorder(null);
            bottomBtn.setVisible(true);
            bottomBtn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (bottomBtn.isEnabled())
                        bottomBtn.setBackground(Color.darkGray.darker().darker());
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    bottomBtn.setBackground(Color.darkGray.darker());
                }
            });
            legendPanel = new JPanel();
            legendPanel.setBounds(40, 580, 180, 50);
            legendPanel.setBackground(Color.darkGray.darker());
            legendPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
            legendPanel.setVisible(true);

            //legendTitle = new JLabel("Legend:");
          //  legendTitle.setForeground(Color.white);
          //
            //   legendTitle.setFont(new Font(mainFont, Font.PLAIN, 15));

            legendSwap = new JLabel("Swap", new ColorIcon(Color.red), SwingConstants.LEFT);
            legendSwap.setForeground(Color.white);
            legendSwap.setFont(new Font(mainFont, Font.PLAIN, 15));

            legendComparison = new JLabel("Comparison", new ColorIcon(Color.blue), SwingConstants.LEFT);
            legendComparison.setForeground(Color.white);
            legendComparison.setFont(new Font(mainFont, Font.PLAIN, 15));

            //legendPanel.add(legendTitle);
            legendPanel.add(legendSwap);
            legendPanel.add(legendComparison);

            btnPanel.add(legendPanel);


            btnPanel.add(jTextField);
            btnPanel.add(generateArrayBtn);
            btnPanel.add(startBtn);
            btnPanel.add(jComboBox);
            btnPanel.add(statusText);
            btnPanel.add(textboxText);
            btnPanel.add(comparisonText);
            btnPanel.add(speedSlider);
            btnPanel.add(speedText);
            btnPanel.add(bottomBtn);

            panel.add(sortarray);
            f.add(btnPanel);
            f.add(panel);
            f.setVisible(true);

        }
        public static void main(String[] args) {
            AlgorithmVisiualizingScreen avs = new AlgorithmVisiualizingScreen();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                public Void doInBackground() {
                    generateArrayBtn.setEnabled(false);
                    jTextField.setEnabled(false);
                    speedText.setText( "Speed: " + sortarray.animSpeed + " ms");
                    if (e.getSource() == startBtn){

                        if (sortarray.isSorting){
                            startBtn.setText("Start");
                            statusText.setText("Stopped");
                            sortarray.isSorting = false;
                            return null;
                        }else{
                            System.out.println("Sort started");
                            statusText.setText("Sorting...");
                            startBtn.setText("Stop");

                            if (jComboBox.getSelectedItem() == "Bubble sort"){
                                new SortingAlgorithms().bubbleSort(sortarray);
                                speedSlider.setText("<html>Algorithm: Bubble Sort\nComplexity: O(n^2)</html>");
                            }else if (jComboBox.getSelectedItem() == "Insertion sort"){
                                new SortingAlgorithms().insertionSort(sortarray);
                                speedSlider.setText("<html>Algorithm: Insertion Sort\nComplexity: O(n^2)</html>");
                            }else if (jComboBox.getSelectedItem() == "Selection sort"){
                                new SortingAlgorithms().selectionSort(sortarray);
                                speedSlider.setText("<html>Algorithm: Selection Sort\nComplexity: O(n^2)</html>");
                            }else if (jComboBox.getSelectedItem() == "Merge sort"){
                                new SortingAlgorithms().sortdeMerge(sortarray);
                                speedSlider.setText("<html>Algorithm: Merge Sort\nComplexity: O(nlogn)</html>");
                            }else if (jComboBox.getSelectedItem() == "Quick sort"){
                                new SortingAlgorithms().sortWithQuickSort(sortarray);
                                speedSlider.setText("<html>Algorithm: Quick Sort\nComplexity: O(n^2)</html>");
                            }
                        }
                    }else if (e.getSource() == generateArrayBtn){
                        try{
                            int a = Integer.parseInt(jTextField.getText());

                            if (Integer.parseInt(jTextField.getText())>=5 && Integer.parseInt(jTextField.getText())<=300){
                                arraySize = Integer.parseInt(jTextField.getText());
                                System.out.println(arraySize);
                                statusText.setText("Generating...");
                                startBtn.setEnabled(false);
                                sortarray.GenerateRandomArray(arraySize);

                                statusText.setText("Unsorted");
                                startBtn.setEnabled(true);
                                comparisonText.setText("");
                            }else{
                                throw new NumberFormatException("Invalid number");
                            }
                        }catch (NumberFormatException e){
                            System.out.println(e);
                            startBtn.setEnabled(false);
                            statusText.setText("Invalid size format");
                            comparisonText.setText("");
                        }
                    }
                    return null;
                }
                @Override
                protected void done() {
                    generateArrayBtn.setEnabled(true);
                    jTextField.setEnabled(true);

                    startBtn.setText("Start");
                    sortarray.isSorting = false;
                    speedText.setText( "Speed: " + sortarray.animSpeed + " ms");
                    if (e.getSource() == startBtn){
                        if (sortarray.IsSorted()){
                            statusText.setText("Sorted");
                            comparisonText.setText(sortarray.comparisons + " comparisons");
                        }
                    }else if(e.getSource() == generateArrayBtn){
                        startBtn.setEnabled(true);
                    }
                }
            };
            worker.execute();
        }
    }

    class MyListCellRenderer extends DefaultListCellRenderer {
        public Color themeColor;

        public MyListCellRenderer(Color t) {
            setOpaque(true);
            this.themeColor = t;
        }

        public Component getListCellRendererComponent(JList jc, Object val, int idx, boolean isSelected, boolean cellHasFocus) {
            setText(val.toString());
            setForeground(Color.white);
            jc.setSelectionBackground(themeColor);
            jc.setSelectionForeground(Color.white);
            if (isSelected)
                setBackground(themeColor);
            else
                setBackground(Color.darkGray);
            return this;
        }
    }
class ColorIcon implements Icon {
    private Color color;

    public ColorIcon(Color color) {
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, getIconWidth(), getIconHeight());
    }

    @Override
    public int getIconWidth() {
        return 15;
    }

    @Override
    public int getIconHeight() {
        return 15;
    }
}


