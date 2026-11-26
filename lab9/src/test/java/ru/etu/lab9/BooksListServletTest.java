package ru.etu.lab9;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Модульные тесты сервлета из лабораторной № 3.
 * Запрос, ответ и конфигурация заменены mock-объектами Mockito.
 * Для выполнения этих тестов Apache Tomcat не нужен.
 */
public class BooksListServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private ServletConfig config;

    private AutoCloseable mocks;
    private BooksListServlet servlet;
    private StringWriter html;

    /** Создаёт mock-окружение перед каждым тестом. */
    @Before
    public void setUp() throws Exception {
        mocks = MockitoAnnotations.openMocks(this);
        html = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(html, true));
        when(request.getContextPath()).thenReturn("/lab9");
        when(config.getInitParameter("libraryTitle"))
                .thenReturn("Домашняя библиотека");
        servlet = new BooksListServlet();
        servlet.init(config);
    }

    @After
    public void tearDown() throws Exception {
        mocks.close();
    }

    /** GET-запрос без фильтра возвращает три книги. */
    @Test
    public void testGetReturnsAllBooks() throws Exception {
        when(request.getParameter("name")).thenReturn("Дарья");
        when(request.getParameter("read")).thenReturn("all");

        servlet.doGet(request, response);

        String result = html.toString();
        assertTrue(result.contains("Список книг читателя Дарья"));
        assertTrue(result.contains("Найдено книг: 3"));
        assertTrue(result.contains("Мастер и Маргарита"));
        assertTrue(result.contains("Чапаев и Пустота"));
        verify(response).setContentType("text/html;charset=UTF-8");
        verify(request).setCharacterEncoding("UTF-8");
        verify(request, times(1)).getParameter("name");
    }

    /** Фильтр yes оставляет только две прочитанные книги. */
    @Test
    public void testGetFiltersReadBooks() throws Exception {
        when(request.getParameter("read")).thenReturn("yes");

        servlet.doGet(request, response);

        assertTrue(html.toString().contains("Найдено книг: 2"));
        assertTrue(html.toString().contains("Мастер и Маргарита"));
        assertFalse(html.toString().contains("Чапаев и Пустота"));
        verify(request).getParameter("read");
    }

    /** Фильтр no оставляет только одну непрочитанную книгу. */
    @Test
    public void testGetFiltersUnreadBooks() throws Exception {
        when(request.getParameter("read")).thenReturn("no");

        servlet.doGet(request, response);

        assertTrue(html.toString().contains("Найдено книг: 1"));
        assertTrue(html.toString().contains("Чапаев и Пустота"));
        assertFalse(html.toString().contains("Мастер и Маргарита"));
    }

    /** Неизвестное значение фильтра трактуется как all. */
    @Test
    public void testInvalidFilterFallsBackToAll() throws Exception {
        when(request.getParameter("read")).thenReturn("unknown");

        servlet.doGet(request, response);

        assertTrue(html.toString().contains("Найдено книг: 3"));
        assertTrue(html.toString().contains("Фильтр: все книги"));
    }

    /** Пустое имя корректно заменяется подписью «без имени». */
    @Test
    public void testEmptyNameUsesDefault() throws Exception {
        when(request.getParameter("name")).thenReturn("   ");

        servlet.doGet(request, response);

        assertTrue(html.toString().contains("Список книг читателя без имени"));
    }

    /** Опасные символы имени кодируются, чтобы не образовать HTML-тег. */
    @Test
    public void testNameIsEscapedForHtml() throws Exception {
        when(request.getParameter("name"))
                .thenReturn("<script>alert('x')</script>");

        servlet.doGet(request, response);

        assertTrue(html.toString().contains("&lt;script&gt;"));
        assertFalse(html.toString().contains("<script>"));
    }

    /** POST использует ту же обработку и не требует реального сервера. */
    @Test
    public void testPostReturnsBooks() throws Exception {
        when(request.getParameter("name")).thenReturn("Анна");
        when(request.getParameter("read")).thenReturn("no");

        servlet.doPost(request, response);

        assertTrue(html.toString().contains("Список книг читателя Анна"));
        assertTrue(html.toString().contains("Найдено книг: 1"));
        verify(response, times(1)).getWriter();
        verify(request, never()).getSession();
    }

    /** Проверяет чтение статического параметра инициализации. */
    @Test
    public void testServletReadsLibraryTitleFromConfig() throws Exception {
        servlet.doGet(request, response);

        assertTrue(html.toString().contains("Домашняя библиотека"));
        verify(config).getInitParameter("libraryTitle");
    }

    /** При отсутствии init-param используется резервный заголовок. */
    @Test
    public void testMissingTitleUsesDefault() throws Exception {
        when(config.getInitParameter("libraryTitle")).thenReturn(null);
        servlet.init(config);

        servlet.doGet(request, response);

        assertTrue(html.toString().contains("Личная библиотека"));
    }
}
