package ru.etu.lab8.client;

import java.util.List;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.etu.lab8.shared.BookReader;

/** Client-side GWT entry point: choose reader and display books. */
public class BookModule implements EntryPoint {
    private final GreetingServiceAsync service = GWT.create(GreetingService.class);
    private final ListBox readers = new ListBox();
    private final Label message = new Label();
    private final Button showBooks = new Button("Получить список книг");
    private final DialogBox dialog = new DialogBox(false, true);
    private final CellTable<BookReader> table = new CellTable<BookReader>();

    /** Builds the UI and requests the list of readers from the server. */
    @Override
    public void onModuleLoad() {
        VerticalPanel content = new VerticalPanel();
        content.setSpacing(16);
        HorizontalPanel controls = new HorizontalPanel();
        controls.setSpacing(12);
        controls.add(new Label("Выберите читателя:"));
        readers.setWidth("230px");
        controls.add(readers);
        controls.add(showBooks);
        content.add(controls);
        message.setStyleName("message");
        content.add(message);
        RootPanel.get("application").add(content);
        buildBookTable();
        dialog.setAnimationEnabled(true);
        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing(14);
        panel.add(table);
        Button close = new Button("Закрыть");
        close.addClickHandler(new ClickHandler() {
            @Override public void onClick(ClickEvent e) { dialog.hide(); }
        });
        panel.add(close);
        dialog.setWidget(panel);
        showBooks.setEnabled(false);
        showBooks.addClickHandler(new ClickHandler() {
            @Override public void onClick(ClickEvent e) { requestBooks(); }
        });
        loadReaders();
    }

    /** Requests the list of reader names asynchronously through GWT-RPC. */
    private void loadReaders() {
        message.setText("Загрузка читателей...");
        service.getReaderList(new AsyncCallback<List<String>>() {
            @Override public void onFailure(Throwable error) {
                message.setText("Не удалось загрузить список читателей.");
            }
            @Override public void onSuccess(List<String> values) {
                readers.clear();
                for (String value : values) { readers.addItem(value); }
                showBooks.setEnabled(!values.isEmpty());
                message.setText(values.isEmpty() ? "Список читателей пуст." : "");
            }
        });
    }

    /** Sends the reader's name and shows the returned rows in a modal table. */
    private void requestBooks() {
        if (readers.getSelectedIndex() < 0) { return; }
        final String reader = readers.getValue(readers.getSelectedIndex());
        showBooks.setEnabled(false);
        message.setText("Получаем книги...");
        service.getBookReaderList(reader, new AsyncCallback<List<BookReader>>() {
            @Override public void onFailure(Throwable error) {
                message.setText("Ошибка: не удалось получить список книг.");
                showBooks.setEnabled(true);
            }
            @Override public void onSuccess(List<BookReader> books) {
                table.setRowCount(books.size(), true);
                table.setRowData(0, books);
                dialog.setText("Список книг читателя " + reader);
                dialog.center();
                message.setText("");
                showBooks.setEnabled(true);
            }
        });
    }

    /** Creates the three-column GWT CellTable. */
    private void buildBookTable() {
        table.setWidth("650px");
        table.addColumn(new TextColumn<BookReader>() {
            @Override public String getValue(BookReader row) { return row.getAuthor(); }
        }, "Автор книги");
        table.addColumn(new TextColumn<BookReader>() {
            @Override public String getValue(BookReader row) { return row.getTitle(); }
        }, "Название книги");
        table.addColumn(new TextColumn<BookReader>() {
            @Override public String getValue(BookReader row) { return row.isRead() ? "Да" : "Нет"; }
        }, "Прочитал");
    }
}
