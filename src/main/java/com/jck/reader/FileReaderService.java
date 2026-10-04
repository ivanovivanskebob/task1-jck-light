package com.jck.reader;

import java.util.List;

public interface FileReaderService {
    List<String> readLines(String filePath);
}
