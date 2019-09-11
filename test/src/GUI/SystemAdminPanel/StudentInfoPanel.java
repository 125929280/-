package GUI.SystemAdminPanel;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Dormitory.SystemAdmin;

import javax.swing.JButton;
//import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;

public class StudentInfoPanel extends JFrame {

    private JPanel contentPane;
    private static JTable table;
    private static JScrollPane scrollPane = new JScrollPane();
    private static SystemAdmin systemAdmin = new SystemAdmin();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentInfoPanel frame = new StudentInfoPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StudentInfoPanel getSelf() {
        return this;
    }

    /**
     * Create the frame.
     */
    public StudentInfoPanel() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(80, 80, 800, 600);
        setLocationRelativeTo(null);
        setTitle("学生信息-系统管理员");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JButton addStudent = new JButton("学生入住");
        addStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new AddStudentPanel(getSelf()).setVisible(true);
            }
        });

        JButton deleteStudent = new JButton("学生注销");
        deleteStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DeleteStudentPanel(getSelf()).setVisible(true);
            }
        });

        showTable();

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane
                .setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
                        gl_contentPane.createSequentialGroup().addContainerGap()
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE).addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(addStudent, GroupLayout.PREFERRED_SIZE, 199,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteStudent, GroupLayout.PREFERRED_SIZE, 199,
                                                GroupLayout.PREFERRED_SIZE))
                                .addContainerGap()));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                .createSequentialGroup()
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup().addGap(99)
                                .addComponent(addStudent, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addGap(128).addComponent(deleteStudent, GroupLayout.PREFERRED_SIZE, 80,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(scrollPane,
                                GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)))
                .addContainerGap()));

        contentPane.setLayout(gl_contentPane);
    }

    public static void showTable() {
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new Object[][] {},
                new String[] { "\u5B66\u53F7", "\u59D3\u540D", "\u6027\u522B", "\u697C\u680B\u53F7",
                        "\u5BBF\u820D\u53F7", "\u5E8A\u53F7", "\u4E13\u4E1A", "\u5165\u4F4F\u65E5\u671F" });

        ResultSet rs = systemAdmin.searchAllStudent();
        try {
            while (rs.next()) {
                String[] data = new String[8];
                for (int i = 1; i <= 8; i++) {
                    data[i - 1] = rs.getString(i);
                }
                model.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        table.setModel(model);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setMinWidth(10);
        scrollPane.setViewportView(table);
    }
}
