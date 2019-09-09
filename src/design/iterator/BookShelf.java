package design.iterator;

public class BookShelf implements Aggregate<Book> {
    private Book[] books;
    private int last = 0;

    public BookShelf(int maxsize) {
        books = new Book[maxsize];
    }

    public Book getBookAt(int index) {
        return books[index];
    }

    public void appendBook(Book book) {
        books[last] = book;
        last++;
    }

    public int getLength() {
        return last;
    }

    @Override
    public Iterator<Book> iterator() {
        return new BookShelfIterator(this);
    }
}
