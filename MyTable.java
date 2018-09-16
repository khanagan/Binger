import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class MyTable extends JFrame{

    //MAIN METHOD
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                //INITIALIZE JFRAME FORM
                MyTable form=new MyTable();
                form.setVisible(true);
            }
        });
    }

    //CONSTRUCTOR
    public MyTable() {

        //the form
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200,200,800,600);
        setTitle("ProgrammingWizards Channel");
        getContentPane().setLayout(null);

        //ADD SCROLLPANE
        JScrollPane scroll=new JScrollPane();
        scroll.setBounds(70,80,600,410);
        getContentPane().add(scroll);

        //THE TABLE
        final JTable table=new JTable();
        scroll.setViewportView(table);

        //THE MODEL OF OUR TABLE
        DefaultTableModel model=new DefaultTableModel()
        {
            public Class<?> getColumnClass(int column)
            {
                switch(column)
                {
                    case 0:
                        return String.class;
                    case 1:
                        return Boolean.class;
                    case 2:
                        return Boolean.class;
                    case 3:
                        return Boolean.class;
                    case 4:
                        return Boolean.class;
                    case 5:
                        return Boolean.class;
                    case 6:
                        return Boolean.class;
                    case 7:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };

        //ASSIGN THE MODEL TO TABLE
        table.setModel(model);

        model.addColumn("Hour:");
        model.addColumn("Monday");
        model.addColumn("Tuesday");
        model.addColumn("Wednesday");
        model.addColumn("Thursday");
        model.addColumn("Friday");
        model.addColumn("Saturday");
        model.addColumn("Sunday");

        //THE ROW
        for(int i=0;i<24;i++) {
            model.addRow(new Object[0]);
            if(i==0)
                model.setValueAt("12a.m. - 1a.m.", i,0);
            else if(i==11)
                model.setValueAt("11a.m. - 12p.m.",i,0);
            else if(i==12)
                model.setValueAt("12p.m. - 1a.m.",i,0);
            else if(i==23)
                model.setValueAt("11p.m. - 12a.m.",i,0);
            else {
                if (i < 12)
                    model.setValueAt(i + "a.m. - " + (i + 1) + "a.m.", i, 0);
                else
                    model.setValueAt((i - 12) + "p.m. - " + (i - 11) + "p.m.", i, 0);
            }
            model.setValueAt(false,i,1);
            model.setValueAt(false,i,2);
            model.setValueAt(false,i,3);
            model.setValueAt(false,i,4);
            model.setValueAt(false,i,5);
            model.setValueAt(false,i,6);
            model.setValueAt(false,i,7);
        }//end for loop for adding rows

        //OBTAIN SELECTED ROW
        JButton btn=new JButton("Get Selected");
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                List<String> startEndTimes = new ArrayList<>();
                List<Integer> hourArray = new ArrayList<>();

                //GET SELECTED
                for(int c=1; c<8;c++) {
                    for (int r = 0; r < 24; r++) {
                        Boolean checked = Boolean.valueOf(table.getValueAt(r, c).toString());

                        //DISPLAY
                        if(checked.equals(false)) {
                            int counter=0;
                            int startCol = c;
                            int startRow = r;

                            String start =" " + r;
                            if(startCol==1)
                                start="Monday,"+r;
                            if(startCol==2)
                                start="Tuesday,"+r;
                            if(startCol==3)
                                start="Wednesday,"+r;
                            if(startCol==4)
                                start="Thursday,"+r;
                            if(startCol==5)
                                start="Friday,"+r;
                            if(startCol==6)
                                start="Saturday,"+r;
                            if(startCol==7)
                                start="Sunday,"+r;
                            System.out.println("start"+start);
                            while(checked.equals(false)){
                                counter++;
                                r++;
                                if(r==24){
                                    c++;
                                    r=0;
                                }
                                if(r==23&&c==7)
                                break;
                                checked = Boolean.valueOf(table.getValueAt(r, c).toString());
                            }//end while loop

                            int endCol = c;
                            int endRow = r;

                            String end =" " + endRow;
                            if(endCol==1)
                                end="Monday,"+endRow;
                            if(endCol==2)
                                end="Tuesday,"+endRow;
                            if(endCol==3)
                                end="Wednesday,"+endRow;
                            if(endCol==4)
                                end="Thursday,"+endRow;
                            if(endCol==5)
                                end="Friday,"+endRow;
                            if(endCol==6)
                                end="Saturday,"+endRow;
                            if(endCol==7)
                                end="Sunday,"+endRow;

                            System.out.println("end"+end);

                            hourArray.add(counter);
                            System.out.println("hourArray:"+hourArray);
                            startEndTimes.add(start);
                            startEndTimes.add(end);
                            System.out.println("startEndTimes:"+startEndTimes);
                        }//end while loop
                    }//end row for loop
                }//end column for loop
            }//end button actionPerformed
        });
        //ADD BUTTON TO FORM
        btn.setBounds(20,30,130,30);
        getContentPane().add(btn);
    }

}