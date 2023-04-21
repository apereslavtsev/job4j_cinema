package ru.job4j.cinema.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.model.File;
import ru.job4j.cinema.repository.FileRepository;

class SimpleFileServiceTest {
    
    FileRepository fileRepository;
    
    FileService fileService;
    
    @BeforeEach
    void initServices() {        
        fileRepository = mock(FileRepository.class);
        fileService = new SimpleFileService(fileRepository);
    }

    @Test
    void whenGetByIdThenReturnFileDto() throws IOException {
       Files.write(Path.of("files/testFile.img"), new byte[] {1, 2, 3});
       
       var expectedFile = new File(1, "test", "files/testFile.img");
       when(fileRepository.findById(any(Integer.class)))
            .thenReturn(Optional.of(expectedFile));
       
       var expectedFileDto = new FileDto("test", new byte[] {1, 2, 3}); 
       
       var actualFile = fileService.getFileById(1).get();
       assertThat(actualFile).usingRecursiveComparison()
            .isEqualTo(expectedFileDto);
       
       Files.delete(Path.of("files/testFile.img"));
    }
    
    @Test
    void whenGetByInvalidIdThenReturnEmpty() throws IOException {
       when(fileRepository.findById(any(Integer.class)))
            .thenReturn(Optional.empty());
       
       var actualFile = fileService.getFileById(101);
       assertThat(actualFile)
            .isEqualTo(Optional.empty());
    }

}
