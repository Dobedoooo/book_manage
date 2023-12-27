package xing_ming_pin_yin.service;

import xing_ming_pin_yin.IO.ReadOrSave;
import xing_ming_pin_yin.JDBC.MyJDBC;
import xing_ming_pin_yin.pojo.Book;

import java.util.List;
import java.util.Vector;

public class BookService {
    Book book = new Book();
    public List<String> readFromFile(){
        String path = "./files/haha.txt";
        return ReadOrSave.read(path);
    }

    public void saveToSQL(Vector<String> vector){
        for (int i=0;i<vector.size();i++){
            String[] str = vector.get(i).split(",");
            book.setIsbn(str[0]);
            book.setTitle(str[1]);
            book.setPublisher(str[2]);
            book.setLanguage(str[3]);
            book.setPrice(Double.valueOf(str[4]));
            MyJDBC.insert(book);
        }

    }

    public Vector<String> queryAsPublisher(String publisher){
        return MyJDBC.queryAsPublisher(publisher);
    }
}
