import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.easymock.EasyMock.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class EasyMockVSMockito {

    @Test
    public void test_easymock(){
        List mock = createNiceMock(List.class);
        expect(mock.get(0)).andReturn("one");
        expect(mock.get(1)).andReturn("three");
        replay(mock);

        assertEquals("one", mock.get(0));
        assertEquals("three", mock.get(1));

        EasyMock.verify(mock);

    }

    @Test
    public void test_mockito(){
        List mock = mock(List.class);
        when(mock.get(0)).thenReturn("one");
        when(mock.get(1)).thenReturn("two");

        assertEquals("one", mock.get(0));

        verify(mock);

    }
    
}
