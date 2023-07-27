package org.kcsmini2.ojeommo.board.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {

    public static final int BAR_LENGTH = 5;

    public List<Integer> getPaginationBar(int currentPageNumber, int totalPageNumber){
        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH/2), 0);
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPageNumber);

        System.out.println(startNumber);
        System.out.println(endNumber);

        return IntStream.range(startNumber, endNumber)
                .boxed()
                .toList();
    }
}
