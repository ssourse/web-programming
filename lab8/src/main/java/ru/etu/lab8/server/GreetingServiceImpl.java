package ru.etu.lab8.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.etu.lab8.client.GreetingService;
import ru.etu.lab8.shared.BookReader;

/** Server-side RPC service hosted by Tomcat. */
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
    private static final long serialVersionUID = 1L;
    private final Map<String, List<BookReader>> readers = createReaders();

    /** {@inheritDoc} */
    @Override
    public List<String> getReaderList() {
        return new ArrayList<String>(readers.keySet());
    }

    /** {@inheritDoc} */
    @Override
    public List<BookReader> getBookReaderList(String readerFIO) throws IllegalArgumentException {
        if (readerFIO == null || !readers.containsKey(readerFIO)) {
            throw new IllegalArgumentException("Читатель не найден");
        }
        return new ArrayList<BookReader>(readers.get(readerFIO));
    }

    /** Creates demonstration data following laboratory methodology. */
    private static Map<String, List<BookReader>> createReaders() {
        Map<String, List<BookReader>> result = new LinkedHashMap<String, List<BookReader>>();
        result.put("Иванов И. И.", Arrays.asList(
            new BookReader("Достоевский", "Игрок", true),
            new BookReader("Толстой", "Анна Каренина", true),
            new BookReader("Достоевский", "Идиот", false)
        ));
        result.put("Петров П. П.", Arrays.asList(
            new BookReader("Толстой", "Анна Каренина", false),
            new BookReader("Достоевский", "Игрок", true)
        ));
        result.put("Сидоров С. С.", Arrays.asList(
            new BookReader("Толстой", "Анна Каренина", false),
            new BookReader("Булгаков", "Белая гвардия", true)
        ));
        return Collections.unmodifiableMap(result);
    }
}
