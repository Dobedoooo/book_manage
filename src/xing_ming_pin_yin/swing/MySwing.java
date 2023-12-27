package xing_ming_pin_yin.swing;

import xing_ming_pin_yin.pojo.Book;
import xing_ming_pin_yin.service.BookService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MySwing {

    private static Book book = new Book();
    private static BookService service = new BookService();
    private static JTextField isbnText = new JTextField(20);
    private static JTextField nameText = new JTextField(20);
    private static JComboBox<String> box = new JComboBox<>();
    private static JTextField priceText = new JTextField(20);
    private static Vector<String> vector = new Vector<>();

    public static void mainPanel() {
        JFrame f = new JFrame("图书信息管理");
        f.setSize(1518, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setResizable(false);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel1.setBackground(Color.red);
        panel2.setBackground(Color.blue);
        panel3.setBackground(Color.yellow);


        panel1(panel1);
        panel2(panel2);
        panel3(panel3);

        f.add(panel1, BorderLayout.WEST);
        f.add(panel2, BorderLayout.CENTER);
        f.add(panel3, BorderLayout.EAST);
        f.setVisible(true);
    }

    public static void panel1(JPanel panel) {
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(350, 50));

        JLabel isbnLabel = new JLabel("ISBN");
        isbnLabel.setBounds(10, 20, 80, 25);
        panel.add(isbnLabel);

        isbnText.setBounds(100, 20, 165, 25);
        panel.add(isbnText);
        JLabel nameLabel = new JLabel("书名");
        nameLabel.setBounds(10, 60, 80, 25);
        panel.add(nameLabel);
        nameText.setBounds(100, 60, 165, 25);
        panel.add(nameText);
        box.setBounds(100, 100, 165, 25);
        box.addItem("请选择出版社");
        box.addItem("高等教育出版社");
        box.addItem("清华大学出版社");
        box.addItem("机械工业出版社");
        panel.add(box);
        ButtonGroup group = new ButtonGroup();
        JRadioButton chinese = new JRadioButton("中文");
        chinese.setBounds(100, 140, 100, 20);
        JRadioButton english = new JRadioButton("英文");
        english.setBounds(200, 140, 100, 20);
        group.add(chinese);
        group.add(english);
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chinese.isSelected()) {
                    System.out.println("haha1");
                    book.setLanguage("chinese");
                }
                if (english.isSelected()) {
                    System.out.println("haha2");
                    book.setLanguage("english");
                }
            }
        };
        chinese.addActionListener(listener);
        english.addActionListener(listener);
        panel.add(chinese);
        panel.add(english);

        JLabel priceLabel = new JLabel("价格");
        priceLabel.setBounds(10, 200, 80, 25);
        panel.add(priceLabel);
        priceText.setBounds(100, 200, 165, 25);
        panel.add(priceText);
    }

    public static void panel2(JPanel panel) {
        panel.setLayout(null);

        JList<String> list = new JList<>();
        list.setBounds(0, 0, 802, 200);
        JButton button1 = new JButton("从文件读取记录");
        button1.setBounds(10, 200, 110, 20);

        ActionListener button1Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listFromFile = service.readFromFile();
                vector.addAll(listFromFile);
                list.setListData(vector);
            }
        };
        button1.addActionListener(button1Listener);

        JButton button2 = new JButton("添加记录");
        button2.setBounds(130, 200, 110, 20);

        ActionListener button2Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                book.setIsbn(isbnText.getText());
                book.setTitle(nameText.getText());
                book.setPublisher((String) box.getSelectedItem());
                book.setPrice(Double.valueOf(priceText.getText()));
                vector.add(book.toString());
                list.setListData(vector);
            }
        };
        button2.addActionListener(button2Listener);


        JButton button3 = new JButton("写入数据库");
        button3.setBounds(250, 200, 110, 20);
        ActionListener button3Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.saveToSQL(vector);
            }
        };
        button3.addActionListener(button3Listener);
        JButton button4 = new JButton("按出版社查询");
        button4.setBounds(370, 200, 110, 20);

        ActionListener button4Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> bookAsPublisher = service.queryAsPublisher((String)box.getSelectedItem());
                list.setListData(bookAsPublisher);
            }
        };
        button4.addActionListener(button4Listener);

        panel.add(list);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

    }

    public static void panel3(JPanel panel) {
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(350, 50));
        JLabel textLabel = new JLabel("假期倒计时");
        JLabel textLabel2 = new JLabel("距离2023年五一假期还有");
        JLabel textLabel3 = new JLabel("1000(秒)");
        JButton button = new JButton("button4");
        button.setBounds(0, 200, 110, 20);
        textLabel.setBounds(5, 5, 165, 25);
        textLabel2.setBounds(5, 25, 165, 25);
        textLabel3.setBounds(5, 45, 165, 25);

        panel.add(textLabel);
        panel.add(textLabel2);
        panel.add(textLabel3);
        panel.add(button);

    }


    public static void main(String[] args) {
        mainPanel();
    }
}
