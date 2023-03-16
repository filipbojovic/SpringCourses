package org.example.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TodoBusinessImplTest {
    private List<String> expected = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

    @Test
    void testRetrieveTodosRelatedToSpringUsingStub() {
        TodoService todoService = new TodoServiceStub();
        TodoBusinessImpl subject = new TodoBusinessImpl(todoService);

        var filteredTodos = subject.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, filteredTodos.size());
    }

    @Test
    void testRetrieveTodosRelatedToSpringUsingMock() {
        TodoService todoServiceMock = mock(TodoService.class);
        TodoBusinessImpl subject = new TodoBusinessImpl(todoServiceMock);

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(expected);

        var filteredTodos = subject.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, filteredTodos.size());
    }

    @Test
    void testDeleteTodosNotRelatedToSpring() {
        TodoService todoServiceMock = mock(TodoService.class);
        TodoBusinessImpl subject = new TodoBusinessImpl(todoServiceMock);

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(expected);

        subject.deleteTodosNotRelatedToSpring("Dummy");

        // verify that method is called with the following parameter
        // with times we can verify that is called a specific number of times
        verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
//        then(todoServiceMock).should().deleteTodo("Learn to Dance"); // alternative

        // verify that method is not called with the following parameter
        verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
//        then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC"); // alternative
    }

    @Test
    void testDeleteTodosNotRelatedToSpringCaptureArgument() {
        // Declare Argument Captor.
        // Define Argument Captor on specific method call.
        // Capture the argument.
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        TodoService todoServiceMock = mock(TodoService.class);
        TodoBusinessImpl subject = new TodoBusinessImpl(todoServiceMock);

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(expected);
        subject.deleteTodosNotRelatedToSpring("Dummy");

        verify(todoServiceMock).deleteTodo(stringArgumentCaptor.capture());
        assertEquals(stringArgumentCaptor.getValue(), "Learn to Dance");
    }

    @Test
    void testDeleteTodosNotRelatedToSpringCaptureArgumentWhenCalledMultipleTimes() {
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        TodoService todoServiceMock = mock(TodoService.class);
        TodoBusinessImpl subject = new TodoBusinessImpl(todoServiceMock);
        List<String> expected = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(expected);
        subject.deleteTodosNotRelatedToSpring("Dummy");

        verify(todoServiceMock, times(2)).deleteTodo(stringArgumentCaptor.capture());
        assertEquals(stringArgumentCaptor.getAllValues().size(), 2);
    }
}