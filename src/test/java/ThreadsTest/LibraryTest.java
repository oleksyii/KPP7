package ThreadsTest;

import com.example.kpp7.Threads.Library;
import com.example.kpp7.Threads.Reader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void getTotalBooks() {
        // Arrange
        Library l = new Library(3);

        //Act
        for (int i = 1; i <= 3; i++) {
            Thread thread = new Thread(new Reader(i, l), "Thread-" + i);
            thread.start();
        }
        //Assert
    }

    @Test
    void setTotalBooks() {
    }

    @Test
    void borrowBook() {
    }

    @Test
    void returnABook() {
    }
}