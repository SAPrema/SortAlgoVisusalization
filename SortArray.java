import javax.swing.*;
import java.awt.*;

    public class SortArray extends JPanel {
        public static final int WIDTH = 1280;
        public static final int HEIGHT = 720;
        public static final int win_WIDTH = 1000;
        public int comparisons = 0;
        public int animSpeed = 250;
        public boolean isSorting = false;
        public static final int FONT_SIZE = 12;


        public Color sortedColor = new Color(110,217,161);
        public Color BGColor  = Color.darkGray;
        public Color blueColor  = new Color(77, 171, 213);
        public Color pinkColor  = new Color(226, 86, 186);
        public Color purpleColor  = new Color(128, 86, 226);
        public Color orangeColor  = new Color(226, 160, 86);
        int arrSize;
        int[] arr;
        Color[] barColor;

        int BAR_WIDTH;
        int BAR_OGWIDTH;

        public SortArray(int n){
            GenerateRandomArray(n);
        }
        public void increaseComparison()
        {
            comparisons++;
        }
        public void GenerateRandomArray(int n){
            this.arrSize = n;
            this.BAR_WIDTH =  win_WIDTH/this.arrSize;
            this.BAR_OGWIDTH  = this.BAR_WIDTH - 2;
            this.arr = new int[n];
            this.barColor = new Color[n];

            int min = 80;
            int max = 700;
            for (int i = 0; i<arrSize; i++){
                this.arr[i] = (int)(Math.random() * (max - min + 1) + min);
                barColor[i] = Color.white;
                sleep(2);
                Update();
            }

            if (arrSize == 5) animSpeed = 180;
            else if (arrSize <= 10) animSpeed = 180;
            else if (arrSize <= 25) animSpeed = 180;
            else if (arrSize <= 50) animSpeed = 180;
            else if (arrSize <= 100) animSpeed = 180;
            else if (arrSize <= 200) animSpeed = 180;
            else if (arrSize <= 300) animSpeed = 180;

        }
        /*@Override
        public void paintComponent(Graphics g){
            Graphics2D graphics = (Graphics2D)g;
            super.paintComponent(graphics);
            graphics.setColor(BGColor);
            graphics.fillRect(0,0, WIDTH,HEIGHT);

            int pos = 5;
            for (int i = 0; i<arrSize; i++){
                graphics.setColor(barColor[i]);
                graphics.fillRect(pos, HEIGHT-arr[i], BAR_OGWIDTH, arr[i]);
                pos += BAR_WIDTH;
            }
        }*/

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D graphics = (Graphics2D) g;
            super.paintComponent(graphics);
            graphics.setColor(BGColor);
            graphics.fillRect(0, 0, WIDTH, HEIGHT);

            int pos = 5;
            for (int i = 0; i < arrSize; i++) {
                graphics.setColor(barColor[i]);
                graphics.fillRect(pos, HEIGHT - arr[i], BAR_OGWIDTH, arr[i]);

                graphics.setColor(Color.WHITE);
                graphics.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
                String valueStr = String.valueOf(arr[i]);
                int textWidth = graphics.getFontMetrics().stringWidth(valueStr);
                int textHeight = graphics.getFontMetrics().getHeight();
                int textX = pos + (BAR_OGWIDTH - textWidth) / 2;
                int textY = HEIGHT - arr[i] - textHeight;
                graphics.drawString(valueStr, textX, textY);

                pos += BAR_WIDTH;
            }
        }


        public int getValue(int index){

            return arr[index];
        }

        public void swap(int a, int b){
            int temp=arr[a];
            arr[a]=arr[b];
            arr[b]=temp;
            barColor[a] = Color.red;
            barColor[b] = Color.red;
            sleep(animSpeed);
            barColor[a] = Color.white;
            barColor[b] = Color.white;
        }

        public void setValueatIndex(int a, int b){
            arr[a] = arr[b];
        }
        public void setValue(int index, int value){
            arr[index] = value;
        }
        public void Update(){
            validate();
            repaint();
        }
        public void sleep(int t){
            try {
                Thread.sleep(t);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        public boolean IsSorted(){
            for (int i = 1 ; i<arrSize; i++){
                if (arr[i-1]>arr[i]){
                    return false;
                }
            }
            return true;
        }
        public void sortAnim(){
            sleep(500);
            for (int i = 0; i<arrSize; i++){
                if(!isSorting)
                    return;

                barColor[i] = sortedColor;
                if (arrSize == 5) sleep(100);
                else if (arrSize <= 10) sleep(100);
                else if (arrSize <= 25) sleep(50);
                else if (arrSize <= 50) sleep(20);
                else if (arrSize <= 100) sleep(10);
                else if (arrSize <= 200) sleep(8);
                else if (arrSize <= 300) sleep(5);
                Update();
            }
            isSorting = false;
        }
    }


