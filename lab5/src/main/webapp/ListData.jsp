
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.etu.lab5.Book" %>
<%@ page import="ru.etu.lab5.HtmlUtil" %>

<%
    List<Book> tableBooks =
        (List<Book>) request.getAttribute("books");
%>

<table>
    <thead>
        <tr>
            <th>&#1040;&#1074;&#1090;&#1086;&#1088; &#1082;&#1085;&#1080;&#1075;&#1080;</th>
            <th>&#1053;&#1072;&#1079;&#1074;&#1072;&#1085;&#1080;&#1077; &#1082;&#1085;&#1080;&#1075;&#1080;</th>
            <th>&#1055;&#1088;&#1086;&#1095;&#1080;&#1090;&#1072;&#1085;&#1086;</th>
        </tr>
    </thead>
    <tbody>
        <% if (tableBooks != null) {
            for (Book book : tableBooks) { %>
                <tr>
                    <td><%= HtmlUtil.escape(book.getAuthor()) %></td>
                    <td><%= HtmlUtil.escape(book.getTitle()) %></td>
                    <td><%= book.isRead()
                        ? "&#1044;&#1072;"
                        : "&#1053;&#1077;&#1090;" %></td>
                </tr>
        <%  }
           } %>
    </tbody>
</table>
