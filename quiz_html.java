import java.util.ArrayList;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class quiz_html extends JFrame implements ActionListener {
    ArrayList<String>question=new ArrayList<>();
    ArrayList<String>option1=new ArrayList<>();
    ArrayList<String>option2=new ArrayList<>();
    ArrayList<String>option3=new ArrayList<>();
    ArrayList<String>option4=new ArrayList<>();
    JButton next,previous,convert;
    JLabel l,l1,l2,l3,l4;
    JTextField qn,o1,o2,o3,o4;
    int current=1,total=5,max=1;
    quiz_html(String a)
    {
         super(a);
         next=new JButton("Next");
         previous=new JButton("Previous");
         convert=new JButton("Convert to HTML");
         l=new JLabel("Enter the question"+current);
         l1=new JLabel("Option1");
         l2=new JLabel("Option2");
         l3=new JLabel("Option3");
         l4=new JLabel("Option4");
          qn=new JTextField();
          o1=new JTextField();
          o2=new JTextField();
          o3=new JTextField();
          o4=new JTextField();
          setVisible(true);
          setLayout(null);
          if(current==1){
            previous.setEnabled(false);
          }
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setSize(1000,1000);
          initializecomponent();
    }
     private void initializecomponent()
    {
        l.setBounds(50,80,200,30);
        l1.setBounds(100,120,200,30);
        l2.setBounds(100,150,200,30);
        l3.setBounds(100,180,200,30);
        l4.setBounds(100,210,200,30);
        qn.setBounds(180,80,200,30);
        o1.setBounds(180,120,200,30);
        o2.setBounds(180,150,200,30);
        o3.setBounds(180,180,200,30);
        o4.setBounds(180,210,200,30);
        previous.setBounds(100, 280, 100, 30);
		next.setBounds(220, 280, 100, 30);
		convert.setBounds(340, 280, 150, 30);
        previous.addActionListener(this);
        next.addActionListener(this);
        convert.addActionListener(this);
        add(l);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(qn);
        add(o1);
        add(o2);
        add(o3);
        add(o4);
        add(next);
        add(previous);
        add(convert);
    }

    
    private void updateComponents()
    {
        l.setText("Enter the Question"+(current));
        qn.setText("");
        o1.setText("");
        o2.setText("");
        o3.setText("");
        o4.setText("");
        previous.setEnabled(current>1);
        next.setEnabled(current>0);
    }
    public void setupPrevious()
    {
        l.setText("Edit the  question"+(current));
        qn.setText(question.get(current-1));
        o1.setText(option1.get(current-1));
        o2.setText(option2.get(current-1));
        o3.setText(option3.get(current-1));
        o4.setText(option4.get(current-1));
        previous.setEnabled(current>1);
        next.setEnabled(current>0);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       
       try{
        if(e.getSource()== next)
        {
           if(max>current)
           {
              question.set(current-1, qn.getText());
              option1.set(current-1, o1.getText());
              option2.set(current-1, o2.getText());
              option3.set(current-1, o3.getText());
              option4.set(current-1, o4.getText());
              current++;
              setupPrevious();;
           }
           else{
            current++;
            question.add(qn.getText());
            option1.add(o1.getText());
            option2.add(o2.getText());
            option3.add(o3.getText());
            option4.add(o4.getText());
            if(max<current)
            {
                max=current;
            }
            updateComponents();;
        }
            if(max<current)
            {
                max=current;
            }
        }
        else if(e.getSource()== previous)
        {
            if(max==current)
            { 
                question.add(qn.getText());
                option1.add(o1.getText());
                option2.add(o2.getText());
                option3.add(o3.getText());
                option4.add(o4.getText());
            }
            current--;
            setupPrevious();;
        }
        else if(e.getSource()==convert)
        {
            conertHtml();
        }
       }
       catch(Exception ex)
       {
        System.out.println(ex.toString());
       }
    }

    public void conertHtml()
    {

        try
        {
            BufferedWriter writer=new BufferedWriter(new FileWriter("quiz.html"));
            writer.write("<html>\n<head>\n<title>quiz</title></head>\n</head><body>");
            for(int i=0;i<question.size();i++)
            {
                writer.write("<p>\n<strong>Questions"+(i+1)+":</strong>\n"+question.get(i)+"</p>\n");
				writer.write("<form>");
				writer.write("<input type=\"radio\" name=\"q"+(i+1)+"\"value =\"1\">"+option1.get(i)+"<br>\n");
				writer.write("<input type=\"radio\" name=\"q"+(i+1)+"\"value =\"2\">"+option2.get(i)+"<br>\n");
				writer.write("<input type=\"radio\" name=\"q"+(i+1)+"\"value =\"3\">"+option3.get(i)+"<br>\n");
				writer.write("<input type=\"radio\" name=\"q"+(i+1)+"\"value =\"4\">"+option4.get(i)+"<br>\n");
				writer.write("</form>");
            }
            writer.write("</body>\n</html>");
            writer.close();
            JOptionPane.showMessageDialog(this, "Conversion to HTML successful!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error saving HTML file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[]args)
    {
        new quiz_html("quiz");
    }
}

            // writer.write("<p>/n<strong>Question"+i+1+":</strong>/n"+question.get(i)+"</p>/n");
            // writer.write("<form>");
            // writer.write("<input type=\"radio\" name=\"q"+(i+1)+"\"value=\"1\">"+option1.get(i)+"<br>/n");
            // writer.write("<input type=\"radio\" name=\"q"+(i+1)+"\"value=\"1\">"+option2.get(i)+"<br>/n");
            // writer.write("<input type=\"radio\" name=\"q"+(i+1)+"\"value=\"1\">"+option3.get(i)+"<br>/n");
            // writer.write("<input type=\"radio\" name=\"q"+(i+1)+"\"value=\"1\">"+option4.get(i)+"<br>/n");
            // writer.write("</form>");