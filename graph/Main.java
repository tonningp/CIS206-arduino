import java.util.Observer;
import java.util.Observable;

import javax.swing.*;  
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;  

import java.util.ArrayList;

public class Main implements ActionListener {  

    JLabel l1,l2;
    JTextArea area;  
    JButton b;  
    Graph graph;
    Timer timer;

    Main() {  
       JFrame f= new JFrame();  
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       l1=new JLabel();  
       l1.setBounds(50,25,100,30);
       l2=new JLabel();  
       // setBounds is a short cut for setLocation and setSize. setPreferredSize provides hints about what the size the component would "like to be" to the layout manager API –
       l2.setBounds(50,25,100,75);
       area=new JTextArea();  
       area.setBounds(20,75,250,200);  
       graph = new Graph();
       graph.setOpaque(true);
       graph.setBackground(Color.white);
       graph.setBounds(300,75,300,500);
       b=new JButton("Send");  
       b.setBounds(100,300,120,30);  
       b.addActionListener(this);  
       f.add(l1);
       f.add(l2);
       f.add(area);
       f.add(graph);  
       f.add(b);  
       f.setSize(450,450);  
       f.setLayout(null);  
       f.setVisible(true);  
       timer = new Timer(5000, this);
       timer.setInitialDelay(5000);
       timer.start();
    }  


    public void actionPerformed(ActionEvent e){  
        String text=area.getText();  
        if(e.getSource() == this) {
            l2.setText("Send: "+text);  
        }
        else if(e.getSource() == timer) {
            l1.setText(text);
            graph.repaint();
        }
    }  

    //The component that actually presents the GUI.
    public class Graph extends JPanel {
        private ArrayList<Double> series;
        public Graph() {
            super(new BorderLayout());
            series = new ArrayList<>();
            series.add(25.0);
            series.add(25.2);
            series.add(25.1);
            series.add(25.3);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int originX = 0;
            int lastX = originX;
            int originY = 300;
            int lastY = originY;
            int xRes = 50;
            for(Double d : series) 
            {
                g.drawLine(lastX,lastY,lastX+xRes,originY - (int)(d*10));
                lastX = lastX+xRes;
                lastY = originY-(int)(d*10);
            }
        }
    }

    public static void main(String[] args) 
    {  
       new Main();  
    }  

}   // end of Main class definition
