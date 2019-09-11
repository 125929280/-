package GUI.SystemAdminPanel;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import Dormitory.SystemAdmin;

public class AddStudentPanel extends JFrame {

        private JPanel contentPane;
        private JTextField sno;
        private JTextField sname;
        private JTextField gender;
        private JTextField building;
        private JTextField room;
        private JTextField bed;
        private JTextField major;
        private JTextField indate;
        private JLabel label_3;
        private JLabel label_4;
        private JLabel label_5;
        private JLabel label_6;
        private JLabel label_7;
        private SystemAdmin systemAdmin = new SystemAdmin();

        /**
         * Launch the application.
         */
        // public static void main(String[] args) {
        // EventQueue.invokeLater(new Runnable() {
        // public void run() {
        // try {
        // updatePasswordPanel frame = new updatePasswordPanel();
        // frame.setVisible(true);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // }
        // });
        // }

        /**
         * Create the frame.
         */
        public AddStudentPanel(StudentInfoPanel sip) {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setBounds(80, 80, 558, 476);
                setLocationRelativeTo(null);
                setTitle("学生入住-系统管理员");
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);

                sno = new JTextField();
                sno.setColumns(10);

                JLabel label = new JLabel("学号");

                JLabel label_2 = new JLabel("姓名");

                sname = new JTextField();
                sname.setColumns(10);

                JLabel label_1 = new JLabel("性别");

                gender = new JTextField();
                gender.setColumns(10);

                building = new JTextField();
                building.setColumns(10);

                room = new JTextField();
                room.setColumns(10);

                bed = new JTextField();
                bed.setColumns(10);

                major = new JTextField();
                major.setColumns(10);

                indate = new JTextField();
                indate.setColumns(10);

                label_3 = new JLabel("楼栋号");

                label_4 = new JLabel("宿舍号");

                label_5 = new JLabel("床号");

                label_6 = new JLabel("专业");

                label_7 = new JLabel("入住日期");

                JButton add = new JButton("入住");
                add.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (sno.getText().equals("") || sname.getText().equals("")
                                                || gender.getText().equals("") || building.getText().equals("")
                                                || room.getText().equals("") || bed.getText().equals("")
                                                || major.getText().equals("") || indate.getText().equals("")) {
                                        JOptionPane.showMessageDialog(null, "请继续输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                } else if (systemAdmin.searchStudent(sno.getText()) != null) {
                                        JOptionPane.showMessageDialog(null, "该学生已存在,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        sno.setText("");
                                } else if (systemAdmin.searchDormitory(building.getText(), room.getText()) == null) {
                                        JOptionPane.showMessageDialog(null, "该宿舍不存在,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        building.setText("");
                                        room.setText("");
                                } else if (bed.getText().equals("1") == false && bed.getText().equals("2") == false
                                                && bed.getText().equals("3") == false
                                                && bed.getText().equals("4") == false) {
                                        JOptionPane.showMessageDialog(null, "床号输入错误,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        bed.setText("");
                                } else if (systemAdmin.searchBed(building.getText(), room.getText(),
                                                bed.getText()) != null) {
                                        JOptionPane.showMessageDialog(null, "该床位已被占用,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        building.setText("");
                                        room.setText("");
                                        bed.setText("");

                                } else {
                                        systemAdmin.addStudent(sno.getText(), sname.getText(), gender.getText(),
                                                        building.getText(), room.getText(), bed.getText(),
                                                        major.getText(), indate.getText());
                                        JOptionPane.showMessageDialog(null, "入住成功", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        setVisible(false);
                                        sip.showTable();
                                }
                        }
                });

                GroupLayout gl_contentPane = new GroupLayout(contentPane);
                gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
                                                .createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addContainerGap(98, Short.MAX_VALUE)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.TRAILING)
                                                                                .addComponent(label_5,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                54,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(label_6,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                54,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(label_4,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                54,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(label_3,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                54,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(gl_contentPane
                                                                                                .createParallelGroup(
                                                                                                                Alignment.LEADING)
                                                                                                .addComponent(label_1,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                54,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(label_2)
                                                                                                .addComponent(label))
                                                                                .addComponent(label_7,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                54,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(6)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.LEADING)
                                                                                .addComponent(building,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                218,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(gender,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                218,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(sname, 218, 218, 218)
                                                                                .addComponent(sno,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                218,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(room,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                218,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(bed,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                218,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(major,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                218,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(indate,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                218,
                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(gl_contentPane.createSequentialGroup().addGap(223)
                                                                .addComponent(add)))
                                                .addContainerGap(155, Short.MAX_VALUE)));
                gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_contentPane.createSequentialGroup().addGap(40).addGroup(gl_contentPane
                                                .createParallelGroup(Alignment.BASELINE)
                                                .addComponent(sno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label)).addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(sname, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(label_2))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(gender, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(label_1))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(building, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(label_3))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(room, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(label_4))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(bed, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(label_5))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(major, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(label_6))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(indate, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(label_7))
                                                .addGap(28).addComponent(add).addContainerGap(34, Short.MAX_VALUE)));
                contentPane.setLayout(gl_contentPane);
        }
}
