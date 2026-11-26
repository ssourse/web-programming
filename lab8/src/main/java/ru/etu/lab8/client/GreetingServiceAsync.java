package ru.etu.lab8.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.etu.lab8.shared.BookReader;

/** Asynchronous interface used in the browser (GWT-RPC). */
public interface GreetingServiceAsync {
    /** Retrieves the available readers without blocking the user interface. */
    void getReaderList(AsyncCallback<List<String>> callback);
    /** Retrieves a selected reader's books asynchronously. */
    void getBookReaderList(String readerFIO, AsyncCallback<List<BookReader>> callback);
}
