package ru.etu.lab8.client;

import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.etu.lab8.shared.BookReader;

/** Synchronous declaration of the GWT-RPC book service. */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
    /** @return known reader names from the server. */
    List<String> getReaderList();
    /** @param readerFIO reader name; @return books belonging to that reader. */
    List<BookReader> getBookReaderList(String readerFIO) throws IllegalArgumentException;
}
