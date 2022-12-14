package com.example.discover.controller;

import com.example.discover.service.DataService;
import com.example.discover.service.impl.DataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OutputViewTest {

    private final DataService dataService = mock(DataServiceImpl.class);

    private OutputView outputView = new OutputView("4444444444444444", dataService);

    @Test
    void getOutput_SuccessfulPath() {
        //given
        when(dataService.handlePatterns(anyString(), anyList())).thenReturn(any());
        //when
        outputView.getOutput();
        //then
        verify(dataService).checkIfPatternIsKnown(any());
        verify(dataService).handlePatterns(any(), any());
    }
}