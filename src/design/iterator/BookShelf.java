package design.iterator;

import java.util.ArrayList;
import java.util.List;

public class BookShelf implements Aggregate<Book> {
    private List<Book> books = new ArrayList<>();

    public Book getBookAt(int index) {
        return books.get(index);
    }

    public void appendBook(Book book) {
        books.add(book);
    }

    public int getLength() {
        return books.size();
    }

    @Override
    public Iterator<Book> iterator() {
        return new BookShelfIterator(this);
    }
}
