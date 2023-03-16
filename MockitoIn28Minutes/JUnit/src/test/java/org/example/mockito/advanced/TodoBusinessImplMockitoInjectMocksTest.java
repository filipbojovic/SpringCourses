package org.example.mockito.advanced;

import org.example.mockito.TodoBusinessImpl;
import org.example.mockito.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoBusinessImplMockitoInjectMocksTest {
    @Mock
    TodoService todoService;

    @InjectMocks
    TodoBusinessImpl subject;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    private List<String> expected = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

    @Test
    void testRetrieveTodosRelatedToSpringUsingMock() {
        when(todoService.retrieveTodos("Dummy")).thenReturn(expected);

        var filteredTodos = subject.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, filteredTodos.size());
    }

    @Test
    void testDeleteTodosNotRelatedToSpring() {
        when(todoService.retrieveTodos("Dummy")).thenReturn(expected);

        subject.deleteTodosNotRelatedToSpring("Dummy");

        // verify that method is called with the following parameter
        // with times we can verify that is called a specific number of times
        verify(todoService, times(1)).deleteTodo("Learn to Dance");
//        then(todoServiceMock).should().deleteTodo("Learn to Dance"); // alternative

        // verify that method is not called with the following parameter
        verify(todoService, never()).deleteTodo("Learn Spring MVC");
//        then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC"); // alternative
    }

    @Test
    void testDeleteTodosNotRelatedToSpringCaptureArgument() {
        when(todoService.retrieveTodos("Dummy")).thenReturn(expected);
        subject.deleteTodosNotRelatedToSpring("Dummy");

        verify(todoService).deleteTodo(stringArgumentCaptor.capture());
        assertEquals(stringArgumentCaptor.getValue(), "Learn to Dance");
    }

    @Test
    void testDeleteTodosNotRelatedToSpringCaptureArgumentWhenCalledMultipleTimes() {
        List<String> expected = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to Dance");

        when(todoService.retrieveTodos("Dummy")).thenReturn(expected);
        subject.deleteTodosNotRelatedToSpring("Dummy");

        verify(todoService, times(2)).deleteTodo(stringArgumentCaptor.capture());
        assertEquals(stringArgumentCaptor.getAllValues().size(), 2);
    }
}
