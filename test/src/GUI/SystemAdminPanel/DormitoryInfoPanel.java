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
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;

public class DormitoryInfoPanel extends JFrame {

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
                    DormitoryInfoPanel frame = new DormitoryInfoPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DormitoryInfoPanel getSelf() {
        return this;
    }

    /**
     * Create the frame.
     */
    public DormitoryInfoPanel() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(80, 80, 800, 600);
        setLocationRelativeTo(null);
        setTitle("宿舍信息-系统管理员");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JButton addDormitory = new JButton("新增宿舍");
        addDormitory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new AddDormitoryPanel(getSelf()).setVisible(true);
            }
        });

        JButton deleteDormitory = new JButton("删除宿舍");
        deleteDormitory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DeleteDormitoryPanel(getSelf()).setVisible(true);
            }
        });

        showTable();

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                .createSequentialGroup().addContainerGap()
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(addDormitory, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteDormitory, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
                .addGap(97)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                .createSequentialGroup()
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(scrollPane,
                                GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup().addGap(99)
                                .addComponent(addDormitory, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addGap(128).addComponent(deleteDormitory, GroupLayout.PREFERRED_SIZE, 80,
                                        GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));

        contentPane.setLayout(gl_contentPane);
    }

    public static void showTable() {
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "\u697C\u680B\u53F7",
                "\u5BBF\u820D\u53F7", "\u536B\u751F\u5F97\u5206", "\u62A5\u4FEE\u60C5\u51B5" });

        ResultSet rs = systemAdmin.searchAllDormitory();
        try {
            while (rs.next()) {
                String[] data = new String[4];
                for (int i = 1; i <= 4; i++) {
                    data[i - 1] = rs.getString(i);
                }
                model.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        table.setModel(model);
        scrollPane.setViewportView(table);
    }
}
